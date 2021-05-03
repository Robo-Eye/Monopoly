import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

//Board Test Comment.
//Test comment to see if push is working
//Pulls is working, is the push working?
public class Board {
	public int numPlayers;
	ArrayList<Integer>Chance=new ArrayList<Integer>();
	ArrayList<Integer>Community=new ArrayList<Integer>();
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
		int die1=rollDice();
		int die2=rollDice();
		int movement = die1+die2;
		Boolean doubles=false;
		Boolean skipJail=false;
		p.setDouble(false);
		
		if(die1==die2) {
			doubles=true;
			p.setDouble(true);
			p.incDouble();
		}
if(p.getDoubleCount()==3) {
	System.out.println("You rolled a "+die1+" and a "+die2+" which means you rolled doubles three times in a row, so you get a speeding ticket and go straight to jail, no Go or $200");
	p.setJail(true);
	p.changeCurrentSpace(0);
	p.resetDouble();
	p.setDouble(false);
	skipJail=true;
	
}

if(p.getJail()&&skipJail==false) {
	Scanner stan=new Scanner(System.in);
	System.out.println(p.getPlayerName()+":\n");
	if(p.getJailRoll()<3) {
	System.out.println("Type 1 to try to roll doubles to get out of jail.  You can only do this three times");
	}
	System.out.println("Type 2 to pay $50 to get out of jail");
	if(p.getJailFree()>0) {
		System.out.println("Type 3 to use your get out of jail free card");
	}
		int ans=stan.nextInt();
	if(ans==3) {
		System.out.println("Congrats! You are out of jail.");
		p.setJail(false);
		p.subJailFree();
		p.resetJailRoll();
	}
	if(ans==2) {
		System.out.println("Congrats!  Your out of jail.");
		p.deductMoney(50);
		System.out.println("Current balance: $"+p.getMoney());
		p.setJail(false);
		p.resetJailRoll();
	}
	if(ans==1&&p.getJailRoll()<3) {
		
		if(doubles) {
		System.out.println("Nice work!  You rolled doubles so you are out of jail.");
		p.setJail(false);
		p.resetJailRoll();
		}else {
			p.incJailRoll();
			System.out.println("Sorry!  You  rolled "+die1+" and "+die2+" not doubles.  Try again next turn.");
		}
	}
	
}
//Make sure this is after jail..we dont want people rolling 3 doubles than immediately getting out of jail


if(p.getJail()==false) {
	System.out.println("Its " + p.getPlayerName() + "'s turn.  They roll a " + die1+" and a "+die2+" giving them a total move of " +movement+ " and have a balance of $"
			+ p.getMoney());
		p.changeCurrentSpace((movement));

		System.out.println(p.getPlayerName() + " is currently on " + propList.get(p.getCurrentSpace()).getName());

}
if(p.getDouble()&&p.getDoubleCount()<3) {
	System.out.println("You rolled doubles so you get to move again!");
}
Space space = propList.get(p.getCurrentSpace());

		
		// Chance
				if (space instanceof Chance&&p.getJail()==false) {
					int chanceCard=Chance.get(0);
					if(chanceCard==1) {
						Chance.remove(0);
						System.out.println("Advance to Go, collect $200");
						p.addMoney(200);
						System.out.println("Current Balance: $"+p.getMoney());
						p.changeCurrentSpace(0);
						//System.out.println("Current balance: $"+ p.getMoney());
						Chance.add(9, 1);
					}
					if(chanceCard==2) {
						Chance.remove(0);
						System.out.println("Bank error!  You get $50");
						p.addMoney(50);
						System.out.println("Current balance: $"+ p.getMoney());
						Chance.add(9,2);
					}
					if(chanceCard==3) {
						Chance.remove(0);
						System.out.println("Poor mans tax!  Pay $15");
						p.deductMoney(15);
						System.out.println("Current balance: $"+ p.getMoney());
						Chance.add(9,3);
					}
					if(chanceCard==4) {						
						Chance.remove(0);
						System.out.println("Your building loan has matured!  You get $150");
						p.addMoney(150);
						System.out.println("Current balance: $"+ p.getMoney());
						Chance.add(9,4);
					}
					if(chanceCard==5) {
						Chance.remove(0);
						System.out.println("Go to St Charles Place!");
						p.changeCurrentSpace(11);
						Chance.add(9,5);
					}
					if(chanceCard==6) {
						Chance.remove(0);
						System.out.println("Go to Illinois ave!");
						p.changeCurrentSpace(24);
						Chance.add(9,6);
					}
					if(chanceCard==7) {
						Chance.remove(0);

						System.out.println("Go back three spaces!");
						p.changeCurrentSpace(p.getCurrentSpace()-3);
						Chance.add(9,7);
					}
					if(chanceCard==8) {
						Chance.remove(0);

						System.out.println("Go to jail! Go directly to jail.  Dont pass go, dont collect $200");
						p.goJail();
						p.setJail(true);
						Chance.add(9,8);
					}
					if(chanceCard==9) {
						Chance.remove(0);

						System.out.println("You got a get out of jail free card!");
						p.addJailFree();
						Chance.add(9,9);
					}
					if(chanceCard==10) {
						Chance.remove(0);

						System.out.println("Go to reading railroad");
						p.changeCurrentSpace(5);
						Chance.add(9,10);
					}
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
//					if (p.isBankrupt() == false) {
//						p.deductMoney(((Chance) space).getTransaction());
//						System.out.println("Current balance: $" + p.getMoney());
//					}
				}
				// Commnity chest
				if (space instanceof ComChest&&p.getJail()==false) {
					int commCard=Community.get(0);
					Community.remove(0);

					if(commCard==1) {
						System.out.println("You got an inheritance!  Collect $100");
						p.addMoney(100);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,1);
					}
					if(commCard==2) {
						System.out.println("You got 2nd in a beauty contest!  Collect $20");
						p.addMoney(20);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,2);
					}
					if(commCard==3) {
						System.out.println("Receive $25 consultancy fee");
						p.deductMoney(25);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,3);
					}
					if(commCard==4) {
						System.out.println("School fees pay $50");
						p.deductMoney(50);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,4);
					}
					if(commCard==5) {
						System.out.println("Hospital fees pay $50");
						p.deductMoney(50);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,5);
					}
					if(commCard==6) {
						System.out.println("Your life insurance matured. Collect $100");
						p.addMoney(100);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,6);
					}
					if(commCard==7) {
						System.out.println("Income tax refund.  Collect $20");
						p.addMoney(20);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,7);
					}
					if(commCard==8) {
						System.out.println("Christmas bonus collect $100");
						p.addMoney(100);
						System.out.println("Current balance: $"+p.getMoney());
						Community.add(9,8);
					}
					if(commCard==9) {
						System.out.println("Move to Go! Collect $200");
						p.changeCurrentSpace(0);
						p.addMoney(200);
					System.out.println("Current balance: $"+p.getMoney());
					Community.add(9,9);
					}
					if(commCard==10) {
						System.out.println("Go to jail.  Do not pass go or collect $200");
						p.goJail();
						p.setJail(true);
						Community.add(9,10);
					}
					
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
//					if (p.isBankrupt() == false) {
//						p.deductMoney(((ComChest) space).getTransaction());
//						System.out.println("Current balance: $" + p.getMoney());
//					}
				}
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
			} 
		}

//Go to jail
		if (space instanceof GoToJail&&p.getJail()==false) {
			System.out.println("Go to jail! Go directly to jail.  Do not pass go, do not collect $200");
			p.goJail();
			p.setJail(true);
		}

		//Properties
		// Properties
		Scanner scn = new Scanner(System.in);
		if (space instanceof Property&&p.getJail()==false) {
			Property pr = (Property) space;

			if (pr.getOwner() == p&&skipJail==false&&p.getJail()==false) {
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
				if (p != pr.getOwner()&&p.getJail()==false) {
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
		if (propList.get(p.getCurrentSpace()) instanceof Taxes&&p.getJail()==false) {
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
		if (space instanceof FreeParking&&p.getJail()==false) {
			System.out.println("Its your lucky day!  Free parking!");
		}
		
		

		// Switching turns
		if (playerTurn == numPlayers) {
			playerTurn = 0;
		} else {
			//if(doubles==false) {
			playerTurn++;
			//}
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
//return 5;  //Testing purposes
		return randomNum;
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
