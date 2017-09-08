package homework1;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class PluginEngine {

	public static ListPanel list;
	public static ExecPanel exec;
	public static Map<String, File> plugins = new HashMap<String, File>();

	public static void main(String[] args) {
		//Loading existing plugins
		Path pluginDir = Paths.get(new File("").getAbsolutePath() + "/plugins");
		loadPlugins(pluginDir);
		ArrayList<String> pluginNames = new ArrayList<String>();
		pluginNames.addAll(plugins.keySet());
		
		//initialize list and execute panel
		exec = new ExecPanel();
		list = new ListPanel(pluginNames);
		exec.addDisplay(getPlugin(pluginNames.get(0),exec.getStatusPanel().ps));
		
		//Setup Display
		UIModule mainDisplay = new UIModule();
		mainDisplay.getContentPane().add(list, BorderLayout.WEST);
		mainDisplay.getContentPane().add(exec, BorderLayout.EAST);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainDisplay.setVisible(true);
	}

	private static void loadPlugins(Path pluginDir) {
		File[] pluginJars = pluginDir.toFile().listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".jar");
			}
		});

		for (File jar : pluginJars) {
			try {
				String pluginName = jar.getName().substring(0,
						jar.getName().indexOf(".jar"));

				if (!plugins.containsKey(pluginName)) {
					plugins.put(pluginName, jar);
					ClassPathLoader.addFile(jar);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void loadPlugins(File jarFile) {
		try {
			String pluginName = jarFile.getName().substring(0,
					jarFile.getName().indexOf(".jar"));

			if (!plugins.containsKey(pluginName)) {
				plugins.put(pluginName, jarFile);
				ClassPathLoader.addFile(jarFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Plugin getPlugin(String pluginName, PrintStream ps) {

		try {
			Object pluginObj = Class.forName(pluginName).getDeclaredConstructor(PrintStream.class).newInstance(ps);
			if (pluginObj instanceof Plugin) {
				return (Plugin) pluginObj;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static void setPlugin(String pluginName){
		Plugin p = getPlugin(pluginName, exec.getStatusPanel().getPrintStream());
		exec.addDisplay(p);
	}
}
