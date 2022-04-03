package sghaier.chahir.piusermanagement.registre.email;

public interface EmailSender {

	void send(String to,String email);

	void sendrendv(String to, String email);
	
	void sendmeet(String to, String email);
}
