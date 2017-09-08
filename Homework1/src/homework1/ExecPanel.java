package homework1;
import java.awt.BorderLayout;

import javax.swing.JPanel;


public class ExecPanel extends JPanel{

	private StatusPanel status;
	private Thread msgThread;
	public ExecPanel() {
		super();
		this.status = new StatusPanel();
		this.setLayout(new BorderLayout());
		this.add(this.status, BorderLayout.SOUTH);
	}
	
	public void addDisplay(JPanel display){
		this.removeAll();
		this.status.kill();
		this.msgThread = new Thread(this.status);
		this.msgThread.start();
		this.status.flush();
		this.revalidate();
		this.add(display, BorderLayout.NORTH);
		this.add(this.status, BorderLayout.SOUTH);
		this.repaint();
	}
	
	public StatusPanel getStatusPanel(){
		return this.status;
	}
}
