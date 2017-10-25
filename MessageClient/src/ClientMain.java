import java.io.File;
import java.util.Scanner;

import javax.swing.*;

public class ClientMain {

	SplashScreen screen;
	private final String CONFIG_FILENAME = "config.dat";
	private int portInt = 7001;
	private String serverIP;

	public ClientMain() {
		
		File file = new File(CONFIG_FILENAME);

		if(file.exists()){

			Scanner readIn;

			try{

				readIn = new Scanner(file);
				
				serverIP = readIn.next();
				
				String port = readIn.next();
				portInt = Integer.parseInt(port);	
				

			}catch(Exception e){

			}
		}
		else{
			
			int reply = JOptionPane.showConfirmDialog(null, "Configuration is not complete. Would" +
					" you like to complete this now?");
			if(reply == JOptionPane.YES_OPTION){
				
				ClientConfig clientConfig = new ClientConfig();				
				clientConfig.setTitle("Config");
				clientConfig.setSize(450, 300);
				clientConfig.setLocationRelativeTo(null);
				clientConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				clientConfig.setVisible(true);
				
				splashScreenDestruct();
				
			}
			else System.exit(0);
			
		}
		
		splashScreenInit();
		
		for (int i = 0; i <= 1050; i++){

			for (long j = 0; j < 50000; j++){

				@SuppressWarnings("unused")
				String str = " " + (j + i);

			}

			screen.setProgress(i);   

		}

		splashScreenDestruct();

		MessageClient client = new MessageClient(serverIP, portInt);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setLocationRelativeTo(null);
		client.startRunning();

	}

	private void splashScreenDestruct() {

		screen.setScreenVisible(false);

	}

	private void splashScreenInit() {

		ImageIcon myImage = new ImageIcon("Notepad.jpg");
		screen = new SplashScreen(myImage);
		screen.setLocationRelativeTo(null);
		screen.setProgressMax(1000);
		screen.setScreenVisible(true);

	}

	static class Shutdown extends Thread{
			
		@Override
		public void run() {
			
			MessageClient.sendMessage("SHUT DOWN");
			
		}

	}
	
	public static void main(String[] args){

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		}catch (Exception e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Shutdown());
		new ClientMain();
		
	}

}
