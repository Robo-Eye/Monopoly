
public class Player {
	private int currBal;
	private String playerName;
	private int currSpace;
	
	public Player(String name) {
		playerName = name;
		currBal = 1500;
		currSpace = 0;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void addMoney(int amtToAdd) {
		currBal += amtToAdd;
	}
	
	public void deductMoney(int amtToDeduct) {
		currBal -= amtToDeduct;
	}
	public int getMoney() {
		return currBal;
	}
	
	public void changeCurrentSpace(int move) {
		currSpace += move;
	}
	
	public int getCurrentSpace() {

		return currSpace;
	}
	
}
