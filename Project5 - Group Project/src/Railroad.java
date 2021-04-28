
public class Railroad extends Property {
	
	private int rent;
	//Constructor
	public Railroad(String name, int cost, int morgage) {
		super(name, cost, morgage);
		}		
//Setter	
public Railroad() {
	this.rent=25;
}
//Getter for rent
@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return this.rent;
	}

}
