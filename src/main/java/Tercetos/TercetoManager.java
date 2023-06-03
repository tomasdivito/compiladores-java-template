package Tercetos;

import java.util.ArrayList;

public class TercetoManager {
    public static ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    public static Integer crearTerceto(String operador, String primer_operando, String segundo_operando) {
        Terceto terceto = new Terceto(operador, primer_operando, segundo_operando);
        tercetos.add(terceto);
        return tercetos.size()-1;
    }

    public static Integer crearTerceto(String operando) {
        Terceto terceto = new Terceto(operando);
        tercetos.add(terceto);
        return tercetos.size()-1;
    }

    public void modificarTerceto(Integer index, Terceto terceto) {
        tercetos.set(index, terceto);
    }
}
