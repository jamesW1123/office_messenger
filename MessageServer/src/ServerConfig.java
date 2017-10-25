import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ServerConfig extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//String variables
	private String[] recipients = new String[20];
	private String port = "7001";
	private final String CONFIG_FILENAME = "config.dat";
			
	//JTextFields
	private JTextField tf1 = new JTextField();
	private JTextField tf2 = new JTextField();
	private JTextField tf3 = new JTextField();
	private JTextField tf4 = new JTextField();
	private JTextField tf5 = new JTextField();
	private JTextField tf6 = new JTextField();
	private JTextField tf7 = new JTextField();
	private JTextField tf8 = new JTextField();
	private JTextField tf9 = new JTextField();
	private JTextField tf10 = new JTextField();
	private JTextField tf11 = new JTextField();
	private JTextField tf12 = new JTextField();
	private JTextField tf13 = new JTextField();
	private JTextField tf14 = new JTextField();
	private JTextField tf15 = new JTextField();
	private JTextField tf16 = new JTextField();
	private JTextField tf17 = new JTextField();
	private JTextField tf18 = new JTextField();
	private JTextField tf19 = new JTextField();
	private JTextField tf20 = new JTextField();
	private JTextField tfPort = new JTextField();

	//JButtons
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	private final JLabel lblPortdefault = new JLabel("Port (default 7001)");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;

	public ServerConfig(){

		super("Configuration");

		File file = new File(CONFIG_FILENAME);

		if(file.exists()){

			Scanner readIn;

			try{

				readIn = new Scanner(file);
				
				int index = 0;

				while(index < 20){
					
						recipients[index] = readIn.next();
						index++;
						readIn.hasNext();
				}
				
					port = readIn.next();
					
				

			}catch(Exception e){

			}
		}

		getContentPane().setLayout(null);

		JPanel tfPanel = new JPanel();
		tfPanel.setToolTipText("Enter the actual computer name. This must match the name of the computer.");
		tfPanel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Recipient Names", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Recipient Names", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfPanel.setBounds(248, 12, 225, 457);
		getContentPane().add(tfPanel);
		tfPanel.setLayout(new GridLayout(0, 1, 1, 1));
		tf1.setFont(new Font("Dialog", Font.PLAIN, 11));

		tfPanel.add(tf1);
		tf2.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf2);
		tf3.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf3);
		tf4.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf4);
		tf5.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf5);
		tf6.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf6);
		tf7.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf7);
		tf8.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf8);
		tf9.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf9);
		tf10.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf10);
		tf11.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf11);
		tf12.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf12);
		tf13.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf13);
		tf14.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf14);
		tf15.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf15);
		tf16.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf16);
		tf17.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf17);
		tf18.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf18);
		tf19.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf19);
		tf20.setFont(new Font("Dialog", Font.PLAIN, 11));
		tfPanel.add(tf20);

		getContentPane().add(tfPanel);

		JPanel networkPanel = new JPanel();
		networkPanel.setBorder(new TitledBorder(null, "Network Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		networkPanel.setBounds(485, 13, 247, 97);
		getContentPane().add(networkPanel);
		networkPanel.setLayout(new GridLayout(0, 1, 0, 0));
		tfPort.setToolTipText("Set the network port number.");
		tfPort.setFont(new Font("Dialog", Font.PLAIN, 14));

		tfPort.setColumns(10);

		networkPanel.add(lblPortdefault);		
		networkPanel.add(tfPort);

		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(544, 236, 146, 122);
		getContentPane().add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 1, 20, 20));
		
		for(int i = 0; i < recipients.length; i++){
			
			if(recipients[i].equals("NULL")) recipients[i] = "";
			
		}
		
		tf1.setText(recipients[0]);
		tf2.setText(recipients[1]);
		tf3.setText(recipients[2]);
		tf4.setText(recipients[3]);
		tf5.setText(recipients[4]);
		tf6.setText(recipients[5]);
		tf7.setText(recipients[6]);
		tf8.setText(recipients[7]);
		tf9.setText(recipients[8]);
		tf10.setText(recipients[9]);
		tf11.setText(recipients[10]);
		tf12.setText(recipients[11]);
		tf13.setText(recipients[12]);
		tf14.setText(recipients[13]);
		tf15.setText(recipients[14]);
		tf16.setText(recipients[15]);
		tf17.setText(recipients[16]);
		tf18.setText(recipients[17]);
		tf19.setText(recipients[18]);
		tf20.setText(recipients[19]);
		tfPort.setText(port);		
		btnSave.setToolTipText("Save the current configuration settings.");

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tf1.getText().equals("")) tf1.setText("NULL");
				if(tf2.getText().equals("")) tf2.setText("NULL");
				if(tf3.getText().equals("")) tf3.setText("NULL");
				if(tf4.getText().equals("")) tf4.setText("NULL");
				if(tf5.getText().equals("")) tf5.setText("NULL");
				if(tf6.getText().equals("")) tf6.setText("NULL");
				if(tf7.getText().equals("")) tf7.setText("NULL");
				if(tf8.getText().equals("")) tf8.setText("NULL");
				if(tf9.getText().equals("")) tf9.setText("NULL");
				if(tf10.getText().equals("")) tf10.setText("NULL");
				if(tf11.getText().equals("")) tf11.setText("NULL");
				if(tf12.getText().equals("")) tf12.setText("NULL");
				if(tf13.getText().equals("")) tf13.setText("NULL");
				if(tf14.getText().equals("")) tf14.setText("NULL");
				if(tf15.getText().equals("")) tf15.setText("NULL");
				if(tf16.getText().equals("")) tf16.setText("NULL");
				if(tf17.getText().equals("")) tf17.setText("NULL");
				if(tf18.getText().equals("")) tf18.setText("NULL");
				if(tf19.getText().equals("")) tf19.setText("NULL");
				if(tf20.getText().equals("")) tf20.setText("NULL");

				recipients[0] = tf1.getText().toUpperCase();
				recipients[1] = tf2.getText().toUpperCase();
				recipients[2] = tf3.getText().toUpperCase();
				recipients[3] = tf4.getText().toUpperCase();
				recipients[4] = tf5.getText().toUpperCase();
				recipients[5] = tf6.getText().toUpperCase();
				recipients[6] = tf7.getText().toUpperCase();
				recipients[7] = tf8.getText().toUpperCase();
				recipients[8] = tf9.getText().toUpperCase();
				recipients[9] = tf10.getText().toUpperCase();
				recipients[10] = tf11.getText().toUpperCase();
				recipients[11] = tf12.getText().toUpperCase();
				recipients[12] = tf13.getText().toUpperCase();
				recipients[13] = tf14.getText().toUpperCase();
				recipients[14] = tf15.getText().toUpperCase();
				recipients[15] = tf16.getText().toUpperCase();
				recipients[16] = tf17.getText().toUpperCase();
				recipients[17] = tf18.getText().toUpperCase();
				recipients[18] = tf19.getText().toUpperCase();
				recipients[19] = tf20.getText().toUpperCase();

				port = tfPort.getText();
				
				Formatter out = null;
				
				try{
					
					out = new Formatter(CONFIG_FILENAME);
					
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				for(int i = 0; i < recipients.length; i++){
					
					out.format("%s", recipients[i] + "\n");
					
				}
								
				out.format("%s", port + " ");				
				out.close();			
				
				dispose();

			}
		});		
		btnPanel.add(btnSave);
		btnCancel.setToolTipText("Cancel and disregard all changes.");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnPanel.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Enter the actual computer name. This must match the name of the computer.");
		panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Recipient Names", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Display Names", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 225, 457);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 1, 1));
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText((String) null);
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText((String) null);
		textField_6.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText((String) null);
		textField_7.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText((String) null);
		textField_8.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText((String) null);
		textField_9.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText((String) null);
		textField_10.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText((String) null);
		textField_11.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setText((String) null);
		textField_12.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText((String) null);
		textField_13.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setText((String) null);
		textField_14.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setText((String) null);
		textField_15.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setText((String) null);
		textField_16.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setText((String) null);
		textField_17.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setText((String) null);
		textField_18.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.setText((String) null);
		textField_19.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel.add(textField_19);
		
		

	}
}