import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

//Board Test Comment
//Test comment to see if push is working
//Pulls is working, is the push working?
public class Board {
	public int numPlayers;
	ArrayList<Space> propList = new ArrayList<Space>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	HashMap<Integer, Integer> optionList = new HashMap<Integer, Integer>();

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
		
		p.changeCurrentSpace((movement));
	
		System.out.println(p.getPlayerName() + " is currently on " + propList.get(p.getCurrentSpace()).getName());

		Scanner scn = new Scanner(System.in);

		if (p.getMoney() >= propList.get(p.getCurrentSpace()).getCost()
				&& propList.get(p.getCurrentSpace()).getOwner() == null) {
			System.out.println(
					"Do you want to buy this property for $" + propList.get(p.getCurrentSpace()).getCost() + "?  Y/N");
			if (scn.next().equalsIgnoreCase("Y")) {
				p.deductMoney(propList.get(p.getCurrentSpace()).getCost());
				if (propList.get(p.getCurrentSpace()) instanceof Railroad) {
					p.setRailCount(p.getRailCount() + 1);
				} else if (propList.get(p.getCurrentSpace()) instanceof Utility) {
					p.setUtilCount(p.getUtilCount() + 1);
				}
				propList.get(p.getCurrentSpace()).changeOwner(p);
				System.out.println("Sale successfull");
				System.out.println("Balance of " + p.getPlayerName() + " is $" + p.getMoney());
			} else if (scn.next().equalsIgnoreCase("N")) {
				System.out.println("No sale");
			}
		} else {
			if (p != propList.get(p.getCurrentSpace()).getOwner()) {
				int rent = (propList.get(p.getCurrentSpace())).getRent();
				if (propList.get(p.getCurrentSpace()) instanceof Railroad) {
					
					//Changed something here, the player in parentehses.
					int n = ((Player) propList.get(p.getCurrentSpace()).getOwner()).getRailCount();
					rent = (int) (rent * (Math.pow(2, n - 1)));

				} else if (propList.get(p.getCurrentSpace()) instanceof Utility) {
					
					//Changed something here, rthe player in parentheses...
					int n = ((Player) propList.get(p.getCurrentSpace()).getOwner()).getUtilCount();
					if (n == 1) {
						rent = 4 * movement;
					} else {
						rent = 10 * movement;
					}

				}
				System.out.println("Property owned by " + ((Player) propList.get(p.getCurrentSpace()).getOwner()).getPlayerName()
						+ ": rent is " + rent);
				if ((p.getMoney()) >= rent) {
					p.deductMoney(rent);
					((Player) propList.get(p.getCurrentSpace()).getOwner()).addMoney(rent);
					System.out.println(p.getMoney());
					System.out.println(((Player) propList.get(p.getCurrentSpace()).getOwner()).getMoney());

				}
				else {
					while ((p.getMoney() < rent) && (isitMorgaged(p)) == true) {
						int count = 0;
						for (int i = 0; i < propList.size(); i++) {
							if (propList.get(i).getOwner() == p && propList.get(i).getIsMorg() == false) {
								count++;
								optionList.put(count, i);
							}
						}

						for (int j = 0; j < optionList.size(); j++) {
							
							System.out.println(j + ". " + propList.get(optionList.get(j)).getName() + " has a mortgage value of $" + propList.get(optionList.get(j)).getMorgage());
						}
						
						System.out.println("Enter the number of the property you would like to mortgage: ");
						int choice = scn.nextInt();
						p.addMoney(propList.get(optionList.get(choice)).getMorgage());
						optionList.remove(choice);
					}
					
					if (p.getMoney() < rent) {
						//bankruptcy steps
					}
					
				// write method if players do not have money to pay the rent


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
	public boolean isitMorgaged(Player p) {
		boolean propToMorgage = false;
		for (int i = 0; i < propList.size(); i++) {
			Space pr = propList.get(i);
			if (pr.getIsMorg() == false && p == propList.get(i).getOwner()) {
				propToMorgage = true;
				break;
			}
		}
		if (propToMorgage == true) {
			return true;
		}
		else {
			return false;
		}
	}

}
