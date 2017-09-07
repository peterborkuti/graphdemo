import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringGraphTest {
	public final int numberOfVertices;

	private final Set<Integer>[] vertices;

	@SuppressWarnings("unchecked")
	public StringGraphTest(Integer numberOfVertices) {
		this.numberOfVertices = numberOfVertices;

		this.vertices = new HashSet[numberOfVertices];

		initVertices();
	}

	private void initVertices() {
		for (int i = 0; i < this.vertices.length; i++) {
			this.vertices[i] = new HashSet<Integer>();
		}
	}

	private void checkNodes(Integer... nodes) {
		for (Integer node: nodes) {
			if (node < 0 || node >= numberOfVertices) {
				throw new IllegalArgumentException("Illegal node number:" + node);
			}
		}
	}

	public void addEdge(Integer node1, Integer node2) {
		checkNodes(node1, node2);

		vertices[node1].add(node2);
		vertices[node2].add(node1);
	}

	public Integer[] getNeighbours(Integer node) {
		checkNodes(node);

		return vertices[node].toArray(new Integer[0]);
	}

	public String toString() {
		String s = "[" + numberOfVertices + ":";
		for (int n = 0; n < numberOfVertices; n++) {
			s += n + "(" +
				String.join(",", Arrays.toString(getNeighbours(n))) + ")";
		}

		return s + "]";
	}
}