
public class Taxes extends Space {
	int tax;

	public Taxes(String name, int tax) {
		this.name = name;
		this.tax = tax;
	}

	public int getTaxes() {
		return tax;
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
