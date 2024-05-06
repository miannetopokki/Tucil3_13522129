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
    private int n_explored;
    private boolean isfound;

    public GBFS() {
        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight));
        finalPath = new ArrayList<>();
        time = 0;
        isfound = false;
    }

    // Todo Leksikografis kalo total weight sama
    public GBFS(Boolean isLeksiko) {

        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight).thenComparing(Simpul::getWord));
        finalPath = new ArrayList<>();
        isfound = false;

    }

    public void searchGBFS(String input, String tujuan, Graf g) {
        long startTime = System.currentTimeMillis();
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, Util.countHeuristic(input, tujuan), initPath, 0));
        while (!pq.isEmpty()) {
            Simpul simp = pq.poll();
            this.n_explored++;
            if (simp.getTotalWeight() == 0) {
                this.isfound = true;
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                finalPath = simp.getPath();
                this.finalPath.add(tujuan);
                this.time = elapsedTime;
                return;
            }
            // Node gk valid di graf
            int nextId = g.getNodeIndex(simp.getWord());
            if (nextId == -1) {
                continue;
            }
            // Simpul gk punya tetangga
            if (g.getAdjWord(simp.getWord(), 0).isEmpty()) {
                break; // gak ada solusi
            }
            for (Graf.Edge edge : g.getAdjList().get(nextId)) {
                if (!simp.getPath().contains(edge.getDestination())) {
                    List<String> temp = new ArrayList<>(simp.getPath());
                    temp.add(simp.getWord());
                    pq.offer(new Simpul(edge.getDestination(),
                            Util.countHeuristic(tujuan, edge.getDestination()),
                            temp, 0));
                }
            }
        }
        // no solusi
        this.isfound = false;
        this.finalPath.clear();
        this.time = 0;
    }

    public void printPriorityQueue() {
        PriorityQueue<Simpul> tempPQ = new PriorityQueue<>(pq);
        System.out.println("Simpul Hidup : ");
        while (!tempPQ.isEmpty()) {
            Simpul simp = tempPQ.poll();
            System.out.print(simp.getWord() + "(" + simp.getTotalWeight() + "," + simp.getOrder() + ")" + " ");
        }
        System.out.println("");
    }

    public List<String> getFinalPath() {
        return this.finalPath;
    }

    public void printResult() {
        System.out.println("====SOLUSI GBFS====");
        if (this.isfound) {
            String hasil = String.join("-> ", this.finalPath);
            System.out.println(hasil);
        } else {
            System.out.println("Tidak ada solusi");
        }
        System.out.println("Banyak step : " + (this.finalPath.size() - 1) + " step");
        System.out.println("Waktu : " + this.time + " ms");
        System.out.println("Waktu : " + (float) this.time / 1000 + " detik");
        System.out.println("Smpul yang dieksplor: " + this.n_explored);

    }
}
