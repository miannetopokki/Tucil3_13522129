package Simpul;

import java.util.List;
import java.util.ArrayList;

public class Simpul implements Comparable<Simpul> {
    private String word;
    private int totalWeight;
    private List<String> path;
    private int order;

    public Simpul(String word, int totalWeight, List<String> path, int order) {
        this.word = word;
        this.totalWeight = totalWeight;
        this.path = new ArrayList<>(path); // Initialize path as ArrayList
        this.order = order;
    }

    public void addPath(String word) {
        this.path.add(word);
    }

    public String getWord() {
        return this.word;
    }

    public int getTotalWeight() {
        return this.totalWeight;
    }

    public void addOrder(int x) {
        this.order += x;
    }

    public List<String> getPath() {
        return this.path;
    }

    public void addTotalWeight(int weight) {
        this.totalWeight += weight;
    }

    public void printSimpul() {
        System.out.println("Nama Simpul : " + this.word);
        String joined = String.join(", ", this.path);
        System.out.print("Path nya : ");
        System.out.println(joined);
    }

    public int getOrder() {
        return this.order;
    }

    @Override
    public int compareTo(Simpul other) {
        // Prioritize based on total weight, and then order counter
        if (this.totalWeight != other.totalWeight) {
            return Integer.compare(this.totalWeight, other.totalWeight);
        } else {
            return Integer.compare(this.order, other.order);
        }
    }
}
