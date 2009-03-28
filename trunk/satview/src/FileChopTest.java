import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;

import edu.pdi2.constants.AppConstants;
import edu.pdi2.imaging.readers.FileChopReader;
import edu.pdi2.math.indexes.satellite.SatelliteImage;


public class FileChopTest {
	public static void main(String[] args) {
		FileChopReader fcr = new FileChopReader();
		List<String> sources = new ArrayList<String>();
		sources.add(AppConstants.getString("band1")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band2")); //$NON-NLS-1$
		sources.add(AppConstants.getString("band3")); //$NON-NLS-1$
		
		int width = 9516;
		int height = 8616;
		
		SatelliteImage img = fcr.read(sources, width, height,0,8000,1,8000,10,10);
		
		JAI.create("filestore",img,"c.jpg","JPEG");   
	}
}
