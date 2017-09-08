package edu.rosehulman.rminewclient;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

import edu.rosehulman.rmicommons.Task;

public class Prime implements Task<String>, Serializable {

	private static final long serialVersionUID = 237L;

	private int numPrimes;
	private String clientIP;

	public Prime(int numPrimes) {
		this.numPrimes = numPrimes;
		this.clientIP = getIP();
	}

	@Override
	public String execute() {
		StringBuilder retString = new StringBuilder();

		retString.append(getIP());
		retString.append("\n");
		retString.append(this.clientIP);
		retString.append("\n");
		retString.append(computePrimes());

		return retString.toString();
	}

	private String computePrimes() {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);

		int num = 3;
		for (int count = 1; count < this.numPrimes; num++) {
			boolean isPrime = true;
			for (int j = 2; j <= Math.sqrt(num); j++) {
				if (num % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes.add(num);
				count++;
			}
		}
		return primes.toString();
	}

	private String getIP() {
		try {
			Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
			while (faces.hasMoreElements()) {
				Enumeration<InetAddress> dresses = faces.nextElement().getInetAddresses();

				while (dresses.hasMoreElements()) {
					InetAddress dress = dresses.nextElement();

					// ignore VMware, localhost, and VirtualBox IP addresses
					if (dress.getHostAddress()
							.matches("^(?!192.168|127.0.0.1|169.254)(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})$")) {
						return dress.getHostAddress();
					}
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
