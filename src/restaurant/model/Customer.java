package restaurant.model;

public class Customer {
	
	private int idcustomer;
	private String Name;
	private String Email;
	private String Guest;
	private String DateTime;
	private String Comments;
	
	public int getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getGuest() {
		return Guest;
	}
	public void setGuest(String guest) {
		Guest = guest;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	
}
