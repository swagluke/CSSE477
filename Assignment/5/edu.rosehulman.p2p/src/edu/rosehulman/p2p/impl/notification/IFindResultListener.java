package edu.rosehulman.p2p.impl.notification;

import java.util.List;

import edu.rosehulman.p2p.protocol.IHost;

public interface IFindResultListener {
	public void findResultReceived(IHost host, List<String> listing);
}