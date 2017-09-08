package edu.rosehulman.p2p.impl.handlers;

import java.io.InputStream;

import edu.rosehulman.p2p.impl.Host;
import edu.rosehulman.p2p.protocol.AbstractHandler;
import edu.rosehulman.p2p.protocol.IHost;
import edu.rosehulman.p2p.protocol.IP2PMediator;
import edu.rosehulman.p2p.protocol.IPacket;
import edu.rosehulman.p2p.protocol.IProtocol;
import edu.rosehulman.p2p.protocol.IRequestHandler;
import edu.rosehulman.p2p.protocol.P2PException;

public class FindRequestHandler extends AbstractHandler implements IRequestHandler {

	public FindRequestHandler(IP2PMediator mediator) {
		super(mediator);
	}

	@Override
	public void handle(IPacket packet, InputStream in) throws P2PException {
		//System.out.println("Packet find");
		int sequencenumber = Integer.parseInt(packet.getHeader(IProtocol.SEQ_NUM));
		int remotePort = Integer.parseInt(packet.getHeader(IProtocol.PORT));
		IHost remoteHost = new Host(packet.getHeader(IProtocol.HOST), remotePort);
		int originalPort = Integer.parseInt(packet.getHeader(IProtocol.ORIGINAL_PORT));
		IHost originalHost = new Host(packet.getHeader(IProtocol.ORIGINAL_HOST), originalPort);
		int depth = Integer.parseInt(packet.getHeader(IProtocol.DEPTH));
		String searchterm = packet.getHeader(IProtocol.SEARCH_TERM);
		//System.out.println("Passed all the header");
		mediator.requestFound(remoteHost, originalHost, searchterm, depth, sequencenumber);
	}
}
