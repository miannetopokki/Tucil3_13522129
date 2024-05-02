package UCS;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import Graf.Graf;
import Simpul.Simpul;

public class UCS {
    private List<String> finalPath;
    private PriorityQueue<Simpul> pq;
    private int orderCounter; // Counter for assigning order value

    public UCS() {
        pq = new PriorityQueue<>(new Comparator<Simpul>() {
            @Override
            public int compare(Simpul s1, Simpul s2) {
                int weightComparison = Integer.compare(s1.getTotalWeight(), s2.getTotalWeight());
                if (weightComparison != 0) {
                    return weightComparison; // Jika total bobotnya berbeda, kembalikan perbandingan bobot
                }
                // Jika total bobotnya sama, bandingkan berdasarkan urutan masuk (order)
                return Integer.compare(s1.getOrder(), s2.getOrder());
            }
        });
        orderCounter = 0;
    }

    public List<String> getFinalPath() {
        return this.finalPath;
    }

    public void searchUCS(String input, String target, Graf g) {
        List<String> initPath = new ArrayList<>();
        pq.offer(new Simpul(input, 0, initPath, orderCounter++)); // Assign order value
        Simpul simp = pq.poll();

        while (!simp.getWord().equals(target)) {
            // System.out.printf("Simpuel E : %s \n", simp.getWord());
            int nextId = g.getNodeIndex(simp.getWord());

            for (Graf.Edge edge : g.getAdjList().get(nextId)) {
                if (!simp.getPath().contains(edge.getDestination())) {
                    List<String> temp = new ArrayList<>(simp.getPath());
                    temp.add(simp.getWord());
                    pq.offer(new Simpul(edge.getDestination(), simp.getTotalWeight() + edge.getWeight(), temp,
                            orderCounter++)); // Assign order value mencegah starvation
                }
            }
            // printPriorityQueue();
            simp = pq.poll();

            // try {
            // Thread.sleep(100);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }

        finalPath = simp.getPath();
    }

    public void printPriorityQueue() {
        System.out.println("Simpul Hidup : ");
        for (Simpul simp : this.pq) {
            System.out.print(simp.getWord() + "(" + simp.getTotalWeight() + ")" + " ");
        }
        System.out.println("");
    }
}
