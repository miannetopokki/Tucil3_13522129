
import java.util.*;

import MapBuilder.*;
import FileParser.FileParser;
import Graf.Graf;
import UCS.UCS;
import GBFS.GBFS;
import AStar.AStar;

public class Main {
    public static void main(String[] args) {
        String fileName = "words_alpha.txt";
        String savePath = "src/save.json";
        MapBuilder builder = new MapBuilder();
        FileParser fileParser = new FileParser();
        List<String> fileContent = fileParser.readFile(fileName, 1, 4);
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
        GBFS gbfs = new GBFS();
        GBFS gbfsL = new GBFS(true);
        AStar astar = new AStar();

        String input = "fist";
        String tujuan = "poop";

        astar.searchAStar(input, tujuan, graph);
        astar.printResult();
        gbfs.searchGBFS(input, tujuan, graph);
        gbfs.printResult();
        ucs.searchUCS(input, tujuan, graph);
        ucs.printResult();

    }
}
