package sd.project.util;

public class MailFactory {

	public static MailObject getMailObject(String type, String from, String to, String subject, String body) {
		
		if("Default".equalsIgnoreCase(type)) {
			
			return new DefaultMail(from, to);
		} else {
			
			return new Mail(from,to,subject,body);
		}
	}
}
