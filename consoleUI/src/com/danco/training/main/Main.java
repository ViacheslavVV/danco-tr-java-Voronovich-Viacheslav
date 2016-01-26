package com.danco.training.main;

import com.danco.training.ui.controller.Controller;
import com.danco.training.ui.menu.builder.MenuBuilder;
import com.training.danco.facade.impl.Facade;

public class Main {

	public static void main(String[] args) {

		Controller c = new Controller();
		MenuBuilder mb = new MenuBuilder(Facade.getInstance());
		c.run(mb.generateMenu());
	}

}
