public class RealProperty extends Property {
	public int rent;

	//Constructor
	public RealProperty(String name, int cost, int morgage, int rent) {
	super(name, cost, morgage);
		this.rent = rent;
	}

	//rent getter
	public int getRent() {
		return this.rent;
	}

}
