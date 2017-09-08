package homework1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class LoadPluginListener extends JPanel implements ActionListener{

	JFileChooser fc;
	ListPanel list;
	
	public LoadPluginListener(ListPanel list){
		super(new BorderLayout());
		this.list = list;
		fc = new JFileChooser();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int returnVal = fc.showOpenDialog(LoadPluginListener.this);
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        file.getName();
		        if(!file.getName().contains(".jar")){
		        	System.out.println("Wrong file format!");
		        	return;
		        }
		        CopyOption[] options = new CopyOption[]{
		        		  StandardCopyOption.REPLACE_EXISTING,
		        		  StandardCopyOption.COPY_ATTRIBUTES
		        		}; 
		        System.out.println("Loading File...");
		        File target = new File(new File("").getAbsolutePath() + "/plugins/"+ file.getName());
		        
		        try {
					java.nio.file.Files.copy(file.toPath(), target.toPath(), options);
					this.list.addPlugin(file.getName().replaceAll(".jar", ""));
					PluginEngine.loadPlugins(target);
					this.list.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 }
	}

}
