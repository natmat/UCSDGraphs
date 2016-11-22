package roadgraph;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMapGraph {

	@Test
	public void testAddNullVertex() {
		MapGraph mg = new MapGraph();
		assertFalse(mg.addVertex(null));
	}
}
