import java.util.Scanner;
import java.io.*;
import java.lang.*;
public class vpts {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File(args[0]));
		double pts = 0;
		s.nextLine(); // skip first line; headers

		// handle each row
		while(s.hasNextLine()) {
			// split each row by tabs
			String[] cols = s.nextLine().split("\t");
			
			// split third col by spaces
			// gets rid of safety ratings after grades
			String v = cols[2].split(" ")[0];

			// get rid of quotation marks around grades that have safety ratings
			if(v.charAt(0) == '\"')
				v = v.substring(1,v.length());

			// skip "V-easy" and non-V-grades
			if(v.charAt(1) == '-' || v.charAt(0) != 'V')
				continue;

			// grade resembles "VX"
			if(v.length() == 2) {
				// V0 gets quarter-point
				double val = Double.parseDouble(Character.toString(v.charAt(1)));
				pts += val == 0 ? 0.25 : val;
			}

			// grade resembles "VX+"
			else if (v.length() == 3) {
				if(v.charAt(2) == '+')
					pts += Double.parseDouble(Character.toString(v.charAt(1))) + 0.5;
				else {
					// so that V0- does not subtract points
					double val = Double.parseDouble(Character.toString(v.charAt(1))) - 0.5;
					pts += val < 0 ? 0 : val;
				}
			}

			// grade resembles "VX-Y"
			else
				pts += Double.parseDouble(Character.toString(v.charAt(1))) + 0.5;
		}

		System.out.println("total v-points: "+pts);
	}
}
