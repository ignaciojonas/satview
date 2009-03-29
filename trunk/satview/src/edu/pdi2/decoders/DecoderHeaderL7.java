package edu.pdi2.decoders;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.pdi2.constants.SatelliteNamingUtils;


public class DecoderHeaderL7 extends Decoder {
	
	public DecoderHeaderL7(String path) {
		super(path);
		satelliteId = SatelliteNamingUtils.LANDSAT7_ID;
	}

	public void decode() {
		String UL = null,UR = null,LL = null,LR = null,PPL_S =null;
		
		BufferedReader reader;
		try {
			//reader = new BufferedReader(new FileReader("C:\\workspace_pdi\\pdi\\l7\\L71225086_08620001204_HTM.FST"));
			reader = new BufferedReader(new FileReader(path));
			String linea= reader.readLine();
			String file=linea;
			while(linea!=null) { 
				
				if(linea.contains("UL ="))
					UL=linea;
				if(linea.contains("UR ="))
					UR=linea;
				if(linea.contains("LL ="))
					LL=linea;
				if(linea.contains("LR ="))
					LR=linea;
				if(linea.contains("PIXELS PER LINE ="))
					PPL_S=linea;
				linea=reader.readLine();
				file+=linea;
				
			}
			UL=UL.substring(5);
			UL_lon=Double.parseDouble(UL.substring(0, 12));
			UL_lat=Double.parseDouble(UL.substring(14, 25));
			UL_1=Double.parseDouble(UL.substring(29, 40));
			UL_2=Double.parseDouble(UL.substring(43, 54));

			UR=UR.substring(5);
			UR_lon=Double.parseDouble(UR.substring(0, 12));
			UR_lat=Double.parseDouble(UR.substring(14, 25));
			UR_1=Double.parseDouble(UR.substring(29, 40));
			UR_2=Double.parseDouble(UR.substring(43, 54));
			LL=LL.substring(5);
			LL_lon=Double.parseDouble(LL.substring(0, 12));
			LL_lat=Double.parseDouble(LL.substring(14, 25));
			LL_1=Double.parseDouble(LL.substring(29, 40));
			LL_2=Double.parseDouble(LL.substring(43, 54));
			LR=LR.substring(5);
			LR_lon=Double.parseDouble(LR.substring(0, 12));
			LR_lat=Double.parseDouble(LR.substring(14, 25));
			LR_1=Double.parseDouble(LR.substring(29, 40));
			LR_2=Double.parseDouble(LR.substring(43, 54));
			PPL=Integer.parseInt(PPL_S.substring(42, 47));
			LPI=Integer.parseInt(PPL_S.substring(64, 69));
			System.out.println("Decode "+path+" of LANDSAT 7");
	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
