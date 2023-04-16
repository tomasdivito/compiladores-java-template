package lyc.compiler;

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
%}


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
Cycle = "ciclo"
Conditional = "if"
Option = "else"
DoubleQuote = "\""
OpenComment = "*-"
CloseComment = "-*"
AnyCharExceptQuote = [^"\""]
ReservedFloat = "float"
ReserverdInt = "int"
ReserverdString = "string"
ReservedRead = "read"
ReservedAnd = "and"
ReservedOr = "or"
ReservedNot = "not"
ReservedInit = "init"
ReservedWrite = "write"

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
FloatConstant = ({Digit}*{FloatPoint}{Digit}+)|({Digit}+{FloatPoint}{Digit}*)
StringConstant = {DoubleQuote}{AnyCharExceptQuote}*{DoubleQuote}
Comment = {OpenComment} [^"-"]* {CloseComment}


%%


/* keywords */

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }
  {FloatConstant}                          { return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }
  {StringConstant}                         { return symbol(ParserSym.STRING_CONSTANT, yytext()); }

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

  /* Brackets */
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                        { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                       { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }

  /* Reserved Words */
  {Cycle}                                   { return symbol(ParserSym.CYCLE); }
  {Conditional}                             { return symbol(ParserSym.CONDITIONAL); }
  {Option}                                  { return symbol(ParserSym.OPTION); }
  {ReservedFloat}                           { return symbol(ParserSym.RESERVED_FLOAT); }
  {ReserverdInt}                            { return symbol(ParserSym.RESERVED_INT); }
  {ReserverdString}                         { return symbol(ParserSym.RESERVED_STRING); }
  {ReservedRead}                            { return symbol(ParserSym.READ); }
  {ReservedAnd}                             { return symbol(ParserSym.AND); }
  {ReservedOr}                              { return symbol(ParserSym.OR); }
  {ReservedNot}                             { return symbol(ParserSym.NOT); }
  {ReservedInit}                            { return symbol(ParserSym.INIT); }
  {ReservedWrite}                           { return symbol(ParserSym.WRITE); }

  /* Comments */
  {Comment}                                 { /* ignore */ }


  /* whitespace */
  {WhiteSpace}                               { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
