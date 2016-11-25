package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

class Vertex extends GeographicPoint {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Vertex> edgeList;

	public Vertex(final GeographicPoint inLocation) {
		super(inLocation.x, inLocation.y);
		edgeList = new ArrayList<Vertex>();
	}
	
	public void addVertex(GeographicPoint location) {
		this(location);
	}
	
	public void addEdge(final Vertex toVertex) {
		if (!edgeList.contains(toVertex)) {
			edgeList.add(toVertex);
		}
	}

	public GeographicPoint getLocation() {
		return location;
	}

	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
}

