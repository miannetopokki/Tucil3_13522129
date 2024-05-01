import java.util.PriorityQueue;

public class UCSAlgorithm {

    // Custom class representing search nodes
    static class Node {
        int state;
        int cost;

        public Node(int state, int cost) {
            this.state = state;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        // Create a priority queue with custom comparator
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        // Add nodes to the priority queue
        pq.offer(new Node(1, 5));
        pq.offer(new Node(2, 3));
        pq.offer(new Node(3, 7));
        pq.offer(new Node(4,1));
        // Retrieve nodes in order of lowest cost
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            System.out.print(" State: " + node.state + ", Cost: " + node.cost);
        }
    }
}