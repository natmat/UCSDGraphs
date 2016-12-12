package roadgraph;

import geography.GeographicPoint;

class Vertex extends GeographicPoint {
	private static final long serialVersionUID = 1L;

	public Vertex(final GeographicPoint inLocation) {
		super(inLocation.x, inLocation.y);
	}
	
	public GeographicPoint getLocation() {
		return(new GeographicPoint(getX(), getY()));
	}

	@Override
	public int hashCode() {
		return(getLocation().hashCode());
	}
	
	@Override
	public boolean equals(Object o) {
		// equality on location
		return((getLocation().getX() == ((Vertex)o).getX()) 
				&& (getLocation().getY() == ((Vertex)o).getY()));
	}
}

