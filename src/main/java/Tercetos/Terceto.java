package Tercetos;

public class Terceto {
    private String operador;
    private String primer_operando;
    private String segundo_operando;

    public Terceto(String operador, String primer_operando, String segundo_operando) {
        this.operador = operador;
        this.primer_operando = primer_operando;
        this.segundo_operando = segundo_operando;
    }

    public Terceto(String operador, String operando) {
        this.operador = operador;
        this.primer_operando = operando;
        this.segundo_operando = "_";
    }

    public Terceto(String operador) {
        this.operador = operador;
        this.primer_operando = "_";
        this.segundo_operando = "_";
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public void setPrimerOperando(String operando) {
        this.primer_operando = operando;
    }

    public void setSegundoOperando(String operando) {
        this.segundo_operando = operando;
    }

    public String getOperador() {
        return this.operador;
    }

    public String getPrimerOperando() {
        return this.primer_operando;
    }

    public String getSegundoOperando() {
        return this.segundo_operando;
    }

    @Override
    public String toString() {
        String tercetoStr = "";
        tercetoStr = "(" + this.operador + ", " + this.primer_operando + ", " + this.segundo_operando + ")";
        return tercetoStr;
    }
}
