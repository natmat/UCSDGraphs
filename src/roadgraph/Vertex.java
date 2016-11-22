package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

class Vertex {
	private GeographicPoint location;
	private List<Vertex> edgeList;

	public Vertex(final GeographicPoint inLocation) {
		setLocation(inLocation);
		edgeList = new ArrayList<Vertex>();
	}
	
	public void addVertex(GeographicPoint location) {
		this.location = location;
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

