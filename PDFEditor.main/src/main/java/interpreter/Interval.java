package interpreter;

public class Interval {
	private int start;
	private int end;
	
	public Interval(int start, int end) {
		if (end < start) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;		
	}
	
	public int [] getNumbersInInterval() {
		int [] r = new int[getSize()];
		int c = 0;
		for (int i = start; i <= end; i++) {
			r[c] = i;
			c++;			
		}
		return r;
	}
	
	public int getSize() {
		return 1 + end - start ;
	}
}
