package edu.rosehulman.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.rosehulman.broadcast.protocol.IMessage;
import edu.rosehulman.broadcast.protocol.IProtocol;

public class BroadcastServer {
	int port;
	List<ConnectionHandler> handlers;
	List<ConnectionHandler> staleHandlers;
	ServerSocket server;
	IProtocol protocol;
	
	public BroadcastServer(IProtocol protocol) {
		this.protocol = protocol;
		this.port = Integer.parseInt(this.protocol.get(IProtocol.PORT));
		this.handlers = Collections.synchronizedList(new ArrayList<ConnectionHandler>());
		this.staleHandlers = Collections.synchronizedList(new ArrayList<ConnectionHandler>());
	}
	
	public IProtocol getProtocol() {
		return this.protocol;
	}
	
	void broadcast(IMessage m) {
		handlers.removeAll(staleHandlers);
		staleHandlers.clear();
		
		System.out.println("Broadcasting to " + handlers.size() + " clients ...");
		handlers.stream().forEach(handler -> handler.send(m));
	}
	
	void remove(ConnectionHandler handler) {
		this.staleHandlers.add(handler);
	}
	
	public void execute() {
		while(true) {
			try {
				this.server = new ServerSocket(port);
				System.out.println("Broadcast server running at port: " + port);
			}
			catch(Exception e) {
				e.printStackTrace();
				return;
			}
			
			while(true) {
				try {
					Socket client = this.server.accept();

					ConnectionHandler handler = new ConnectionHandler(this, client);
					this.handlers.add(handler);

					Thread runner = new Thread(handler);
					runner.start();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<String> getAllUsers(){
		handlers.removeAll(staleHandlers);
		staleHandlers.clear();
		
		ArrayList<String> users = new ArrayList<String>();	
		System.out.println("num connections: ");
		for(ConnectionHandler handler : handlers){
			System.out.println(handler.getUser());
			users.add(handler.getUser());
		}
		
		return users;
	}
}
