import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DijkstraSearch<T> {

    private List<Edge<T>> processedEdges = new ArrayList<>();
    private List<Edge<T>> unprocessedEdges = new ArrayList<>();
    private Graph<T> graph;

    public DijkstraSearch(Graph<T> graph) {
        this.graph = graph;
    }

    public void findLowestCost(T finalValue) {
        collectEdges();
        Edge<T> lowestCostEdge = findLowestCostsEdge();
        float cost = lowestCostEdge.getWeight();
        while (lowestCostEdge != null) {
            unprocessedEdges.remove(lowestCostEdge);
            List<Edge<T>> neighbors = graph.getGraphMap().get(lowestCostEdge.getDestination());
            for (Edge<T> edge : neighbors) {
//                float newCost = cost + edge.getWeight();
                if (edge.getWeight() > newCost) {
                    edge.setSource(lowestCostEdge.getSource());
                    edge.setWeight((int) cost);
                }
            }
            processedEdges.add(lowestCostEdge);
            lowestCostEdge = findLowestCostsEdge();
        }
        for (Edge<T> edge : graph.getGraphMap().get(finalValue)) {
            System.out.println(edge.getWeight());
        }
    }

    private Edge<T> findLowestCostsEdge() {
        float lowestCost = 0;
        Edge<T> lowestCostEdge = null;
        for (Edge<T> edge : unprocessedEdges) {
            float cost = edge.getWeight();
            if (cost <= lowestCost && !processedEdges.contains(edge)) {
                lowestCost = cost;
                lowestCostEdge = edge;
            }
            if (lowestCostEdge == null) {
                lowestCost = cost;
                lowestCostEdge = edge;
            }
        }
        return lowestCostEdge;
    }

    private void collectEdges() {
        for (List<Edge<T>> edges : graph.getGraphMap().values()) {
            unprocessedEdges.addAll(edges);
        }
    }
}
