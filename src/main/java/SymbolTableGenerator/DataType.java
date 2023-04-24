package SymbolTableGenerator;

public enum DataType {
    INTEGER_TYPE("Int"),
    FLOAT_TYPE("Float"),
    STRING_TYPE("String"),
    INTEGER_CONST("Int_Cte"),
    FLOAT_CONST("Float_Cte"),
    STRING_CONST("String_Cte");

    private String name;

    DataType(String name) { this.name = name; }

    public String getName() { return this.name; }
}
