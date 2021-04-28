
public class Player {
	private int currBal;
	private String playerName;
	private int currSpace;
	private int railCount;
	private int utilCount;

	public Player(String name) {
		playerName = name;
		currBal = 1500;
		currSpace = 0;
		railCount = 0;
		utilCount = 0;
		
	}

	public int getRailCount() {
		return railCount;
	}


	public void setRailCount(int railCount) {
		this.railCount = railCount;
	}


	public int getUtilCount() {
		return utilCount;
	}


	public void setUtilCount(int utilCount) {
		this.utilCount = utilCount;
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
		currSpace = (currSpace + move) % 4;
	}

	public int getCurrentSpace() {

		return currSpace;
	}

}
