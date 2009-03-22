package edu.pdi2.imaging.corrections;

/**
 * Encapsula la lógica para hacer correcciones a las imágenes. Por ejemplo, para sacarle
 * los errores introducidos por el efecto Rayleigh.
 * @author fede
 *
 */
public interface ImageCorrector {

	public String getId();
	public byte[] correct(byte[] data, double[][][] indexMatrix);
}
