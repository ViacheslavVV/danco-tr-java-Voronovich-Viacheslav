package classes;

import java.util.Random;

public class Number {
	
	static private Random rnd = new Random();
	
	static public final int MAX_NUMBER = 1000;
	static public final int MIN_THREE_DIGIT_NUMBER = 100;
	
	private int value;

	public Number(int value) {
		this.value = value;
	}

	static public int getRandomThreeDigitNumber()
	{
		return MIN_THREE_DIGIT_NUMBER+rnd.nextInt(MAX_NUMBER-MIN_THREE_DIGIT_NUMBER);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.value);
	}
}
