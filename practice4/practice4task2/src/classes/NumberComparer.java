package classes;

public class NumberComparer {
	
	public int compareNumbers(Number first, Number second, Number third) // returns the difference between concatinated 1st and 2nd, and 3rd
	{
		return Integer.parseInt(first.toString()+second.toString())-third.getValue();
	}

}
