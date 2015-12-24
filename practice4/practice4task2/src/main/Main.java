package main;

import classes.Number;
import classes.NumberComparer;

public class Main {

	public static void main(String[] args) {
		
		Number first = new Number(Number.getRandomThreeDigitNumber());
		Number second = new Number(Number.getRandomThreeDigitNumber());
		Number third = new Number(Number.getRandomThreeDigitNumber());
		System.out.println("first ="+first+"\tsecond ="+second+"\tthird ="+third);
		NumberComparer numComparer = new NumberComparer();
		System.out.println("result ="+numComparer.compareNumbers(first, second, third));
	}

}
