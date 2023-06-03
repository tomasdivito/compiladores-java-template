package lyc.compiler.files;

import Tercetos.Terceto;

import java.io.FileWriter;
import java.io.IOException;

import static Tercetos.TercetoGenerator.tercetos;

public class IntermediateCodeGenerator implements FileGenerator {
    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("TODO");
    }
}
