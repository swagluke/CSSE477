package edu.rosehulman.broadcast.protocol;

import java.io.InputStream;
import java.io.OutputStream;

public interface IProtocol {
	public static final String PROTOCOL = "PROTCOL";
	public static final String CRLF = "CRLF";
	public static final String ECHO = "ECHO";
	public static final String QUIT = "QUIT";
	public static final String LIST = "LIST";
	public static final String LOGIN = "LOGIN";
	public static final String USERS = "USERS";
	public static final String CHAR_SET = "CHAR_SET";
	public static final String PORT = "PORT";
	
	public String get(String key);
	public IMessage receive(InputStream in) throws ProtocolException;
	public void send(IMessage m, OutputStream out) throws ProtocolException;
}
