public class RealProperty extends Property {
	public int rent;

	public RealProperty(String name, int cost, int morgage, int rent) {
	super(name, cost, morgage);
		this.rent = rent;
	}

	/*
	 * rent is however much the property card says
	 */

	//	public int getRent() {
	//		return this.rent;
	//	}

	public int getRent() {
		return this.rent;
	}
	/*
	 * 
	 * morgage is cost of property/2 or something like that
	 */

	

	/*
	 * Build a house on a property
	 */
	void buildHouse() {

	}

	/**
	 * build a hotel on a property
	 */
	void buildHotel() {

	}

}
