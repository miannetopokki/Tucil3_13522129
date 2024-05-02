// package src.MapBuilder;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.SerializationFeature;

// import java.io.File;
// import java.io.IOException;
// import java.util.List;
// import java.util.Map;

// public class MapSaver {
//     private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

//     // Method to save nested map to a JSON file
//     public static void saveNestedMapToFile(Map<Integer, Map<String, List<String>>> nestedMap, String filePath)
//             throws IOException {
//         objectMapper.writeValue(new File(filePath), nestedMap);
//     }

//     // Method to load nested map from a JSON file
//     public static Map<Integer, Map<String, List<String>>> loadNestedMapFromFile(String filePath) throws IOException {
//         return objectMapper.readValue(new File(filePath), new TypeReference<Map<Integer, Map<String, List<String>>>>() {
//         });
//     }
// }
