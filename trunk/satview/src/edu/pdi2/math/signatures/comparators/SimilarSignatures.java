package edu.pdi2.math.signatures.comparators;

public class SimilarSignatures implements SignatureComparator {
	private byte[] top;
	private byte[] bottom;
	
	/**
	 * Constructor de la clase. Recibe 2 arreglos de bytes que indican posición a posición la <i>tolerancia</i>
	 * de la comparación.
	 * @param top El <b>techo</b> que puede alcanzar una firma comparada. Si {@code firma[i]} > {@code top[i]} para
	 * cualquier {@code i}, entonces la firma no es aceptada como <i>similar</i>.
	 * @param bottom El <b>piso</b> que puede alcanzar una firma comparada. Si {@code firma[i]} < {@code top[i]} para
	 * cualquier {@code i}, entonces la firma no es aceptada como <i>similar</i>.
	 */
	public SimilarSignatures(byte[] top, byte[] bottom) {
		super();
		this.top = top;
		this.bottom = bottom;
	}

	/**
	 * Compara una firma en base al <b>techo</b> y al <b>piso</b> que tiene este comparador.S
	 * @param signature
	 * @return
	 */
	public boolean areEquivalent(byte[] signature) {
		for (int i = 0; i < signature.length; i++) {
			byte b=signature[i];
			if (b<bottom[i] || b>top[i])
				return false;
		}
		return true;
	}

	public void setTop(byte[] top) {
		this.top = top;
	}

	public void setBottom(byte[] bottom) {
		this.bottom = bottom;
	}

}
