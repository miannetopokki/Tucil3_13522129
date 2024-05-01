package src;

import java.util.*;
import java.io.*;

import src.MapBuilder.*;
import src.FileParser.FileParser;
import src.Graf.Graf;
import src.UCS.UCS;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/words_alpha.txt";
        String savePath = "src/save.json";
        MapBuilder builder = new MapBuilder();
        FileParser fileParser = new FileParser();
        List<String> fileContent = fileParser.readFile(fileName, 1, 5);
        fileParser.displayInformation();
        
    
        Map<Integer, Map<String, List<String>>> wordMap = builder.buildWordMap(fileContent);
        Set<String> uniqueWords = new HashSet<>();



        /* Distinct Word, mencegah redundansi */
        for (Map.Entry<Integer, Map<String, List<String>>> entry : wordMap.entrySet()) {
            Map<String, List<String>> lengthMap = entry.getValue();
            uniqueWords.addAll(lengthMap.keySet());
        }
        Graf graph = new Graf(uniqueWords.size());
        graph.convertMapToGraph(wordMap);
        UCS ucs = new UCS();
        String input = "debug";
        String tujuan = "plant";
        ucs.searchUCS(input,tujuan,graph);
        List<String> finalPath = ucs.getFinalPath();
        finalPath.add(tujuan);
        String joined = String.join("-> ", finalPath);
        System.out.println(joined);

    }
}
