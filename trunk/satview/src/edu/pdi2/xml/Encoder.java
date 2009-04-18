package edu.pdi2.xml;

import java.io.FileWriter;
import java.util.Vector;

import org.exolab.castor.xml.Marshaller;

import edu.pdi2.forms.Polygon;

public class Encoder {

	public Encoder() {
		// TODO Auto-generated constructor stub
	}

	public static boolean encode(Vector<Polygon> pol, String nameFile) {
		try {
			String aux = nameFile;
			FileWriter faux = null;
			String tmp[];
			tmp = nameFile.split(".");
			if (tmp.length > 1) {
				if (tmp[1].compareTo("xml") != 0)
					aux = aux + ".xml";
			} else
				aux = aux + ".xml";
			faux = new FileWriter(aux);
			Marshaller.marshal(pol, faux);
			faux.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}