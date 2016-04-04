package com.training.danco.server;

import java.net.ServerSocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

	private static final int STANDART_PORT = 6265;
	private static final String CLIENT_ACCAPTED = "Client accapted!";
	private static final String WAITING_FOR_A_CLIENT = "Waiting for a client...";
	private static final Logger LOGGER = LogManager.getLogger(Server.class);
	private static boolean isWorking = true;

	public static void main(String[] args) {
		int port = STANDART_PORT;
		ServerSocket serverSocket = null;
		ClientThread clientThread = null;

		try {

			serverSocket = new ServerSocket(port);

			while (isWorking) {
				System.out.println(WAITING_FOR_A_CLIENT);
				clientThread = new ClientThread(serverSocket.accept());
				System.out.println(CLIENT_ACCAPTED);

				clientThread.start();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {

			try {
				serverSocket.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}

		}
	}

}
