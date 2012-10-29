package com.ambiesense.context;

public class User {

	private int _userID = 0;
	private String _password = "";
	private String _login = "";
	private String _name = "";

	public User(String login, String password){
		_login = login;
		_password = password;
		_name = "";  
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password){
		_password = password;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login){
		_login = login;
	}

	public int getUserID(){
		return _userID;
	}

	protected void setUserID(int userID, Object o){
		if(o.getClass().getName().equals("com.ambiesense.context.ContextSpace")){
			_userID = userID;
		} else {
			System.out.println("Unpermitted access to UserID - access denied!");
		}
	}

	public String getName(){
		return _name;
	}

	public void setName(String name){
		_name = name;
	}
}