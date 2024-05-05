package MapBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;

public class MapSaver {
    public static void saveMapToFile(Map<Integer, Map<String, List<String>>> wordMap, String saveDirectory) {
        for (Map.Entry<Integer, Map<String, List<String>>> entry : wordMap.entrySet()) {
            int length = entry.getKey();
            String filePath = saveDirectory + length + ".txt";
            saveLengthToFile(entry.getValue(), filePath);
        }
        System.out.println("Maps have been saved to files in directory: " + saveDirectory);
    }

    private static void saveLengthToFile(Map<String, List<String>> lengthMap, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, List<String>> innerEntry : lengthMap.entrySet()) {
                String word = innerEntry.getKey();
                List<String> neighbors = innerEntry.getValue();
                writer.write(word + ": " + neighbors.toString() + "\n");
            }
            System.out.println("Map has been saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the map to file: " + e.getMessage());
        }
    }
}
