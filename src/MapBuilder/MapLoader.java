package MapBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLoader {
    public static Map<String, List<String>> loadMapFromFile(String filePath) {
        Map<String, List<String>> resultMap = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Memisahkan kunci dari nilai dengan menggunakan ":"
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String valuesString = parts[1].trim();
                    // Menghapus tanda kurung siku dan spasi dari nilai
                    valuesString = valuesString.replaceAll("[\\[\\]\\s]", "");
                    String[] valuesArray = valuesString.split(",");
                    List<String> valuesList = new ArrayList<>();
                    for (String value : valuesArray) {
                        valuesList.add(value.trim());
                    }
                    resultMap.put(key, valuesList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return resultMap;
    }
}
