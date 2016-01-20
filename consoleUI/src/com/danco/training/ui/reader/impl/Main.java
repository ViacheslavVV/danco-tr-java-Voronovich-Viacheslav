package com.danco.training.ui.reader.impl;

import java.util.Scanner;

import com.danco.training.ui.controller.Controller;
import com.danco.training.ui.display.impl.ConsoleEntityDisplayer;
import com.danco.training.ui.display.impl.ConsoleMenuDisplayer;
import com.training.danco.facade.impl.Facade;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Controller c = new Controller(new ConsoleMenuDisplayer(), new ConsoleEntityDisplayer(), Facade.getInstance(), new ConsoleReader(sc));
		c.run();
	}

}
