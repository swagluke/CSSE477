package edu.rosehulman.rabbitmq.newservers;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class inventory {
	public int id;
	public int quantity;
}
