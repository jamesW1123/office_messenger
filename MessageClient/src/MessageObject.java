import java.util.Date;

public class MessageObject implements java.io.Serializable {
		
	/**
	 * CLIENT
	 */
	private static final long serialVersionUID = 1L;
	private String date, name, address, phoneNumber, faxNumber, message, recipient;
	
	public MessageObject(String name, String address, String phoneNumber, 
			String faxNumber, String message, String recipient){
		
		this.date = new Date().toString();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.message = message;
		this.recipient = recipient;
		
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public String getMessage() {
		return message;
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public String getDate(){
		return date;
	}
	
	public String toString(){
		
		return ("Date: " + getDate().toString() + "\n" +
				"Recipient: " + getRecipient() + "\n" +
				"Name: " + getName() + "\n" +
				"Address: " + getAddress() + "\n" +
				"Phone Number: " + getPhoneNumber() + "\n" +
				"Fax Number: " + getFaxNumber() + "\n" +
				"Message: " + getMessage() + "\n");
		
	}
	
}

















