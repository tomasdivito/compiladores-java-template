package SymbolTableGenerator;

import java.util.HashMap;

public class SymbolTableManager {
    public static HashMap<String, SymbolEntry> symbolTable = new HashMap<>();

    public static HashMap<String, SymbolEntry> getSymbolTable() {
        return symbolTable;
    }

    public static void insert(SymbolEntry entry) {
        symbolTable.put(entry.getName(), entry);
    }

    public static void setDataType(String key, DataType type) {
        SymbolEntry entry = symbolTable.get(key);
        entry.setDataType(type);
    }

    public static void setValue(String key, String value) {
        SymbolEntry entry = symbolTable.get(key);
        entry.setValue(value);
    }

    public static String getValue(String key) {
        SymbolEntry entry = symbolTable.get(key);
        String value = entry.getValue();
        return value;
    }

    public static void setLength(String key, String length) {
        SymbolEntry entry = symbolTable.get(key);
        entry.setLength(length);
    }

    public static boolean exists(String entryName) {
        return symbolTable.containsKey(entryName);
    }

    public static DataType getType(String key) {
        SymbolEntry entry = symbolTable.get(key);
        return entry.getDataType();
    }
}
