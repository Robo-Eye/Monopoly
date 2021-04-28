
public abstract class Property extends Space{

	public String name;
	public int cost;
	public int morgage;
	public Player owner;
	private boolean isMorg; 
	
	//Default constructor
	public Property() {
		name = "Empty";
		cost = 0;
		morgage = 0;
		owner=null;
	//	rent=0;
		isMorg = false;
	}
	//Getter for owner
	public Player getOwner() {
		return this.owner;
	}
	//Setter for owner
	public void changeOwner(Player p) {
		this.owner=p;
	}
	//Constructor
	public Property(String name, int cost, int morgage) {
		this.name = name;
		this.cost = cost;
		this.morgage = morgage;
		//this.rent=rent;
	}
//Getter for cost
	public int getCost() {
		return cost;
	}
//Getter for morgage
	public int getMorgage() {
		return morgage;
	}
	//Getter for name
	public String getName() {
		return name;
	}
	
	public void setMorg(boolean b) {
		isMorg = b;
	}
	
	public boolean getIsMorg() {
		return isMorg;
	}
	
	 abstract public int getRent();
	
}
