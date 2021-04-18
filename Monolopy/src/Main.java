import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		File prop = new File("Properties");
		Scanner f = new Scanner(prop);
		Board b = new Board();
		
		while(f.hasNext()) {
			String line = f.nextLine();
			Scanner sc = new Scanner(line);
			while(sc.hasNext()) {
				sc.
			}
			
			
			RealProperty p = new RealProperty(name,cost,morg);
			b.propList.add(p);
		}
		System.out.println(b.propList);
	}

}
