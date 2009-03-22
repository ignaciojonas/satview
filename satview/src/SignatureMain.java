import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;

import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.visual.AppConstants;


public class SignatureMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> sources = new ArrayList<String>();
		sources.add(AppConstants.getString("band1")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band2")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band3")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band4")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band5")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band6")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band7")); //$NON-NLS-1$
		
		
		Rectangle portion = new Rectangle(0,0,9516,8616);
		BandsManager bm = new BandsManager(sources,portion,9516,8616);
		
		Point point = new Point(3026,1390);
		byte[] sign = bm.getSignature(sources,point);
		
		
		JAI.create("Lsat: ",bm.getImageWithThisSignature(sign,3000, 4000, 1000, 2000)," ","Lr: ");   
		//JAI.create("filestore",bm.getImage(sources,3000, 4000, 1000, 2000),"sign.jpeg","JPEG");

	}

}
