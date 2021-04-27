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
		
		//Create board
		Board b = new Board();
		
		//Add in players
		addPlayer(b);
	
		//Calls all properties
		AddProp(b);
		
		

		int x = 0;
		while (x < 10) {
			b.move(b.playerList.get(0));
			b.move(b.playerList.get(1));
			if(b.numPlayers>2) {
			b.move(b.playerList.get(2));
			if(b.numPlayers>3) {
				b.move(b.playerList.get(3));
				if(b.numPlayers>4) {
					b.move(b.playerList.get(4));
					if(b.numPlayers>5) {
						b.move(b.playerList.get(5));
						if(b.numPlayers>6) {
							b.move(b.playerList.get(6));
							if(b.numPlayers>7) {
								b.move(b.playerList.get(7));
							}
						}
					}
				}
			}
			}
			x++;
		}

		
	
	}

	public static  void AddProp(Board b) {
		Property Mediteranean=new RealProperty("Mediterranean ave",60, 30,2);
		Property Baltic=new RealProperty("Baltic ave", 60,30, 4 );
		Property Oriental=new RealProperty("Oriental ave", 100,50, 6 );
		Property Vermont= new RealProperty("Vermont ave", 100, 50, 6);
		Property Conneticut=new RealProperty("Conneticut ave", 120, 60, 8);
		Property StCharles=new RealProperty("St Charles Place", 140,70, 10);
		Property States=new RealProperty("States ave", 140, 70, 10);
		Property Virginia=new RealProperty("Virginia ave", 160, 80, 12);
		Property StJames=new RealProperty("St James Place", 180, 90, 14);
		Property Tenessee=new RealProperty("Tenessee ave", 180, 90, 14);
		Property NewYork=new RealProperty("New York ave", 200, 100, 16);
		Property Kentucky=new RealProperty("Kentucky ave", 220,110,18);
		Property Indianna=new RealProperty("Indianna ave", 220,110,18);
		Property Illinois=new RealProperty("Illinois ave", 240,120,20);
		Property Atlantic=new RealProperty("Atlantic ave", 260, 130, 22);
		Property Ventor=new RealProperty("Ventor ave", 260, 130, 22);
		Property MarvinGardens=new RealProperty("Marvin Gardens", 280, 140, 24);
		Property Pacific=new RealProperty("Pacific ave", 300, 150, 26);
		Property NorthCarolina=new RealProperty("North Carolina Ave", 300, 150, 26);
		Property Pennsylvania=new RealProperty("Pennsylvania ave", 320, 160, 28);
		Property Parkplace=new RealProperty("Park Place", 350, 175, 35);
		Property Boadwalk=new RealProperty("Boardwalk", 400,200,50 );

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
public static void addPlayer(Board b) {
	Scanner scan=new Scanner(System.in);
	System.out.println("Enter how many players are playing, from 2-8");
	b.numPlayers=scan.nextInt();
	System.out.println("Enter player 1's name");
	Player p1=new Player(scan.next());
	b.playerList.add(p1);
	System.out.println("Enter player 2's name");
	Player p2=new Player(scan.next());
	b.playerList.add(p2);
	if(b.numPlayers>=3) {
		System.out.println("Enter player 3's name");
		Player p3=new Player(scan.next());
		b.playerList.add(p3);
		if(b.numPlayers>=4) {
			System.out.println("Enter player 4's name");
			Player p4=new Player(scan.next());
			b.playerList.add(p4);
			if(b.numPlayers>=5) {
				System.out.println("Enter player 5's name");
				Player p5=new Player(scan.next());
				b.playerList.add(p5);
				if(b.numPlayers>=6) {
					System.out.println("Enter player 6's name");
					Player p6=new Player(scan.next());
					b.playerList.add(p6);
					if(b.numPlayers>=7) {
						System.out.println("Enter player 7's name");
						Player p7=new Player(scan.next());
						b.playerList.add(p7);
						if(b.numPlayers>=8) {
							System.out.println("Enter player 8's name");
							Player p8=new Player(scan.next());
							b.playerList.add(p8);
						}
					}
				}
			}
		}
	}
}
					}


