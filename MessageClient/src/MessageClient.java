import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import sun.audio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class MessageClient extends JFrame implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int displayIndex, portInt, index, recPanelIndex = 0;
	
	private JLabel lblSendName = new JLabel("Name:");
	private JLabel lblSendPhoneNumber = new JLabel("Ph Number:");
	private JLabel lblSendFaxNumber = new JLabel("Fax Number:");
	private JLabel lblSendAddress = new JLabel("Address:");
	private JLabel lblSendStatus = new JLabel("Offline");
	private JLabel lblRecName = new JLabel("Name:");
	private JLabel lblRecPhoneNumber = new JLabel("Ph Number:");
	private JLabel lblRecFaxNumber = new JLabel("Fax Number:");
	private JLabel lblRecAddress = new JLabel("Address:");
	private JLabel lblRecStatus = new JLabel("Offline");
	
	private JTextField jtfSendName= new JTextField(); 
	private JTextField jtfSendPhoneNumber= new JTextField();
	private JTextField jtfSendFaxNumber= new JTextField();
	private JTextField jtfSendAddress= new JTextField();
	private JTextField jtfRecName= new JTextField(20); 
	private JTextField jtfRecPhoneNumber= new JTextField();
	private JTextField jtfRecFaxNumber= new JTextField();
	private JTextField jtfRecAddress= new JTextField();
	private JTextArea jtaRecMessage = new JTextArea();
	private static JTextArea jtaSendMessage = new JTextArea();
	
	private JButton jbtSend = new JButton("Send");
	private JButton jbtBack = new JButton("Back");
	private JButton jbtNext = new JButton("Next");
	private JButton jbtDelete = new JButton("Delete");
	
	private JCheckBox jcbAll = new JCheckBox("ALL");
	private JCheckBox jcb1 = new JCheckBox("");
	private JCheckBox jcb2 = new JCheckBox("");
	private JCheckBox jcb3 = new JCheckBox("");
	private JCheckBox jcb4 = new JCheckBox("");
	private JCheckBox jcb5 = new JCheckBox("");
	private JCheckBox jcb6 = new JCheckBox("");
	private JCheckBox jcb7 = new JCheckBox("");
	private JCheckBox jcb8 = new JCheckBox("");
	private JCheckBox jcb9 = new JCheckBox("");
	private JCheckBox jcb10 = new JCheckBox("");
	private JCheckBox jcb11 = new JCheckBox("");
	private JCheckBox jcb12 = new JCheckBox("");
	private JCheckBox jcb13 = new JCheckBox("");
	private JCheckBox jcb14 = new JCheckBox("");
	private JCheckBox jcb15 = new JCheckBox("");
	private JCheckBox jcb16 = new JCheckBox("");
	private JCheckBox jcb17 = new JCheckBox("");
	private JCheckBox jcb18 = new JCheckBox("");
	private JCheckBox jcb19 = new JCheckBox("");
	private JCheckBox jcb20 = new JCheckBox("");
	
	private String sendName = "";
	private String sendPhoneNumber = "";
	private String sendFaxNumber = "";
	private String sendAddress = "";
	private String sendMessage = "";
	private String sendPanelTab = "Send Message";
	private String recName = "";
	private String recPhoneNumber = "";
	private String recFaxNumber = "";
	private String recAddress = "";
	private String recMessage = "";
	private String recPanelTab = "Received Messages";
	private String serverIP, recipientString;
	private final String LOG_FILENAME = "message.log";
	private String[] recipients = new String[20];
	
	private JTabbedPane tp;
	
	private static MessageObject messageObject;
	private ArrayList<MessageObject> receivedMessages = new ArrayList<MessageObject>();
	private Socket socket;
	private static ObjectOutputStream output;
	private ObjectInputStream input;
			
	public MessageClient(String serverIP, int portInt){
		
		super("Tab Gui");
		setSize(660, 550);

		this.serverIP = serverIP;
		this.portInt = portInt;
		this.displayIndex = 0;
		this.index = 0;

		lblSendStatus.setForeground(Color.RED);
		lblSendStatus.setText("Offline. Check that server program is active and restart client.");

		lblRecStatus.setForeground(Color.RED);
		lblRecStatus.setText("Offline. Check that server program is active and restart client.");

		try {

			connectToServer();
			setUpStreams();
			setRecipients();

		}catch (IOException e1) {
			e1.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu("File");
		JMenu menuEdit = new JMenu("Edit");
		JMenu menuHelp = new JMenu("Help");

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClientConfig clientConfig = new ClientConfig();				
				clientConfig.setTitle("Config");
				clientConfig.setSize(450, 300);
				clientConfig.setLocationRelativeTo(null);
				clientConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				clientConfig.setVisible(true);
				
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
		
		/******
		 * Send Panel
		 */
		JPanel sendPanel = new JPanel();
		sendPanel.setToolTipText("Send message panel.");
		sendPanel.setLayout(null);
		
		JPanel lblSendPanel = new JPanel();
		lblSendPanel.setBounds(5, 17, 97, 149);
		lblSendPanel.setLayout(null);
		lblSendPanel.setLayout(new GridLayout(4, 1, 0, 0));		
		lblSendPanel.add(lblSendName);
		lblSendPanel.add(lblSendAddress);
		lblSendPanel.add(lblSendPhoneNumber);
		lblSendPanel.add(lblSendFaxNumber);
		
		JPanel tfSendPanel = new JPanel();
		tfSendPanel.setToolTipText("Message Information.");
		tfSendPanel.setBounds(109, 17, 272, 149);
		tfSendPanel.setLayout(null);
		tfSendPanel.setLayout(new GridLayout(4, 1, 0, 0));				
		tfSendPanel.add(jtfSendName);
		tfSendPanel.add(jtfSendAddress);
		tfSendPanel.add(jtfSendPhoneNumber);
		tfSendPanel.add(jtfSendFaxNumber);		
		
		JPanel messageSendPanel = new JPanel();
		messageSendPanel.setToolTipText("Message information.");
		messageSendPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), 
				"Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		messageSendPanel.setBounds(7, 174, 386, 213);		
		messageSendPanel.setLayout(null);
		
		JScrollPane scrollSendPane = new JScrollPane();
		scrollSendPane.setBounds(5, 17, 376, 184);
		messageSendPanel.add(scrollSendPane);		
		jtaSendMessage.setLineWrap(true);
		scrollSendPane.setViewportView(jtaSendMessage);
		
		jbtSend.setToolTipText("Send message.");		
		jbtSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				getInfo();
				messageObject = new MessageObject(sendName, sendAddress, 
						sendPhoneNumber, sendFaxNumber, sendMessage, recipientString);
				sendMessage();
				clearInfo();

			}
		});		
		jbtSend.setBounds(32, 398, 117, 25);
		jbtSend.setEnabled(false);
		
		lblSendStatus.setBounds(32, 435, 511, 15);
					
		JPanel checkPanel = new JPanel();
		checkPanel.setToolTipText("Select recipients to receive the message");
		checkPanel.setBorder(new TitledBorder(null, "Recipient(s)", TitledBorder.LEADING, 
				TitledBorder.TOP, null, null));
		checkPanel.setBounds(400, 12, 241, 375);
		checkPanel.setLayout(new GridLayout(21, 1, 0, 0));

		jcbAll.setSelected(true);
		checkPanel.add(jcbAll);
		if(!recipients[0].equals("NULL")){
			jcb1.setText(recipients[0]);
			checkPanel.add(jcb1);
		}
		if(!recipients[1].equals("NULL")){
			jcb2.setText(recipients[1]);
			checkPanel.add(jcb2);
		}
		if(!recipients[2].equals("NULL")){
			jcb3.setText(recipients[2]);
			checkPanel.add(jcb3);
		}
		if(!recipients[3].equals("NULL")){
			jcb4.setText(recipients[3]);
			checkPanel.add(jcb4);
		}
		if(!recipients[4].equals("NULL")){
			jcb5.setText(recipients[4]);
			checkPanel.add(jcb5);
		}
		if(!recipients[5].equals("NULL")){
			jcb6.setText(recipients[5]);
			checkPanel.add(jcb6);
		}
		if(!recipients[6].equals("NULL")){
			jcb7.setText(recipients[6]);
			checkPanel.add(jcb7);
		}
		if(!recipients[7].equals("NULL")){
			jcb8.setText(recipients[7]);
			checkPanel.add(jcb8);
		}
		if(!recipients[8].equals("NULL")){
			jcb9.setText(recipients[8]);
			checkPanel.add(jcb9);
		}
		if(!recipients[9].equals("NULL")){
			jcb10.setText(recipients[9]);
			checkPanel.add(jcb10);
		}
		if(!recipients[10].equals("NULL")){
			jcb11.setText(recipients[10]);
			checkPanel.add(jcb11);
		}
		if(!recipients[11].equals("NULL")){
			jcb12.setText(recipients[11]);
			checkPanel.add(jcb12);
		}
		if(!recipients[12].equals("NULL")){
			jcb13.setText(recipients[12]);
			checkPanel.add(jcb13);
		}
		if(!recipients[13].equals("NULL")){
			jcb14.setText(recipients[13]);
			checkPanel.add(jcb14);
		}
		if(!recipients[14].equals("NULL")){
			jcb15.setText(recipients[14]);
			checkPanel.add(jcb15);
		}
		if(!recipients[15].equals("NULL")){
			jcb16.setText(recipients[15]);
			checkPanel.add(jcb16);
		}
		if(!recipients[16].equals("NULL")){
			jcb17.setText(recipients[16]);
			checkPanel.add(jcb17);
		}
		if(!recipients[17].equals("NULL")){
			jcb18.setText(recipients[17]);
			checkPanel.add(jcb18);
		}
		if(!recipients[18].equals("NULL")){
			jcb19.setText(recipients[18]);
			checkPanel.add(jcb19);
		}
		if(!recipients[19].equals("NULL")){
			jcb20.setText(recipients[19]);
			checkPanel.add(jcb20);
		}
		
		sendPanel.add(lblSendPanel);
		sendPanel.add(tfSendPanel);
		sendPanel.add(messageSendPanel);
		sendPanel.add(lblSendStatus);
		sendPanel.add(checkPanel);
		sendPanel.add(jbtSend);
				
		/*****
		 * Receive Panel
		 */		
		JPanel receivePanel = new JPanel();		
		receivePanel.setToolTipText("Receive message panel.");
		receivePanel.setLayout(null);
		
		JPanel messageRecPanel = new JPanel();
		messageRecPanel.setToolTipText("Message information.");
		messageRecPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), 
				"Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		messageRecPanel.setBounds(7, 174, 386, 212);		
		messageRecPanel.setLayout(null);
		
		JScrollPane scrollRecPane = new JScrollPane();
		scrollRecPane.setBounds(5, 17, 376, 183);
		messageRecPanel.add(scrollRecPane);		
		jtaRecMessage.setLineWrap(true);
		scrollRecPane.setViewportView(jtaRecMessage);
		
		lblRecStatus.setBounds(20, 435, 511, 15);
		
		JPanel lblRecPanel = new JPanel();
		lblRecPanel.setBounds(5, 17, 97, 149);
		lblRecPanel.setLayout(null);
		lblRecPanel.setLayout(new GridLayout(4, 1, 0, 0));		
		lblRecPanel.add(lblRecName);
		lblRecPanel.add(lblRecAddress);
		lblRecPanel.add(lblRecPhoneNumber);
		lblRecPanel.add(lblRecFaxNumber);
		
		JPanel tfRecPanel = new JPanel();
		tfRecPanel.setToolTipText("Message information.");
		tfRecPanel.setBounds(109, 17, 272, 149);
		tfRecPanel.setLayout(null);
		tfRecPanel.setLayout(new GridLayout(4, 1, 0, 0));				
		tfRecPanel.add(jtfRecName);
		tfRecPanel.add(jtfRecAddress);
		tfRecPanel.add(jtfRecPhoneNumber);
		tfRecPanel.add(jtfRecFaxNumber);
		
		receivePanel.add(lblRecPanel);
		receivePanel.add(tfRecPanel);
		receivePanel.add(messageRecPanel);
		receivePanel.add(lblRecStatus);
		
		jbtBack.setToolTipText("Display previous message.");
		jbtBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(displayIndex == 0) displayIndex = 0;
				else displayIndex--;
				
				getRecInfo();
				setRecInfo();
				
			}
		});
		jbtBack.setBounds(20, 398, 117, 25);
		receivePanel.add(jbtBack);
		
		jbtNext.setToolTipText("Display next message.");
		jbtNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(displayIndex < receivedMessages.size() - 1) displayIndex++;
								
				getRecInfo();
				setRecInfo();
				
			}
		});
		jbtNext.setBounds(248, 398, 117, 25);
		receivePanel.add(jbtNext);
		
		jbtDelete.setToolTipText("Delete the current message.");
		jbtDelete = new JButton("Delete");
		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would" +
						" like to\ndelete this message?");
				
				if(answer == JOptionPane.YES_OPTION){
					
					receivedMessages.remove(displayIndex);
					
					if(displayIndex != 0) displayIndex--;
					if(recPanelIndex != 0) recPanelIndex--;
					
					tp.setTitleAt(1, recPanelTab + " ( " + recPanelIndex + " )");
					
					getRecInfo();
					setRecInfo();
					
				}
				
			}
		});
		jbtDelete.setBounds(465, 398, 117, 25);
		receivePanel.add(jbtDelete);

		tp = new JTabbedPane();		
		tp.addTab(sendPanelTab, sendPanel);
		tp.addTab(recPanelTab, receivePanel);
		tp.setTitleAt(1, recPanelTab + " ( " + recPanelIndex + " )");

		
		getContentPane().add(tp);
		
		setVisible(true);
		
	}
	
	public void startRunning(){

		try{

			
			whileConnected();

		}catch(EOFException eofException){
			showMessage("\n Client terminated connection");			
		}catch(IOException ioException){
		}finally{
			closeConnections();
		}
	}

	private void connectToServer(){		
		
			try {
				
				socket = new Socket(InetAddress.getByName(serverIP), portInt);
						
		} catch(NoRouteToHostException ne){
			ne.printStackTrace();
		}catch(SocketTimeoutException se){
			se.printStackTrace();
		}catch (UnknownHostException e) {
			e.printStackTrace();
			int reply = JOptionPane.showConfirmDialog(null, "Cannot connect to the server. " +
					"Check configuration settings, check that the server program is " +
					"running, and restart program.\n Would " +
					"you like to run configuration now?");
			
			if(reply == JOptionPane.YES_OPTION){
				
				ClientConfig clientConfig = new ClientConfig();				
				clientConfig.setTitle("Config");
				clientConfig.setSize(450, 300);
				clientConfig.setLocationRelativeTo(null);
				clientConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				clientConfig.setVisible(true);
				
			}
			else System.exit(0);
			
		} catch (IOException e) {
			e.printStackTrace();
			int reply = JOptionPane.showConfirmDialog(null, "Cannot connect to the server. " +
					"Check configuration settings and restart program.\n Would " +
					"you like to run configuration now?");
			
			if(reply == JOptionPane.YES_OPTION){
				
				ClientConfig clientConfig = new ClientConfig();				
				clientConfig.setTitle("Config");
				clientConfig.setSize(450, 300);
				clientConfig.setLocationRelativeTo(null);
				clientConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				clientConfig.setVisible(true);
				
			}
			else System.exit(0);
		} 
		
		lblSendStatus.setForeground(Color.BLACK);
		lblSendStatus.setText("Connected");
		lblRecStatus.setForeground(Color.BLACK);
		lblRecStatus.setText("Connected");
		
	}

	private void setUpStreams() throws IOException{
		
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();

		input = new ObjectInputStream(socket.getInputStream());
		
	}

	public void showMessage(String s){

		jtaSendMessage.append(s + "\n");

	}
	
	public void setRecipients(){
		
		try {
			
			recipients = (String[]) input.readObject();			
			
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		
	}

	private void whileConnected() throws IOException{

		jbtSend.setEnabled(true);

		while(true){

			try{
				
				//messageObject = (MessageObject) input.readObject();
				Object o = input.readObject();
				
				if(o.equals("SHUT DOWN")) closeConnections();

				else receivedMessages.add((MessageObject) input.readObject());
				
				recPanelIndex++;
				tp.setTitleAt(1, recPanelTab + " ( " + recPanelIndex + " )");
				
				getRecInfo();
				setRecInfo();
				logMessage(receivedMessages.get(index).toString());				

				FileInputStream in;
				//AudioStream audio;
				try{

					in = new FileInputStream("sound.wav");
					//audio = new AudioStream(in);
					//AudioPlayer.player.start(audio);
					

				}catch(Exception e){
					e.printStackTrace();
				}
				
				index++;

			}catch(ClassNotFoundException classNotFoundException){
				showMessage("ERROR");
			}
		}
	}
	
	public void logMessage(String m){
		
		File file =new File(LOG_FILENAME);

		try {
			
			if(!file.exists()){
				
				file.createNewFile();
				
			}

			FileWriter fileWritter = new FileWriter(file.getName(),true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(m);
			bufferWritter.write("\n");
			bufferWritter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void getInfo(){

		sendName = jtfSendName.getText();
		sendAddress = jtfSendAddress.getText();
		sendPhoneNumber = jtfSendPhoneNumber.getText();
		sendFaxNumber = jtfSendFaxNumber.getText();
		sendMessage = jtaSendMessage.getText();
		recipientString = getRecipientString();

	}

	private String getRecipientString(){

		String r = "";

		if(jcbAll.isSelected()){
			
			for(int i = 0; i < recipients.length; i++){

				if(!recipients[i].equals("NULL")) r+= recipients[i] + " ";

			}

		}
		else{ 
			if(jcb1.isSelected()) r+= recipients[0] + " ";		
			if(jcb2.isSelected()) r+= recipients[1] + " ";
			if(jcb3.isSelected()) r+= recipients[2] + " ";
			if(jcb4.isSelected()) r+= recipients[3] + " ";
			if(jcb5.isSelected()) r+= recipients[4] + " ";
			if(jcb6.isSelected()) r+= recipients[5] + " ";
			if(jcb7.isSelected()) r+= recipients[6] + " ";
			if(jcb8.isSelected()) r+= recipients[7] + " ";
			if(jcb9.isSelected()) r+= recipients[8] + " ";
			if(jcb10.isSelected()) r+= recipients[9] + " ";
			if(jcb11.isSelected()) r+= recipients[10] + " ";
			if(jcb12.isSelected()) r+= recipients[11] + " ";
			if(jcb13.isSelected()) r+= recipients[12] + " ";
			if(jcb14.isSelected()) r+= recipients[13] + " ";
			if(jcb15.isSelected()) r+= recipients[14] + " ";
			if(jcb16.isSelected()) r+= recipients[15] + " ";
			if(jcb17.isSelected()) r+= recipients[16] + " ";
			if(jcb18.isSelected()) r+= recipients[17] + " ";
			if(jcb19.isSelected()) r+= recipients[18] + " ";
			if(jcb20.isSelected()) r+= recipients[19] + " ";
		}

		return r;

	}

	private void clearInfo(){

		sendName = "";
		sendAddress = "";
		sendPhoneNumber = "";
		sendFaxNumber = "";
		sendMessage = "";

		jtfSendName.setText("");
		jtfSendAddress.setText("");
		jtfSendPhoneNumber.setText("");
		jtfSendFaxNumber.setText("");
		jtaSendMessage.setText("");

		jcbAll.setSelected(true);
		jcb1.setSelected(false);
		jcb2.setSelected(false);
		jcb3.setSelected(false);
		jcb4.setSelected(false);
		jcb5.setSelected(false);
		jcb6.setSelected(false);
		jcb7.setSelected(false);
		jcb8.setSelected(false);
		jcb9.setSelected(false);
		jcb10.setSelected(false);
		jcb11.setSelected(false);
		jcb12.setSelected(false);
		jcb13.setSelected(false);
		jcb14.setSelected(false);
		jcb15.setSelected(false);
		jcb16.setSelected(false);
		jcb17.setSelected(false);
		jcb18.setSelected(false);
		jcb19.setSelected(false);
		jcb20.setSelected(false);

	}

	protected static void sendMessage(){
		
		try{

			output.writeObject(messageObject);			
			output.flush();

		}catch(IOException ioException){
			jtaSendMessage.append("\n Error sending message");
		}

	}
	
	protected static void sendMessage(String str){

		try{

			output.writeObject(str);			
			output.flush();

		}catch(IOException ioException){
			jtaSendMessage.append("\n Error sending message");
		}

	}

	private void getRecInfo(){

		//set MessageObject to current indexed object
		MessageObject current = receivedMessages.get(displayIndex);

		recName = current.getName();
		recAddress = current.getAddress();
		recPhoneNumber = current.getPhoneNumber();
		recFaxNumber = current.getFaxNumber();
		recMessage = current.getMessage();

	}

	private void setRecInfo(){

		jtfRecName.setText(recName);
		jtfRecAddress.setText(recAddress);
		jtfRecPhoneNumber.setText(recPhoneNumber);
		jtfRecFaxNumber.setText(recFaxNumber);
		jtaRecMessage.setText(recMessage);

	}

	private void closeConnections(){

		showMessage("\n Closing down ...");

		jbtSend.setEnabled(false);

		try{

			output.close();
			input.close();
			socket.close();
			lblSendStatus.setForeground(Color.RED);
			lblSendStatus.setText("Offline. Check that server program is active and restart client.");
			lblRecStatus.setForeground(Color.RED);
			lblRecStatus.setText("Offline. Check that server program is active and restart client.");

		}catch(IOException ioException){
			System.out.println("Good-bye");
		}
	}

}






	
	
	
	
	
	
	
	
	
