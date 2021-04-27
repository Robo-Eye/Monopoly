
public class Utility extends Property{

	
	//How do I acess dice roll from Board to here? DO i take in Board B??
	@Override
	public int getRent(Board b) {
		// TODO Auto-generated method stub
		return(b.rollDice());
		
	}
}
