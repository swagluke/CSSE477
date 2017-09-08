package edu.rosehulman.broadcast.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.Protocol;

public class BroadcastClientApp {

	public static void main(String[] args) throws Exception {
		IProtocol protocol = Protocol.getInstance();
		String server = "localhost"; // Use "localhost" for testing in your local machine

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.format("Rose-Hulman Broadcast Client (%s)%n", protocol.get(IProtocol.PROTOCOL));
	    System.out.print("Please enter the username that you would like to use:");
	    String user = in.readLine().trim();
	    System.out.println(user);
		
		
		BroadcastClient client = new BroadcastClient(server, protocol, user);
		Thread runner = new Thread(client);
		runner.start();
		client.send("login");
	    
	    System.out.println("Type your message and press enter. Type \"quit\" to terminate.");
	    String line = "";
	    do{
	    	line = in.readLine();
	    	client.send(line);
	    }
	    while(!line.equalsIgnoreCase("quit"));
	    client.stop();
	}
}
