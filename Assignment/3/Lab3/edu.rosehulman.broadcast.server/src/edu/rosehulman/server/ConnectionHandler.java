package edu.rosehulman.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import edu.rosehulman.broadcast.protocol.IMessage;
import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.IControlMessage;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.UsersMessage;


public class ConnectionHandler implements Runnable {
	Socket socket;
	OutputStream out;
	InputStream in;

	BroadcastServer server;
	IProtocol protocol;
	
	String user;
	
	public ConnectionHandler(BroadcastServer server, Socket socket) throws Exception {
		this.server = server;
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		this.protocol = server.getProtocol();
	}
	
	public void send(IMessage m) {
		try {
			this.protocol.send(m, out);
		}
		catch(Exception e) {
			e.printStackTrace();
			server.remove(this);
			try {
				socket.close();
			}
			catch(Exception ex){}
		}
	}

	@Override
	public void run() {
		System.out.println("Connected to: " + socket.getRemoteSocketAddress());
		boolean active = true;
		while(active) {
			try {
				IMessage m = protocol.receive(in);
				if(m.getCommand().equalsIgnoreCase(protocol.get(IProtocol.QUIT))) {
					active = false;
					server.remove(this);
					System.out.println("Closing connection to: " + socket.getRemoteSocketAddress());
					try {
						socket.close();
					}
					catch(Exception ex){}
				}
				else if (m.getCommand().equalsIgnoreCase(protocol.get(IProtocol.LIST))){
					System.out.println(this.server.getAllUsers());
					this.send(new UsersMessage(user, this.server.getAllUsers().toString()));
				} else if (m.getCommand().equalsIgnoreCase(protocol.get(IProtocol.LOGIN))) { 
					this.user = ((IControlMessage) m).getUser();
					System.out.println("User " + this.user + "joined");
				}else {
					server.broadcast(m);
				}
			}
			catch(Exception e) {
				active = false;
				e.printStackTrace();
				server.remove(this);
				try {
					socket.close();
				}
				catch(Exception ex){}
			}
		}
	}
	
	public String getUser(){
		return this.user;
	}
}
