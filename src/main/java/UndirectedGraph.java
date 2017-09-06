import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertices {
	public final List<Integer> vertices = new ArrayList<Integer>();
}
public class UndirectedGraph {
	public final int numberOfVertices;

	private final List<Vertices> neighbours = new ArrayList<Vertices>();

	public UndirectedGraph(Integer numberOfVertices) {
		this.numberOfVertices = numberOfVertices;

		for (int i = 0; i <= numberOfVertices; i++) {
			neighbours.add(new Vertices());
		}
	}

	private boolean isNodeValid(Integer... nodes) {
		boolean isValid = true;

		for (Integer node: nodes) {
			if (node <= 0 || node > numberOfVertices) {
				isValid = false;

				break;
			}
		}

		return isValid;
	}

	public void addEdges(String edgesString) {
		String[] edges = edgesString.split(";");
		for (String edge: edges) {
			String[] nodes = edge.split(",");
			Integer node1 = Integer.valueOf(nodes[0]);
			Integer node2 = Integer.valueOf(nodes[1]);

			addEdge(node1, node2);
		}
	}

	public void addEdge(Integer node1, Integer node2) {
		if (!isNodeValid(node1, node2)) {
			throw new IllegalArgumentException("Illegal node number");
		}

		if (node1 > node2) {
			Integer tmp = node1;
			node1 = node2;
			node2 = tmp;
		}

		assert node1 <= node2;

		Vertices node1Neighbours = neighbours.get(node1);
		if (!node1Neighbours.vertices.contains(node2)) {
			node1Neighbours.vertices.add(node2);
		}
	}

	public List<Integer> getNeighbours(Integer node) {
		if (!isNodeValid(node)) {
			throw new IllegalArgumentException("Illegal node number");
		}

		return Collections.unmodifiableList(neighbours.get(node).vertices);
	}

	public String toString() {
		String s = "[" + numberOfVertices + ":";
		for (int n = 1; n < numberOfVertices; n++) {
			s += n + "(" + 
				String.join(",", getNeighbours(n).toArray(new String[0])) + ")";
		}

		return s + "]";
	}
}