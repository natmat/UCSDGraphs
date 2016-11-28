package roadgraph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import geography.GeographicPoint;

public class TestMapGraph {
	
	private MapGraph mg = null;

	@Before
    public void setUp() {
        this.mg = new MapGraph();
        assertTrue(this.mg.getNumVertices() == 0);
    }
	
	@Test
	public void test_addNullVertex() {
		assertFalse(this.mg.addVertex(null));
	}
	
	@Test
	public void test_addNonNullVertex() {
		MapGraph mg = new MapGraph();
		Vertex v = new Vertex(new GeographicPoint(10, 20));
		assertTrue(mg.addVertex(v));
	}
	
	@Test
	public void test_addVertexToMapGraph1() {
		mg.addVertex(new GeographicPoint(10, 20));
		assertEquals(1, mg.getNumVertices());
	}

	@Test
	public void test_addDuplicateVertexToMapGraph1() {
		mg.addVertex(new GeographicPoint(10, 20));
		mg.addVertex(new GeographicPoint(10, 20));
		assertEquals(1, mg.getNumVertices());
	}

	@Test
	public void test_addVerticiesToMapGraph() {
		final int max = 100;
		for (int i = 0 ; i < max ; i++) {
			mg.addVertex(new GeographicPoint(i, i));
		}
		assertEquals(max, mg.getNumVertices());
	}
}
