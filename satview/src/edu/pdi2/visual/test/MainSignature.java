package edu.pdi2.visual.test;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.visual.ViewSignature;

public class MainSignature {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> sources = new ArrayList<String>();
		sources.add("/mnt/datos/facultad/pdi2/l5/BAND1.dat");
		sources.add("/mnt/datos/facultad/pdi2/l5/BAND2.dat");
		sources.add("/mnt/datos/facultad/pdi2/l5/BAND3.dat");
		
		Rectangle portion = new Rectangle(4000,4000,1000,1000);
		BandsManager bm = new BandsManager(sources,portion,9516,8616);
		
		Point point = new Point(4500,4500);
		byte[] sign = bm.getSignature(sources,point);
		//byte[] sign = {1,2,3};
		
		ViewSignature vs = new ViewSignature("signature",sign);
		
	}

}
