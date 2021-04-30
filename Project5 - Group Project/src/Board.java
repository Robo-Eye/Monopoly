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
	HashMap<Integer, Integer> optionList = new HashMap<Integer, Integer>();

	public final int NUM_SPACES;
	int playerTurn;

	public Board() {
		NUM_SPACES = 38;
		playerTurn = 0;
		numPlayers = playerList.size();
	}

	/**
	 * Moves player using result on rollDice
	 */

	public void move(Player p) {
		int startPoint=p.getCurrentSpace();
		int movement = rollDice();
		
		
		
		System.out.println("Its " + p.getPlayerName() + "'s turn.  They roll a " + movement+" and have a balance of $"+p.getMoney());

		p.changeCurrentSpace((movement));

		System.out.println(p.getPlayerName() + " is currently on " + propList.get(p.getCurrentSpace()).getName());
//Go
if(startPoint>0&&p.getCurrentSpace()<startPoint&&p.getJail()==false) {
	System.out.println("You passed Go!  You collect $200");
	p.addMoney(200);
	System.out.println("Updated balance: $"+p.getMoney());
}
		
		
		Scanner scn = new Scanner(System.in);
		if (propList.get(p.getCurrentSpace()) instanceof Property) {
			Property pr = (Property) propList.get(p.getCurrentSpace());
			
			if(pr.getOwner()==p) {
				System.out.println("You already own this property.  No rent today!");
			}
			if (pr.getOwner() == null) {
				if (p.getMoney() >= pr.getCost()) {
					System.out.println("Do you want to buy "+propList.get(p.getCurrentSpace()).getName() +" for $" + pr.getCost() + "?  Y/N");
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
					System.out.println("Property owned by "
							+ (pr.getOwner()).getPlayerName() + ": rent is " + rent);
					if ((p.getMoney()) >= rent) {
						p.deductMoney(rent);
						((Player) pr.getOwner()).addMoney(rent);
						System.out.println(p.getPlayerName()+"'s balance: $"+p.getMoney());
						System.out.println(pr.getOwner().getPlayerName()+"'s balance: $"+((Player) pr.getOwner()).getMoney());

					} else {
						while ((p.getMoney() < rent) && (isitMorgaged(p)) == true) {
							int count = 0;
							for (int i = 0; i < propList.size(); i++) {
								if (propList.get(i) instanceof Property) {
									Property prop = (Property) propList.get(i);
								if (prop.getOwner() == p && prop.getIsMorg() == false) {
									count++;
									optionList.put(count, i);
								}
								}
							}

							for (int j = 0; j < optionList.size(); j++) {
								if (propList.get(optionList.get(j)) instanceof Property) {
									Property pr1 = (Property) propList.get(optionList.get(j));
									System.out.println("hello");
									System.out.println(j);
									System.out.println(". ");
									System.out.println(pr1.getName());
									System.out.println(" has a mortgage value of $");
									System.out.println(pr1.getMorgage());
								}
							}

							System.out.println("Enter the number of the property you would like to mortgage: ");
							int choice = scn.nextInt();
							Property pr2 = (Property) propList.get(optionList.get(choice));
							p.addMoney((pr2).getMorgage());
							optionList.remove(choice);
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

						// write method if players do not have money to pay the rent

					}

				}
			}
		}

		//Taxes
		Space space=propList.get(p.getCurrentSpace());
		if(propList.get(p.getCurrentSpace()) instanceof Taxes) {
	System.out.println("Uh oh! You landed on "+space.getName()+".  You owe $"+((Taxes) space).getTaxes());
		p.deductMoney(((Taxes) space).getTaxes());
		System.out.println("Current balance: $"+p.getMoney());
		}
		
		//Free parking
		if(space instanceof FreeParking) {
			System.out.println("Its your lucky day!  Free parking!");
		}
		//Chance
		if(space instanceof Chance) {
			System.out.println("You got chance!  More code to follow");
		}
		
		//Commnity chest
		if(space instanceof ComChest) {
			System.out.println("You got a chest card!  MOre to follow");
		}
		
		
		
		//Switching turns
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
