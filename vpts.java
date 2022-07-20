import java.util.Scanner;
import java.io.*;
import java.lang.*;
public class vpts {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("ticks.csv"));
		double pts = 0;
		s.nextLine(); // skip first line; headers

		// handle each row
		while(s.hasNextLine()) {
			// split each row by commas
			String[] cols = s.nextLine().split(",");
			
			// split third col by spaces
			// gets rid of safety ratings after grades
			String v = cols[2].split(" ")[0];

			// get rid of quotation marks around grades that have safety ratings
			if(v.charAt(0) == '\"')
				v = v.substring(1,v.length());

			// grade resembles "VX"
			if(v.length() == 2)
				pts += Double.parseDouble(Character.toString(v.charAt(1)));
			
			// grade resembles "VX+"
			else if (v.length() == 3) {
				if(v.charAt(2) == '+')
					pts += Double.parseDouble(Character.toString(v.charAt(1))) + 0.5;
				else
					pts += Double.parseDouble(Character.toString(v.charAt(1))) - 0.5;

			}

			// grade resembles "VX-Y"
			else
				pts += Double.parseDouble(Character.toString(v.charAt(1))) + 0.5;
		}

		System.out.println("total v-points: "+pts);
	}
}
