package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

class Vertex extends GeographicPoint {
	private static final long serialVersionUID = 1L;
	private Integer index;
	private String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	private List<Vertex> edgeList;

	public Vertex(final GeographicPoint inLocation) {
		super(inLocation.x, inLocation.y);
		edgeList = new ArrayList<Vertex>();
	}
	
	public void addEdge(final Vertex toVertex) {
		if (!edgeList.contains(toVertex)) {
			edgeList.add(toVertex);
		}
	}
}

