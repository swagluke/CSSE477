package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StatusPanel extends JPanel implements Runnable {

	public Graphics2D g;
	public ArrayList<String> status;
	public ByteArrayOutputStream baos;
	public PrintStream ps;
	public String messages;
	public boolean run = true;

	public StatusPanel() {
		Dimension size = new Dimension(UIModule.WINDOW_WIDTH * 4 / 5,
				UIModule.WINDOW_HEIGHT / 4);
		this.status = new ArrayList<String>();
		this.messages = "";
		this.baos = new ByteArrayOutputStream();
		this.ps = new PrintStream(this.baos);
		System.setOut(this.ps);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setOpaque(true);
		this.setLayout(null);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int y = 0;
		for (int i = 0; i < Math.min(this.status.size(), 20); i++) {
			if (this.status.get(i).charAt(0) == '\n') {
				continue;
			}

			JLabel message = new JLabel(this.status.get(i), SwingConstants.LEFT);
			message.setSize(new Dimension(UIModule.WINDOW_WIDTH * 4 / 5, 20));
			message.setOpaque(false);
			message.setLocation(10, y);
			this.add(message);
			y+=20;
		}
		// g.drawString(this.messages, 10, y);

		this.setBackground(Color.GREEN);
	}

	public void waitForMessage() {
		int i = 0;
		while (this.run) {
			if (!this.messages.equals(this.baos.toString())) {
				i++;
				int msgLength = this.baos.toString().length()
						- this.messages.length();
				String msg = this.baos.toString().substring(
						this.messages.length());
				this.messages = this.baos.toString();
				this.status.add(0, msg);
				this.removeAll();
				this.revalidate();
				this.repaint();
			}
		}
	}

	public void kill() {
		this.run = false;
	}

	public PrintStream getPrintStream() {
		return this.ps;
	}

	@Override
	public void run() {
		this.run = true;
		waitForMessage();
	}

	public void flush() {
		this.status.clear();
	}
}
