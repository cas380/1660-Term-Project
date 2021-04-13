import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

public class GUI {
	
	public GUI() {
		// Make our GUI frame and panels
		JFrame frame = new JFrame();
		JPanel bodyPanel = new JPanel();
		JPanel titlePanelFlow = new JPanel();
		JPanel titlePanelGrid = new JPanel();
		JPanel vectorPanel = new JPanel();
		
		// Defining the button text here...
		String RStudioString		= "RStudio";
		String SpyderString			= "Spyder";
		String IBMSASString			= "IBM SAS";
		String GitString			= "Git";
		String JupyterString		= "Jupyter Notebook";
		String OrangeString			= "Orange";
		String VSCodeString			= "Visual Studio Code";
		String HadoopString			= "Apache Hadoop";
		String SparkString			= "Apache Spark";
		String TableauString		= "Tableau";
		String SonarString			= "SonarQube & SonarScanner";
		String TensorflowString		= "Tensorflow";
		String MarkdownString		= "Markdown";
		
		// Make our objects to place in the GUI
		JLabel title = new JLabel("Data Science Toolbox", SwingConstants.CENTER);
		JLabel subtitle = new JLabel("Brought to you by CS165-- err... CS1660!", SwingConstants.CENTER);
		JButton RStudioButton		= new JButton(RStudioString);
		JButton SpyderButton		= new JButton(SpyderString);
		JButton IBMSASButton		= new JButton(IBMSASString);
		JButton GitButton			= new JButton(GitString);
		JButton JupyterButton		= new JButton(JupyterString);
		JButton OrangeButton		= new JButton(OrangeString);
		JButton VSCodeButton		= new JButton(VSCodeString);
		JButton HadoopButton		= new JButton(HadoopString);
		JButton SparkButton			= new JButton(SparkString);
		JButton TableauButton		= new JButton(TableauString);
		JButton SonarButton			= new JButton(SonarString);
		JButton TensorflowButton	= new JButton(TensorflowString);
		JButton MarkdownButton		= new JButton(MarkdownString);
		
		// Define our action listeners for button click events
		ActionListener eventManager = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String buttonName = e.getActionCommand();
				System.out.println(buttonName);
				if (buttonName.equals(RStudioString)) {
					openWebpage("RStudio", 8787, "/");
				} else if (buttonName.equals(SpyderString)) {
					runProgram("Spyder", "my-spyder");
				} else if (buttonName.equals(IBMSASString)) {
					openWebpage("ibm_sas", 2751, "/");
				} else if (buttonName.equals(GitString)) {
					runTerminal(false, "Git", "bitnami/git");
				} else if (buttonName.equals(JupyterString)) {
					openWebpage("Jupyter", 8888, "/tree");
				} else if (buttonName.equals(OrangeString)) {
					runProgram("Orange", "my-orange");
				} else if (buttonName.equals(VSCodeString)) {
					openWebpage("VS_Code", 8080, "/");
				} else if (buttonName.equals(HadoopString)) {
					runTerminal(false, "Hadoop", "sequenceiq/hadoop-docker");
				} else if (buttonName.equals(SparkString)) {
					runTerminal(false, "Spark", "bitnami/spark");
				} else if (buttonName.equals(TableauString)) {
					execTerminal(false, "Tableau");
				} else if (buttonName.equals(SonarString)) {
					openWebpage("SonarQube", 9000, "/");
				} else if (buttonName.equals(TensorflowString)) {
					runTerminal(false, "Tensorflow", "tensorflow/tensorflow");
				} else if (buttonName.equals(MarkdownString)) {
					openWebpage("Markdown", 80, "/");
				} else {
					System.err.println("ERROR: no corresponding button text.");
				}
			}
		};
		RStudioButton.addActionListener(eventManager);
		SpyderButton.addActionListener(eventManager);
		IBMSASButton.addActionListener(eventManager);
		GitButton.addActionListener(eventManager);
		JupyterButton.addActionListener(eventManager);
		OrangeButton.addActionListener(eventManager);
		VSCodeButton.addActionListener(eventManager);
		HadoopButton.addActionListener(eventManager);
		SparkButton.addActionListener(eventManager);
		TableauButton.addActionListener(eventManager);
		SonarButton.addActionListener(eventManager);
		TensorflowButton.addActionListener(eventManager);
		MarkdownButton.addActionListener(eventManager);
		// ...or I could split this up into multiple events but that's a lot of repeated code
		
		// Assign layout managers to our panels
		bodyPanel.setLayout(new GridLayout(2, 1));
		bodyPanel.add(titlePanelFlow);
			titlePanelFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
			titlePanelFlow.add(titlePanelGrid);
				titlePanelGrid.setLayout(new GridLayout(2, 1));
		bodyPanel.add(vectorPanel);
		
		// Add our objects
		titlePanelGrid.add(title);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		titlePanelGrid.add(subtitle);
		subtitle.setFont(new Font("Arial", Font.ITALIC, 15));
		vectorPanel.add(RStudioButton);
		vectorPanel.add(SpyderButton);
		vectorPanel.add(IBMSASButton);
		vectorPanel.add(GitButton);
		vectorPanel.add(JupyterButton);
		vectorPanel.add(OrangeButton);
		vectorPanel.add(VSCodeButton);
		vectorPanel.add(HadoopButton);
		vectorPanel.add(SparkButton);
		vectorPanel.add(TableauButton);
		vectorPanel.add(SonarButton);
		vectorPanel.add(TensorflowButton);
		vectorPanel.add(MarkdownButton);
		
		// Final GUI info tweaks
		frame.add(bodyPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Data Science Toolbox");
		frame.setSize(600, 300);
		frame.setVisible(true);
	}
	
	// Executes the provided command in CLI
	public static void executeCommand(String[] cmd) {
		System.out.println("Running command: " + cmd[0]);
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
	
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
	
			// Read the output from the command
			System.out.println("\nHere is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
	
			// Read any errors from the attempted command
			System.out.println("\nHere is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	* Opens the URL cname:0000/sublink in microservices-gui's firefox
	*
	* cname: Must match the service name in docker-compose.yml
	* port: The port to connect on (must match container port in docker-compose.yml)
	* sublink: The rest of the link following the port (for example: "/")
	*/
	public static void openWebpage(String cname, int port, String sublink) {
		String[] launch = {"firefox", "-new-window", cname+":"+port+sublink};
		executeCommand(launch);
	}

	/**
	* Runs `docker run` on the provided image in a new xterm window,
	* essentially allowing shell access to a new container. Compared
	* to execTerminal, this is useful for images whose containers
	* close after the initial docker-compose.
	*
	* hold: Determines whether or not to use the -hold flag. -hold is good for
	*       debugging, but the programs will hold the window open on their own,
	*       effectively meaning you need to close the terminal twice
	* cname: Must match the service name in docker-compose.yml
	* iname: The image we want to run a container of
	*/
	public static void runTerminal(boolean hold, String cname, String iname) {
		String[] cmd = {"docker", "run", "--rm", "-it", "--name", cname+"_JAVAGUI", iname, "bash"};
		xtermCMD(hold, cmd);
	}

	/**
	* Runs `docker run` on the provided image to start a new container.
	* When run through the microservices-gui (which has X access), this
	* essentially runs a program (otherwise, such as on startup with
	* `docker-compose up`, the container can not display and exits).
	*
	* cname: Must match the service name in docker-compose.yml
	* iname: The image we want to run a container of
	*/
	public static void runProgram(String cname, String iname) {
		// This has the environment and volumes configured by run-gui.sh hardcoded, so they may need changed
		String[] spyder = {"docker", "run", "--rm", "--name", cname+"_JAVAGUI", "-e", "DISPLAY=:0", "-v", "/tmp/.X11-unix:/tmp/.X11-unix/:ro", iname};
		executeCommand(spyder);
	}

	/**
	* Runs `docker exec` on the provided container in a new xterm window,
	* essentially allowing shell access to an already running container.
	*
	* hold: Determines whether or not to use the -hold flag for xterm.
	* cname: Must match the service name in docker-compose.yml
	*/
	public static void execTerminal(boolean hold, String cname) {
		String[] cmd = {"docker", "exec", "-it", "1660-term-project_"+cname+"_1", "bash"};
		xtermCMD(hold, cmd);
	}

	/**
	* Executes the provided command in a new xterm window.
	*
	* hold: Determines whether or not to use the -hold flag. -hold is good for
	*       debugging, but the programs will hold the window open on their own,
	*       effectively meaning you need to close the terminal twice
	* cmd: The String array representing the command to be run in the new xterm window
	*/
	public static void xtermCMD(boolean hold, String[] cmd) {
		int displacement = 2;
		if (hold) { displacement = 3; }
		String[] xterm = new String[cmd.length + displacement];
		xterm[0] = "xterm";
		int i = displacement;

		if (hold) {
			xterm[1] = "-hold";
			xterm[2] = "-e";
		} else {
			xterm[1] = "-e";
		}

		for (; i - displacement < cmd.length; i++) {
			xterm[i] = cmd[i - displacement];
		}

		System.out.println("xterm command being run:");
		for (int j = 0; j < xterm.length; j++) {
			System.out.print(xterm[j]+" ");
		}
		System.out.println("");

		executeCommand(xterm);
	}
	
	public static void main(String[] args) {
		System.out.println("Attempting to launch GUI:");
		new GUI();
	}

}