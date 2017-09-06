import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class UndirectedGraph {
	public final int numberOfVertices;

	private final int[] distances;

	private final Set<Integer>[] vertices;

	private final Deque<Integer> queue = new LinkedList<Integer>();

	@SuppressWarnings("unchecked")
	public UndirectedGraph(Integer numberOfVertices) {
		this.numberOfVertices = numberOfVertices;

		this.distances = new int[numberOfVertices + 1];

		this.vertices = new HashSet[numberOfVertices + 1];

		initVertices();
	}

	private void initVertices() {
		for (int i = 0; i < this.vertices.length; i++) {
			this.vertices[i] = new HashSet<Integer>();
		}
	}

	private void initDistances() {
		for (int i = 0; i < distances.length; i++) {
			distances[i] = -1;
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
			addEdge(edge);
		}
	}

	public void addEdge(String edge) {
		String[] nodes = edge.split(",");

		Integer node1 = Integer.valueOf(nodes[0]);
		Integer node2 = Integer.valueOf(nodes[1]);

		addEdge(node1, node2);
	}

	public void addEdge(Integer node1, Integer node2) {
		if (!isNodeValid(node1, node2)) {
			throw new IllegalArgumentException("Illegal node number");
		}

		vertices[node1].add(node2);
		vertices[node2].add(node1);
	}

	public Integer[] getNeighbours(Integer node) {
		if (!isNodeValid(node)) {
			throw new IllegalArgumentException("Illegal node number");
		}

		return vertices[node].toArray(new Integer[0]);
	}

	public String getDistancesFromNode(Integer root) {
		queue.clear();

		initDistances();

		walk(root);

		return String.join(" ", Arrays.toString(distances));
	}

	private void walk(Integer node) {
		for (Integer neighbour: getNeighbours(node)) {
			int newDistance = distances[node] + 1;
			if (distances[neighbour] < 0) {
				distances[neighbour] = newDistance;
				queue.offer(neighbour);
			}
			else if (distances[neighbour] > newDistance) {
				//TODO: should not recount all the distances?
				//at least on path which has this neighbour as a node
				distances[neighbour] = newDistance;
			}
		}

		walk(queue.poll());
	}

	public String toString() {
		String s = "[" + numberOfVertices + ":";
		for (int n = 1; n <= numberOfVertices; n++) {
			s += n + "(" + 
				String.join(",", Arrays.toString(getNeighbours(n))) + ")";
		}

		return s + "]";
	}
}