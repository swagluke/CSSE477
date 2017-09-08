package edu.rosehulman.csse477.exam1.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class SystemCommand implements Runnable, Serializable {
	private static final long serialVersionUID = 5639897389482743511L;

	@Override
	public void run() {
		System.out.println("\n------------------------------------------------------");
		System.out.println("Listing files in the working directory ...");
		System.out.println("------------------------------------------------------");
		
		try {
			String command = "ls -lah";
			
			if(System.getProperty("os.name").toLowerCase().startsWith("win")) {
				command = "dir";
			}
			
			Process systemProcess = Runtime.getRuntime().exec(command);
			InputStream in = systemProcess.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
