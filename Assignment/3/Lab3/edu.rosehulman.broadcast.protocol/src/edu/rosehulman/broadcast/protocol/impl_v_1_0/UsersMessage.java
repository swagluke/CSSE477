package edu.rosehulman.broadcast.protocol.impl_v_1_0;

import edu.rosehulman.broadcast.protocol.IProtocol;

public class UsersMessage extends EchoMessage {

	private IProtocol protocol = Protocol.getInstance();

	public UsersMessage(){ super(); }
	
	public UsersMessage(String user, String content){
		super(user, content);
	}
	
	@Override
	public String getCommand() {
		return protocol.get(IProtocol.USERS);
	}
}
