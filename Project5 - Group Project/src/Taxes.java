
public class Taxes extends Space{
	int tax;
	
public Taxes(String name, int tax) {
	this.name=name;
	this.tax=tax;
}

@Override
public Object getOwner() {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected int getCost() {
	// TODO Auto-generated method stub
	return 0;
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
protected String getName() {
	// TODO Auto-generated method stub
	return null;
}

}
