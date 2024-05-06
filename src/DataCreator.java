
import MapBuilder.*;
import FileParser.*;
import java.util.*;;

// Program ini digunakan untuk membuat data kamus txt dari map.
public class DataCreator {
    public static void main(String[] args) {
        String fileName = "oracle.txt";
        String saveDircetory = "data/asisten/";
        MapBuilder builder = new MapBuilder();
        FileParser fileParser = new FileParser();
        List<String> fileContent = new ArrayList<>();

        for (int i = 8; i < 9; i++) {
            fileContent = fileParser.readFile(fileName, i, i);
            fileParser.displayInformation();
            Map<Integer, Map<String, List<String>>> wordMap = builder.buildWordMap(fileContent);
            MapSaver.saveMapToFile(wordMap, saveDircetory);
            fileContent.clear();
            wordMap.clear();
        }

    }

}
