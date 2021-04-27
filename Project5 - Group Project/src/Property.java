
public abstract class Property {

	public String name;
	public int cost;
	public int morgage;
	public Player owner;
	//public int rent;//CHANGE OVER TO REALPROPERTY
	private boolean isMorg; 
	/**
	 * abstract classes for the morgage and rent of properties
	 */
	public Property() {
		name = "Empty";
		cost = 0;
		morgage = 0;
		owner=null;
	//	rent=0;
		isMorg = false;
	}
	public Player getOwner() {
		return this.owner;
	}
	public void changeOwner(Player p) {
		this.owner=p;
	}
	public Property(String name, int cost, int morgage) {//CHANGE RENT OUT TO REALPROP
		this.name = name;
		this.cost = cost;
		this.morgage = morgage;
		//this.rent=rent;
	}

	public int getCost() {
		return cost;
	}

	public int getMorgage() {
		return morgage;
	}
	public String getName() {
		return name;
	}

	
	
	public void setIsMorg(Property p) {
		if(isMorg) {
			p.isMorg = false;
		}else {
			p.isMorg = true;
		}
	}
	
	public boolean getIsMorg() {
		return isMorg;
	}
	
	abstract public int getRent();
	//public void setProperty(String name, int cost, int morgage) {
	//	this.name=name;
	//	this.cost=cost;
	//	this.morgage=morgage;
	//}
	//abstract void morgage();
}
