package main;

import java.io.IOException;
import java.util.Scanner;

import classes.Text;
import classes.Word;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		input.close();
		Text txt = new Text(str);
		System.out.println("Words:");
        for (Word word : txt.getWords()){
        	System.out.println(word.toString());        	
        }
	}

}
