package interpreter;

import org.junit.Test;

public class IntervalTests {
	
	@Test
	public void simpleIntervalTest() {
		Interval i = new Interval(5,15);
		int [] got = i.getNumbersInInterval();
		int [] expected = {5,6,7,8,9,10,11,12,13,14,15};
		org.junit.Assert.assertArrayEquals(expected, got);
	}
	
	@Test
	public void negativeIntervalTest() {
		Interval i = new Interval(-8,-1);
		int [] got = i.getNumbersInInterval();
		int [] expected = {-8,-7,-6,-5,-4,-3,-2,-1};
		org.junit.Assert.assertArrayEquals(expected, got);
	}
	
	@Test
	public void singleNumberIntervalTest() {
		Interval i = new Interval(1,1);
		int [] got = i.getNumbersInInterval();
		int [] expected = {1};
		org.junit.Assert.assertArrayEquals(expected, got);
	}
	
	@Test (expected=Exception.class)
	public void wrongIntervalTest() {
		new Interval(5,4);
	}

}
