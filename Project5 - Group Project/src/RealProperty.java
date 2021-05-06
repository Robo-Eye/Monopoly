public class RealProperty extends Property {
	public int rent;
	public int houseCount;
	public int houseCost;
	public int rent1house;
	public int rent2house;
	public int rent3house;
	public int rent4house;
	public int rentHotel;
	public String color;

	//Constructor
	public RealProperty(String name, int cost, int morgage, int rent, int houseCost, int rent1house, int rent2house, int rent3house, int rent4house, int rentHotel) {
	super(name, cost, morgage);
		this.rent = rent;
		this.houseCount = 0;
		this.houseCost = houseCost;
		this.rent1house = rent1house;
		this.rent2house = rent2house;
		this.rent3house = rent3house;
		this.rent4house = rent4house;
		this.rentHotel = rentHotel;
		this.color = color;
	}

	public int getRent1house() {
		return rent1house;
	}

	public int getRent2house() {
		return rent2house;
	}

	public int getRent3house() {
		return rent3house;
	}

	public int getRent4house() {
		return rent4house;
	}

	public int getRentHotel() {
		return rentHotel;
	}

	//rent getter
	public int getRent() {
		return this.rent;
	}

	public int getHouseCount() {
		return houseCount;
	}

	public String getColor() {
		return color;
	}
	public void setHouseCount(int houseCount) {
		this.houseCount = houseCount;
	}


}
