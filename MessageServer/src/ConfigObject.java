
public class ConfigObject {

	private String[] recipients = new String[20];
	private String serverIP, port;
	
	public ConfigObject(Object o){
		
		String[] s = o.toString().split(" ");
		
		for(int i = 0; i < recipients.length; i++){
			
			this.recipients[i] = s[i];
			
		}
		
		this.serverIP = s[20];
		this.port = s[21];
		
	}
	
	public String[] getRecipients() {
		return recipients;
	}
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String toString(){
		
		String s = "";
		String[] r = getRecipients();
		
		for(int i = 0; i < 20; i++){
			
			s+= r[i] + " ";
			
		}
		
		s+= getServerIP() + " ";
		s+= getPort() + " ";
		
		return s;
		
	}
	
	
}