package lyc.compiler.files;

import Tercetos.Terceto;
import Tercetos.TercetoGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static Tercetos.TercetoGenerator.tercetos;

public class IntermediateCodeGenerator implements FileGenerator {
    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        // los tercetos
        ArrayList<Terceto> tercetos = TercetoGenerator.tercetos;

        // todo: necesitamos que se escriban los tercetos en un archivo
        // puede ser como cada linea sea:
        //      1 [ 'operador'| 'primer_operando' | 'segundo_operando' ]
        //      2 [ 'operador'| __ | __ ]


        fileWriter.write("TODO");
    }
}
