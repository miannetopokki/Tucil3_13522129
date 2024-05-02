package GBFS;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import Graf.Graf;
import Simpul.Simpul;

public class GBFS {
    private List<String> finalPath;
    private PriorityQueue<Simpul> pq;

    public GBFS() {
        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight));
        finalPath = new ArrayList<>();

    }

    // Todo Leksikografis kalo total weight sama
    public GBFS(Boolean isLeksiko) {

        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight).thenComparing(Simpul::getWord));
        finalPath = new ArrayList<>();

    }

    public int countHeuristic(String word1, String word2) {
        // prekondisi : panjang word1 == word2
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount;
    }

    public void searchGBFS(String input, String tujuan, Graf g) {
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, countHeuristic(input, tujuan), initPath, 0));
        Simpul simp = pq.poll();
        while (simp.getTotalWeight() != 0) {
            int nextId = g.getNodeIndex(simp.getWord());
            for (Graf.Edge edge : g.getAdjList().get(nextId)) {
                if (!simp.getPath().contains(edge.getDestination())) {
                    List<String> temp = new ArrayList<>(simp.getPath());
                    temp.add(simp.getWord());
                    pq.offer(new Simpul(edge.getDestination(), countHeuristic(tujuan, edge.getDestination()), temp, 0));
                }
            }
            // printPriorityQueue();
            simp = pq.poll();
        }
        this.finalPath = simp.getPath();

    }

    public void printPriorityQueue() {
        System.out.println("Simpul Hidup : ");
        for (Simpul simp : this.pq) {
            System.out.print(simp.getWord() + "(" + simp.getTotalWeight() + ")" + " ");
        }
        System.out.println("");
    }

    public List<String> getFinalPath() {
        return this.finalPath;
    }

}
