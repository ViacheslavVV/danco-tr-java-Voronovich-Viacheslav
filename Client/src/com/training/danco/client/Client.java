package com.training.danco.client;

import java.net.InetAddress;
import java.net.Socket;

import com.danco.training.ui.controller.Controller;
import com.danco.training.ui.io.SocketIOHandler;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.builder.MenuBuilder;
import com.training.danco.message.handler.IMessageHandler;

public class Client {

	private static final int STANDART_PORT = 6265;
	private static final String INET_ADDRESS = "127.0.0.1";

	public static void main(String[] args) throws Exception {
		int serverPort = STANDART_PORT; 
		String address = INET_ADDRESS;
		Socket clientSocket = new Socket(InetAddress.getByName(address), serverPort);
		MenuBuilder menuBuilder = new MenuBuilder();
		IMessageHandler messageHandler = new SocketIOHandler(clientSocket);
		Menu mainMenu = menuBuilder.generateMenu(messageHandler);
		Controller controller = new Controller();
		controller.run(mainMenu);
	}
}