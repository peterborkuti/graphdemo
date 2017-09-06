import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class UndirectedGraphTest {
	@Test
	public void testCreateGraph() {
		UndirectedGraph graph = new UndirectedGraph(1);
		assertEquals(1, graph.numberOfVertices);
	}

	@Test
	public void testAddEdge() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(1, 2);
		Integer[] neighbours1 = graph.getNeighbours(1);
		assertEquals(1, neighbours1.length);
		assertEquals((Integer) 2, neighbours1[1]);
		Integer[] neighbours2 = graph.getNeighbours(2);;
		assertEquals(1, neighbours2.length);
		assertEquals((Integer) 0, neighbours2[1]);
	}

	public void testAddEdges() {
		UndirectedGraph graph = new UndirectedGraph(4);
		graph.addEdges("1,2;1,3;1,4;2,4;3,4");
		Integer[] neighbours1 = graph.getNeighbours(1);
		assertEquals(1, neighbours1.length);
		assertEquals((Integer) 2, neighbours1[1]);
		Integer[] neighbours2 = graph.getNeighbours(2);
		assertEquals(1, neighbours2.size());
		assertEquals((Integer) 0, neighbours2.get(0));
	}
}