public class Road implements Comparable<Road>{
	private Town source;
	private Town destination;
	private int degrees;
	private String roadName;
	
	public Road(Town source, Town destination, int degrees, String roadName) {
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.roadName = roadName;
	}
	
	public Road (Town source, Town destination, String roadName){
		this.source = source;
		this.destination = destination;
		this.degrees = 1;
		this.roadName = roadName;
	}
	
	public boolean contains(Town town) {
		return (source.equals(town) || destination.equals(town));
	}
	public String toString() {
		return roadName;
	}
	public String getName() {
		return roadName;
	}
	public Town getDestination() {
		return destination;
	}
	public Town getSource() {
		return source;
	}
	public int compareTo(Road o) {
		return roadName.compareTo(o.getName());
	}
	public int getWeight() {
		return degrees;
	}
	public boolean equals(Object r) {
		Road other = (Road) r;
		if(source.equals(destination)) {
			return (other.source.equals(source) && other.destination.equals(source));
		} else {
			return ((other.source.equals(source) || other.source.equals(destination)) &&
					(other.destination.equals(source) || other.destination.equals(destination)));
		}
	}
}