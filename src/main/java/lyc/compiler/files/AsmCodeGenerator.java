package lyc.compiler.files;

import Tercetos.Terceto;
import Tercetos.TercetoManager;
import java_cup.runtime.Symbol;
import SymbolTableGenerator.SymbolTableManager;
import SymbolTableGenerator.SymbolEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import SymbolTableGenerator.DataType;

import javax.xml.crypto.Data;

public class AsmCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        String asmOutput = generateHeader();
        asmOutput += generateData();
        asmOutput += generateCode();
        asmOutput += "\nmov ax,4C00h\n";
        asmOutput += "int 21h\n";
        asmOutput += "END START\n";
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
        asmData += "\n";
        return asmData;
    }

    public static String generateCode() {
        SymbolEntry symbol;
        HashMap<String, SymbolEntry> symbolTable = SymbolTableManager.getSymbolTable();
        Stack<Integer> jumpStack = new Stack<>();
        Stack<Integer> jumpBIStack = new Stack<>();
        Integer jumpIndex = 0;
        String label = "";
        String jumpLabel = "";
        String asmCode = ".CODE\n\n";
        asmCode += "START:\n";
        ArrayList<Terceto> tercetos = TercetoManager.getTercetos();
        for(Terceto terceto : tercetos) {
            switch (terceto.getOperador()) {
                case "=":
                    System.out.println("INSIDE =");
                    symbol = symbolTable.get(terceto.getPrimerOperando());
                    if(symbol.getDataType() == DataType.STRING_TYPE || symbol.getDataType() == DataType.STRING_CONST) {
                        asmCode += "\t STRCPY " + terceto.getPrimerOperando() + "," + symbol.getValue();
                    }
                    else {
                        asmCode += "\tfstp " + terceto.getPrimerOperando();
                    }
                    break;
                case "+":
                    asmCode += "\tfadd";
                    break;
                case "-":
                    asmCode += "\tfsub";
                    break;
                case "*":
                    asmCode += "\tfmul";
                    break;
                case "/":
                    asmCode += "\tfdiv";
                    break;
                case "CMP":
                    asmCode += "\tfxch\n";
                    asmCode += "\tfcom\n";
                    asmCode += "\tfstsw ax\n";
                    asmCode += "\tsahf";
                    break;
                case "BE":
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJE " + jumpLabel;
                    break;
                case "BNE":
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJNE " + jumpLabel;
                    break;
                case "BGT":
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJA " + jumpLabel;
                    break;
                case "BGE":
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJAE " + jumpLabel;
                    break;
                case "BLT":
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJB " + jumpLabel;
                    break;
                case "BLE":
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJBE " + jumpLabel;
                    break;
                case "BI":
                    if(!jumpStack.isEmpty()) {
                        jumpIndex = jumpStack.pop();
                        if(terceto.getIndex() == jumpIndex) {
                            asmCode += "LABEL" + jumpIndex.toString() + ":\n";
                        }
                        else {
                            jumpStack.push(jumpIndex);
                        }
                    }
                    if(!jumpBIStack.isEmpty()) {
                        jumpIndex = jumpBIStack.pop();
                        if(terceto.getIndex() == jumpIndex) {
                            asmCode += "LABEL" + jumpIndex.toString() + ":\n";
                        }
                        else {
                            jumpBIStack.push(jumpIndex);
                        }
                    }
                    jumpLabel = terceto.getPrimerOperando();
                    jumpLabel = jumpLabel.substring(1, jumpLabel.lastIndexOf("]"));
                    jumpIndex = Integer.valueOf(jumpLabel);
                    if(jumpStack.search(jumpIndex) == -1 && jumpBIStack.search(jumpIndex) == -1 && jumpIndex >= tercetos.size()) {
                        jumpBIStack.push(jumpIndex);
                    }
                    jumpLabel = "LABEL" + jumpLabel;
                    asmCode += "\tJMP " + jumpLabel;
                    break;
                case "WRITE":
                    asmCode += "LABEL" + terceto.getIndex() + ":\n";
                    symbol = symbolTable.get(terceto.getPrimerOperando());

                    if(symbol == null) {
                        System.exit(0);
                    }

                    if(symbol.getDataType() == DataType.STRING_CONST || symbol.getDataType() == DataType.STRING_TYPE) {
                        asmCode += "displayString " + terceto.getPrimerOperando() + "\n";
                    }
                    else {
                        asmCode += "DisplayFloat " + terceto.getPrimerOperando() + "," + 3 + "\n";
                    }

                    asmCode += "newLine";
                    break;
                case "READ":
                    asmCode += "LABEL" + terceto.getIndex() + ":\n";
                    asmCode += "getString " + terceto.getPrimerOperando() + "\n";
                    break;
                default:
                    if(!jumpStack.isEmpty()) {
                        jumpIndex = jumpStack.pop();
                        if(terceto.getIndex() == jumpIndex) {
                            asmCode += "LABEL" + jumpIndex.toString() + ":\n";
                        }
                        else {
                            jumpStack.push(jumpIndex);
                        }
                    }
                    if(!jumpBIStack.isEmpty()) {
                        jumpIndex = jumpBIStack.pop();
                        if(terceto.getIndex() == jumpIndex) {
                            asmCode += "LABEL" + jumpIndex.toString() + ":\n";
                        }
                        else {
                            jumpBIStack.push(jumpIndex);
                        }
                    }
                    System.out.println("INSIDE DEFAULT");
                    System.out.println(terceto.getIndex());
                    symbol = symbolTable.get(terceto.getOperador());
                    if(symbol.getDataType() != DataType.STRING_TYPE && symbol.getDataType() != DataType.STRING_CONST) {
                        label = terceto.getIndex().toString();
                        asmCode += "LABEL" + label + ":\n";
                        asmCode += "\tfld ";
                        asmCode += terceto.getOperador();
                    }
                    break;
            }
            asmCode += "\n";
        }
        asmCode += "LABEL" + tercetos.size() + ":\n";
        return asmCode;
    }
}