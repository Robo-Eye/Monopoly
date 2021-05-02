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
		
		//Create board
		Board b = new Board();
		
		//Add in players
		addPlayer(b);
	
		//Puts in cards
		addCards(b);
		//Calls all properties
		AddProp(b);
		//Gameloop
		//game seems to stop after 4 properties are bought
		while (b.playerList.size() > 1) {
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
		}
		System.out.println("Game over.  " + b.playerList.get(0).getPlayerName() + " wins.");

		
	
	}

	  
	public static void addCards(Board b) {
		
		Random rand = new Random();
        int a[]= {1, 2, 3, 4, 5, 6, 7, 8, 9,10};
        for (int i = 0; i < 10; i++)
        {
            // Random for remaining positions.
            int r = i + rand.nextInt(10 - i);
              
             //swapping the elements
             int temp = a[r];
             a[r] = a[i];
             a[i] = temp;
               
        }
    for(int i=0; i<10; i++) {
    	b.Chance.add(a[i]);
    	b.Community.add(a[i]);
    }
		
		
	//	System.out.println(b.Chance);
      //  System.out.println(b.Community);
	}
	public static  void AddProp(Board b) {
		
		
		//Regular properties
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

		//Railroads
		Railroad Reading=new Railroad("Reading Railroad", 200, 100);
		Railroad PennsylvaniaRR=new Railroad("Pennsylvania Railroad", 200, 100);
		Railroad Bo=new Railroad("B&O Railroad", 200, 100);
		Railroad Shortline=new Railroad("Short Line Railroad", 200, 100);
		
		//Community Chest
		ComChest comChest1=new ComChest(50);
		ComChest comChest2=new ComChest(100);
		ComChest comChest3=new ComChest(-50);
		//Chance
		Chance chance1=new Chance(-50);
		Chance chance2=new Chance(100);
		Chance chance3=new Chance(50);
		//Utilities
		Utility Waterworks=new Utility("Water Works", 150, 75);
		Utility Electric=new Utility("Electric Company", 150, 75);
		
		//go
		Go go=new Go();
		//Free parking
		FreeParking FreeParking=new FreeParking();
		
		//Taxes 
		Taxes income=new Taxes("Income tax", 200);
		Taxes luxury=new Taxes("Luxury tax", 100);
		
		//Jail
		Jail jail=new Jail();
		
		//Go to Jail
		GoToJail goToJail=new GoToJail();
		
		//IMPORTANT  add in order
		b.propList.add(go);
		b.propList.add(Mediteranean);
		b.propList.add(comChest1);
		b.propList.add(Baltic);
		b.propList.add(income);
		b.propList.add(Reading);
		b.propList.add(Oriental);
		b.propList.add(chance1);
		b.propList.add(Vermont);
		b.propList.add(Conneticut);
		b.propList.add(jail);
		b.propList.add(StCharles);
		b.propList.add(Electric);
		b.propList.add(States);
		b.propList.add(Virginia);
		b.propList.add(PennsylvaniaRR);
		b.propList.add(StJames);
		b.propList.add(comChest2);
		b.propList.add(Tenessee);
		b.propList.add(NewYork);
		b.propList.add(FreeParking);
		b.propList.add(Kentucky);
		b.propList.add(chance2);
		b.propList.add(Indianna);
		b.propList.add(Illinois);
		b.propList.add(Bo);
		b.propList.add(Atlantic);
		b.propList.add(Ventor);
		b.propList.add(Waterworks);
		b.propList.add(MarvinGardens);
		b.propList.add(goToJail);
		b.propList.add(Pacific);
		b.propList.add(NorthCarolina);
		b.propList.add(comChest3);
		b.propList.add(Pennsylvania);
		b.propList.add(Shortline);
		b.propList.add(chance3);
		b.propList.add(Parkplace);
		b.propList.add(luxury);
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


