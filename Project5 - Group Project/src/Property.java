
public class Property {

	public String name;
	public int cost;
	public int morgage;
	public Player owner;
	public int rent;//CHANGE OVER TO REALPROPERTY

	/**
	 * abstract classes for the morgage and rent of properties
	 */
	public Property() {
		name = "Empty";
		cost = 0;
		morgage = 0;
		owner=null;
		rent=0;
	}
public Player getOwner() {
	return this.owner;
}
public void changeOwner(Player p) {
	this.owner=p;
}
	public Property(String name, int cost, int morgage, int rent) {//CHANGE RENT OUT TO REALPROP
		this.name = name;
		this.cost = cost;
		this.morgage = morgage;
		this.rent=rent;
	}

	public int getCost() {
		return cost;
	}

	public int getMorgage() {
		return morgage;
	}
	public String getName() {
		return this.name;
	}
	public int getRent() {
		return this.rent;
	}
//public void setProperty(String name, int cost, int morgage) {
//	this.name=name;
//	this.cost=cost;
//	this.morgage=morgage;
//}
//abstract void rent();
//abstract void morgage();
}
