package homework1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ListPanel extends JPanel{
	
	private ArrayList<String> pluginList;
	
	public ListPanel(ArrayList<String> pluginList){
		Dimension size = new Dimension(UIModule.WINDOW_WIDTH/5, UIModule.WINDOW_HEIGHT);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setOpaque(true);
		this.pluginList = pluginList;
		this.setBackground(Color.CYAN);
		this.addLoadBtn();
    	for(int i = 0; i < this.pluginList.size(); i++){
    		this.addPlugin(pluginList.get(i));
    	}
	}
	
    private void addLoadBtn() {
		JButton loadBtn = new JButton("Load New Plugin");
		loadBtn.addActionListener(new LoadPluginListener(this));
		this.add(loadBtn);
	}

	public void paintComponent(Graphics g) {
    	super.paintComponent(g);

    	Graphics2D g2 = (Graphics2D) g;
    }

	public void addPlugin(String name) {
		final JButton btn = new JButton(name);
		Dimension btnSize = new Dimension(UIModule.WINDOW_WIDTH/5, 30);
		btn.setPreferredSize(btnSize);
		btn.setMaximumSize(btnSize);
		btn.setMinimumSize(btnSize);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                PluginEngine.setPlugin(btn.getText());
            }
		});
		this.add(btn, BorderLayout.NORTH);
		this.revalidate();
		this.repaint();
	}
}