package com.ambiesense.context;


public class UserGroup {

	private int _userGroupID = 0;
	private Users _users = new Users();
	private String _userGroupName="";

	public UserGroup(String userGroupName) {
		_userGroupName = userGroupName;
	}
	
	public boolean add(User user){
		boolean success= false;
		String userName = user.getLogin();
		String password = user.getPassword();
		if(userName!="" && password!=""){
			_users.addElement(user);
			success=true;
		}
		return success;
	}
	

	public void deleteUser(int pos){
		if(pos<_users.size()){
			_users.remove(pos);
		}
	}	

	public Users getUsers(){
		return _users;
	}

	public void setName(String name){
		_userGroupName = name;
	}

	public String getName(){
		return _userGroupName;
	}
	
	public int getID(){
		return _userGroupID;
	}

	protected void setID(int id, Object o){
    if(o.getClass().getName().equals("com.ambiesense.context.ContextSpace")){
			_userGroupID = id;
		} else {
      System.out.println("Unpermitted access to UserGroupID - access denied!");
    }
	}
}
