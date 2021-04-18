
public class Property {

	public String name;
	public int cost;
	public int morgage;

	/**
	 * abstract classes for the morgage and rent of properties
	 */
	public Property() {
		name = "Empty";
		cost = 0;
		morgage = 0;
	}

	public Property(String name, int cost, int morgage) {
		this.name = name;
		this.cost = cost;
		this.morgage = morgage;
	}

	public int getCost() {
		return cost;
	}

	public int getMorgage() {
		return morgage;
	}
//public void setProperty(String name, int cost, int morgage) {
//	this.name=name;
//	this.cost=cost;
//	this.morgage=morgage;
//}
//abstract void rent();
//abstract void morgage();
}
