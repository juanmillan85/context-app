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

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ambiesense.persistent.AbstractPersistentObject;
import com.ambiesense.persistent.UUIDGenerator;
import com.ambiesense.util.Attributes;
import com.ambiesense.util.DistanceFilters;

/**
 * <p>
 *  A template defines the structure of the contexts that are instantiated from
 *  the template. One need to add attributes to the template. Once a context is 
 *  instantiated from the template, it automatically inherits all the attributes.
 *  </p>
 * 
 * @author Hans Myrhaug
 * @since Amber 0.1
 * @version 1.0 
 * 
 * <br>
 * <br>
 * <p> 
 *  AmbieSense Ltd   
 * </p>
 * <p>	
 * (C) Copyright from 2005 and onwards by AmbieSense Ltd.	All rights reserved.
 * </p>
 * <p>
 * <b>NOTICE:</b>  All information contained herein or attendant hereto is,
 * and remains, the property of AmbieSense Ltd.  Many of the
 * intellectual and technical concepts contained herein are
 * proprietary to AmbieSense Ltd and may be covered
 * by National and Foreign Patents or Patents Pending, or are
 * protected as trade secrets.  Any dissemination of this
 * information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained
 * from AmbieSense Ltd.
 * </p>
 */  
public final class Template implements Serializable{

	private static final long serialVersionUID = 1L;
	private Attributes attributes = new Attributes();
	private DistanceFilters filters = new DistanceFilters();
	private String templateName = "";
	long timestamp = System.currentTimeMillis();
	

	public Template(String templateName) {
		this.templateName = UUIDGenerator.createId();
	    if (templateName != null) this.templateName = templateName;
	}
		
	public String getTemplateName(){
		return this.templateName;
	}

	public boolean addBooleanAttribute(String name){
		if ((name = parseAttributeName(name)) != null){ 
			this.attributes.put(name, new Value(Value.DEFAULT_BOOLEAN_VALUE, timestamp));
			this.filters.put(name, new Double(0));
			return true;
		} else{
			return false;
		}
	}

	public boolean addDoubleAttribute(String name){
		if ((name = parseAttributeName(name)) != null){ 
			this.attributes.put(name, new Value(Value.DEFAULT_DOUBLE_VALUE, timestamp));
			this.filters.put(name, new Double(0));
			return true;
		} else{
			return false;
		}
	}

	public boolean addLongAttribute(String name){
		if ((name = parseAttributeName(name)) != null){
			this.attributes.put(name, new Value(Value.DEFAULT_LONG_VALUE, timestamp));
			this.filters.put(name, new Double(0));
			return true;
		} else{
			return false;
		}
	}

	public boolean addStringAttribute(String name){
		if ((name = parseAttributeName(name)) != null){ 
			this.attributes.put(name, new Value(Value.DEFAULT_STRING_VALUE, timestamp));
			this.filters.put(name, new Double(0));
			return true;
		} else{
			return false;
		}
	}
	public boolean addUrlAttribute(String name){
		try{
			if ((name = parseAttributeName(name)) != null){ 
				this.attributes.put(name, new Value(new URL("http://www.ambiesense.com"), timestamp));
				this.filters.put(name, new Double(0));
				return true;
			} else{
				return false;
			}			
		}catch (Exception e){return false;}
	}
	public boolean addFileAttribute(String name){
		if ((name = parseAttributeName(name)) != null){ 
			this.attributes.put(name, new Value(new File("index.html"), timestamp));
			this.filters.put(name, new Double(0));
			return true;
		} else{
			return false;
		}
	}



	public boolean addAttribute(String name, long type){
		try{
			if ((name = parseAttributeName(name)) != null){
				if (type == Value.BOOLEAN){
					this.attributes.put(name, new Value(Value.DEFAULT_BOOLEAN_VALUE, timestamp));
					this.filters.put(name, new Double(0));
					return true;
				}
				else if (type == Value.DOUBLE){
					this.attributes.put(name, new Value(Value.DEFAULT_DOUBLE_VALUE, timestamp));				
					this.filters.put(name, new Double(0));
					return true;
				}
				else if (type == Value.STRING){
					this.attributes.put(name, new Value(Value.DEFAULT_STRING_VALUE, timestamp));			
					this.filters.put(name, new Double(0));
					return true;
				}
				else if (type == Value.LONG){
					this.attributes.put(name, new Value(Value.DEFAULT_LONG_VALUE, timestamp));			
					this.filters.put(name, new Double(0));
					return true;
				}
				else if (type == Value.URL){
					this.attributes.put(name, new Value(new URL("http://www.ambiesense.com"), timestamp));			
					this.filters.put(name, new Double(0));
					return true;
				}
				else if (type == Value.FILE){
					this.attributes.put(name, new Value(new File("index.html"), timestamp));			
					this.filters.put(name, new Double(0));
					return true;
				}

			} else{
				return false;
			}
			return false;			
		}catch(Exception e){
			return false;
		}

	}

	/**
	 * Get the Attributes of this  Template
	 */
	public Attributes getAttributes(){
		return this.attributes;
	}

	public Double getDistanceFilter(String name){
		return this.filters.get(name);
	}
	
	public DistanceFilters getDistanceFilters(){
		return this.filters;
	}
	


	public void setDistanceFilter(String name, double maxDistance){
		if (name != null && this.attributes.containsKey(name)){
			this.filters.put(name, new Double(Math.abs(maxDistance)));				
		}							
	}

	public String toString(){
		String result = "  <template name='";
		result += this.templateName + "'";
		Enumeration<String> e = this.attributes.keys();
		while (e.hasMoreElements()){
			String name = e.nextElement();
			Object value = this.attributes.get(name);
			result += " " + name + "=" + "'" + value.toString() + "'";
		}
		result += " />";
		return result;
	}
	
	/**
	 * The Matcher.matches method attempts to match the *entire*
	 * input to the given pattern all at once.
	 */
	public static String parseAttributeName(String attributeName){
		if (attributeName == null) return null;
		attributeName = attributeName.toLowerCase();
		Pattern pattern = Pattern.compile( fREGEXP, Pattern.COMMENTS );
		Matcher matcher = pattern.matcher( attributeName );
		if( matcher.matches() ) {
			return attributeName;
		}
		else {
			return null;
		}
	}

	//PRIVATE //

	private static final String fNEW_LINE = System.getProperty("line.separator");

	/**
	 * A commented regular expression for fully-qualified type names which
	 * follow the common naming conventions, for example, "com.myappBlah.thing".
	 *
	 * Thus, the "dot + letter" is sufficient to define where the
	 * package names end.
	 *
	 * This regular expression uses two groups, one for the package, and one
	 * for the class. Groups are defined by parentheses. Note that ?: will
	 * define a group as "non-contributing"; that is, it will not contribute
	 * to the return values of the <code>group</code> method.
	 */
	private static final String fREGEXP =
		"#Group1 - Package prefix without last dot: " + fNEW_LINE +
		"( (?:\\w|\\.)+ ) \\." + fNEW_LINE +
		"#Group2 - Class name starts with uppercase: " + fNEW_LINE +
		"( [a-z](?:\\w)+ )";
}
