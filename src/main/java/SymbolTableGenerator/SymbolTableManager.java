package SymbolTableGenerator;

import java.util.HashMap;

public class SymbolTableManager {
    public static HashMap<String, SymbolEntry> symbolTable = new HashMap<>();

    public HashMap<String, SymbolEntry> getSymbolTable() {
        return this.symbolTable;
    }

    public static void insert(SymbolEntry entry) {
        symbolTable.put(entry.getName(), entry);
    }

    public static void setDataType(String key, DataType type) {
        SymbolEntry entry = symbolTable.get(key);
        entry.setDataType(type);
    }

    public static boolean exists(String entryName) {
        return symbolTable.containsKey(entryName);
    }
}
