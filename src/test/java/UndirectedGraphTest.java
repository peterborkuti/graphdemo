import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

public class UndirectedGraphTest {
	private Integer[] invokeGetNeighbours(UndirectedGraph graph, Integer vertex) {
		Method method = null;
		try {
			method = graph.getClass().getDeclaredMethod("getNeighbours", Integer.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		method.setAccessible(true);

		Object neighbours = null;
		try {
			vertex--;
			neighbours = method.invoke(graph, vertex);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (Integer[])neighbours;
	}

	@Test
	public void testCreateGraph() {
		UndirectedGraph graph = new UndirectedGraph(1);
		assertEquals(1, graph.numberOfVertices);
	}

	@Test
	public void testAddEdge() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(0, 1);
		Integer[] neighbours1 =  graph.getNeighbours(0);
		assertEquals(1, neighbours1.length);
		assertEquals((Integer)1, neighbours1[0]);
		Integer[] neighbours2 = graph.getNeighbours( 1);
		assertEquals(1, neighbours2.length);
		assertEquals((Integer) 0, neighbours2[0]);
	}
}