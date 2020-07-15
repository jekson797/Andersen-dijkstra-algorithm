public class Main {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 0);
        graph.addEdge(1, 4, 20);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 30);
        graph.addEdge(2, 4, 35);
        graph.addEdge(3, 5, 20);
        graph.addEdge(4, 5, 10);
        DijkstraSearch<Integer> search = new DijkstraSearch<>(graph);
        System.out.println("Lowest cost = " + search.findLowestCost(0, 4));
        System.out.println(graph);
    }
}
