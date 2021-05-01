
public class ComChest extends Space{
	
	public int transaction;
	
	public ComChest(int transaction) {
		this.name="Community Chest";
		this.transaction = transaction;
	}
	
public int getTransaction() {
		return transaction;
	}
	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

}
