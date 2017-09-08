package edu.rosehulman.broadcast.protocol.impl_v_1_0;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import edu.rosehulman.broadcast.protocol.IMessage;
import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.ProtocolException;

public class Protocol implements IProtocol {
	private static Protocol instance = new Protocol();

	public static Protocol getInstance() {
		return instance;
	}
	
	private Map<String, String> properties;
	
	private Protocol() {
		properties = new HashMap<>();
		properties.put(IProtocol.PROTOCOL, "BCAST1.0");
		properties.put(IProtocol.PORT, "9000");
		properties.put(IProtocol.CRLF, "\r\n");
		properties.put(IProtocol.ECHO, "echo");
		properties.put(IProtocol.LIST, "list");
		properties.put(IProtocol.LOGIN, "login");
		properties.put(IProtocol.USERS, "users");
		properties.put(IProtocol.QUIT, "quit");
		properties.put(IProtocol.CHAR_SET, "UTF-8");
	}
	
	@Override
	public String get(String key) {
		return this.properties.get(key);
	}
	
	@Override
	public IMessage receive(InputStream in) throws ProtocolException {
		try {
			int c = in.read();
			StringBuilder builder = new StringBuilder();
			while(c != '\n' && c != -1) {
				builder.append((char)c);
				c = in.read();
			}
			
			String statusLine = builder.toString();
			StringTokenizer tokenizer = new StringTokenizer(statusLine);

			String protocol = tokenizer.nextToken(" ").trim();
			if(!instance.get(IProtocol.PROTOCOL).equalsIgnoreCase(protocol)) {
				throw new ProtocolException("Protocol version mismatch!");
			}
			
			String command = tokenizer.nextToken(" ").trim();
			String strSize = tokenizer.nextToken("\r").trim();
			int size = Integer.parseInt(strSize);

			byte[] buffer = new byte[size];
			int readSize = 0;
			while(readSize < size) {
				readSize += in.read(buffer, readSize, size);
			}
			
			IMessage m = null;
			if (command.equalsIgnoreCase(instance.get(IProtocol.QUIT))) {
				m = new QuitMessage();
			} else if (command.equalsIgnoreCase(instance.get(IProtocol.LIST))) {
				m = new ListMessage();
			} else if (command.equalsIgnoreCase(instance.get(IProtocol.LOGIN))) {
				m = new LoginMessage();
			} else if (command.equalsIgnoreCase(instance.get(IProtocol.USERS))) {
				m = new UsersMessage();
			} else if (command.equalsIgnoreCase(instance.get(IProtocol.ECHO))) {
				m = new EchoMessage();
			} else {
				throw new ProtocolException("Command not supported!");
			}
			
			// Return the buffer
			m.fromBuffer(buffer);
			return m;
		}
		catch(IOException e) {
			throw new ProtocolException(e);
		}
	}
	
	@Override
	public void send(IMessage m, OutputStream out) throws ProtocolException {
		StringBuilder builder = new StringBuilder();
		builder.append(instance.get(IProtocol.PROTOCOL));
		builder.append(" ");
		builder.append(m.getCommand());
		builder.append(" ");
		builder.append(m.getSize());
		builder.append(instance.get(IProtocol.CRLF));
		String statusLine = builder.toString();
		
		try {
			// Send the status line
			byte[] buffer = statusLine.getBytes(instance.get(IProtocol.CHAR_SET)); 
			out.write(buffer);
			
			// Send the payload
			buffer = m.toBuffer();
			out.write(buffer);
		}
		catch(Exception e) {
			throw new ProtocolException(e);
		}
	}
}
