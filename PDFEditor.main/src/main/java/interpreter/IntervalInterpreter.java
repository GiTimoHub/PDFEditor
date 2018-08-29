package interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Help to convert string expressions of intervals to an array of integers
 * @author Timo Birr
 *
 */
public class IntervalInterpreter {
	
	/**
	 * The regular expression that allows which expressions representing intervals are valid
	 */
	private static final String REGEX = "(([0-9]+)-(([0-9])|),)*([0-9]+)-(([0-9])|)";
	
	private String toInterpret;	

	
	/**
	 * Creates a new interval interpreter based on the given expression
	 * @param expression
	 */
	public IntervalInterpreter(String expression) {
		this.toInterpret = expression;
	}
	
	/**
	 * Returns the Expression that the IntervalInterpreter should interpret
	 * @return
	 */
	public String getExpression() {
		return toInterpret;
	}
	
	/**
	 * Concats the expression to concat to the current IntervalInterpreter
	 * @param toConcat
	 */
	public void concatExpression(String toConcat) {
		this.toInterpret += toConcat;
	}
	
	/**
	 * Returns an array of intergers that are produced based on the expression given in the constructor
	 * @return an array of integers
	 * @throws Exception
	 */
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
		return r;		
	}
	
	public Interval [] getIntervals() {
		Interval [] intervals;
		/*Pattern pattern = Pattern.compile(REGEX);
		final Matcher m = pattern.matcher(toInterpret);
		if (m.matches()) {*/
			String [] intervalStrings = toInterpret.split(",");
			intervals = new Interval[intervalStrings.length];
			int i = 0;
			
			for (String s : intervalStrings) {
				intervals[i] = new Interval(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1]));
				i++;
			}
			
		/*} else {
			throw new Exception();
		}*/
	return intervals;
	}
	
	/**
	 * Convinience method to get all numbers within interval
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	public static int [] interpret(String expression) throws Exception {
		return new IntervalInterpreter(expression).interpret();
	}
}
