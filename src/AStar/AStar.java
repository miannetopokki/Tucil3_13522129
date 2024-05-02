package AStar;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import Graf.Graf;
import Util.Util;
import Simpul.Simpul;

public class AStar {
    private List<String> finalPath;
    private PriorityQueue<Simpul> pq;
    private long time;

    public AStar() {
        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight));
        finalPath = new ArrayList<>();
        time = 0;
    }

    public void searchAStar(String input, String tujuan, Graf g) {
        long startTime = System.currentTimeMillis();
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, Util.countHeuristic(input, tujuan), initPath, 0));
        Simpul simp = pq.poll();
        while (!simp.getWord().equals(tujuan)) {
            int nextId = g.getNodeIndex(simp.getWord());
            for (Graf.Edge edge : g.getAdjList().get(nextId)) {
                if (!simp.getPath().contains(edge.getDestination())) {
                    List<String> temp = new ArrayList<>(simp.getPath());
                    temp.add(simp.getWord());
                    pq.offer(new Simpul(edge.getDestination(),
                            simp.getPath().size() + edge.getWeight()
                                    + Util.countHeuristic(tujuan, edge.getDestination()),
                            temp, 0));
                }

            }
            // printPriorityQueue();
            simp = pq.poll();

        }
        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        finalPath = simp.getPath();
        this.finalPath.add(tujuan);
        this.time = elapsedTime;
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
        System.out.println("====SOLUSI AStar====");
        System.out.println("Waktu : " + this.time + " ms");
        System.out.println("Waktu : " + (float) this.time / 1000 + " detik");
        System.out.println(hasil);

    }
}
