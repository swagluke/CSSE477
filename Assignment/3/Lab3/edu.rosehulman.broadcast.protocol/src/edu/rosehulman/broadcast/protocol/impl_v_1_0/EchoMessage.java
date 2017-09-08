package edu.rosehulman.broadcast.protocol.impl_v_1_0;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import edu.rosehulman.broadcast.protocol.IEchoMessage;
import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.ProtocolException;

public class EchoMessage implements IEchoMessage {
	private int size;
	private String user;
	private String content;
	private IProtocol protocol = Protocol.getInstance();
	
	public EchoMessage() {
	}
	
	public EchoMessage(String user, String content) {
		this.user = user;
		this.content = content;
		
		// Compute buffer size
		int crlfLen = protocol.get(IProtocol.CRLF).length();
		this.size = user.length() + content.length() + crlfLen * 2;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public String getCommand() {
		return protocol.get(IProtocol.ECHO);
	}
	
	@Override
	public String getUser() {
		return this.user;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public void fromBuffer(byte[] buffer) throws ProtocolException {
		String packet = new String(buffer, Charset.forName(protocol.get(IProtocol.CHAR_SET)));
		StringTokenizer tokenizer = new StringTokenizer(packet);

		String crlf = protocol.get(IProtocol.CRLF);
		try {
			this.user = tokenizer.nextToken(crlf);
			this.content = tokenizer.nextToken(crlf);
			this.size = buffer.length;
		}
		catch(Exception e) {
			throw new ProtocolException(e);
		}
	}

	@Override
	public byte[] toBuffer() throws ProtocolException {
		String crlf = protocol.get(IProtocol.CRLF);

		StringBuilder builder = new StringBuilder();
		builder.append(this.user);
		builder.append(crlf);
		builder.append(content);
		builder.append(crlf);
		
		try {
			String buffer = builder.toString();
			return buffer.getBytes(protocol.get(IProtocol.CHAR_SET));
		} 
		catch (UnsupportedEncodingException e) {
			throw new ProtocolException(e);
		}
	}
}
