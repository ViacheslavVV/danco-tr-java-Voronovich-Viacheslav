package com.training.danco.message.handler;

import java.io.Closeable;

import com.training.danco.message.Message;

public interface IMessageHandler extends Closeable {

	Object sendMessage(Message message);
}
