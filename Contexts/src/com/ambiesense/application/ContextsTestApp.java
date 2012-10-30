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

package com.ambiesense.application;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;

import com.ambiesense.context.Context;
import com.ambiesense.context.ContextAccess;
import com.ambiesense.context.ContextSpace;
import com.ambiesense.context.Template;
import com.ambiesense.context.Link;
import com.ambiesense.context.User;
import com.ambiesense.util.Links;
import com.ambiesense.util.Relevance;
import com.ambiesense.util.Templates;
import com.ambiesense.util.Contexts;

public class ContextsTestApp {
	long timeBefore = 0;
	long timeAfter = 0;
	long timeUsed = 0;
	Template _ct;
	User myUser = new User("Hans", "");

	//declare contextSpace
	private ContextSpace contextSpace;
	//declare context middleware
	private ContextAccess cmInstance;

	public static void main(String args[]) {
		new ContextsTestApp();
	}

	public ContextsTestApp() {
		try {
			System.out.println("Test start...");
			cmInstance = ContextAccess.getInstance(myUser);
			System.out.println("Here.");
			contextSpace = ContextSpace.getInstance(myUser);
			System.out.println("Start buildTemplate.");

			Template template = this.buildTemplate();
			System.out.println("TEMPLATE IS NULL: "+ (template == null));
			Context c1 = buildContext(template);

			Template template2 = this.buildTemplate2();
			System.out.println("TEMPLATE2 IS NULL: "+ (template2 == null));
			Context c2 = buildContext2(template2);
			System.out.println("C2 IS NULL: "+ (c2 == null));
			
			contextSpace.addTemplate(template);
			contextSpace.addTemplate(template2);

			Templates all = contextSpace.getTemplates();
			int n = all.size();
			for (int i = 0; i < n; i++){
				Template t = (Template)all.elementAt(i);
				System.out.println(t);
			}
			System.out.println("Done getTemplates.");
			System.out.println("Start build contexts");
			Link l1 = new Link();
			
			l1.setContextUUID(c1.getId());
			l1.setHref("http://www.ambiesense.com");
			l1.setHreflang("English");
			l1.setMedia("for mobile");	
			l1.setRel("undefined");
			l1.setTarget("this");				
			l1.setType("text/html");
			l1.setLinkText("AmbieSense");
			l1.setTimestamp(459409405l);
			
			c1.addLink(l1);
			c1.addLink(l1);
			c1.addLink(l1);
			c1.addLink(l1);
			Link l2 = new Link();
			l2.setContextUUID(c2.getId());
			l2.setHref("http://www.camilla.com");
			l2.setHreflang("English");
			l2.setMedia("for tablet");	
			l2.setRel("undefined");
			l2.setTarget("this");				
			l2.setType("text/html");
			l2.setLinkText("Erlend");
			l2.setTimestamp(450353934499l);
			c2.addLink(l2);
			
			Link l3 = new Link();
			l3.setContextUUID(c2.getId());
			l3.setHref("http://www.erlend.com");
			l3.setHreflang("Norwegian");
			l3.setType("mp3");
			l3.setLinkText("Camilla");
			l3.setTimestamp(60968484499l);
			c2.addLink(l3);

			//contextSpace.addLink(l2);

			System.out.println("Done build contexts");
			System.out.println("Start add contexts");
			System.out.println("Adding new context c1: " + contextSpace.addContext(c1));
			System.out.println("Adding new context c2: " + contextSpace.addContext(c2));
			System.out.println(c1);
			System.out.println(c2);
			System.out.println("Done add contexts");
			String s = "4a7d3a9b-0360-4366-91b4-ab9715d55825";
			System.out.println("Requesting for: "+s);
			System.out.println("getContext()="+contextSpace.getContext(s));
			String s1 = "57f9f23b-4a97-4395-a5af-5974d83f96c4";
			//System.out.println(contextSpace.deleteContext(s1));
			System.out.println("--All contexts----------");
			//int m = contexts.size();
			System.out.println("Context "+ s1+"deleted = "+ contextSpace.deleteContext(s1));
			System.out.println("Contexts deleted = "+ contextSpace.deleteAllContexts("MyTemplate12"));
			System.out.println("Template deleted = "+ contextSpace.deleteTemplate("MyTemplate12"));
			
			System.out.println("Context equality match = "+contextSpace.equalityMatch(c1, c2));
			System.out.println("Context distance match = "+contextSpace.distanceMatch(c1, c2));
			Object value1 = c1.getAttribute("Double");
			Object value2 = c2.getAttribute("Double");
			System.out.println("Relevance= "+ Relevance.relevance(value1, value2, 4d));

			
			Object value3 = c1.getAttribute("Integer");
			Object value4 = c2.getAttribute("Integer");
			System.out.println("Relevance= "+ Relevance.relevance(value3, value4, 50));

			Object value5 = c1.getAttribute("Time");
			Object value6 = c2.getAttribute("Time");
			System.out.println("Relevance= "+ Relevance.relevance(value5, value6, (int)10));

			Object value7 = c1.getAttribute("Text");
			Object value8 = c2.getAttribute("Text");
			System.out.println("Relevance= "+ Relevance.relevance(value7, value8, (int)4));

			System.out.println("Reading all links as html...)");		
			System.out.println(contextSpace.getLinks().toHtml());
			System.out.println("Reading all links as string...)");		
			System.out.println(contextSpace.getLinks().toString());

			
			System.out.println("Printing out all contexts:");
			Contexts contexts = contextSpace.getContexts();

			System.out.println(contexts.toString());
			System.out.println("Printing all contexts");

			System.out.println("Test completed.)");		

			contextSpace.closeConnection();
			cmInstance.disconnect();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			if (contextSpace != null) contextSpace.closeConnection();
			if (cmInstance != null) cmInstance.disconnect();
			System.exit(0);
		}
	}
	
