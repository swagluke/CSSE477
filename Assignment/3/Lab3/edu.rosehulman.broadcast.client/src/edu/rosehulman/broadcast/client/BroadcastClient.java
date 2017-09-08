package edu.rosehulman.broadcast.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import edu.rosehulman.broadcast.protocol.IEchoMessage;
import edu.rosehulman.broadcast.protocol.IMessage;
import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.EchoMessage;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.ListMessage;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.LoginMessage;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.QuitMessage;

public class BroadcastClient implements Runnable {
	String user;
	Socket socket;
	IProtocol protocol;
	
	OutputStream out;
	InputStream in;
	volatile boolean active;
	
	public BroadcastClient(String server, IProtocol protocol, String user) {
		this.protocol = protocol;
		this.user = user;

		active = true;

		try {
			int port = Integer.parseInt(protocol.get(IProtocol.PORT));
			socket = new Socket(server, port);
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();
		}
		catch(Exception e) {
			e.printStackTrace();
			active = false;
		}
	}	
	
	public void stop() {
		active = false;
	}
	
	@Override
	public void run() {
		
		while (active) {
			try {
				IMessage m = protocol.receive(in);
				if (m.getCommand().equalsIgnoreCase(protocol.get(IProtocol.ECHO))) {
					IEchoMessage echo = (IEchoMessage) m;
					System.out.println(echo.getUser() + ": " + echo.getContent());
				} else if (m.getCommand().equalsIgnoreCase(protocol.get(IProtocol.USERS))) {
					IEchoMessage users = (IEchoMessage) m;
					System.out.println("Users: " + users.getContent());
				}
			} catch (Exception e) {
				e.printStackTrace();
				active = false;
			}
		}
	}
	
	public boolean send(String line) {
		if (!active)
			return false;

		String quitCmd = protocol.get(IProtocol.QUIT);
		String listCmd = protocol.get(IProtocol.LIST);

		try {
			IMessage m = null;
			if (line.equalsIgnoreCase(quitCmd)) {
				m = new QuitMessage(this.user);
			} else if (line.equalsIgnoreCase(listCmd)) {
				m = new ListMessage(this.user);
			} else if (line.equalsIgnoreCase(protocol.LOGIN)){
				m = new LoginMessage(this.user);
			} else {
				m = new EchoMessage(this.user, line);
			}
			protocol.send(m, out);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			active = false;
			return false;
		}
	}
}
