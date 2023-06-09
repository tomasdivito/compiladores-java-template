package Tercetos;

import java.util.ArrayList;

public class TercetoManager {
    public static ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    public static ArrayList<Terceto> getTercetos() {
        return tercetos;
    }

    public static Integer crearTerceto(String operador, String primer_operando, String segundo_operando) {
        Terceto terceto = new Terceto(operador, primer_operando, segundo_operando);
        Integer index = tercetos.size();
        terceto.setIndex(index);
        tercetos.add(terceto);
        return index;
    }

    public static Integer crearTerceto(String operando, String salto) {
        Terceto terceto = new Terceto(operando, salto);
        Integer index = tercetos.size();
        terceto.setIndex(index);
        tercetos.add(terceto);
        return index;
    }

    public static Integer crearTerceto(String operando) {
        Terceto terceto = new Terceto(operando);
        Integer index = tercetos.size();
        terceto.setIndex(index);
        tercetos.add(terceto);
        return index;
    }

    public static void modificarTerceto(Integer index, String primer_operando, String segundo_operando) {
        Terceto terceto = tercetos.get(index);
        terceto.setPrimerOperando(primer_operando);
        terceto.setSegundoOperando(segundo_operando);
        tercetos.set(index, terceto);
    }

    public static void modificarOperador(Integer index, String operador) {
        Terceto terceto = tercetos.get(index);
        terceto.setOperador(operador);
        tercetos.set(index, terceto);
    }

    public static void modificarSalto(Integer index, String primer_operando) {
        Terceto terceto = tercetos.get(index);
        terceto.setPrimerOperando(primer_operando);
        tercetos.set(index, terceto);
    }

    public static Integer getTercetosSize() {
        return tercetos.size();
    }

    public static ArrayList<String> getTercetoString() {
        ArrayList<String> result = new ArrayList<>();
        Integer index = 0;
        for(Terceto terceto : tercetos) {
            result.add("[" + index.toString() + "] " + terceto.toString());
            index++;
        }
        return result;
    }
}
