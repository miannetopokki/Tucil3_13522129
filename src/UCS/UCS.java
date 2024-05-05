package UCS;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import Graf.Graf;
import Util.Util;
import Simpul.Simpul;
import java.util.*;

public class UCS {
    private List<String> finalPath;
    private PriorityQueue<Simpul> pq;
    private int orderCounter; // Counter for assigning order value
    private long time;
    private int n_explored;
    private boolean isfound;

    public UCS() {
        pq = new PriorityQueue<>(Comparator.comparing(Simpul::getTotalWeight).thenComparing(Simpul::getOrder));
        finalPath = new ArrayList<>();
        time = 0;
        orderCounter = 0;
        n_explored = 0;
        isfound = false;
    }

    public List<String> getFinalPath() {
        return this.finalPath;
    }

    public PriorityQueue<Simpul> getPrioQueue() {
        return this.pq;
    }

    public void searchUCS(String input, String target, Graf g) {
        long startTime = System.currentTimeMillis();
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, 0, initPath, orderCounter++));
        Set<String> visited = new HashSet<>(); // Simpan simpul yang sudah dieksplorasi
    
        while (!pq.isEmpty()) {
            Simpul simp = pq.poll();
            if (simp.getWord().equals(target)) {
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                this.time = elapsedTime;
                this.finalPath = simp.getPath();
                this.finalPath.add(target);
                this.isfound = true;
                return;
            }
    
            if (visited.contains(simp.getWord())) {
                continue; // Skip simpul yang sudah dieksplorasi
            }
    
            visited.add(simp.getWord());
            n_explored++;
            int nextId = g.getNodeIndex(simp.getWord());
            // System.out.printf("==================SIMPUL BARU %s=======================\n", simp.getWord());
            for (Graf.Edge edge : g.getAdjList().get(nextId)) {
                if (!simp.getPath().contains(edge.getDestination())) {
                    List<String> temp = new ArrayList<>(simp.getPath());
                    temp.add(simp.getWord());
                    Simpul add = new Simpul(edge.getDestination(), simp.getTotalWeight() + edge.getWeight(), temp,
                            orderCounter++);
                    
                    // Periksa apakah simpul sudah ada di PriorityQueue
                    boolean exists = pq.stream().anyMatch(s -> s.getWord().equals(add.getWord()));
                    if (!exists) {
                        pq.offer(add);
                        // Util.timer(100);
                        // printPriorityQueue();
                    }
                }
            }
        }
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

    public void printResult() {
        System.out.println("====SOLUSI UCS====");
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
