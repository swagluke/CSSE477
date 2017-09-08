package edu.rosehulman.rabbitmq.newservers;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("shipment")
public class shipment {
	public int id;
	@XStreamAlias("deliver-by")
	public String deliverby;
	@XStreamAlias("method-id")
	public int methodid;
}
