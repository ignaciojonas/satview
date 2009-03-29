package edu.pdi2.visual.test;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.pdi2.constants.AppConstants;
import edu.pdi2.decoders.DecoderHeaderL5;
import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.visual.ViewSignature;

public class MainSignature {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> sources = new ArrayList<String>();
		sources.add(AppConstants.getString("band1"));
		sources.add(AppConstants.getString("band2"));
		sources.add(AppConstants.getString("band3"));
		
		Rectangle portion = new Rectangle(4000,4000,1000,1000);
		BandsManager bm = new BandsManager(new DecoderHeaderL5(AppConstants.getString("header")),sources,portion,9516,8616);
		
		edu.pdi2.forms.Point point = new edu.pdi2.forms.Point(4500,4500);
		byte[] sign = bm.getSignature(sources,point);
		//byte[] sign = {1,2,3};
		
		ViewSignature vs = new ViewSignature("signature",sign);
		
	}
}
