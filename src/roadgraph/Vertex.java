package roadgraph;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;

import geography.GeographicPoint;

class Vertex extends GeographicPoint {
	private static final long serialVersionUID = 1L;
	private String name;
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

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

