import java.util.*;

public class Distance {
	private StringGraph stringGraph;

	private final int[] distances;

	private final Deque<Integer> queue = new LinkedList<Integer>();

	public Distance(Integer numberOfVertices, String edges) {
		this.distances = new int[numberOfVertices];

		stringGraph = new StringGraph(numberOfVertices, edges);
	}

	private void initDistances() {
		for (int i = 0; i < distances.length; i++) {
			distances[i] = -1;
		}
	}

	public String getDistancesFromNode(Integer root) {
		queue.clear();

		initDistances();

		walk(root - 1);

		return String.join(" ", Arrays.toString(distances));
	}

	private void walk(Integer node) {
		for (Integer neighbour: stringGraph.getGraph().getNeighbours(node)) {
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
		String s = "[" + stringGraph.getGraph().toString();
		s += ": distances: " + String.join(",", Arrays.toString(distances));

		return s + "]";
	}
}