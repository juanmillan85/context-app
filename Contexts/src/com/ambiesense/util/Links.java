/*-----------------------------------------------------------------------

  AmbieSense Ltd  

  -----------------------------------------------------------------------

 (C) Copyright from 2005 and onwards by AmbieSense Ltd.	All rights reserved.

 NOTICE:  All information contained herein or attendant hereto is,
          and remains, the property of AmbieSense Ltd.  Many of the
          intellectual and technical concepts contained herein are
          proprietary to AmbieSense Ltd and may be covered
          by National and Foreign Patents or Patents Pending, or are
          protected as trade secrets.  Any dissemination of this
          information or reproduction of this material is strictly
          forbidden unless prior written permission is obtained
          from AmbieSense Ltd.

 -----------------------------------------------------------------------
*/

package com.ambiesense.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import com.ambiesense.context.Link;

/**
 * <p>
 *  This class is a list of Link objects. Extends ClassVector to inherit the
 *  needed functionality to maintain lists of objects.
 * </p>
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
public class Links extends Hashtable<String,Link> {

	private static final long serialVersionUID = 1L;

	public Links() {
		super();
	}
	
	public String toString(){
		String ret = "";
		Enumeration<Link> links = this.elements();
		while (links.hasMoreElements()){			
			Link link = links.nextElement();
			ret += link.toString() + '\n';
		}
		return ret;
	}
	public String toHtml(){
		String ret = "";
		Enumeration<Link> links = this.elements();
		while (links.hasMoreElements()){			
			Link link = links.nextElement();
			ret += link.toHtml() + '\n';
		}
		return ret;
	}

}
