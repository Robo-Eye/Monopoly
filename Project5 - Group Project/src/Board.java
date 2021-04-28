import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Board Test Comment
//Test comment to see if push is working
//Pulls is working, is the push working?
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

		if (p.getMoney() >= propList.get(p.getCurrentSpace()).cost && propList.get(p.getCurrentSpace()).getOwner() == null) {
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
		} else if (p != propList.get(p.getCurrentSpace()).getOwner()) {
			int rent = (propList.get(p.getCurrentSpace())).getRent();
			if (propList.get(p.getCurrentSpace()) instanceof Railroad) {
				int n = propList.get(p.getCurrentSpace()).getOwner().getRailCount();
				rent = (int) (rent*(Math.pow(2, n-1)));
				
			}
			else if (propList.get(p.getCurrentSpace()) instanceof Utility) {
				int n = propList.get(p.getCurrentSpace()).getOwner().getUtilCount();
				if (n == 1) {
					rent = 4*movement;
				}
				else {
					rent = 10*movement;
				}
				
			}
			System.out.println("Property owned by " + propList.get(p.getCurrentSpace()).getOwner().getPlayerName()
					+ ": rent is " + rent);
			if ((p.getMoney()) >= rent) {
				p.deductMoney(rent);
				propList.get(p.getCurrentSpace()).getOwner().addMoney(rent);
				System.out.println(p.getMoney());
				System.out.println(propList.get(p.getCurrentSpace()).getOwner().getMoney());

			}
			// write method if players do not have money to pay the rent

			else if ((p.getMoney()) >= (propList.get(p.getCurrentSpace()).getRent())) {

				p.deductMoney((propList.get(p.getCurrentSpace())).getRent());
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
