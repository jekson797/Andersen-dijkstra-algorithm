import java.util.*;

public class Graph<T> {

    private Map<T, LinkedList<Edge<T>>> graphMap = new HashMap<>();

    public void addVertex(T value) {
        graphMap.put(value, new LinkedList<>());
    }

    public void addEdge(T source, T destination, float weight) {
        Edge<T> edge = new Edge<>(source, destination, weight);
        if (!graphMap.containsKey(source)) {
            addVertex(source);
        }
        if (!graphMap.containsKey(destination)) {
            addVertex(destination);
        }
        graphMap.get(source).add(edge);
    }

    public Map<T, LinkedList<Edge<T>>> getGraphMap() {
        return graphMap;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LinkedList<Edge<T>> edges : graphMap.values()) {
            for (Edge<T> edge : edges) {
                stringBuilder.append(edge.getSource()).append(" => ").append(edge.getDestination())
                        .append(" with weight ").append(edge.getWeight()).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
