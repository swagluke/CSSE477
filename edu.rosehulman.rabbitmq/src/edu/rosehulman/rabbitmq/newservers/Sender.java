package edu.rosehulman.rabbitmq.newservers;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.thoughtworks.xstream.XStream;

public class Sender {
	private static final String EXCHANGE_NAME = "direct";

	public static void main(String[] argv) throws Exception {
		XStream xstream = new XStream();
		transaction realtransaction = new transaction();
		Class[] classtype = {transaction.class, creditcard.class, inventory.class, shipment.class};
		xstream.processAnnotations(classtype);
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		System.out.println("Type XML below. Type 'quit' to exit the program.");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String message = scanner.nextLine().trim();
			if (message.equalsIgnoreCase("quit"))
				break;
			realtransaction = (transaction)xstream.fromXML(message);
			String creditinfo = "Credit Card ID is " + realtransaction.creditcard.id + " Amount is " + realtransaction.creditcard.amount;
			String inventoryinfo = "Inventory ID is " + realtransaction.inventory.id + " Quantity is " + realtransaction.inventory.quantity;
			String shipmentinfo = "Shipment ID is " + realtransaction.shipment.id + " Deliver by " + realtransaction.shipment.deliverby + " Method ID is " + realtransaction.shipment.methodid;
			channel.basicPublish(EXCHANGE_NAME, "credit-card", null, creditinfo.getBytes("UTF-8"));
			channel.basicPublish(EXCHANGE_NAME, "inventory", null, inventoryinfo.getBytes("UTF-8"));
			channel.basicPublish(EXCHANGE_NAME, "shipment", null, shipmentinfo.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
		}
		scanner.close();
		channel.close();
		connection.close();
	}
}
