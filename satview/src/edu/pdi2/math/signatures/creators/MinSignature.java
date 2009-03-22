package edu.pdi2.math.signatures.creators;

public class MinSignature extends SignatureCreator {

	public MinSignature() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getSignature(byte[][][] datos) {
		byte [] sign = new byte[datos[0][0].length];
		
		for (int i = 0; i < sign.length; i++) {
			sign[i] = Byte.MAX_VALUE;
		}
		
		for (int k=0;k<datos[0][0].length;++k){
			
			for (int i=0;i<datos.length;++i)
				for (int j=0;j<datos[0].length;++j)
					if (datos[i][j][k] < sign[k])
						sign[k] = datos[i][j][k];
		}
		
		return sign;
	}

}
