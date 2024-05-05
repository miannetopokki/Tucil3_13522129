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
    private int n_explored;
    private boolean isfound;

    public AStar() {
        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight));
        finalPath = new ArrayList<>();
        time = 0;
        isfound = false;
    }

    public void searchAStar(String input, String tujuan, Graf g) {
        long startTime = System.currentTimeMillis();
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, Util.countHeuristic(input, tujuan), initPath, 0));
        while (!pq.isEmpty()) {
            // printPriorityQueue();
            Simpul simp = pq.poll();
            this.n_explored++;
            if (simp.getWord().equals(tujuan)) {
                this.isfound = true;
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                finalPath = simp.getPath();
                this.finalPath.add(tujuan);
                this.time = elapsedTime;
                return;
            }
            //simpul gk valid
            int nextId = g.getNodeIndex(simp.getWord());
            if (nextId == -1) { 
                continue;
            }
            // Simpul gk punya tetangga
            if (g.getAdjWord(input,0).isEmpty()) { 
                continue; 
            }
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
        }
        // no solusi
        this.isfound = false;
        this.finalPath.clear(); 
        this.time = 0; //
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
        System.out.println("====SOLUSI AStar====");
        if(this.isfound){
            String hasil = String.join("-> ", this.finalPath);
            System.out.println(hasil);
        }else{
            System.out.println("Tidak ada solusi");
        }
        System.out.println("Waktu : " + this.time + " ms");
        System.out.println("Waktu : " + (float) this.time / 1000 + " detik");
        System.out.println("Smpul yang dieksplor: " + this.n_explored);
       
    }
}
