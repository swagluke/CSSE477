package homework1;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;


public class HelloPlugin extends Plugin{

	
	public HelloPlugin(PrintStream ps) {
		super(ps);
		this.setOpaque(true);
		JButton btn = new JButton("Button");
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the button " + Math.random()*100 + " time(s)");
            }
		});
		
		this.add(btn);
	}

	public void paintComponents(Graphics g){
		super.paintComponents(g);
	
		
	}
	@Override
	public void execute() {
		for(int i = 0; i < 10; i ++){
//			System.out.println("Hello World" + i);
		}
	}
}
