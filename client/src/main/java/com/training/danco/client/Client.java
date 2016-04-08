package com.training.danco.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.training.danco.ui.controller.Controller;
import com.training.danco.ui.io.SocketIOHandler;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.builder.MenuBuilder;
import com.training.danco.message.handler.IMessageHandler;

public class Client {

	private static final Integer STANDART_PORT = 6265;
	private static final String INET_ADDRESS = "127.0.0.1";

	public static void main(String[] args) throws Exception {
		Integer serverPort = STANDART_PORT; 
		String address = INET_ADDRESS;
		Socket clientSocket = new Socket(InetAddress.getByName(address), serverPort);
		InputStream in = clientSocket.getInputStream();
		OutputStream s =clientSocket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(s);
		ObjectInputStream ois = new ObjectInputStream(in);
		MenuBuilder menuBuilder = new MenuBuilder();
		IMessageHandler messageHandler = new SocketIOHandler(ois,oos);
		Menu mainMenu = menuBuilder.generateMenu(messageHandler);
		Controller controller = new Controller();
		controller.run(mainMenu);
		clientSocket.close();
	}
}