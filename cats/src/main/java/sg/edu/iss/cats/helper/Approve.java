package sg.edu.iss.cats.helper;

public class Approve {
	
	private String decision;
	private String comment;
	public Approve() {
		super();
		
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Approve [decision=" + decision + ", comment=" + comment + "]";
	}

}
