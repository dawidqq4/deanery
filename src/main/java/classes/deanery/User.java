package classes.deanery;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int idUser, approved;
	String login, password;
	
	public User(int idUser, String login, String password) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
	}
	
	public User(int idUser, String login, String password, int approved) {
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.approved = approved;
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public User() {
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setApproved(int approved) {
		this.approved = approved;
	}
	
	public int getApproved() {
		return approved;
	}
	
	@Override
	public String toString() {
		return "User: " + getLogin() + " " + getPassword() + " " + getApproved();
	}
}
