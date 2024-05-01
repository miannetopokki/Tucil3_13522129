package src.MapBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class MapBuilder {
    // Metode untuk membangun map dari daftar kata-kata
    public Map<Integer, Map<String, List<String>>> buildWordMap(List<String> wordList) {
        System.out.println("Building map for graph...");
        Map<Integer, Map<String, List<String>>> wordMap = new HashMap<>();

        for (String word : wordList) {
            int wordLength = word.length();
            if (!wordMap.containsKey(wordLength)) {
                wordMap.put(wordLength, new HashMap<>());
            }

            Map<String, List<String>> lengthMap = wordMap.get(wordLength);
            lengthMap.put(word, getNeighbors(word, wordList));
        }
        // Memanggil fungsi print setelah membangun map
        System.out.println("Success!");
        return wordMap;
    }

    // Metode untuk mendapatkan tetangga dari sebuah kata
    private List<String> getNeighbors(String word, List<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        Set<String> words = new HashSet<>(wordList); // Mengubah wordList menjadi Set untuk pencarian yang lebih efisien
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    chars[i] = c;
                    String neighbor = new String(chars);
                    // System.out.printf(" word %s, neighbour %s \n", word, neighbor);
                    if (words.contains(neighbor)) {
                        neighbors.add(neighbor);

                    }
                }
            }
            chars[i] = originalChar; // Kembalikan karakter ke aslinya
        }
        return neighbors;
    }

    // Metode untuk mencetak map
    public void printWordMap(Map<Integer, Map<String, List<String>>> wordMap) {
        for (Map.Entry<Integer, Map<String, List<String>>> entry : wordMap.entrySet()) {
            System.out.println("Panjang Kata: " + entry.getKey());
            Map<String, List<String>> lengthMap = entry.getValue();
            for (Map.Entry<String, List<String>> wordEntry : lengthMap.entrySet()) {
                String word = wordEntry.getKey();
                List<String> neighbors = wordEntry.getValue();
                System.out.println(word + " -> " + neighbors);
            }
            System.out.println();
        }
    }

    // public static void main(String[] args) {
    // WordLadderMapBuilder builder = new WordLadderMapBuilder();
    // // Misalkan Anda sudah memiliki List<String> wordList dari file
    // List<String> wordList = /* hasil pembacaan file */;
    // Map<Integer, Map<String, List<String>>> wordMap =
    // builder.buildWordMap(wordList);

    // // Cetak map dari Main
    // builder.printWordMap(wordMap);
    // }
}
