package com.danco.training.main;

import com.danco.training.ui.controller.Controller;
import com.danco.training.ui.menu.builder.MenuBuilder;

public class Main {

	public static void main(String[] args) {

		Controller c = new Controller();
		MenuBuilder mb = new MenuBuilder();
		c.run(mb.generateMenu());
	}

}
