package edu.pdi2.decoders;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.pdi2.constants.SatelliteNamingUtils;


public class DecoderHeaderL5 extends Decoder {
	

	public DecoderHeaderL5(String path) {
		super(path);
		satelliteId = SatelliteNamingUtils.LANDSAT5_ID;
	}

	@Override
	public void decode() {
		BufferedReader reader;
		try {
			//reader = new BufferedReader(new FileReader("C:\\workspace_pdi\\pdi\\l5\\header.dat"));
			reader = new BufferedReader(new FileReader(path));
			String linea= reader.readLine();
			UL_lon=Double.parseDouble(linea.substring(linea.indexOf("UL ")+3,linea.indexOf("UL ")+15));
			UL_lat=Double.parseDouble(linea.substring(linea.indexOf("UL ")+17,linea.indexOf("UL ")+28));
			UL_1=Double.parseDouble(linea.substring(linea.indexOf("UL ")+32,linea.indexOf("UL ")+41));
			UL_2=Double.parseDouble(linea.substring(linea.indexOf("UL ")+45,linea.indexOf("UL ")+56));
			UR_lon=Double.parseDouble(linea.substring(linea.indexOf("UR ")+3,linea.indexOf("UR ")+15));
			UR_lat=Double.parseDouble(linea.substring(linea.indexOf("UR ")+17,linea.indexOf("UR ")+28));
			UR_1=Double.parseDouble(linea.substring(linea.indexOf("UR ")+32,linea.indexOf("UR ")+41));
			UR_2=Double.parseDouble(linea.substring(linea.indexOf("UR ")+45,linea.indexOf("UR ")+56));
			LL_lon=Double.parseDouble(linea.substring(linea.lastIndexOf("LL ")+3,linea.lastIndexOf("LL ")+15));
			LL_lat=Double.parseDouble(linea.substring(linea.lastIndexOf("LL ")+17,linea.lastIndexOf("LL ")+28));
			LL_1=Double.parseDouble(linea.substring(linea.lastIndexOf("LL ")+32,linea.lastIndexOf("LL ")+41));
			LL_2=Double.parseDouble(linea.substring(linea.lastIndexOf("LL ")+45,linea.lastIndexOf("LL ")+56));
			LR_lon=Double.parseDouble(linea.substring(linea.indexOf("LR ")+3,linea.indexOf("LR ")+15));
			LR_lat=Double.parseDouble(linea.substring(linea.indexOf("LR ")+17,linea.indexOf("LR ")+28));
			LR_1=Double.parseDouble(linea.substring(linea.indexOf("LR ")+32,linea.indexOf("LR ")+41));
			LR_2=Double.parseDouble(linea.substring(linea.indexOf("LR ")+45,linea.indexOf("LR ")+56));
			PPL=Integer.parseInt(linea.substring(linea.indexOf("PIXELS PER LINE=")+17,linea.indexOf("PIXELS PER LINE=")+21));
			LPI=Integer.parseInt(linea.substring(linea.indexOf("LINES PER IMAGE=")+17,linea.indexOf("LINES PER IMAGE=")+21));
			gains_biases=linea.substring(linea.indexOf("GAINS/BIASES =")+16,linea.indexOf("VOLUME")-1);
			System.out.println("Decode "+path+" of LANDSAT 5");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
