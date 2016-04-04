package com.training.danco.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.facade.invoker.MethodInvoker;
import com.training.danco.message.Message;

public class ClientThread implements Runnable {

	private static final String CLIENTS_SOCKET = "Client's socket";

	public Thread thread;
	
	private Socket clientSocket;
	private boolean isAlive = true;
	private static final Logger LOGGER = LogManager.getLogger(ClientThread.class);
	
	public ClientThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.thread = new Thread(this, CLIENTS_SOCKET);
	}

	public void start(){
		thread.start();
	}
	
	@Override
	public void run() {
		Object data = null;
		Message message = null;
		ObjectInputStream in;
		ObjectOutputStream out;
		try {			
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			while (isAlive) {			
				data = in.readObject();
				if (data == null) {
					isAlive = false;
				} else {
					message = (Message) data;
					out.writeObject(MethodInvoker.invokeMethod(message.getText(), message.getData()));
					out.flush();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

}

