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