import java.util.ArrayList;
import java.util.Random;

public class Board {
	public int numPlayers;
	ArrayList<Property> propList = new ArrayList<Property>();
	ArrayList<Player> playerList = new ArrayList<Player>();

	public final int NUM_SPACES = 2;
	int playerTurn = 0;

	/**
	 * Moves player using result on rollDice
	 */

	public void move(Player p) {
		int movement = rollDice();
		System.out.println("Its " + p.getPlayerName() + "'s turn.  They roll a " + movement);
		int nextLocation = (p.getCurrentSpace() + movement) % NUM_SPACES;

//	
//	p.changeCurrentSpace(movement);
//	if(p.getCurrentSpace()>1) {
//		p.changeCurrentSpace(movement % 2);
//	}
		p.changeCurrentSpace(nextLocation);

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

//todo
	}

}
