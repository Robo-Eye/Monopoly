/**
 * runs all other methods within game loop
 * 
 * @author PLANKED20
 *
 */
//See if this works...
//Applying patch...

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// Test Change 1:48 4/24/21
		Board b = new Board();
		b.numPlayers = 4;

		Property boardwalk = new Property("Boardwalk", 400, 200, 100);
		Property parkplace = new Property("Park Place", 360, 180, 100);
		Property stJames = new Property("St James Place", 200, 100, 100);
		Property RRailroad = new Property("Reading Railroad", 400, 200, 100);
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
		b.propList.add(boardwalk);
		b.propList.add(parkplace);
		b.propList.add(stJames);
		b.propList.add(RRailroad);
		int x = 0;
		while (x < 10) {
			b.move(jeff);
			b.move(steve);
			b.move(fred);
			b.move(joe);
			x++;
		}

		// Testing move function

		// Testing addmoney, deduct money, and current balance

		// Player p1 = new Player("Tom");
		// p1.getMoney();
		// p1.addMoney(500);
		// p1.getMoney();
		// p1.deductMoney(300);
		// p1.getMoney();
		// // p1.getCurrentSpace();
		// jeff.changeCurrentSpace(0);
		// jeff.getCurrentSpace();
		// RealProperty property1 = new RealProperty("Board walk", 2000, 100);
		// RealProperty property2 = new RealProperty("Park place", 2000, 100);
		// b.propList.add(property1);
		// b.propList.add(property2);

		// Testing File Read

		// File prop = new File("properties.txt");
		// Scanner f = new Scanner(prop);
		//
		// while(f.hasNext()) {
		// String line = f.nextLine();
		// Scanner sc = new Scanner(line);
		// String name = "";
		// int cost = 0;
		// int morg = 0;
		//
		// while(sc.hasNext()) {
		// name = sc.next();
		// cost = Integer.parseInt(sc.next());
		// morg = Integer.parseInt(sc.next());
		// }
		// RealProperty p = new RealProperty(name,cost,morg);
		// b.propList.add(p);
		// }
		// System.out.println(b.propList); //Loop needs fixed as it prints out locations
		// and not actual data
	}

	public static  void AddProp(Board b) {
		Property Mediteranean=new Property("Mediterranean ave",60, 30,2  );
		Property Baltic=new Property("Baltic ave", 60,30, 4 );
		Property Oriental=new Property("Oriental ave", 100,50, 6 );
		Property Vermont= new Property("Vermont ave", 100, 50, 6);
		Property Conneticut=new Property("Conneticut ave", 120, 60, 8);
		Property StCharles=new Property("St Charles Place", 140,70, 10);
		Property States=new Property("States ave", 140, 70, 10);
		Property Virginia=new Property("Virginia ave", 160, 80, 12);
		Property StJames=new Property("St James Place", 180, 90, 14);
		Property Tenessee=new Property("Tenessee ave", 180, 90, 14);
		Property NewYork=new Property("New York ave", 200, 100, 16);
		Property Kentucky=new Property("Kentucky ave", 220,110,18  );
		Property Indianna=new Property("Indianna ave", 220,110,18  );
		Property Illinois=new Property("Illinois ave", 240,120,20  );
		Property Atlantic=new Property("Atlantic ave", 260, 130, 22);
		Property Ventor=new Property("Ventor ave", 260, 130, 22);
		Property MarvinGardens=new Property("Marvin Gardens", 280, 140, 24);
		Property Pacific=new Property("Pacific ave", 300, 150, 26);
		Property NorthCarolina=new Property("North Carolina Ave", 300, 150, 26);
		Property Pennsylvania=new Property("Pennsylvania ave", 320, 160, 28);
		Property Parkplace=new Property("Park Place", 350, 175, 35);
		Property Boadwalk=new Property("Boardwalk", 400,200,50 );

		b.propList.add(Mediteranean);
		b.propList.add(Baltic);
		b.propList.add(Oriental);
		b.propList.add(Vermont);
		b.propList.add(Conneticut);
		b.propList.add(StCharles);
		b.propList.add(States);
		b.propList.add(Virginia);
		b.propList.add(StJames);
		b.propList.add(Tenessee);
		b.propList.add(NewYork);
		b.propList.add(Kentucky);
		b.propList.add(Indianna);
		b.propList.add(Illinois);
		b.propList.add(Atlantic);
		b.propList.add(Ventor);
		b.propList.add(MarvinGardens);
		b.propList.add(Pacific);
		b.propList.add(NorthCarolina);
		b.propList.add(Pennsylvania);
		b.propList.add(Parkplace);
		b.propList.add(Boadwalk);

		}
}
