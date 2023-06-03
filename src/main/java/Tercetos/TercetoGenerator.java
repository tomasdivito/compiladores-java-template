package Tercetos;

import java.util.ArrayList;

public class TercetoGenerator {
    public static ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    public Integer crearTerceto(String operador, String primer_operando, String segundo_operando) {
        Terceto terceto = new Terceto(operador, primer_operando, segundo_operando);
        tercetos.add(terceto);
        return tercetos.size()-1;
    }

    public Integer crearTerceto(String operando) {
        Terceto terceto = new Terceto(operando);
        tercetos.add(terceto);
        return tercetos.size()-1;
    }

    public void modificarTerceto(Integer index, Terceto terceto) {
        tercetos.set(index, terceto);
    }
}
