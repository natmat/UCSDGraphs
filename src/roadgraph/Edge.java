package roadgraph;

import geography.GeographicPoint;

class Edge {
	private GeographicPoint end1;
	private GeographicPoint end2;
	private Boolean directed;
	private String roadName;
	private String roadType;
	private double length;
	
	public void addEdge(GeographicPoint end1, GeographicPoint end2, 
			String roadName, String roadType, double length) {
		this.end1 = end1;
		this.end2 = end2;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
}
