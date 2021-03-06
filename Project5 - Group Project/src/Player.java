
public class Player {
	private int currBal;
	private String playerName;
	private int currSpace;
	private int railCount;
	private int utilCount;
	private boolean bankrupt;
	private boolean rollDouble;
	private int doubleCount;
	private int jailRoll;
private boolean inJail;
private int jailFree;
	public Player(String name) {
		jailRoll=0;
		doubleCount=0;
		playerName = name;
		currBal = 1500;
		currSpace = 0;
		railCount = 0;
		utilCount = 0;
		inJail=false;
		bankrupt = false;
		jailFree=0;
		rollDouble=false;
		
	}
public int getDoubleCount() {
	return doubleCount;
}
public void incDouble() {
	doubleCount++;
}
public void incJailRoll() {
	jailRoll++;
}
public int getJailRoll() {
	return jailRoll;
}
public void resetJailRoll() {
	jailRoll=0;
}
public void resetDouble() {
	doubleCount=0;
}
public Boolean getDouble() {
	return rollDouble;
}
public void setDouble(boolean b) {
	rollDouble=b;
}
	public int getRailCount() {
		return railCount;
	}

public void subJailFree() {
	jailFree--;
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
		currSpace = (currSpace + move) % 40;//CHANGE THIS FOR HOWEVER MANY SPACES THERE ARE
	}
public void goJail() {
	currSpace=10;
}

	public int getCurrentSpace() {

		return currSpace;
	}
	public void setJail(Boolean b) {
		this.inJail=b;
	}
	public Boolean getJail() {
		return this.inJail;
	}

	public boolean isBankrupt() {
		return bankrupt;
	}

	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}
	public int getJailFree() {
		return jailFree;
	}
	public void addJailFree() {
		jailFree++;
	}
	public void subtractJailFree() {
		jailFree--;
	}

}
