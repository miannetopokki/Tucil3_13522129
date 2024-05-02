package GBFS;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import Graf.Graf;
import Util.Util;
import Simpul.Simpul;

public class GBFS {
    private List<String> finalPath;
    private PriorityQueue<Simpul> pq;
    private long time;

    public GBFS() {
        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight));
        finalPath = new ArrayList<>();
        time = 0;

    }

    // Todo Leksikografis kalo total weight sama
    public GBFS(Boolean isLeksiko) {

        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight).thenComparing(Simpul::getWord));
        finalPath = new ArrayList<>();

    }

    public void searchGBFS(String input, String tujuan, Graf g) {
        long startTime = System.currentTimeMillis();
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, Util.countHeuristic(input, tujuan), initPath, 0));
        Simpul simp = pq.poll();
        while (simp.getTotalWeight() != 0) {
            int nextId = g.getNodeIndex(simp.getWord());
            for (Graf.Edge edge : g.getAdjList().get(nextId)) {
                if (!simp.getPath().contains(edge.getDestination())) {
                    List<String> temp = new ArrayList<>(simp.getPath());
                    temp.add(simp.getWord());
                    pq.offer(new Simpul(edge.getDestination(), Util.countHeuristic(tujuan, edge.getDestination()), temp,
                            0));
                }
            }
            // printPriorityQueue();
            simp = pq.poll();
        }
        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        this.time = elapsedTime;
        this.finalPath = simp.getPath();
        this.finalPath.add(tujuan);

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

    public void printResult() {
        String hasil = String.join("-> ", this.finalPath);
        System.out.println("====SOLUSI GBFS====");
        System.out.println("Waktu : " + this.time + " ms");
        System.out.println("Waktu : " + (float) this.time / 1000 + " detik");

        System.out.println(hasil);

    }

}
