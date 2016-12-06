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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((directed == null) ? 0 : directed.hashCode());
		result = prime * result + ((end1 == null) ? 0 : end1.hashCode());
		result = prime * result + ((end2 == null) ? 0 : end2.hashCode());
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
		if (end1 == null) {
			if (other.end1 != null)
				return false;
		} else if (!end1.equals(other.end1))
			return false;
		if (end2 == null) {
			if (other.end2 != null)
				return false;
		} else if (!end2.equals(other.end2))
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
}


