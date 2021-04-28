
public class Railroad extends Property {
	
	private int rent;
	//How do I access the propList from Board to see how many railroads the person owns?
	//Do i need to take in a Board b?  if so, that throws errorss in Board where getRent is called due to it needing a Board within the board class.
	
	
		
	
	//rent = 25
public Railroad() {
	this.rent=25;
}
@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return this.rent;
	}

}
