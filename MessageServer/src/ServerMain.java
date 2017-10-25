import java.io.File;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class ServerMain{

    SplashScreen	 screen;
    private int		 portInt	 = 7001;
    private String[]	 recipients	 = new String[20];
    private final String CONFIG_FILENAME = "config.dat";

    public ServerMain(){

	splashScreenInit();

	File file = new File(CONFIG_FILENAME);

	if (file.exists()){

	    Scanner readIn;

	    try{

		readIn = new Scanner(file);

		int index = 0;

		while (index < 20){

		    recipients[index] = readIn.next();
		    index++;
		    readIn.hasNext();

		}

		String port = readIn.next();
		portInt = Integer.parseInt(port);

	    }
	    catch (Exception e){

	    }
	}

	for (int i = 0; i <= 1050; i++){
	    for (long j = 0; j < 50000; ++j){
		@SuppressWarnings("unused")
		String str = " " + (j + i);
	    }

	    screen.setProgress(i);
	}
	splashScreenDestruct();
	ServerGui server = new ServerGui(portInt, recipients);
	server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void splashScreenDestruct(){

	screen.setScreenVisible(false);
    }

    private void splashScreenInit(){

	ImageIcon myImage = new ImageIcon("Notepad.jpg");
	screen = new SplashScreen(myImage);
	screen.setLocationRelativeTo(null);
	screen.setProgressMax(1000);
	screen.setScreenVisible(true);
    }

    public static void main(String[] args){

	try{
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
	catch (Exception e){
	    e.printStackTrace();
	}
	new ServerMain();
    }

}
