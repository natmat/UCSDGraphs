package roadgraph;

import geography.GeographicPoint;

class Edge {
	private GeographicPoint start;
	private GeographicPoint end;
	final private Boolean directed = true;
	private String roadName;
	/**
	 * @return the roadName
	 */
	public String getRoadName() {
		return roadName;
	}

	private String roadType;
	private double length;
	
	public Edge(GeographicPoint end1, GeographicPoint end2, 
			String roadName, String roadType, double length) {
		this.start = end1;
		this.end = end2;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((directed == null) ? 0 : directed.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((roadName == null) ? 0 : roadName.hashCode());
		result = prime * result + ((roadType == null) ? 0 : roadType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (directed == null) {
			if (other.directed != null)
				return false;
		} else if (!directed.equals(other.directed))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
			return false;
		if (roadName == null) {
			if (other.roadName != null)
				return false;
		} else if (!roadName.equals(other.roadName))
			return false;
		if (roadType == null) {
			if (other.roadType != null)
				return false;
		} else if (!roadType.equals(other.roadType))
			return false;
		return true;
	}

	/**
	 * 
	 * @return end GP for this edge
	 */
	public GeographicPoint getEndPoint() {
		return(end);
	}
}


