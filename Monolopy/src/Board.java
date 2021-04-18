import java.util.ArrayList;
import java.util.Random;
public class Board extends Space{
public int numPlayers;
protected ArrayList<Property> propList;
int playerTurn=1;

/**
 * Moves player using result on rollDice
 */
public void move() {
    System.out.println("Its Player "+playerTurn+"'s turn.  They roll a "+rollDice());
    
    
    
    
    
    
    
    if(playerTurn==numPlayers) {
        playerTurn=0;
    }else {
    playerTurn++;
    }
    
}
/**
 * returns random number between 2 and 12
 * @return
 */
    public int rollDice() {
Random randy=new Random();
        
int randomNum = randy.nextInt((12 -2) + 1) + 2;
        return randomNum;
        

    }
    
    
}
