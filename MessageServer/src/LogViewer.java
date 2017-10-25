import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class LogViewer extends JFrame implements Serializable{

	/**
	 * SERVER
	 */
	private static final long serialVersionUID = 1L;
	
	private final String LOG_FILENAME = "message.log";
	
	private JTextArea jtaMain = new JTextArea();
	
	public LogViewer(){
				
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu("File");
		JMenu menuEdit = new JMenu("Edit");
		JMenu menuHelp = new JMenu("Help");

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				
			}
		});
		
		JMenuItem clear = new JMenuItem("Clear Log");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				File file =new File(LOG_FILENAME);

				try {
					
					if(!file.exists()){
						
						file.createNewFile();
						
					}

					FileWriter fileWritter = new FileWriter(file.getName(), false);
					BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
					bufferWritter.write("");
					bufferWritter.close();
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				jtaMain.setText("");
				
			}
		});
		
		JMenuItem about = new JMenuItem("About");
				
		menuFile.add(exit);
		menuEdit.add(clear);
		menuHelp.add(about);

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);

		setJMenuBar(menuBar);
		
		getContentPane().setLayout(new BorderLayout(3, 3));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		jtaMain = new JTextArea();
		jtaMain.setEditable(false);
		jtaMain.setLineWrap(true);
		scrollPane.setViewportView(jtaMain);
		
		File file = new File(LOG_FILENAME);

		if(file.exists()){

			Scanner readIn;

			try{

				readIn = new Scanner(file);
				
				String current = "";

				while(readIn.hasNext()){

					current = readIn.nextLine();
					jtaMain.append(current);
					jtaMain.append("\n");

				}
				
			}catch(Exception e){

			}
		}
	}

}






















