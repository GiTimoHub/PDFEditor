package interpreter;


/**
 * Represents an interval of integers.
 * @author Timo Birr
 *
 */
public class Interval {
	private int start;
	private int end;
	
	/**
	 * Creates a new interval between start and end
	 * @param start the first integer within the interval
	 * @param end the last integer within the interval
	 */
	public Interval(int start, int end) {
		if (end < start) {
			throw new IllegalArgumentException("end has to be greater or equal than start");
		}
		this.start = start;
		this.end = end;		
	}
	
	/**
	 * Calculates all numbers within the given interval
	 * 
	 * @return an array of all integers within interval
	 */
	public int [] getNumbersInInterval() {
		int [] r = new int[getSize()];
		int c = 0;
		for (int i = start; i <= end; i++) {
			r[c] = i;
			c++;			
		}
		return r;
	}
	
	/**
	 * Returns the size of the interval
	 * Size means the amount of integers within the interval
	 * For example the inteval [5,10] contains 5,6,7,8,9,10 so it has the size 6
	 * @return the intervals size
	 */
	public int getSize() {
		return 1 + end - start ;
	}
	
	/**
	 * Convinience Method to get all Numbers within a given Interval
	 * @param start of the interval
	 * @param end of the Interval
	 * @return all integers within the interval
	 */
	public static int []  getNumbersWithin(int start, int end) {
		return new Interval(start,end).getNumbersInInterval();
	}
	
	/**
	 * Convinience Method to get an open interval (not including start and end) within the given boundaries
	 * @param start
	 * @param end
	 * @return
	 */
	public static Interval getOpenInterval(int start, int end) {
		return new Interval(start + 1, end - 1);
	}
}
