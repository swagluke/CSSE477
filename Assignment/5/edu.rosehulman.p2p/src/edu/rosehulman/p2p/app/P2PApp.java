/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package edu.rosehulman.p2p.app;

import javax.swing.JFrame;

import edu.rosehulman.p2p.impl.ConnectionMonitor;
import edu.rosehulman.p2p.impl.P2PMediator;
import edu.rosehulman.p2p.impl.Protocol;
import edu.rosehulman.p2p.impl.handlers.FindRequestHandler;
import edu.rosehulman.p2p.impl.handlers.FoundRequestHandler;
import edu.rosehulman.p2p.impl.handlers.GetRequestHandler;
import edu.rosehulman.p2p.impl.handlers.ListRequestHandler;
import edu.rosehulman.p2p.impl.handlers.ListingRequestHandler;
import edu.rosehulman.p2p.impl.handlers.PutRequestHandler;
import edu.rosehulman.p2p.impl.handlers.PutResponseHandler;
import edu.rosehulman.p2p.protocol.IConnectionMonitor;
import edu.rosehulman.p2p.protocol.IP2PMediator;
import edu.rosehulman.p2p.protocol.IProtocol;

public class P2PApp {
	public static final int DEPTH = 4;
	
	public static void main(String args[]) throws Exception {
		// Initialize the main window
		JFrame mainFrame = new JFrame("P2P Main Window");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
		// Open configuration window		
		P2PConfigurationWindow configWindow = new P2PConfigurationWindow(mainFrame);
		configWindow.show();
		
		// Get the settings
		String rootDirectory = configWindow.getRootDirectory();
		int port = configWindow.getPort();
		

		// Configure the main worker that mediates between peers
		IP2PMediator mediator = new P2PMediator(port, rootDirectory);

		// Configure the protocol by setting up handlers
		IProtocol protocol = Protocol.getInstance();
		protocol.setRequestHandler(IProtocol.GET, new GetRequestHandler(mediator));
		protocol.setRequestHandler(IProtocol.PUT, new PutRequestHandler(mediator));
		protocol.setResponseHandler(IProtocol.PUT, new PutResponseHandler(mediator));
		protocol.setRequestHandler(IProtocol.LIST, new ListRequestHandler(mediator));
		protocol.setRequestHandler(IProtocol.LISTING, new ListingRequestHandler(mediator));
		protocol.setRequestHandler(IProtocol.FIND, new FindRequestHandler(mediator));
		protocol.setRequestHandler(IProtocol.FOUND, new FoundRequestHandler(mediator));
	
		// Let's start a connection monitor that listens for incoming connection request
		IConnectionMonitor connectionMonitor = new ConnectionMonitor(mediator);
		Thread runner = new Thread(connectionMonitor);
		runner.start();
		
		// Configure the GUI to receive event notification
		P2PGUI gui = new P2PGUI(mainFrame, mediator, connectionMonitor, DEPTH);
		mediator.addActivityListener(gui);
		mediator.addConnectionListener(gui);
		mediator.addDownloadListener(gui);
		mediator.addListingListener(gui);
		mediator.addRequestLogListener(gui);
		mediator.addFindResultListener(gui);
		
		// Show the gui
		gui.show();
	}
}
