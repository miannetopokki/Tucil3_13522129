package Graf;

import java.util.*;

public class Graf {
    private int V; // Jumlah node/vertex
    private Map<String, Integer> indexMap; // Map untuk memetakan nama node ke indeks
    private List<LinkedList<Edge>> adjList; // Daftar ketetanggaan untuk setiap node

    // Inner class untuk merepresentasikan edge antar node
    public class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }

        public String getDestination() {
            return this.destination;
        }
    }

    // Konstruktor untuk inisialisasi graf dengan jumlah node tertentu
    public Graf(int V) {
        this.V = V;
        indexMap = new HashMap<>();
        adjList = new ArrayList<>();
        for (int i = 0; i < V; ++i) {
            adjList.add(new LinkedList<>());
        }
    }

    public List<LinkedList<Edge>> getAdjList() {
        return this.adjList;
    }

    // Method untuk menambahkan edge baru dengan biaya antar node
    public void addEdge(String source, String destination, int weight) {
        if (!indexMap.containsKey(source)) {
            indexMap.put(source, indexMap.size()); // Tambahkan ke map jika belum ada
        }
        if (!indexMap.containsKey(destination)) {
            indexMap.put(destination, indexMap.size()); // Tambahkan ke map jika belum ada
        }
        int sourceIndex = indexMap.get(source);
        Edge edge = new Edge(destination, weight);
        adjList.get(sourceIndex).add(edge);
    }

    public void convertMapToGraph(Map<Integer, Map<String, List<String>>> mapWord) {
        System.out.println("Converting from map to graph...");
        for (Map.Entry<Integer, Map<String, List<String>>> entry : mapWord.entrySet()) {
            System.out.printf("Sekarang converting map word length %d ke graph %d \n", entry.getKey(), entry.getKey());
            Map<String, List<String>> lengthMap = entry.getValue();
            for (Map.Entry<String, List<String>> wordEntry : lengthMap.entrySet()) {
                String word = wordEntry.getKey();
                List<String> neighbors = wordEntry.getValue();
                for (String kata : neighbors) {
                    addEdge(word, kata, 1);
                }
            }
        }
        System.out.println("Converting  success!");
    }

    // Method untuk mencetak graf
    public void printGraph() {
        for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
            String nodeLabel = entry.getKey();
            int nodeIndex = entry.getValue();
            System.out.print("Node " + nodeLabel + " terhubung ke: ");
            for (Edge edge : adjList.get(nodeIndex)) {
                System.out.print(edge.destination + ",");
            }
            System.out.println();
        }
    }

    public void printAdjNode(String word) {

        int nodeIndex = getNodeIndex(word);
        System.out.println("Node " + word + " Terhubung ke :");
        for (Edge edge : adjList.get(nodeIndex)) {
            System.out.print(edge.destination + ",");
        }
        System.out.println();

    }

    public int getNodeIndex(String nodeLabel) {
        // Iterasi melalui map indeks untuk mencari nodeLabel
        for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
            if (entry.getKey().equals(nodeLabel)) {
                return entry.getValue(); // Mengembalikan indeks node jika ditemukan
            }
        }
        return -1; // Mengembalikan -1 jika node tidak ditemukan
    }

}
