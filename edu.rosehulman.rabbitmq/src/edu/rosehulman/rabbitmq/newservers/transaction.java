package edu.rosehulman.rabbitmq.newservers;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("transaction")
public class transaction {
	public int id;
	@XStreamAlias("credit-card")
	public creditcard creditcard;
	@XStreamAlias("item")
	public inventory inventory;
	@XStreamAlias("shipment")
	public shipment shipment;
}
