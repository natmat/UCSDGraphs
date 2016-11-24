package roadgraph;

import geography.GeographicPoint;

class Edge {
	private GeographicPoint end1;
	private GeographicPoint end2;
	final private Boolean directed = true;
	private String roadName;
	private String roadType;
	private double length;
	
	public Edge(GeographicPoint end1, GeographicPoint end2, 
			String roadName, String roadType, double length) {
		this.end1 = end1;
		this.end2 = end2;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		if ((null != o) && (o instanceof Edge)) {
			isEqual = 
					(this.end1 == ((Edge)o).end1)
					&& (this.end2 == ((Edge)o).end2)
					&& (this.directed == ((Edge)o).directed)
					&& (this.roadName == ((Edge)o).roadName)
					&& (this.roadType == ((Edge)o).roadType)
					&& (this.length == ((Edge)o).length);
		}
		return isEqual;		
	}
}


