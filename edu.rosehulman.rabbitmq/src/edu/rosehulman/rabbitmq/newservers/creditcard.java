package edu.rosehulman.rabbitmq.newservers;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("credit-card")
public class creditcard {
	public int id;
	public int amount;
}
