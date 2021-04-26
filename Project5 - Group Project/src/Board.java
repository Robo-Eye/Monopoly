import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Board Test Comment
//Test comment to see if push is working
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
		// int nextLocation = (p.getCurrentSpace() + movement) % NUM_SPACES;
		// System.out.println((p.getCurrentSpace()+movement)%NUM_SPACES);
//		if (movement > NUM_SPACES) {
//			movement =- NUM_SPACES;
//		}
		p.changeCurrentSpace((movement));
		// p.changeCurrentSpace(movement);
		System.out.println(p.getPlayerName() + " is currently on " + propList.get(p.getCurrentSpace()).getName());
		
		
		// System.out.println(p.getPlayerName()+" is currently on"+
		// propList.get(p.getCurrentSpace()).getName());
		//	
		//	p.change CurrentSpace(movement);
		//	if(p.getCurrentSpace()>1) {
		//		p.changeCurrentSpace(movement % 2);
		//	}
		Scanner scn = new Scanner(System.in);

		
		
		if(p.getMoney() >= propList.get(p.getCurrentSpace()).cost ) {
			if (propList.get(p.getCurrentSpace()).getOwner() == null) {
				System.out.println("Do you want to buy this property for $" 
				+ propList.get(p.getCurrentSpace()).getCost() + "?  Y/N");
				if (scn.next().equalsIgnoreCase("Y")) {
					p.deductMoney(propList.get(p.getCurrentSpace()).getCost());
					propList.get(p.getCurrentSpace()).changeOwner(p);
					System.out.println("Sale successfull");
					System.out.println("Balance of " + p.getPlayerName() + " is $" + p.getMoney());
				} else if (scn.next().equalsIgnoreCase("N")) {
					System.out.println("No sale");
				}
			} else {
				System.out.println("Property owned by " + propList.get(p.getCurrentSpace()).getOwner().getPlayerName() + 
				" rent is " + propList.get(p.getCurrentSpace()).getRent());
				if(p.getMoney() < 0) {
					//Working On This 
			
				}else {
					p.deductMoney((propList.get(p.getCurrentSpace())).getRent());
				}
				
			}
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

	}

	public void gameLoop(int numPlayers) {
		boolean w = false;
		while (!w) {

		}
	}

}
