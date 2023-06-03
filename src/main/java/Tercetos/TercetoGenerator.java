package Tercetos;

import java.util.ArrayList;

public class TercetoGenerator {

    // Tenemos que tener un indice por terminal

    // Cuando creas el terceto se guarda el operador.
    private ArrayList<Terceto> tercetos;

    public TercetoGenerator() {
        tercetos = new ArrayList<Terceto>();
    }

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
}
