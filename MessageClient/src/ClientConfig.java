import javax.swing.*;
import java.awt.GridLayout;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.border.TitledBorder;
import java.awt.event.*;


public class ClientConfig extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serverIP, port;
	private final String CONFIG_FILENAME = "config.dat";
	private JTextField tfServerIP;
	private JTextField tfPort;
	
	public ClientConfig(){
		
		super("Client Config");
		
		File file = new File(CONFIG_FILENAME);

		if(file.exists()){

			Scanner readIn;

			try{

				readIn = new Scanner(file);
				
				serverIP = readIn.next();
				
				port = readIn.next();
					
				

			}catch(Exception e){

			}
		}
		
		getContentPane().setLayout(null);
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(new TitledBorder(null, "Network Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		settingsPanel.setBounds(12, 12, 424, 184);
		getContentPane().add(settingsPanel);
		settingsPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblServerIpAddress = new JLabel("Server IP Address");
		settingsPanel.add(lblServerIpAddress);
		
		tfServerIP = new JTextField();
		tfServerIP.setToolTipText("Enter the IP address of the Server computer.");
		settingsPanel.add(tfServerIP);
		tfServerIP.setColumns(10);
		
		JLabel lblPortdefault = new JLabel("Port (Default 7001)");
		settingsPanel.add(lblPortdefault);
		
		tfPort = new JTextField();
		tfPort.setToolTipText("Enter the network port number. This must match the port number in the Server configuration settings.");
		settingsPanel.add(tfPort);
		tfPort.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(40, 226, 379, 33);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 50, 20));
		
		JButton btnSave = new JButton("Save");
		btnSave.setToolTipText("Save and exit configuration.");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				serverIP = tfServerIP.getText();
				port = tfPort.getText();

				Formatter out = null;

				try{

					out = new Formatter(CONFIG_FILENAME);


				}catch(Exception ex){
					ex.printStackTrace();
				}

				out.format("%s", serverIP + "\n");
				out.format("%s", port + " ");

				out.close();	
				
				JOptionPane.showMessageDialog(null, "The program is shutting down.\n" +
						"Please restart");
				dispose();
				System.exit(0);

			}
		});
		panel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel and disregard any changes made.");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		panel.add(btnCancel);



	}
}
