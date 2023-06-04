package lyc.compiler.files;

import Tercetos.Terceto;
import Tercetos.TercetoManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static Tercetos.TercetoManager.tercetos;

public class IntermediateCodeGenerator implements FileGenerator {
    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        // los tercetos
        ArrayList<String> tercetos = TercetoManager.getTercetoString();
        for(String tercetoStr : tercetos) {
            fileWriter.write(tercetoStr+"\n");
        }
    }
}
