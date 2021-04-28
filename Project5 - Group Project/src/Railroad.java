
public class Railroad extends Property {
	
	
	//How do I access the propList from Board to see how many railroads the person owns?
	//Do i need to take in a Board b?  if so, that throws errorss in Board where getRent is called due to it needing a Board within the board class.
	@Override
	public int getRent(Board b) {
		// TODO Auto-generated method stub
		int rrCount=0;
		
		for(int i=1; i<b.propList.size()+1; i++) {
			if(b.propList.get(i).getOwner()==owner) {
				rrCount++;
			}
		}
		
		if(rrCount==1) {
		return 25;
		}else if(rrCount==2) {
			return 50;
		}else if(rrCount==3) {
			return 100;
		}else {
			return 200;
		}
	}
	
		
	

}
