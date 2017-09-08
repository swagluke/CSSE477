package edu.rosehulman.csse477.fileapp.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

public class FileDao {
	String path;

	public FileDao(String path) {
		this.path = path;
	}

	public String getFile() {
		String file = "";
		// Load the file with the counter
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		PrintWriter writer = null;
		String inputline = "";
		try {
			File f = new File(path + "/file.txt");
			if (!f.exists()) {
				f.createNewFile();
				writer = new PrintWriter(new FileWriter(f));
				writer.println("Hello World!!!");
			}
			if (writer != null) {
				writer.close();
			}

			fileReader = new FileReader(f);
			bufferedReader = new BufferedReader(fileReader);
			while ((inputline = bufferedReader.readLine()) != null) {
				file += inputline;
			}
		} catch (Exception ex) {
			if (writer != null) {
				writer.close();
			}
		}
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public void save(int count) throws Exception {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		fileWriter = new FileWriter(path + "/file.txt");
		printWriter = new PrintWriter(fileWriter);

		// make sure to close the file
		if (printWriter != null) {
			printWriter.close();
		}
	}

	public void removeFile() {
		File f = new File(path + "/file.txt");
		if (!f.exists())
			return;
		else
			f.delete();
	}

	public String postFile(String content) {
		File f = new File(path + "/file.txt");
		String newcontent = "";
		String inputline = "";
		try (FileWriter fileWriter = new FileWriter(f, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(fileWriter)) {
			if (!f.exists()) {
				f.createNewFile();
				printWriter.print(content);
			}

			bufferedWriter.write(content);
			bufferedWriter.close();
			FileReader fileReader = new FileReader(f);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((inputline = bufferedReader.readLine()) != null) {
				newcontent += inputline;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newcontent;
	}

}