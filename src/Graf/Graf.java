package Graf;

import java.util.*;

public class Graf {

    private Map<String, Integer> indexMap; // Map untuk memetakan nama node ke indeks
    private List<LinkedList<Edge>> adjList; // Daftar ketetanggaan untuk setiap node

    // Inner class edge
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

    public Graf(int V) {
        indexMap = new HashMap<>();
        adjList = new ArrayList<>();
        for (int i = 0; i < V; ++i) {
            adjList.add(new LinkedList<>());
        }
    }

    public List<LinkedList<Edge>> getAdjList() {
        return this.adjList;
    }

    public void addEdge(String source, String destination, int weight) {
        if (!indexMap.containsKey(source)) {
            indexMap.put(source, indexMap.size()); // masuk ke map kalo belum ada
        }
        int sourceIndex = indexMap.get(source);
        if (!indexMap.containsKey(destination)) {
            indexMap.put(destination, indexMap.size()); // masuk ke map jg
        }
        int destinationIndex = indexMap.get(destination);
        
        // Tambahkan edge ke adjList hanya jika destination valid
        if (destinationIndex < adjList.size()) {
            Edge edge = new Edge(destination, weight);
            adjList.get(sourceIndex).add(edge);
        } else {
            // Hapus semua elemen dari adjList jika destination tidak valid
            adjList.get(sourceIndex).clear();
        }
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
        LinkedList<Edge> edges = adjList.get(nodeIndex);
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            System.out.print(edge.destination);
            if (i < edges.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    public String getAdjWord(String word,int i){
        int nodeIndex = getNodeIndex(word);
        LinkedList<Edge> edges = adjList.get(nodeIndex);
        return edges.get(i).destination;
    
    }

    public int getNodeIndex(String nodeLabel) {
        for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
            if (entry.getKey().equals(nodeLabel)) {
                return entry.getValue();
            }
        }
        return -1; // idx invalid
    }
    public boolean hasNeighbors(String nodeLabel) {
        int nodeIndex = getNodeIndex(nodeLabel);
        if (nodeIndex != -1) {
            return !adjList.get(nodeIndex).isEmpty();
        }
        return false; // Node tidak ditemukan
    }

}
