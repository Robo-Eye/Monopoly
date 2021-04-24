import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//Board Test Comment
public class Board {
	public int numPlayers;
	ArrayList<Property> propList = new ArrayList<Property>();
	ArrayList<Player> playerList = new ArrayList<Player>();

	public final int NUM_SPACES;
	int playerTurn;

	
	public Board() {
		NUM_SPACES = 4;
		playerTurn = 0;
	}
	/**
	 * Moves player using result on rollDice
	 */

	public void move(Player p) {
		int movement = rollDice();
		System.out.println("Its " + p.getPlayerName() + "'s turn.  They roll a " + movement);
		//int nextLocation = (p.getCurrentSpace() + movement) % NUM_SPACES;
		//System.out.println((p.getCurrentSpace()+movement)%NUM_SPACES);
if(movement>NUM_SPACES) {
	movement=movement-NUM_SPACES;
}
	p.changeCurrentSpace(((p.getCurrentSpace()+movement)%NUM_SPACES));
		//p.changeCurrentSpace(movement);
		System.out.println(p.getPlayerName()+" is currently on "+propList.get(p.getCurrentSpace()).getName());
		//System.out.println(p.getPlayerName()+" is currently on"+ propList.get(p.getCurrentSpace()).getName());
//	
//	p.change CurrentSpace(movement);
//	if(p.getCurrentSpace()>1) {
//		p.changeCurrentSpace(movement % 2);
//	}
		Scanner scn=new Scanner(System.in);

		if(propList.get(p.getCurrentSpace()).getOwner()==null) {
			System.out.println("Do you want to buy this property for $"+propList.get(p.getCurrentSpace()).getCost() +"?  Y/N");
		}else {
			System.out.println("Property owned by "+propList.get(p.getCurrentSpace()).getOwner()+" rent is "+propList.get(p.getCurrentSpace()).getRent());
p.deductMoney((propList.get(p.getCurrentSpace())).getRent());		
		}
if(scn.next().equalsIgnoreCase("Y")||scn.next().equalsIgnoreCase("y")) {
	p.deductMoney(propList.get(p.getCurrentSpace()).getCost());
	propList.get(p.getCurrentSpace()).changeOwner(p);
	System.out.println("Sale successfull");
}else {
	System.out.println("Sale unsucessful");
}



		if (playerTurn == numPlayers) {
			playerTurn = 0;
		} else {
			playerTurn++;
		}

	}

	/**
	 * returns random number between 2 and 12
	 * 
	 * @return
	 */
	public int rollDice() {
		Random randy = new Random();

		int randomNum = randy.nextInt((6 - 1) + 1) + 1;
		int randomNum2 = randy.nextInt((6 - 1) + 1) + 1;
		return randomNum + randomNum2;

//todo
		
	
	}
	public void gameLoop(int numPlayers) {
		boolean w = false; 
		while(!w) {
			
		}
	}

}
