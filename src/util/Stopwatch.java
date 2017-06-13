package util;

public class Stopwatch {

	private long	start;

	public Stopwatch() {
		start = System.currentTimeMillis();
	}

	public long getTimeSpent() {
		return System.currentTimeMillis() - start;
	}
}
