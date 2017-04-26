package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class UserFx {

	private SimpleStringProperty login;
	private SimpleStringProperty haslo;
	
	public UserFx(String login, String haslo) {
		this.login = new SimpleStringProperty(login);
		this.haslo = new SimpleStringProperty(haslo);
	}
	
	public String getLogin() {
		return login.get();
	}
	
	public void setLogin(String login) {
		this.login.set(login);
	}
	
	public String getHaslo() {
		return haslo.get();
	}
	
	public void setHaslo(String haslo) {
		this.haslo.set(haslo);
	}
}
