
public abstract class Space {

	public int cost;

	public abstract Object getOwner();
		// TODO Auto-generated method stub

	protected abstract int getCost();

	protected abstract void changeOwner(Player p);

	protected abstract int getRent();

	protected abstract String getName();
		
	

}
