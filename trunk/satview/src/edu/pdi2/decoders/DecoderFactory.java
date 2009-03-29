package edu.pdi2.decoders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DecoderFactory {

	public static Decoder getDecoder(String path){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String linea= reader.readLine();
			while(linea!=null) { 
				if(linea.contains("SATELLITE ="))
					break;
				else
					linea=reader.readLine();


			}
			String split[]= linea.split("SATELLITE =");
			String satellite=split[1].substring(0,split[1].indexOf(" "));
			if(satellite.equals("L5"))
				return new DecoderHeaderL5(path);
			if(satellite.equals("LANDSAT7"))
				return new DecoderHeaderL7(path);
			if(satellite.equals("SC"))
				return new DecoderHeaderSacc(path);
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Decoder getDecoderById(String satelliteId) {
		// TODO Auto-generated method stub
		return null;
	}
}
