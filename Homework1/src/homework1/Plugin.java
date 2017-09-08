package homework1;

import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.JPanel;


public abstract class Plugin extends JPanel{
	public Plugin(PrintStream ps){
		Dimension size = new Dimension(UIModule.WINDOW_WIDTH*4/5, UIModule.WINDOW_HEIGHT*23/32);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		execute();
	}
	
	public abstract void execute();
}

