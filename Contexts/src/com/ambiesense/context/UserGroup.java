/*=================================================================
AmbieSense Context Software GPL Source Code.

Copyright (C) 2005 and onwards by AmbieSense Limited, 
 and/or its subsidiaries and co-founders. AmbieSense Limited
 is an R&D-focused company incorporated in Scotland.

This file is part of the AmbieSense Context Software GPL Source Code.

The AmbieSense Context Software GPL Source Code is free software: 
you can redistribute it and/or modify it under the terms of the 
GNU General Public License Version 3 as published by
the Free Software Foundation.

The AmbieSense Context Software Source Code is distributed in 
the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or 
FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with The AmbieSense Context Software Source Code. 
If not, see <http://www.gnu.org/licenses/>.

In addition, the The AmbieSense Context Software Source Code is also 
subject to certain additional terms. You should have received a copy 
of these additional terms immediately following the terms and 
conditions of the GNU General Public License which 
accompanied the The AmbieSense Context Software Source Code. 
If not, please request a copy in writing from id Software at the address below.

If you have questions concerning this license or the applicable 
additional terms, you may contact in writing: 
AmbieSense Limited, 7 Queens Gardens, Aberdeen, 
AB25 4YD, Scotland; or by email: 
kontactATambiesense.com (replace "AT" with "@").
============================================================ */

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
