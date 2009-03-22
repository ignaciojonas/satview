import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import edu.pdi2.imaging.readers.FileChopReader;
import edu.pdi2.math.indexes.Rayleigh.Rayleigh;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.visual.PDI;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PDI P=new PDI();
		P.setVisible(true);
		/*FileChopReader fcr = new FileChopReader();
		List<String> sources = new ArrayList<String>();
		//sources.add("/mnt/datos/facultad/pdi2/l5/BAND1.dat");
		//sources.add("/mnt/datos/facultad/pdi2/l5/BAND2.dat");
		sources.add("/mnt/datos/facultad/pdi2/l5/BAND1.dat");
		SatelliteImage si = fcr.read(sources, 9516, 8616);
		
		SatelliteImage rc = si.getRadianceCorrected();
		JAI.create("filestore",rc,"rc.jpeg","JPEG");*/
		/*
		Point p = new Point(6000,6000);//centro de la imagen
		double[] lsat = si.getRadiance(p);
		System.out.print("Lsat: ");
		for (int i = 0; i < lsat.length; i++) {
			System.out.print(lsat[i] + " ");
		}
		System.out.println();
		
		//ahora el satélite
		Rayleigh landsat = new L7Rayleigh();
		System.out.print("Lr: ");
		
		for (int i = 1; i <= lsat.length; i++) {
			System.out.print(landsat.getRadiance(new Integer(i)) + " ");
		}
		
		System.out.println();
		System.out.print("bandas: ");
		
		String nombre = "l5-bandas-";
		
		int []bandas = si.getBands();
		for (int i = 0; i < bandas.length; i++) {
			System.out.print(bandas[i] + " ");
			nombre += bandas [i];
		}
		
		nombre += ".jpg";
		
		JAI.create("filestore",si,nombre,"JPEG");
		*/
	}

}
