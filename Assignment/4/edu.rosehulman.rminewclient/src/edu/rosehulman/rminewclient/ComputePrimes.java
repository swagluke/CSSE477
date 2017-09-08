package edu.rosehulman.rminewclient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.rosehulman.rmicommons.Compute;

public class ComputePrimes {
	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "edu.rose-hulman.csse477.rmi";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Compute comp = (Compute) registry.lookup(name);
			Prime task = new Prime(Integer.parseInt(args[1]));
			String prime = comp.executeTask(task);
			System.out.println(prime);

		} catch (Exception e) {
			System.err.println("Execution in the ComputePrimes RMI Client:");
			e.printStackTrace();
		}
	}
}
