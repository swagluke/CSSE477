package edu.rosehulman.server;

import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.impl_v_1_0.Protocol;

public class BroadcastServerApp {
	public static void main(String[] args) {
		IProtocol protocol = Protocol.getInstance();
		BroadcastServer server = new BroadcastServer(protocol);
		server.execute();
	}
}
