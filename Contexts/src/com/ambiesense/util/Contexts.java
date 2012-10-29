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
import com.ambiesense.context.Context;




/**
 * <p>
 *  This class is a list of Context objects. Extends ClassVector to inherit the
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
public class Contexts extends Hashtable<String, Context> {

	private static final long serialVersionUID = 1L;

	public Contexts() {
		super();
	}
	
	public String toString(){
		String ret = "<contexts>" + '\n';
		Enumeration<Context> ec = this.elements();
		while (ec.hasMoreElements()){
			Context context = ec.nextElement();
			ret += context.toString();		
		}
		ret += "</contexts>" + '\n';
		return ret;
	}
}
