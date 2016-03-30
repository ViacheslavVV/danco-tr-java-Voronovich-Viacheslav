package com.danco.training.ui.io;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class SocketIOHandler implements IMessageHandler{

	private static final Logger LOGGER = LogManager.getLogger(SocketIOHandler.class);
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public SocketIOHandler(ObjectInputStream in, ObjectOutputStream out){
		try {
			this.in = in;
			this.out = out;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
	}
	
	@Override
	public Object sendMessage(Message message){
        Object response = null;
		try {
			out.writeObject(message);
			out.flush();
			response = in.readObject();			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}			
		return response;		
	}

	@Override
	public void close() {
		try {
			out.close();
			in.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
