package edu.pdi2.xml;



import java.io.FileReader;
import java.util.Vector;

import org.exolab.castor.xml.Unmarshaller;

import edu.pdi2.forms.Polygon;


public class Decoder {


	public static Vector decode(String fileName){
		Vector ret=null;
		try {
		      ret = (Vector)Unmarshaller.unmarshal(Vector.class, new FileReader(fileName));
		} catch (Exception e) {}
		    return ret;
	}
	
}
