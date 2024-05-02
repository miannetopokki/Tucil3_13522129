
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
        List<String> fileContent = fileParser.readFile(fileName, 5, 5);
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

        String input = "debug";
        String tujuan = "plant";
        // graph.printAdjNode("dat");
        // graph.printAdjNode("dot");
        // System.out.println(gbfs.countHeuristic(input, tujuan));
        astar.searchAStar(input, tujuan, graph);
        gbfs.searchGBFS(input, tujuan, graph);

        List<String> gbfsPath = gbfs.getFinalPath();
        gbfsPath.add(tujuan);
        String joined = String.join("-> ", gbfsPath);
        System.out.println(joined);

        List<String> astarpath = astar.getFinalPath();
        astarpath.add(tujuan);
        joined = String.join("-> ", astarpath);
        System.out.println(joined);

    }
}
