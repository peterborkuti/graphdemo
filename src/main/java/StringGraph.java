import java.util.Arrays;

import static java.util.Arrays.setAll;

public class StringGraph {
    private final UndirectedGraph graph;

    public StringGraph(int numberOfVertices, String edgesAsString) {
        graph = new UndirectedGraph(numberOfVertices);

        initGraph(edgesAsString);
    }

    private void initGraph(String edgesAsString) {
        String[] edges = edgesAsString.split(";");

        for (String edge: edges) {
            addEdge(edge);
        }
    }

    private void addEdge(String edge) {
        String[] vertices = edge.split(",");

        Integer vertex1 = Integer.valueOf(vertices[0]);
        Integer vertex2 = Integer.valueOf(vertices[1]);

        vertex1--;
        vertex2--;

        graph.addEdge(vertex1, vertex2);
    }

    public UndirectedGraph getGraph() {
        return graph;
    }
}
