import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

//Board Test Comment.
//Test comment to see if push is working
//Pulls is working, is the push working?
public class Board {
	public int numPlayers;
	ArrayList<Space> propList = new ArrayList<Space>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Integer> optionList = new ArrayList<Integer>();

	public final int NUM_SPACES;
	int playerTurn;

	public Board() {
		NUM_SPACES = 30;
		playerTurn = 0;
		numPlayers = playerList.size();
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
		if (propList.get(p.getCurrentSpace()) instanceof Property) {
			Property pr = (Property) propList.get(p.getCurrentSpace());
			if (pr.getOwner() == null) {
				if (p.getMoney() >= pr.getCost()) {
					System.out.println("Do you want to buy this property for $" + pr.getCost() + "?  Y/N");
					String response = scn.next();
					if (response.equalsIgnoreCase("Y")) {
						p.deductMoney(pr.getCost());
						if (pr instanceof Railroad) {
							p.setRailCount(p.getRailCount() + 1);
						} else if (pr instanceof Utility) {
							p.setUtilCount(p.getUtilCount() + 1);
						}
						pr.changeOwner(p);
						System.out.println("Sale successfull");
						System.out.println("Balance of " + p.getPlayerName() + " is $" + p.getMoney());
					} else if (response.equalsIgnoreCase("N")) {
						System.out.println("No sale");
					}
				}

				else {
					System.out.println("You cannot buy this property because you don't have enough money.");

				}
			} else {
				if (p != pr.getOwner()) {
					int rent = pr.getRent();
					if (pr instanceof Railroad) {

						// Changed something here, the player in parentehses.
						int n = ((Player) pr.getOwner()).getRailCount();
						rent = (int) (rent * (Math.pow(2, n - 1)));

					} else if (pr instanceof Utility) {

						// Changed something here, rthe player in parentheses...
						int n = ((Player) pr.getOwner()).getUtilCount();
						if (n == 1) {
							rent = 4 * movement;
						} else {
							rent = 10 * movement;
						}

					}
					System.out.println("Property owned by " + (pr.getOwner()).getPlayerName() + ": rent is " + rent);
					if ((p.getMoney()) >= rent) {
						p.deductMoney(rent);
						((Player) pr.getOwner()).addMoney(rent);
						System.out.println(p.getMoney());
						System.out.println(((Player) pr.getOwner()).getMoney());

					} else {
						while ((p.getMoney() < rent) && isitMorgaged(p)) {
						optionList = new ArrayList<>();
						for (int i = 0; i < propList.size(); i++) {
							if (propList.get(i) instanceof Property) {
								Property prop = (Property) propList.get(i);
								if (prop.getOwner() == p && prop.getIsMorg() == false) {
									optionList.add(i);
								}
							}
						}
							for (int j = 0; j < optionList.size(); j++) {
								if (propList.get(optionList.get(j)) instanceof Property) {
									Property pr1 = (Property) propList.get(optionList.get(j));
									System.out.println(j+1 + ". " + pr1.getName() + " has a mortgage value of $" + pr1.getMorgage() + ".");
								}
							}

							System.out.println("Enter the number of the property you would like to mortgage: ");
							int choice = scn.nextInt();
							Property pr2 = (Property) propList.get(optionList.get(choice - 1));
							p.addMoney((pr2).getMorgage());
							pr2.setMorg(true);
							optionList.remove(choice - 1);
							
						}

						if (p.getMoney() < rent) { // player is bankrupt
							for (int k = 0; k < propList.size(); k++) {
								Property pr3 = (Property) propList.get(k);
								if (p == pr3.getOwner()) {
									pr3.changeOwner(pr.getOwner());
								}
							}
							int bankruptmoney = p.getMoney();
							p.deductMoney(p.getMoney());
							pr.getOwner().addMoney(bankruptmoney);
							playerList.remove(p);
							}
						}
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
			if (propList.get(i) instanceof Property) {
				Property pr = (Property) propList.get(i);
				if (pr.getIsMorg() == false && p == pr.getOwner()) {
					propToMorgage = true;
					break;
				}
			}
		}
		if (propToMorgage == true) {
			return true;
		} else {
			return false;
		}
	}

}
