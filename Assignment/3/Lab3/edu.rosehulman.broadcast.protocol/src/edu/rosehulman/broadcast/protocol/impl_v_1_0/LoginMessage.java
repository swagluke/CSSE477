package edu.rosehulman.broadcast.protocol.impl_v_1_0;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import edu.rosehulman.broadcast.protocol.IControlMessage;
import edu.rosehulman.broadcast.protocol.IProtocol;
import edu.rosehulman.broadcast.protocol.ProtocolException;

public class LoginMessage implements IControlMessage {

	private String user;
	private int size;
	private IProtocol protocol = Protocol.getInstance();

	public LoginMessage() {
	}

	public LoginMessage(String user) {
		this.user = user;

		// Compute buffer size
		int crlfLen = protocol.get(IProtocol.CRLF).length();
		this.size = user.length() + crlfLen;
	}

	@Override
	public String getCommand() {
		return protocol.get(IProtocol.LOGIN);
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public void fromBuffer(byte[] buffer) throws ProtocolException {
		String packet = new String(buffer, Charset.forName(protocol
				.get(IProtocol.CHAR_SET)));
		StringTokenizer tokenizer = new StringTokenizer(packet);

		String crlf = protocol.get(IProtocol.CRLF);
		try {
			this.user = tokenizer.nextToken(crlf);
			this.size = buffer.length;
		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}

	@Override
	public byte[] toBuffer() throws ProtocolException {
		String crlf = protocol.get(IProtocol.CRLF);

		StringBuilder builder = new StringBuilder();
		builder.append(this.user);
		builder.append(crlf);

		try {
			String buffer = builder.toString();
			return buffer.getBytes(protocol.get(IProtocol.CHAR_SET));
		} catch (UnsupportedEncodingException e) {
			throw new ProtocolException(e);
		}
	}

}
