
import java.util.*;
import java.io.File;

import MapBuilder.*;

import FileParser.FileParser;
import Graf.Graf;
import UCS.UCS;
import GBFS.GBFS;
import AStar.AStar;

public class Main {
    public static void main(String[] args) {
        String input, tujuan, data_dir;
        boolean isValid = false;
        String saveDircetory = "../src/data/";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kata awal: ");
        input = scanner.nextLine();
        System.out.println("Kata tujuan: ");
        tujuan = scanner.nextLine();
        System.out.println("-default\n-asisten : ");
        System.out.println("Mau pakai kamus folder apa?  ");
        data_dir = scanner.nextLine();
        File directory = new File(saveDircetory + data_dir);
        if (directory.exists() && directory.isDirectory()) {
            isValid = true;
        } else {
            System.out.println("Direktori tidak valid.");
        }
        while (!isValid) {
            System.out.println("Mau pakai kamus folder apa? : ");
            data_dir = scanner.nextLine();
            directory = new File(saveDircetory + data_dir);
            if (directory.exists() && directory.isDirectory()) {
                isValid = true;
            } else {
                System.out.println("Direktori tidak valid.");
            }

        }
        Map<String, List<String>> wordList = MapLoader
                .loadMapFromFile(saveDircetory + data_dir + "/" + input.length() + ".txt");
        if (input.length() != tujuan.length()) {
            System.out.println("Panjang kata input dan tujuan tidak sama!");
        } else if (!wordList.containsKey(input) || !wordList.containsKey(tujuan)) {
            System.out.println("Error! kata input atau tujuan tidak ada di kamus data!");

        } else {
            Map<Integer, Map<String, List<String>>> wordMap = new HashMap<>();
            wordMap.put(input.length(), wordList);
            Set<String> uniqueWords = new HashSet<>();

            /* Distinct Word, mencegah redundansi */
            for (Map.Entry<Integer, Map<String, List<String>>> entry : wordMap.entrySet()) {
                Map<String, List<String>> lengthMap = entry.getValue();
                uniqueWords.addAll(lengthMap.keySet());
            }

            Graf graph = new Graf(uniqueWords.size() + 1);
            graph.convertMapToGraph(wordMap);

            UCS ucs = new UCS();
            GBFS gbfs = new GBFS();
            AStar astar = new AStar();

            System.out.println("Mau Pakai algoritma apa?");
            System.out.println("1. UCS\n2. Greedy-Best First Search\n3. AStar\n4. Semuanya");
            String day = scanner.nextLine();
            String dayString;
            switch (Integer.parseInt(day)) {
                case 1:
                    ucs.searchUCS(input, tujuan, graph);
                    ucs.printResult();

                    break;
                case 2:
                    gbfs.searchGBFS(input, tujuan, graph);
                    gbfs.printResult();
                    break;
                case 3:
                    astar.searchAStar(input, tujuan, graph);
                    astar.printResult();
                    break;
                case 4:
                    ucs.searchUCS(input, tujuan, graph);
                    ucs.printResult();
                    gbfs.searchGBFS(input, tujuan, graph);
                    gbfs.printResult();
                    astar.searchAStar(input, tujuan, graph);
                    astar.printResult();
                    break;

                default:
                    dayString = "Invalid input";
                    break;
            }

        }

    }
}
