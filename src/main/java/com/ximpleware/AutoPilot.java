/* 
 * Copyright (C) 2002-2007 XimpleWare, info@ximpleware.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package com.ximpleware;


import java.util.*;
import java.io.*;
/**
 * XimpleWare's AutoPilot implementation encapsulating node iterator
 * and XPath.
 * 
 */
public class AutoPilot {
    private int depth;
    // the depth of the element at the starting point will determine when to stop iteration
    private int iter_type; // see selectElement
    private VTDNav vn; // the navigator object
    private int index; // for iterAttr
    private boolean ft; // a helper variable for 
    				   		   // the case of node() and * for preceding axis
    						   // of xpath evaluation
    private String name; // Store element name after selectElement
    private String localName; // Store local name after selectElemntNS
    private String URL; // Store URL name after selectElementNS
    private int size; // for iterateAttr
    boolean special;
    
    private int stackSize;  // the stack size for xpath evaluation
    //private parser p;
    // defines the type of "iteration"
    public final static int UNDEFINED = 0;
    // set the mode corresponding to DOM's getElemetnbyName(string)
    public final static int SIMPLE = 1;
    // set the mode corresponding to DOM's getElementbyNameNS(string)
    public final static int SIMPLE_NS = 2;
    
    

/**
 * AutoPilot constructor comment.
 * @exception IllegalArgumentException If the VTDNav object is null 
 */
public AutoPilot(VTDNav v) {
    if (v == null)
        throw new IllegalArgumentException(" instance of VTDNav can't be null ");
    name = null;
    vn = v;
    //depth = v.getCurrentDepth();
    iter_type = UNDEFINED; // not defined
    ft = true;
    size = 0;
     special = false;
    //p = null;       
}

/**
 * Use this constructor for delayed binding to VTDNav
 * which allows the reuse of XPath expression 
 *
 */
public AutoPilot(){
    name = null;
    //vn = v;
    //depth = v.getCurrentDepth();
    special = false;
    iter_type = UNDEFINED; // not defined
    ft = true;
    size = 0;
}

/**
 * Bind is to replace rebind() and setVTDNav()
 * It resets the internal state of AutoPilot
 * so one can attach a VTDNav object to the autopilot
 * @param vnv
 *
 */
public void bind (VTDNav vnv){
    name = null;
    if (vnv == null)
        throw new IllegalArgumentException(" instance of VTDNav can't be null ");
    vn = vnv;
    //depth = v.getCurrentDepth();
    iter_type = UNDEFINED; // not defined
    ft = true;
    size = 0;
    //resetXPath();
}
/**
 * Iterate over all the selected element nodes in document order.
 * Null element name allowed, corresponding to node() in xpath
 * Creation date: (12/4/03 5:25:42 PM)
 * @return boolean
 * @exception com.ximpleware.NavException See description in method toElement() in VTDNav class.
 */
public boolean iterate() throws PilotException, NavException {
    switch (iter_type) {
        case SIMPLE :
        	//System.out.println("iterating ---> "+name+ " depth ---> "+depth);
            /*if (elementName == null)
                throw new PilotException(" Element name not set ");*/
        	if (vn.atTerminal)
        	    return false;
            if (ft == false)
                return vn.iterate(depth, name, special);
            else {
            	ft = false;
                if (special || 
                		vn.matchElement(name)) {                	
                    return true;
                } else
                    return vn.iterate(depth, name, special);
            }
            
        case SIMPLE_NS :
        	if (vn.atTerminal)
        	    return false;
            if (ft == false)
                return vn.iterateNS(depth, URL, localName);
            else {
            	ft = false;
                if (vn.matchElementNS(URL, localName)) {
                	return true;
                } else
                    return vn.iterateNS(depth, URL, localName);
            }
            
        default :
            throw new PilotException(" iteration action type undefined");
    }
}

/**
 * Select the element name before iterating.
 * "*" matches every element
 * Creation date: (12/4/03 5:51:31 PM)
 * @param en java.lang.String
 */
	public void selectElement(String en) {
		if (en == null)
			throw new IllegalArgumentException("element name can't be null");
		iter_type = SIMPLE;
		depth = vn.getCurrentDepth();
		//startIndex = vn.getCurrentIndex();
		name = en;
		ft = true;
	}
/**
 * Select the element name (name space version) before iterating. URL, if set to *,
 * matches every namespace URL, if set to null, indicates the namespace is
 * undefined. localname, if set to *, matches any localname Creation date:
 * (12/4/03 6:05:19 PM)
 * 
 * @param URL
 *            java.lang.String
 * @param ln
 *            java.lang.String
 */
public void selectElementNS(String ns_URL, String ln) {
	if (ln == null)
		throw new IllegalArgumentException("local name can't be null");
    iter_type = SIMPLE_NS;
    depth = vn.getCurrentDepth();
    //startIndex = vn.getCurrentIndex();
    localName = ln;
    URL = ns_URL;
    ft = true;
}


}
