import java.io.*;
import java.net.Socket;

public class HandleAClient implements Runnable{

    /**
     * 
     */
    private Socket	       socket;
    private ObjectInputStream  input;
    private ObjectOutputStream output;
    private Object	       message;
    private String[]	       recipients   = new String[20];
    private final String       LOG_FILENAME = "message.log";

    public HandleAClient(Socket socket, String[] recipients){

	this.socket = socket;
	this.recipients = recipients;

    }

    @Override
    public void run(){

	try{

	    input = new ObjectInputStream(this.socket.getInputStream());
	    output = new ObjectOutputStream(this.socket.getOutputStream());

	    sendMessage(recipients);

	    while (true){

		message = input.readObject();

		if (message.equals("SHUT DOWN")) remove();

		String[] recipientArray =
			((MessageObject) message).getRecipient().split(" ");

		for (int i = 0; i < recipientArray.length; i++){

		    for (int j = 0; j < ServerGui.clientNameList.size(); j++){

			if (recipientArray[i]
				.equals(ServerGui.clientNameList.get(j))){

			    ServerGui.clientObjectList.get(j)
				    .sendMessage((MessageObject) message);
			    ServerGui.clientObjectList.get(j)
				    .sendMessage((MessageObject) message);
			}
		    }
		}

		logMessage(message.toString());
	    }
	}
	catch (IOException e){
	}
	catch (ClassNotFoundException ce){
	}
    }

    public void sendMessage(Object m) throws IOException{

	output.writeObject(m);
	output.flush();
    }

    public void logMessage(String m){

	File file = new File(LOG_FILENAME);

	try{

	    if (!file.exists()){

		file.createNewFile();
	    }

	    FileWriter fileWritter = new FileWriter(file.getName(), true);
	    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	    bufferWritter.write(m);
	    bufferWritter.write("\n");
	    bufferWritter.close();
	}
	catch (IOException e){
	    e.printStackTrace();
	}
    }

    public void remove(){

	String name = this.socket.getInetAddress().getHostName();

	for (int j = 0; j < ServerGui.clientNameList.size(); j++){

	    if (name.equals(ServerGui.clientNameList.get(j))){

		ServerGui.clientObjectList.remove(j);
		ServerGui.clientNameList.remove(j);
		ServerGui.index--;
	    }
	}
    }

    public void closeConnection() throws IOException{

	output.flush();
	input.close();
	output.close();
	socket.close();
	ServerGui.clientNameList.remove(this);
	ServerGui.clientObjectList.remove(this);
    }
}
