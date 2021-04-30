
public abstract class Space {

	String name;
	
	//ALL these methods are abstract and useless.  Space has nothing other than a index in an arrayList  
	//I think all child classes need to have their methods be abstract in the parent class for some stupid reason.

	public abstract Object getOwner();

	protected abstract int getCost();

	protected abstract void changeOwner(Player p);

	protected abstract int getRent();

	protected  String getName() {
		return name;
	}

	protected abstract int getTaxes();
		
	

}
