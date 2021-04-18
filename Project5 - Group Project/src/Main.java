/**
 * runs all other methods within game loop
 * 
 * @author PLANKED20
 *
 */
//See if this works...
//Applying patch...
public class Main {
	public static void main(String[] args) {

		Board b = new Board();
		b.numPlayers = 4;
		

		// for (int i = 0; i < 10; i++) {
		// System.out.println(b.rollDice());
		// }
		Player jeff = new Player("Jeff");
		Player steve = new Player("Steve");
		Player fred = new Player("fred");
		Player joe = new Player("Joe");
		b.playerList.add(jeff);
		b.playerList.add(steve);
		b.playerList.add(fred);
		b.playerList.add(joe);
		b.move(jeff);
		b.move(steve);
		b.move(fred);
		b.move(joe);
		System.out.println(jeff.getCurrentSpace());
		System.out.println(steve.getCurrentSpace());
		System.out.println(fred.getCurrentSpace());
		System.out.println(joe.getCurrentSpace());

//Testing move function

		// Testing addmoney, deduct money, and current balance

//		Player p1 = new Player("Tom");
//		p1.getMoney();
//		p1.addMoney(500);
//		p1.getMoney();
//		p1.deductMoney(300);
//		p1.getMoney();
//		// p1.getCurrentSpace();
//		jeff.changeCurrentSpace(0);
//		jeff.getCurrentSpace();
//		RealProperty property1 = new RealProperty("Board walk", 2000, 100);
//		RealProperty property2 = new RealProperty("Park place", 2000, 100);
//		b.propList.add(property1);
//		b.propList.add(property2);

	}

}
