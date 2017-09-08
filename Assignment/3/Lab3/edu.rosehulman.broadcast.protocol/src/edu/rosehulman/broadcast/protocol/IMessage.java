package edu.rosehulman.broadcast.protocol;

public interface IMessage {
	public int getSize();
	public String getCommand();
	public void fromBuffer(byte[] buffer) throws ProtocolException;
	public byte[] toBuffer() throws ProtocolException;
}
