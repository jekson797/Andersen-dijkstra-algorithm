import java.util.ArrayList;
import java.util.List;

public class DijkstraSearch<T> {

    private List<Edge<T>> unprocessedEdges = new ArrayList<>();
    private Graph<T> graph;

    public DijkstraSearch(Graph<T> graph) {
        this.graph = graph;
    }


    public float findLowestCost(T firstValue, T lastValue) {
        unprocessedEdges = collectEdges();
        Edge<T> lowestCostEdge = findLowestCostsEdgeInUnprocessedEdges();
        while (lowestCostEdge != null) {
            List<Edge<T>> neighbors = graph.getGraphMap().get(lowestCostEdge.getDestination());
            if (!neighbors.isEmpty()) {
                processIfHasNeighbors(neighbors, lowestCostEdge);
            }
            unprocessedEdges.remove(lowestCostEdge);
            lowestCostEdge = findLowestCostsEdgeInUnprocessedEdges();
        }
        return calculateLowestCost(firstValue, lastValue);
    }

    private Edge<T> findLowestCostsEdgeInUnprocessedEdges() {
        float lowestCost = 0;
        Edge<T> lowestCostEdge = null;
        for (Edge<T> edge : unprocessedEdges) {
            float cost = edge.getWeight();
            if (cost <= lowestCost) {
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

    private List<Edge<T>> collectEdges() {
        List<Edge<T>> edgesList = new ArrayList<>();
        for (List<Edge<T>> edges : graph.getGraphMap().values()) {
            edgesList.addAll(edges);
        }
        return edgesList;
    }

    private void processIfHasNeighbors(List<Edge<T>> neighbors, Edge<T> lowestCostEdge) {
        float cost = lowestCostEdge.getWeight();
        for (Edge<T> neighbor : neighbors) {
            float newCost = cost + neighbor.getIntermediateWeight();
            if (neighbor.getWeight() > newCost) {
                neighbor.setSource(lowestCostEdge.getSource());
                neighbor.setIntermediateWeight(cost + neighbor.getWeight());
                neighbor.setWeight(cost + neighbor.getWeight());
            }
        }
    }

    private float calculateLowestCost(T firstValue, T lastValue) {
        Edge<T> lowestWeightEdgeForLastValue = findLowestCostEdge(graph.getGraphMap().get(lastValue));
        if (lowestWeightEdgeForLastValue.getSource().equals(firstValue)) {
            return lowestWeightEdgeForLastValue.getWeight();
        } else {
            List<Edge<T>> suitableEdges = getSuitableEdges(firstValue, lastValue);
            Edge<T> lowestWeightEdgeForFirstValue = findLowestCostEdge(suitableEdges);
            return lowestWeightEdgeForFirstValue.getWeight() + lowestWeightEdgeForLastValue.getWeight();
        }
    }

    private Edge<T> findLowestCostEdge(List<Edge<T>> edges) {
        float lowestWeight = -1;
        Edge<T> lowestCostEdge = null;
        for (Edge<T> edge : edges) {
            float edgeWeight = edge.getWeight();
            if (lowestWeight < 0) {
                lowestWeight = edgeWeight;
                lowestCostEdge = edge;
            }
            if (edge.getWeight() < lowestWeight) {
                lowestWeight = edgeWeight;
                lowestCostEdge = edge;
            }
        }
        return lowestCostEdge;
    }

    private List<Edge<T>> getSuitableEdges(T firstValue, T lastValue) {
        List<Edge<T>> suitableEdges = new ArrayList<>();
        for (Edge<T> edge : collectEdges()) {
            if (edge.getSource().equals(firstValue)
                    && edge.getDestination().equals(lastValue)) {
                suitableEdges.add(edge);
            }
        }
        return suitableEdges;
    }
}
