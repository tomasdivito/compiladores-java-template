package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import SymbolTableGenerator.SymbolEntry;

import static SymbolTableGenerator.SymbolTableManager.symbolTable;

public class SymbolTableGenerator implements FileGenerator {
    private final int columnNameWidth = 52;
    private final int columnValueWidth = 52;
    private final int columnTypeWidth = 20;
    private final int columnLengthWidth = 24;

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        List<String> keys = new ArrayList<>(symbolTable.keySet());

        Collections.sort(keys);
        Collections.reverse(keys);

        fileWriter.write("+----------------------------------------------------------------- TABLA DE SIMBOLOS ---------------------------------------------------------------+\n");
        fileWriter.write("|\t\t\t\t\t\tNOMBRE\t\t\t\t\t\t|\t\tTIPO\t\t|\t\t\t\t\t\tVALOR\t\t\t\t\t\t|\t\tLONGITUD\t\t|\n");
        fileWriter.write("+---------------------------------------------------------------------------------------------------------------------------------------------------+\n");

        for (String key : keys) {
            SymbolEntry entry = symbolTable.get(key);

            String dataTypeStr = entry.getDataType() == null
                    ? ""
                    : entry.getDataType().getName();

            try {
                fileWriter.write("|" + key + tabCalculator(key, columnNameWidth) + "|" + dataTypeStr + tabCalculator(dataTypeStr, columnTypeWidth) + "|" + entry.getValue() + tabCalculator(entry.getValue(), columnValueWidth) + "|" + entry.getLength() + tabCalculator(entry.getLength(), columnLengthWidth) + "|\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String tabCalculator(String value, int columnWidth) {
        String tab = "\t";
        int repeatTimes = roundNumber((float)(columnWidth - value.length()) / 4);
        return tab.repeat(repeatTimes);
    }

    private int roundNumber(float num) {
        float diff = num - (int)num;
        if (diff >= 0.5) {
            return (int)num+1;
        } else {
            return (int)num;
        }
    }
}
