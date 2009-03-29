package edu.pdi2.constants;

/**
 * Contiene métodos para trabajar con imágenes <b>Landsat</b>. Por ejemplo, para
 * identificar los archivos de bandas.
 * 
 * @author fede
 * 
 */
public class SatelliteNamingUtils {
	public static int L5_CANT_BANDS = Integer.valueOf(AppConstants.getString("L5_CANT_BANDS"));

	public static String L5_BAND_PREFIX = "BAND";

	public static String L5_BAND_EXTENSION = ".dat";
	// TODO
	public static int L7_CANT_BANDS = Integer.valueOf(AppConstants.getString("L7_CANT_BANDS"));;
	// TODO
	public static String L7_BAND_PREFIX;
	// TODO
	public static String L7_BAND_EXTENSION;
	//TODO
	private static final int SACC_CANT_BANDS = Integer.valueOf(AppConstants.getString("SACC_CANT_BANDS"));;

	// Los identificadores de los satelites.
	public static final String SACC_ID = "SACC";

	public static final String LANDSAT5_ID = "L5";

	public static final String LANDSAT7_ID = "L7";

	

	/**
	 * Devuelve el nombre de archivo de una banda para una imagen de <b>Landsat
	 * 5</b>.
	 * 
	 * @param band
	 *            El número de banda.
	 */
	public static String getL5BandFilename(int band) {
		return getBandFilename(band, LANDSAT5_ID);
	}

	/**
	 * Devuelve el nombre de archivo de una banda para una imagen de <b>Landsat
	 * 7</b>.
	 * 
	 * @param band
	 *            El número de banda.
	 */
	public static String getL7BandFilename(int band) {
		return getBandFilename(band, LANDSAT7_ID);
	}

	/**
	 * Devuelve el nombre de archivo de una banda para una imagen de
	 * <b>SACC</b>.
	 * 
	 * @param band
	 *            El número de banda.
	 */
	public static String getSACCBandFilename(int band) {
		return getBandFilename(band, SACC_ID);
	}

	/**
	 * Devuelve el nombre de archivo de una banda para una imagen satelital.
	 * 
	 * @param band
	 *            El número de banda.
	 * @param satelliteId
	 *            El identificador del satelite. Puede ser uno de los siguientes
	 *            valores:<br> {@code SatelliteNamingUtils.LANDSAT5_ID}<br> {@code
	 *            SatelliteNamingUtils.LANDSAT7_ID}<br> {@code
	 *            SatelliteNamingUtils.SACC_ID}
	 */
	public static String getBandFilename(int i, String satelliteId) {
		return AppConstants.getString(satelliteId + "_BAND_" + i); //$NON-NLS-1$
	}

	/**
	 * Devuelve el nombre de archivo del header para una imagen satelital.
	 * 
	 * @param satelliteId
	 *            El identificador del satelite. Puede ser uno de los siguientes
	 *            valores:<br> {@code SatelliteNamingUtils.LANDSAT5_ID}<br> {@code
	 *            SatelliteNamingUtils.LANDSAT7_ID}<br> {@code
	 *            SatelliteNamingUtils.SACC_ID}
	 */
	public static String getHeaderFilename(String satelliteId) {
		return AppConstants.getString(satelliteId + "_HEADER");
	}

	/**
	 * Obtiene el número de banda a partir del nombre del archivo y del
	 * identificador de la imagen
	 * @throws Exception 
	 */
	public static Integer getBandNumber(String satelliteId, String bandFileName) throws Exception {
		for (int i=1; i<= getCantBands(satelliteId); ++i)
			if (getBandFilename(i, satelliteId).equals(bandFileName))
				return i;
		
		throw new Exception("Hubo un error con un nombre de banda... no se reconoce");
		
	}

	/**
	 * Devuelve la cantidad de bandas que tiene un satélite.
	 */
	public static int getCantBands(String satelliteId) {
		if (satelliteId.equals(LANDSAT5_ID))
			return L5_CANT_BANDS;
		else if (satelliteId.equals(LANDSAT7_ID))
			return L7_CANT_BANDS;
		else if (satelliteId.equals(SACC_ID))
			return SACC_CANT_BANDS;
		return 0;
	}
}
