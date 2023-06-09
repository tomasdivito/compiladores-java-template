package lyc.compiler;

import SymbolTableGenerator.DataType;
import SymbolTableGenerator.SymbolEntry;
import SymbolTableGenerator.SymbolTableManager;
import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}


%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
  StringBuffer stringBuffer;
%}

%x OUTER_COMMENT INNER_COMMENT


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = "="
OpenBracket = "("
CloseBracket = ")"
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"
Greater = ">"
Lesser = "<"
GreaterEq = ">="
LesserEq = "<="
Equal = "=="
Letter = [a-zA-Z]
Digit = [0-9]
FloatPoint = "."
Coma = ","
Colon = ":"
Or = "||"
And = "&"
Cycle = "ciclo"
If = "if"
Else = "else"
DoubleQuote = "\""
OpenComment = "*-"
CloseComment = "-*"
AnyCharExceptQuote = [^"\""]
ReservedFloat = "Float"
ReserverdInt = "Int"
ReserverdString = "String"
ReservedRead = "read"
ReservedNot = "not"
ReservedInit = "init"
ReservedWrite = "write"
ReservedConcat = "ConcatenarConRecorte"

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
FloatConstant = ({Digit}*{FloatPoint}{Digit}+)|({Digit}+{FloatPoint}{Digit}*)
StringConstant = {DoubleQuote}{AnyCharExceptQuote}*{DoubleQuote}
Comment = {OpenComment} [^"*-"] ~{CloseComment}


%%


/* keywords */

<YYINITIAL> {
/* Reserved Words */
  {Cycle}                                   { return symbol(ParserSym.CYCLE); }
  {If}                                      { return symbol(ParserSym.IF); }
  {Else}                                    { return symbol(ParserSym.ELSE); }
  {ReservedFloat}                           { return symbol(ParserSym.RESERVED_FLOAT); }
  {ReserverdInt}                            { return symbol(ParserSym.RESERVED_INT); }
  {ReserverdString}                         { return symbol(ParserSym.RESERVED_STRING); }
  {ReservedRead}                            { return symbol(ParserSym.READ); }
  {ReservedNot}                             { return symbol(ParserSym.NOT); }
  {ReservedInit}                            { return symbol(ParserSym.INIT); }
  {ReservedWrite}                           { return symbol(ParserSym.WRITE); }
  {ReservedConcat}                          { return symbol(ParserSym.RESERVED_CONCAT); }
  /* identifiers */
  {Identifier}                             {
                                                  if(yytext().length() > MAX_LENGHT) {
                                                    throw new InvalidLengthException("Identifier out of bounds");
                                                  }

                                                  if (!SymbolTableManager.exists(yytext())) {
                                                      SymbolEntry entry = new SymbolEntry(yytext());
                                                      SymbolTableManager.insert(entry);
                                                  }

                                                  return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        {
                                                  if (Long.valueOf(yytext()) > MAX_INTEGER_LENGTH || (Long.valueOf(yytext()) < MIN_INTEGER_LENGTH)) {
                                                      throw new InvalidIntegerException("Integer out of bounds.");
                                                  }

                                                  if (!SymbolTableManager.exists(yytext())) {
                                                      SymbolEntry entry = new SymbolEntry(
                                                              "_"+yytext(),
                                                              DataType.INTEGER_CONST,
                                                              yytext()
                                                      );
                                                      SymbolTableManager.insert(entry);
                                                  }

                                                  return symbol(ParserSym.INTEGER_CONSTANT, yytext());
                                             }

  {FloatConstant}                          {
                                                if (Float.valueOf(yytext()) > MAX_FLOAT || (Float.valueOf(yytext()) < MIN_FLOAT ) ) {
                                                  throw new InvalidIntegerException("Float out of bounds.");
                                                }

                                                if (!SymbolTableManager.exists(yytext())) {
                                                    SymbolEntry entry = new SymbolEntry(
                                                          "_"+yytext(),
                                                          DataType.FLOAT_CONST,
                                                          yytext()
                                                    );
                                                    SymbolTableManager.insert(entry);
                                                }

                                                return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }
  {StringConstant}                         {
                                                     stringBuffer = new StringBuffer(yytext());

                                                     if (yytext().length() > MAX_STRING_LENGTH + 2)
                                                       { throw new InvalidLengthException("String lenght out of bounds"); }

                                                     stringBuffer.replace(0, 1, "");
                                                     stringBuffer.replace(stringBuffer.length()-1,stringBuffer.length(), "");

                                                     if(!SymbolTableManager.exists(yytext()))
                                                     {
                                                       SymbolEntry entry = new SymbolEntry("_" + stringBuffer.toString().replace(" ","_"), DataType.STRING_CONST, stringBuffer.toString(), Integer.toString(stringBuffer.length()));
                                                       SymbolTableManager.insert(entry);
                                                     }

                                                     return symbol(ParserSym.STRING_CONSTANT, yytext());

                                                 }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {Greater}                                 { return symbol(ParserSym.GREATER); }
  {Lesser}                                  { return symbol(ParserSym.LESSER); }
  {GreaterEq}                               { return symbol(ParserSym.GREATER_EQ); }
  {LesserEq}                                { return symbol(ParserSym.LESSER_EQ); }
  {Equal}                                   { return symbol(ParserSym.EQUAL); }
  {Coma}                                    { return symbol(ParserSym.COMA); }
  {Colon}                                   { return symbol(ParserSym.COLON); }
  {Or}                                      { return symbol(ParserSym.OR); }
  {And}                                     { return symbol(ParserSym.AND); }

  /* Brackets */
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                        { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                       { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }

  /* Comments */
  {OpenComment}                             { yybegin(OUTER_COMMENT); }
  {CloseComment}                            { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                              { /* ignore */ }

  {LineTerminator}                          { /* ignore */ }
  {Identation}                              { /* ignore */ }
}

<OUTER_COMMENT> {
    {OpenComment}                           { yybegin(INNER_COMMENT); }
    {CloseComment}                          { yybegin(YYINITIAL); }
    [^"*"]                                  { /* ignore */ }
}

<INNER_COMMENT> {
   {OpenComment}                            { throw new MultiLevelCommentException(" No more than one level of inner comments are allowed "); }
   {CloseComment}                           { yybegin(OUTER_COMMENT); }
   [^"*"]                                   { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
