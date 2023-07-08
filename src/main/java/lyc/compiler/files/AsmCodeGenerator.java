package lyc.compiler.files;

import Tercetos.Terceto;
import Tercetos.TercetoManager;
import java_cup.runtime.Symbol;
import SymbolTableGenerator.SymbolTableManager;
import SymbolTableGenerator.SymbolEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import SymbolTableGenerator.DataType;

import javax.xml.crypto.Data;

public class AsmCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        String asmOutput = generateHeader();
        asmOutput += generateData();
        asmOutput += generateCode();
        fileWriter.write(asmOutput);
    }

    public static String generateHeader() {
        String asmHeader = "include macros2.asm\n";
        asmHeader += "include number.asm\n\n";
        asmHeader += ".MODEL LARGE\n";
        asmHeader += ".386\n";
        asmHeader += ".STACK 200h\n\n";
        asmHeader += "MAXTEXTSIZE equ 50\n\n";
        return asmHeader;
    }

    public static String generateData() {
        String asmData = ".DATA\n\n";
        HashSet<SymbolEntry> symbols = new HashSet<SymbolEntry>(SymbolTableManager.getSymbolTable().values());
        for (SymbolEntry symbol : symbols) {
                asmData += "\t";
            if (symbol.getDataType() == DataType.STRING_TYPE || symbol.getDataType() == DataType.STRING_CONST) {
                asmData += symbol.getName() + " db ";
            } else {
                asmData += symbol.getName() + " dd ";
            }
            if (symbol.getName().startsWith("_")) {
                if (symbol.getDataType() == DataType.STRING_TYPE || symbol.getDataType() == DataType.STRING_CONST) {
                    asmData += "\"" + symbol.getValue() + "\",0";
                } else if (symbol.getDataType() == DataType.INTEGER_TYPE || symbol.getDataType() == DataType.INTEGER_CONST) {
                    asmData += symbol.getValue() + ".0";
                } else {
                    asmData += symbol.getValue();
                }
            } else {
                asmData += "?";
            }
            asmData += "\n";
        }
        return asmData;
    }

    public static String generateCode() {
        String asmCode = ".CODE\n\n";
        asmCode += "START:\n";
        ArrayList<Terceto> tercetos = TercetoManager.getTercetos();
        for(Terceto terceto : tercetos) {
            asmCode += "\t";
            switch (terceto.getOperador()) {
                case "=":
                    asmCode += "fstp " + terceto.getPrimerOperando();
                    break;
                case "+":
                    asmCode += "fadd";
                    break;
                case "-":
                    asmCode += "fsub";
                    break;
                case "*":
                    asmCode += "fmul";
                    break;
                case "/":
                    asmCode += "fdiv";
                    break;
                default:
                    asmCode += "fld ";
                    asmCode += terceto.getOperador();
                    break;
            }
            asmCode += "\n";
        }
        return asmCode;
    }
}
