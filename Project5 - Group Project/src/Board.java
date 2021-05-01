import java.util.ArrayList;
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
		NUM_SPACES = 40;
		playerTurn = 0;
		numPlayers = playerList.size();
	}

	/**
	 * Moves player using result on rollDice
	 */

	public void move(Player p) {
		int startPoint = p.getCurrentSpace();
		int movement = rollDice();

		System.out.println("Its " + p.getPlayerName() + "'s turn.  They roll a " + movement + " and have a balance of $"
				+ p.getMoney());

		p.changeCurrentSpace((movement));

		System.out.println(p.getPlayerName() + " is currently on " + propList.get(p.getCurrentSpace()).getName());

		Space space = propList.get(p.getCurrentSpace());

		// Go
		if (startPoint > 0 && p.getCurrentSpace() < startPoint && p.getJail() == false) {
			System.out.println("You passed Go!  You collect $200");
			p.addMoney(200);
			System.out.println("Updated balance: $" + p.getMoney());
		}
//Jail
		if (space instanceof Jail) {
			// If your just visiting
			if (p.getJail() == false) {
				System.out.println("Just visiting!");
			} else {
				System.out.println(
						"Oof.  Your in jail.  Pay $50 next turn, roll doubles, or use a get out of jail free card to escape.");
			}
		}

//Go to jail
		if (space instanceof GoToJail) {
			System.out.println("Go to jail! Go directly to jail.  Do not pass go, do not collect $200");
			p.goJail();
			p.setJail(true);
		}

		//Properties
		// Properties
		Scanner scn = new Scanner(System.in);
		if (space instanceof Property) {
			Property pr = (Property) space;

			if (pr.getOwner() == p) {
				System.out.println("You already own this property.  No rent today!");
			}
			else if (pr.getIsMorg()) {
				System.out.println("This property is mortgaged.  You pay nothing!");
			}
			if (pr.getOwner() == null) {
				if (p.getMoney() >= pr.getCost()) {
					System.out.println("Do you want to buy " + pr.getName() + " for $"
							+ pr.getCost() + "?  Y/N");
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
			} else if (pr.getIsMorg() == false){
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
					if (p.getMoney() < rent) {
						while ((p.getMoney() < rent) && isitMorgaged(p)) {
							needToMortgage(p);
						}
						if (p.getMoney() < rent) {
							Bankrupt(p);
						}
					}
					if (p.isBankrupt() == false) {
						p.deductMoney(rent);
						((Player) pr.getOwner()).addMoney(rent);
						System.out.println(p.getPlayerName() + "'s balance: $" + p.getMoney());
						System.out.println(
								pr.getOwner().getPlayerName() + "'s balance: $" + ((Player) pr.getOwner()).getMoney());
					}

				}

			}
		}

		// Taxes
		if (propList.get(p.getCurrentSpace()) instanceof Taxes) {
			int tax = ((Taxes) space).getTaxes();
			System.out.println("Uh oh! You landed on " + space.getName() + ".  You owe $" + tax);
			if ((p.getMoney()) >= tax) {

			} else {
				while ((p.getMoney() < tax) && isitMorgaged(p)) {
					needToMortgage(p);
				}
				if (p.getMoney() < tax) {
					Bankrupt(p);
				}

			}
			if (p.isBankrupt() == false) {
				p.deductMoney(((Taxes) space).getTaxes());
				System.out.println("Current balance: $" + p.getMoney());
			}
		}

		// Free parking
		if (space instanceof FreeParking) {
			System.out.println("Its your lucky day!  Free parking!");
		}
		// Chance
		if (space instanceof Chance) {
			System.out.println("You got chance!  More code to follow");
			int chance = ((Chance) space).getTransaction();
			if ((p.getMoney()) >= chance) {

			} else {
				while ((p.getMoney() < chance) && isitMorgaged(p)) {
					needToMortgage(p);
				}
				if (p.getMoney() < chance) {
					Bankrupt(p);
				}

			}
			if (p.isBankrupt() == false) {
				p.deductMoney(((Chance) space).getTransaction());
				System.out.println("Current balance: $" + p.getMoney());
			}
		}

		// Commnity chest
		if (space instanceof ComChest) {
			System.out.println("You got a chest card!  MOre to follow");
			int comChest = ((ComChest) space).getTransaction();
			if ((p.getMoney()) >= comChest) {

			} else {
				while ((p.getMoney() < comChest) && isitMorgaged(p)) {
					needToMortgage(p);
				}
				if (p.getMoney() < comChest) {
					Bankrupt(p);
				}

			}
			if (p.isBankrupt() == false) {
				p.deductMoney(((ComChest) space).getTransaction());
				System.out.println("Current balance: $" + p.getMoney());
			}
		}

		// Switching turns
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
//return 10;  //Testing purposes
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

	public void needToMortgage(Player p) {
		Scanner scnr = new Scanner(System.in);
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
				System.out.println(j + 1 + ". " + pr1.getName() + " has a mortgage value of $"
						+ pr1.getMorgage() + ".");
			}
		}

		System.out.println("Enter the number of the property you would like to mortgage: ");
		int choice = scnr.nextInt();
		Property pr2 = (Property) propList.get(optionList.get(choice - 1));
		p.addMoney((pr2).getMorgage());
		pr2.setMorg(true);
		optionList.remove(choice - 1);


	}
	
	public void Bankrupt(Player p) {
		int bankruptmoney = p.getMoney();
		p.deductMoney(p.getMoney());
		Space s = propList.get(p.getCurrentSpace());
		int moneyToPay = 0;
		if (s instanceof Property) {
			moneyToPay = ((Property) s).getRent();
		}
		if (s instanceof Taxes) {
			moneyToPay = ((Taxes) s).getTaxes();
		}
		if (s instanceof Chance) {
			moneyToPay = ((Chance) s).getTransaction();
		}
		if (s instanceof ComChest) {
			moneyToPay = ((ComChest) s).getTransaction();
		}
		if (p.getMoney() < moneyToPay) { // player is bankrupt
			if (propList.get(p.getCurrentSpace()) instanceof Property) {
				Property pr4 = (Property) propList.get(p.getCurrentSpace());
				for (int k = 0; k < propList.size(); k++) {
					if (propList.get(k) instanceof Property) {
						Property pr3 = (Property) propList.get(k);
						pr3.changeOwner(pr4.getOwner());
						pr4.getOwner().addMoney(bankruptmoney);
					}
				}
			}
			else {
				for (int k = 0; k < propList.size(); k++) {
					if (propList.get(k) instanceof Property) {
						Property pr5 = ((Property) propList.get(k));
						pr5.changeOwner(null);
					}
				}
			}


			p.setBankrupt(true);
			playerList.remove(p);
		}
	}

}
