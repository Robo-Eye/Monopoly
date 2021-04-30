
public class Go extends Space {
	public int goMoney;
	public Go() {
		this.name = "Go";
		goMoney = 200;
		
	}
	
	public int passGo() {
		return goMoney;
	}

	@Override
	protected int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Player getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void changeOwner(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean getIsMorg() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int getMorgage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
