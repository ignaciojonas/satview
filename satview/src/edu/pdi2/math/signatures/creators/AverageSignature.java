package edu.pdi2.math.signatures.creators;

public class AverageSignature extends SignatureCreator {

	@Override
	public byte[] getSignature(byte[][][] datos) {
		byte [] sign = new byte[datos[0][0].length];
		int acum = 0;
		int con = 0;
		
		for (int k=0;k<datos[0][0].length;++k){
			acum = 0;
			con = 0;
			for (int i=0;i<datos.length;++i)
				for (int j=0;j<datos[0].length;++j){
					acum += datos[i][j][k];
					con ++;
				}
			sign[k] = (byte) (acum / con);
		}
		
		return sign;
	}

}
