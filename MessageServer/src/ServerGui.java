import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ServerGui extends JFrame implements Serializable{

    /**
     * 
     */
    private static final long		      serialVersionUID = 1L;
    private int				      portInt;
    protected static int		      index;

    private String[]			      recipients       = new String[20];
    protected static ArrayList<HandleAClient> clientObjectList =
	    new ArrayList<HandleAClient>();
    protected static ArrayList<String>	      clientNameList   =
	    new ArrayList<String>();

    private JButton			      shutDown	       =
	    new JButton("Shut Down");
    protected static JTextArea		      messageLog;

    private ServerSocket		      serverSocket;

    public ServerGui(int portInt, String[] recipients){

	super("Message Server");

	this.portInt = portInt;
	this.recipients = recipients;

	JMenuBar menuBar = new JMenuBar();

	JMenu menuFile = new JMenu("File");
	JMenu menuEdit = new JMenu("Edit");
	JMenu menuHelp = new JMenu("Help");

	JMenuItem exit = new JMenuItem("Exit");
	exit.addActionListener(new ActionListener(){

	    public void actionPerformed(ActionEvent e){

		dispose();
		System.exit(0);

	    }
	});
	JMenuItem log = new JMenuItem("View Log");
	log.addActionListener(new ActionListener(){

	    public void actionPerformed(ActionEvent e){

		LogViewer logViewer = new LogViewer();
		logViewer.setTitle("Message Log");
		logViewer.setSize(525, 510);
		logViewer.setLocationRelativeTo(null);
		logViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		logViewer.setVisible(true);

	    }
	});
	JMenuItem config = new JMenuItem("Config");
	config.addActionListener(new ActionListener(){

	    public void actionPerformed(ActionEvent arg0){

		ServerConfig serverConfig = new ServerConfig();
		serverConfig.setTitle("Config");
		serverConfig.setSize(525, 510);
		serverConfig.setLocationRelativeTo(null);
		serverConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverConfig.setVisible(true);

	    }
	});
	JMenuItem about = new JMenuItem("About");

	menuFile.add(log);
	menuFile.add(exit);
	menuEdit.add(config);
	menuHelp.add(about);

	menuBar.add(menuFile);
	menuBar.add(menuEdit);
	menuBar.add(menuHelp);

	setJMenuBar(menuBar);
	shutDown.setToolTipText(
		"Close all connections to Client programs. Clients must be restarted.");

	shutDown.addActionListener(new ActionListener(){

	    @Override
	    public void actionPerformed(ActionEvent event){

		messageLog.append("\nSHUT DOWN\n");

		try{
		    closeConnections();
		}
		catch (IOException e){
		    e.printStackTrace();
		}

	    }

	});
	getContentPane().setLayout(new BorderLayout(0, 0));

	getContentPane().add(shutDown, BorderLayout.NORTH);
	messageLog = new JTextArea();
	messageLog.setEditable(false);
	messageLog.setLineWrap(true);
	messageLog.setAutoscrolls(true);
	getContentPane().add(new JScrollPane(messageLog));

	setSize(620, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	try{

	    serverSocket = new ServerSocket((this.portInt));
	    writeLog("Multi-Thread Server Started");

	    index = 0;

	    while (true){

		Socket socket = serverSocket.accept();

		writeLog("\nStarting thread for client " + index + " at "
			+ new Date() + "\n");

		InetAddress inetAddress = socket.getInetAddress();
		writeLog("Client " + (index + 1) + "'s host name is "
			+ inetAddress.getHostName() + "\n");
		writeLog("Client " + (index + 1) + "'s IP address is "
			+ inetAddress.getHostAddress() + "\n");

		String name = inetAddress.getHostName().toUpperCase();

		clientNameList.add(name);
		clientObjectList
			.add(new HandleAClient(socket, this.recipients));
		Thread t = new Thread(clientObjectList.get(index));
		t.start();

		index++;

	    }

	}
	catch (IOException ex){
	}
    }

    public static void writeLog(String message){

	messageLog.append(message);

    }

    public void closeConnections() throws IOException{

	int i = clientObjectList.size() - 1;

	while (!clientObjectList.isEmpty()){

	    clientObjectList.get(i).sendMessage("SHUT DOWN");
	    clientObjectList.remove(i);
	    i--;
	}

    }

}
