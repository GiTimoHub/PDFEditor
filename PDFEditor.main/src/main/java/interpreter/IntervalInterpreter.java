package interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntervalInterpreter {
	
	private String toInterpret;
	
	private static final String REGEX = "(([0-9]+)-(([0-9])|),)*([0-9]+)-(([0-9])|)";
	
	public IntervalInterpreter(String expression) {
		this.toInterpret = expression;
	}
	
	public int [] interpret() throws Exception {
		Interval [] intervals;
		int intervalSize = 0;
		/*Pattern pattern = Pattern.compile(REGEX);
		final Matcher m = pattern.matcher(toInterpret);
		if (m.matches()) {*/
			String [] intervalStrings = toInterpret.split(",");
			intervals = new Interval[intervalStrings.length];
			int i = 0;
			
			for (String s : intervalStrings) {
				intervals[i] = new Interval(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1]));
				intervalSize += intervals[i].getSize();
				i++;
			}
			
		/*} else {
			throw new Exception();
		}*/
		int [] r = new int [intervalSize];
		i = 0;		
		for (Interval interval : intervals) {
			int [] current = interval.getNumbersInInterval();
			for (int c = 0; c < current.length; c++) {
				r[i] = current[c];	
				i++;
			}
		}
		for (int j : r) {
			System.out.println("Hey " + j);
		}
		return r;
		
	}
}
