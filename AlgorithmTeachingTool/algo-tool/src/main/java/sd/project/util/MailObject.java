package sd.project.util;

public abstract class MailObject {

	public abstract String getFrom();
	public abstract String getTo();
	public abstract String getSubject();
	public abstract String getContent();
	
	public String toString() {
		
		return "From " + this.getFrom() + " to " + this.getTo() + "\n" + "Subject: " + this.getSubject()
				+ "\n" + this.getContent();
	}
}
