import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 * Contains the bulk of the code.  Checks what each player rolls, and what to do when they move.  Also handles jail time and the Chance/Community chest cards
 * @author PLANKED20
 *
 */
public class Board {
	//test
	public int numPlayers;
	//Chance and community chest Queues
	Queue<Integer> Chance = new LinkedList<Integer>();
	Queue<Integer> Community = new LinkedList<Integer>();
	//Property and plater arrayLists
	ArrayList<Space> propList = new ArrayList<Space>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	//Morgage arrayLists
	ArrayList<Integer> optionList = new ArrayList<Integer>();
	ArrayList<Property> toUnMortgageList = new ArrayList<Property>();
	Queue<Player> inBid = new LinkedList<Player>();

	public final int NUM_SPACES;
	int playerTurn;
	boolean valid = false;

	public Board() {
		NUM_SPACES = 40;
		playerTurn = 0;
		numPlayers = playerList.size();
	}

	/*

	Moves player
	using result
	on rollDice*/

	public void move(Player p) {
		int startPoint = p.getCurrentSpace();
		int die1 = rollDice();
		int die2 = rollDice();
		int movement =die1 + die2;
		Boolean doubles = false;
		Boolean skipJail = false;
		p.setDouble(false);
//Checking for doubles
		if(die1!=die2) {
			doubles=false;
			p.resetDouble();
		}
		if (die1 == die2) {
			doubles = true;
			p.setDouble(true);
			p.incDouble();
		}
		//Doubles 3x=jail
		if (p.getDoubleCount() == 3) {
			System.out.println(p.getPlayerName()+", you rolled a " + die1 + " and a " + die2
					+ " which means you rolled doubles three times in a row, so you get a speeding ticket and go straight to jail, no Go or $200");
			p.setJail(true);
			p.changeCurrentSpace(0);
			p.resetDouble();
			p.setDouble(false);
			skipJail = true;

		}
//Options for getting out of jail
		if (p.getJail() && skipJail == false) {
			Scanner stan = new Scanner(System.in);
			System.out.println(p.getPlayerName() + ":\n");
			if (p.getJailRoll() < 3) {
				System.out
						.println("Type 1 to try to roll doubles to get out of jail.  You can only do this three times");
			}
			System.out.println("Type 2 to pay $50 to get out of jail");
			if (p.getJailFree() > 0) {
				System.out.println("Type 3 to use your get out of jail free card");
			}
			int ans = stan.nextInt();
			while (!valid) {
				try {
					if (ans == 1 || ans == 2 || ans == 3) {
						valid = true;
					}
					else {
						throw new Exception("Answer must be an integer between 1 and 3.  Please try again.");
					}
				} catch (Exception e){
					System.out.println("Answer must be an integer between 1 and 3.  Please try again.");
					ans = stan.nextInt();
				}
			}

			if (ans == 3) {
				System.out.println("Congrats! You are out of jail.");
				p.setJail(false);
				p.subJailFree();
				p.resetJailRoll();
			//	System.out.println(p.getJailRoll());
			}
			if (ans == 2) {
				System.out.println("Congrats!  Your out of jail.");
				int payment = 50;
				while (p.getMoney() < payment) {
					needToMortgage(p);
				}
				if (p.getMoney() < payment) {
					Bankrupt(p);
				}
				p.deductMoney(payment);
				System.out.println("Current balance: $" + p.getMoney());
				p.setJail(false);
				p.resetJailRoll();
			//	System.out.println(p.getJailRoll());
			}
			if (ans == 1 && p.getJailRoll() < 3) {

				if (doubles) {
					System.out.println("Nice work!  You rolled doubles so you are out of jail.");
					p.setJail(false);
					p.resetJailRoll();
				} else {
					p.incJailRoll();
					System.out.println(
							"Sorry!  You  rolled " + die1 + " and " + die2 + " not doubles.  Try again next turn.");
				}
			}

		}
//Regular turn
		if (p.getJail() == false) {
			System.out.println("Its " + p.getPlayerName() + "'s turn.  They roll a " + die1 + " and a " + die2
					+ " giving them a total move of " + movement + " and have a balance of $" + p.getMoney());
			p.changeCurrentSpace((movement));

			System.out.println(p.getPlayerName() + " is currently on " + propList.get(p.getCurrentSpace()).getName());

		}
		
		Space space = propList.get(p.getCurrentSpace());

		// Chance
		if (space instanceof Chance && p.getJail() == false) {
			int chanceCard = Chance.poll();
			if (chanceCard == 1) {
				System.out.println("Advance to Go, collect $200");
				p.addMoney(200);
				System.out.println("Current Balance: $" + p.getMoney());
				p.changeCurrentSpace(0);
				Chance.add(1);
			}
			if (chanceCard == 2) {
				System.out.println("Bank error!  You get $50");
				p.addMoney(50);
				System.out.println("Current balance: $" + p.getMoney());
				Chance.add(2);
			}
			if (chanceCard == 3) {
				System.out.println("Poor mans tax!  Pay $15");
				p.deductMoney(15);
				System.out.println("Current balance: $" + p.getMoney());
				Chance.add(3);
			}
			if (chanceCard == 4) {
				System.out.println("Your building loan has matured!  You get $150");
				p.addMoney(150);
				System.out.println("Current balance: $" + p.getMoney());
				Chance.add(4);
			}
			if (chanceCard == 5) {
				System.out.println("Go to St Charles Place!");
				p.changeCurrentSpace(11);
				Chance.add(5);
			}
			if (chanceCard == 6) {
				System.out.println("Go to Illinois ave!");
				p.changeCurrentSpace(24);
				Chance.add(6);
			}
			if (chanceCard == 7) {

				System.out.println("Go back three spaces!");
				p.changeCurrentSpace(p.getCurrentSpace() - 3);
				Chance.add(7);
			}
			if (chanceCard == 8) {

				System.out.println("Go to jail! Go directly to jail.  Dont pass go, dont collect $200");
				p.goJail();
				p.setJail(true);
				Chance.add(8);
			}
			if (chanceCard == 9) {

				System.out.println("You got a get out of jail free card!");
				p.addJailFree();
				Chance.add(9);
			}
			if (chanceCard == 10) {

				System.out.println("Go to reading railroad");
				p.changeCurrentSpace(5);
				Chance.add(10);
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

		}
		// Commnity chest
		if (space instanceof ComChest && p.getJail() == false) {
			int commCard = Community.poll();
			if (commCard == 1) {
				System.out.println("You got an inheritance!  Collect $100");
				p.addMoney(100);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add( 1);
			}
			if (commCard == 2) {
				System.out.println("You got 2nd in a beauty contest!  Collect $20");
				p.addMoney(20);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add(2);
			}
			if (commCard == 3) {
				System.out.println("Receive $25 consultancy fee");
				p.deductMoney(25);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add( 3);
			}
			if (commCard == 4) {
				System.out.println("School fees pay $50");
				p.deductMoney(50);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add(4);
			}
			if (commCard == 5) {
				System.out.println("Hospital fees pay $50");
				p.deductMoney(50);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add(5);
			}
			if (commCard == 6) {
				System.out.println("Your life insurance matured. Collect $100");
				p.addMoney(100);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add( 6);
			}
			if (commCard == 7) {
				System.out.println("Income tax refund.  Collect $20");
				p.addMoney(20);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add( 7);
			}
			if (commCard == 8) {
				System.out.println("Christmas bonus collect $100");
				p.addMoney(100);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add( 8);
			}
			if (commCard == 9) {
				System.out.println("Move to Go! Collect $200");
				p.changeCurrentSpace(0);
				p.addMoney(200);
				System.out.println("Current balance: $" + p.getMoney());
				Community.add(9);
			}
			if (commCard == 10) {
				System.out.println("Go to jail.  Do not pass go or collect $200");
				p.goJail();
				p.setJail(true);
				Community.add(10);
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

		}
		// Go
		if (startPoint > 0 && p.getCurrentSpace() < startPoint && p.getJail() == false) {
			System.out.println("You passed Go!  You collect $200");
			p.addMoney(200);
			System.out.println("Updated balance: $" + p.getMoney());
		}
		// Jail
		if (space instanceof Jail) {
			// If your just visiting
			if (p.getJail() == false) {
				System.out.println("Just visiting!");
			}
		}

		// Go to jail
		if (space instanceof GoToJail && p.getJail() == false) {
			System.out.println("Go to jail! Go directly to jail.  Do not pass go, do not collect $200");
			p.goJail();
			p.setJail(true);
		}

		// Landing on Properties
		Scanner scn = new Scanner(System.in);
		if (space instanceof Property && p.getJail() == false) {
			Property pr = (Property) space;
			if (pr.getOwner() == p && skipJail == false && p.getJail() == false) {
				System.out.println("You already own this property.  No rent today!");
	            int numPur= 0;
	            int numBlu = 0;
	            int numLBl = 0;
	            int numPin = 0;
	            int numOra = 0;
	            int numRed = 0;
	            int numYel = 0;
	            int numGre = 0;
				for (int i = 0; i < propList.size(); i++) {           
					if (propList.get(i) instanceof RealProperty) {        
						RealProperty prop = (RealProperty) propList.get(i);    
						if (prop.getOwner() == p && prop.getIsMorg() == false) {
							if(prop.getColor().equals("Purple")) {
								numPur++;
							}else if(prop.getColor().equals("Blue")) {
								numBlu++;
							}else if(prop.getColor().equals("Light Blue")) {
								numLBl++;
							}else if(prop.getColor().equals("Pink")) {
								numPin++;
							}else if(prop.getColor().equals("Orange")) {
								numOra++;
							}else if(prop.getColor().equals("Red")) {
								numRed++;
							}else if(prop.getColor().equals("Yellow")) {
								numYel++;
							}else if(prop.getColor().equals("Green")) {
								numGre++;
							}
		                  }
		                }
					}
	           
				boolean Pur = false;
				boolean Blu = false;
				boolean LBl = false;
				boolean Pin = false;
				boolean Ora = false;
				boolean Red = false;
	            boolean Yel = false;
	            boolean Gre = false;
	            
	            
				if(numPur == 2) {
					Pur = true;	
				}else if(numBlu == 2) {
					Blu = true;
				}else if(numLBl == 3) {
					LBl = true;
				}else if(numPin == 3) {
					Pin = true;
				}else if(numOra == 3) {
					Ora = true;
				}else if(numRed == 3) {
					Red = true;
				}else if(numYel == 3) {
					Yel = true;
				}else if(numGre == 3) {
					Gre = true;
				}
				
				if(Pur || Blu || LBl || Pin || Ora || Red || Yel || Gre) {
					System.out.println("You have enough of the same kind of properties to build houses.");
					if(Pur) { 
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Purple")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 Baltic or 2 Mediteranean?(1 or 2) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(1);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(3);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
			
					}else if(Blu) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Blue")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 Parkplace or 2 Boardwalk?(1 or 2) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(37);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(39);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}else if(LBl) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Light Blue")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 Oriental or 2 Vermont or 3 Conneticut?(1 or 2 or 3) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(6);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(8);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 3) {
										RealProperty prop = (RealProperty) propList.get(9);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}else if(Pin) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Pink")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 StCharles or 2 States or 3 Virginia?(1 or 2 or 3) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(11);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(13);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 3) {
										RealProperty prop = (RealProperty) propList.get(14);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}else if(Ora) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Orange")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 StJames or 2 Tenessee or 3 New York?(1 or 2 or 3) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(16);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(18);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 3) {
										RealProperty prop = (RealProperty) propList.get(19);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}else if(Red) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Red")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 Kentucky or 2 Indianna or 3 Illinois?(1 or 2 or 3) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(21);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(23);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 3) {
										RealProperty prop = (RealProperty) propList.get(24);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}else if(Yel) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Yellow")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 Alantic or 2 Ventor or 3 MarvinGardens?(1 or 2 or 3) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(26);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(27);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 3) {
										RealProperty prop = (RealProperty) propList.get(29);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}else if(Gre) {
						int cost = 0;
						for (int i = 0; i < propList.size(); i++) {           
							if (propList.get(i) instanceof RealProperty) {        
								RealProperty prop = (RealProperty) propList.get(i);  
								if (prop.getOwner() == p ) {
									if(prop.getColor().equals("Green")) {
										System.out.println("Property: " + prop.getName());
										cost = prop.getCost();
									}
								}
							}
						}
						if(p.getMoney() > cost) {
							System.out.println("Do you want to build any houses on the following property? (Y/N)");
							String response = scn.next();
							if (response.equalsIgnoreCase("Y")) {
								boolean yes = true;
								while(yes) {
									Scanner sc = new Scanner(System.in);
									System.out.println("Do you want a house on 1 Pacific or 2 NorthCarolina or 3 Pennsylvania?(1 or 2 or 3) ");
									if(sc.nextInt() == 1) {  
										RealProperty prop = (RealProperty) propList.get(31);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 2) {
										RealProperty prop = (RealProperty) propList.get(32);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}else if(sc.nextInt() == 3) {
										RealProperty prop = (RealProperty) propList.get(34);
										prop.setHouseCount(prop.getHouseCount() + 1);
										p.deductMoney(cost);
									}
									System.out.println("Do you want to buy another house on this property group?(Y/N)");
									response = sc.next();
									if (response.equalsIgnoreCase("Y")) {
										
									}else if (response.equalsIgnoreCase("N")) {
										yes = false;
									}
								}
								
							}else if(response.equalsIgnoreCase("N")) {}
						}
					}
				}
				
				
				
			} else if (pr.getIsMorg()) {
				System.out.println("This property is mortgaged.  You pay nothing!");
			}
			if (pr.getOwner() == null) {
				if (p.getMoney() >= pr.getCost()) {
					System.out.println("Do you want to buy " + pr.getName() + " for $" + pr.getCost() + "?  Y/N");
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
						// AUCTIONING
			            for (int i = 0; i <= playerList.size() - 1; i++) {
			              inBid.add(playerList.get(i));
			            }
			//System.out.println(playerList.size());
			            System.out.println("No sale.  Begin auction");
			            Boolean sold = false;
			            int player = 0;
			            int bid = 0;
			            int minbid = 0;
			            while (sold == false) {
			              System.out.println("Num of players bidding: "+inBid.size() );
			              minbid = bid;
			              minbid++;

			              Player currentBidder = inBid.poll();

			              System.out.println(
			                  currentBidder.getPlayerName() + ": Type Y to bid or type N to stop bidding");
			              String q = scn.next();
			              while (!(q.equalsIgnoreCase("Y") || q.equalsIgnoreCase("N"))) {
			                System.out.println("Invalid input.  Type Y to bid or N to stop bidding.");
			                q = scn.next();
			              }
			              if (q.equalsIgnoreCase("Y")) {
			                System.out
			                    .println("Enter the amount of money that you want to bid. Current minimum bid: "
			                        + minbid);
			                bid = scn.nextInt();
			                while (bid < minbid) {
			                  System.out.println("Invalid bid. Please try again and bid at least " + minbid);
			                  bid = scn.nextInt();

			                }
			                inBid.add(currentBidder);

			              } else if(q.equalsIgnoreCase("N")){
			                System.out.println(currentBidder.getPlayerName() + " exited the auction");
			                inBid.remove(currentBidder);
			              }


			              if (inBid.size() == 1) {
			Player winner=inBid.poll();
			                System.out.println(winner.getPlayerName() + " won "
			                    + propList.get(p.getCurrentSpace()).getName() + " for $" + bid);
			                System.out.println("Current balance: $" + winner.getMoney());
			                pr.changeOwner(winner);
			                winner.deductMoney(bid);
			                sold = true;
			              } 
			              //Add him back in
			              

			              if (player == inBid.size()-1) {
			                player = 0;
			              } else {
			                player++;

			              }
			            }
						System.out.println("No sale");
					}
				}

				else {
					System.out.println("You cannot buy this property because you don't have enough money.");

				}
			} else if (pr.getIsMorg() == false) {
				if (p != pr.getOwner() && p.getJail() == false) {
					int rent = pr.getRent();
					if (pr instanceof Railroad) {

						int n = ((Player) pr.getOwner()).getRailCount();
						rent = (int) (rent * (Math.pow(2, n - 1)));

					} else if (pr instanceof Utility) {

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
		if (propList.get(p.getCurrentSpace()) instanceof Taxes && p.getJail() == false) {
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
		if (space instanceof FreeParking && p.getJail() == false) {
			System.out.println("Its your lucky day!  Free parking!");
		}

		if (toUnMortgageList.size() > 0) {
			Scanner scnr1 = new Scanner(System.in);

			System.out.println(
					"You currently have the following properties mortgaged.  Would you like to unmortgage any of them for the price shown?");
			int count = 1;
			for (Property q : toUnMortgageList) {
				if (q.getOwner() == p) {
					System.out.println(
							count + ". " + q.getName() + " has a unmortgage cost of $" + (int) (q.getMorgage() * 1.10));
				}
				count++;
			}
			System.out.println("Do you want to unmortgage any of these properties? Y/N");
			String response = scnr1.next();
			if (response.equalsIgnoreCase("Y")) {
				boolean b = true;
				while (b) {
					System.out.println("Enter the number of the property you wish to unmortgage.");
					int response1 = scnr1.nextInt();
					Property q = toUnMortgageList.get(response1 - 1);
					if (p.getMoney() >= q.getMorgage() * 1.10) {
						q.setMorg(false);
						p.deductMoney((int) (q.getMorgage() * 1.10));
						toUnMortgageList.remove(q);
						System.out.println("Do you want to unmortgage any other properties? Y/N");
						String response2 = scnr1.next();
						if (response2.equalsIgnoreCase("Y")) {
							System.out.println(
									"You currently have the following properties mortgaged.  Would you like to unmortgage any of them for the price shown?");
							count = 1;
							for (Property q1 : toUnMortgageList) {
								System.out.println(count + ". " + q1.getName() + " has a unmortgage cost of $"
										+ (int) q1.getMorgage() * 1.10);
								count++;
							}
							System.out.println("Do you want to unmortgage any of these properties? Y/N");
							String response3 = scnr1.next();
							if (response3.equalsIgnoreCase("Y")) {

							}
						}
						if (response2.equalsIgnoreCase("N")) {
							b = false;
						}
					}
					if (p.getMoney() < q.getMorgage() * 1.10) {
						System.out.println(
								"You do not have the money to unmortgage this property.  Please select a different property.");
						System.out.println(
								"You currently have the following properties mortgaged.  Would you like to unmortgage any of them for the price shown?");
						count = 1;

						for (Property q2 : toUnMortgageList) {
							System.out.println(count + ". " + q2.getName() + " has a unmortgage cost of $"
									+ (int) q2.getMorgage() * 1.10);
							count++;
						}
						System.out.println("Do you want to unmortgage any of these properties? Y/N");
						String response3 = scnr1.next();
						if (response3.equalsIgnoreCase("Y")) {

						}
						if (response3.equalsIgnoreCase("N")) {
							System.out.println("Okay, your turn is over.");
							break;
						}
					}
				}
			} else if (response.equalsIgnoreCase("N")) {
				System.out.println("Okay, your turn is over.");
			}
		}
//Doubles 
		if (p.getDouble() && p.getDoubleCount() < 3&p.getJail()==false) {
		System.out.println("You rolled doubles so you get to move again!");
	}
		// Switching turns
		if (playerTurn == numPlayers) {
			playerTurn = 0;
		} else {
			// if(doubles==false) {
			playerTurn++;
			// }
		}

	}

/**
 * Returns a random number between 1 and 6 to simulate die roll
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
/**
 * Method to call when player lands on property to check if its morgaged or not
 * @param p
 * @return
 */
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
/**
 * Method to morgage a property
 * @param p
 */
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
            toUnMortgageList.add(pr2);
            optionList.remove(choice - 1);


          }
/**
 * Method to handle bankruptcy
 * @param p
 */
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