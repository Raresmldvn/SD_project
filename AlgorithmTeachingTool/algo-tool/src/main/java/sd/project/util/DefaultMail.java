package sd.project.util;

public class DefaultMail extends MailObject{

	 private String from;
	    private String to;
	    private String subject;
	    private String content;

	    public DefaultMail() {
	    }

	    public DefaultMail(String from, String to) {
	        this.from = from;
	        this.to = to;
	        this.subject = "Notification from " + from;
	        this.content = "You have received a request " + from + ". Manage it when you enter your account on SATT.";
	    }

	    public String getFrom() {
	        return from;
	    }

	    public void setFrom(String from) {
	        this.from = from;
	    }

	    public String getTo() {
	        return to;
	    }

	    public void setTo(String to) {
	        this.to = to;
	    }

	    public String getSubject() {
	        return subject;
	    }

	    public String getContent() {
	        return content;
	    }


}