	private Template buildTemplate() {
		Template template = new Template("org.ambie");
		template.addLongAttribute("hello.Time");
		template.addStringAttribute("hello.Text");
		template.addBooleanAttribute("hello.Boolean");
		template.addLongAttribute("hello.Integer");
		template.addDoubleAttribute("hello.Double");
		template.setDistanceFilter("hello.Time", 0);
		template.setDistanceFilter("hello.Text", 3);
		template.setDistanceFilter("hello.Boolean", 0);
		template.setDistanceFilter("hello.Integer", 0);
		template.setDistanceFilter("hello.Double", 0);
		//Template.parseAttributeName("1Com.0Ambie8Sense.ljjj.www.dkdkdk.Hello");
		System.out.println(template.toString());
		return template;
	}

	private Context buildContext(Template template){
		System.out.println("Template: "+template);
		Context context = new Context(template);
		context.updateAttribute("hello.Time", (long)2);
		context.updateAttribute("hello.Text", "Erlend og Camilla");
		context.updateAttribute("hello.Boolean", true);
		context.updateAttribute("hello.Integer", 18);
		context.updateAttribute("hello.Double", 20.18d);
		context.updateAttribute("hello.DOUBLE", 2012d, 110290390239l);
		System.out.println("Context: "+context);
		return context;
	}
	
	private Template buildTemplate2() {
		Template template = new Template("org.zoomin");
		template.addLongAttribute("org.MyTemplate12.Long");
		template.addStringAttribute("org.MyTemplate12.String");
		template.addBooleanAttribute("org.MyTemplate12.Boolean");
		template.addLongAttribute("org.MyTemplate12.Integer");
		template.addDoubleAttribute("org.MyTemplate12.Double");
		template.addUrlAttribute("org.MyTemplate12.Url");
		template.addFileAttribute("org.MyTemplate12.File");

		return template;
	}
	private Context buildContext2(Template template){
		System.out.println("Template: "+template);
		URL url = null;
		File f = null;
		try{
			url = new URL("http://www.ambiesense.com");
			f = new File("test.html");
		}catch(Exception e){}
		Context context = new Context(template);
		context.updateAttribute("org.MyTemplate12.Long", 100100l);
		context.updateAttribute("org.MyTemplate12.String", "Camilla og Erlend");
		context.updateAttribute("org.MyTemplate12.Boolean", true);
		context.updateAttribute("org.MyTemplate12.Integer", 19);
		context.updateAttribute("org.MyTemplate12.Double", 21.18d);
		context.updateAttribute("org.MyTemplate12.Double", 234.56d, 111234542l);
		context.updateAttribute("org.MyTemplate12.Url", url);
		context.updateAttribute("org.MyTemplate12.File", f);
		System.out.println("Context: "+context.toString());
		return context;
	}
	

}