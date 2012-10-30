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
import com.ambiesense.persistent.AbstractPersistentObject;
import com.ambiesense.util.Attributes;
import com.ambiesense.util.Links;

/**
 * <p>
 *  A context is based on a template. The template has attributes that the context 
 *  automatically inherits. No attributes can be added to a context. All attributes
 *  must already exist in the template. Each of the attributes have their specific 
 *  data primitive type of number, boolean, or text. Relations can be added to context.
 * </p>
 * 
 * @author AmbieSense Ltd 
 * @version 0.1
 * 
 */  
public class Context extends AbstractPersistentObject implements Serializable{

	private static final long serialVersionUID = 1L;	
	private Attributes attributes = new Attributes();
	private Links links = new Links();
	private long timestamp = System.currentTimeMillis();
	private Template template = null;
	private String templateName = "";

	public Context(Template template){	
		try{
			if (template != null){
				this.template = template;
				this.templateName = this.template.getTemplateName();
				Attributes attributes = template.getAttributes();
				Enumeration<String> e = attributes.keys();
				while (e.hasMoreElements()){
					String name = e.nextElement().toLowerCase();
					Value value = attributes.get(name);
					long type = value.getType();
					if (type == Value.BOOLEAN){
						this.addAttribute(name, Value.DEFAULT_BOOLEAN_VALUE);
					}
					else if (type == Value.DOUBLE){
						this.addAttribute(name, Value.DEFAULT_DOUBLE_VALUE);		
					}
					else if (type == Value.LONG){
						this.addAttribute(name, Value.DEFAULT_LONG_VALUE);		
					}
					else if (type == Value.STRING){
						this.addAttribute(name, Value.DEFAULT_STRING_VALUE);												
					}
					else if (type == Value.URL){
						this.addAttribute(name, new URL("http://localhost"));												
					}
					else if (type == Value.FILE){
						this.addAttribute(name, new File("index.html"));												
					}
				}
			}			

		}catch(Exception e){}
	}


	Context(){}


	
	void setTemplateName(String templateName){	 
		if (templateName != null) this.templateName = templateName;
	}
	

	public void setId(String id){
		super.setId(id);
	}

	public void addLink(Link link){
		try{
			if (link != null){
				link.setContextId(this.getId());
				this.links.put(link.getLinkUUID(), link);
			}			
		}catch(Exception e){}
	}

	public void deleteLink(Link link){
		if (link != null) this.links.remove(link.getLinkUUID());
	}

	public Links getLinks(){
		return this.links;	
	}

	protected void setLinks(Links links){
		if (links != null) this.links = links;	
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public Object getAttribute(String name){
		return attributes.get(name);
	}

	protected void addAttribute(String name, double value){
		if (name != null){
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, double:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, String value){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, boolean value){
		if (name != null){
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, boolean:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, long value){
		if (name != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, long:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}
	protected void addAttribute(String name, URL value){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}
	protected void addAttribute(String name, File value){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, double value, long timestamp){
		if (name != null){
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, double:"+ this.getId() + ", " + name + " = " + value + ", timestamp = " + timestamp + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, String value, long timestamp){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", timestamp = " + timestamp + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, boolean value, long timestamp){
		if (name != null){
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, boolean:"+ this.getId() + ", " + name + " = " + value + ", timestamp = " + timestamp + ", type = " + v.getType());
		}
	}

	protected void addAttribute(String name, long value, long timestamp){
		if (name != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, long:"+ this.getId() + ", " + name + " = " + value + ", timestamp = " + timestamp + ", type = " + v.getType());
		}
	}
	protected void addAttribute(String name, URL value, long timestamp){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}
	protected void addAttribute(String name, File value, long timestamp){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}

	public void updateAttribute(String name, double value){
		if (name != null){
			name = name.toLowerCase();
			Value v = (Value) this.attributes.get(name);
			if (v != null) {
				v.updateValue(value);	
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}
	}

	public void updateAttribute(String name, boolean value){
		if (name != null){
			name = name.toLowerCase();
			Value v = (Value) this.attributes.get(name);
			if (v != null) {
				v.updateValue(value);	
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}		
	}

	public void updateAttribute(String name, String value){
		if (name != null && value != null){
			name = name.toLowerCase();
			Value v = (Value) this.attributes.get(name);
			if (v != null) {
				v.updateValue(value);	
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}
	}
	public void updateAttribute(String name, URL value){
		if (name != null && value != null){
			name = name.toLowerCase();
			Value v = (Value) this.attributes.get(name);
			if (v != null) {
				v.updateValue(value);	
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}
	}
	public void updateAttribute(String name, File value){
		if (name != null && value != null){
			name = name.toLowerCase();
			Value v = (Value) this.attributes.get(name);
			if (v != null) {
				v.updateValue(value);	
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}
	}



	public void updateAttribute(String name, long value){
		if (name != null){
			name = name.toLowerCase();
			Value v = this.attributes.get(name);
			if (v != null){
				v.updateValue(value);			
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}	
	}

	public void updateAttribute(String name, double value, long timestamp){
		if (name != null){
			name = name.toLowerCase();
			Value v = this.attributes.get(name);
			if (v != null) {
				v.updateValue(value, timestamp);
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}		
	}
	public void updateAttribute(String name, boolean value, long timestamp){
		if (name != null){
			name = name.toLowerCase();
			Value v = this.attributes.get(name);
			if (v != null) {
				v.updateValue(value, timestamp);
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}		
	}
	public void updateAttribute(String name, String value, long timestamp){
		if (name != null && value != null){
			name = name.toLowerCase();
			Value v = this.attributes.get(name);
			if (v != null){
				v.updateValue(value, timestamp);
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}
		}
	}
	public void updateAttribute(String name, long value, long timestamp){
		if (name != null){
			name = name.toLowerCase();
			Value v = this.attributes.get(name);
			if (v != null){
				v.updateValue(value, timestamp);
				System.out.println("Context:"+ this.getId() + ", " + name + " = " + v.getType());
			}				
		}	
	}
	protected void updateAttribute(String name, java.net.URL value, long timestamp){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}
	protected void updateAttribute(String name, File value, long timestamp){
		if (name != null && value != null) {
			name = name.toLowerCase();
			Value v = new Value(value, timestamp);
			this.attributes.put(name, v);			
			System.out.println("Context, add attribute, string:"+ this.getId() + ", " + name + " = " + value + ", type = " + v.getType());
		}
	}

	public String toString(){
		String t = "";
		if (this.templateName != null) t = this.templateName;
		String result = "  <context id=\"";
		result += this.getId() + "\"";
		result += " template=\"" + t + "\">\n";
		Enumeration<String> e = this.attributes.keys();
		while (e.hasMoreElements()){
			String name = e.nextElement();
			Value value = this.attributes.get(name);
			result += "    <attribute " + name + "=" + "\"" + value.get(value.getTimestamp()) + "\" />\n";
		}
		Enumeration<Link> elements = this.getLinks().elements();
		while (elements.hasMoreElements()){
			Link link = elements.nextElement();
			result += link.toString() + '\n';
		}
		result +=  "  </context>" + '\n';
		return result;
	}

	public Template getTemplate() {
		return template;
	}
}

