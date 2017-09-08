package edu.rosehulman.p2p.impl.handlers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.rosehulman.p2p.impl.Host;
import edu.rosehulman.p2p.protocol.AbstractHandler;
import edu.rosehulman.p2p.protocol.IHost;
import edu.rosehulman.p2p.protocol.IP2PMediator;
import edu.rosehulman.p2p.protocol.IPacket;
import edu.rosehulman.p2p.protocol.IProtocol;
import edu.rosehulman.p2p.protocol.IRequestHandler;
import edu.rosehulman.p2p.protocol.P2PException;

public class FoundRequestHandler extends AbstractHandler implements IRequestHandler {

	public FoundRequestHandler(IP2PMediator mediator) {
		super(mediator);
	}

	@Override
	public void handle(IPacket packet, InputStream in) throws P2PException {
		String host = packet.getHeader(IProtocol.HOST);
		int port = Integer.parseInt(packet.getHeader(IProtocol.PORT));
		IHost remoteHost = new Host(host, port);
		int sequencenumber = Integer.parseInt(packet.getHeader(IProtocol.SEQ_NUM));
		int payloadsize = Integer.parseInt(packet.getHeader(IProtocol.PAYLOAD_SIZE));
		
		IPacket requestpacket = mediator.getRequest(sequencenumber);	
		try {
			List<String> listing = new ArrayList<>();
			byte[] buffer = new byte[payloadsize];
			in.read(buffer);
			String listingStr = new String(buffer, IProtocol.CHAR_SET);
			StringTokenizer tokenizer = new StringTokenizer(listingStr);
			while(tokenizer.hasMoreTokens()) {
				String file = tokenizer.nextToken(IProtocol.LF).trim();
				if(!file.isEmpty()) {
					listing.add(file);
				}
			}
			mediator.fireFileFound(remoteHost, listing);
		}
		catch(Exception e) {
			throw new P2PException(e);
		}
	}
}

