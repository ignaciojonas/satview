package edu.pdi2.imaging.readers;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.jai.codec.FileSeekableStream;

import edu.pdi2.constants.SatelliteNamingUtils;
import edu.pdi2.decoders.Decoder;
import edu.pdi2.forms.Point;
import edu.pdi2.imaging.ImageFactory;
import edu.pdi2.imaging.RawDataUtils;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.math.signatures.comparators.SignatureComparator;
import edu.pdi2.math.signatures.creators.SignatureCreator;

/**
 * Esta clase va a ser la que tenga todos los archivos de bandas del sat茅lite y
 * va a encargarse de formar las im谩genes, de encontrar firmas espectrales, etc.
 * 
 * @author fede
 * 
 */
public class BandsManager {

//	private String directoryPath;

	private SignatureComparator signatureComparator;// = new SimilarSignatures();


	/**
	 * Ancho de la imagen satelital <b>original</b>, no
	 * la de la porci贸n con la que se trabaje por defecto.
	 */
	private int width;
	
	/**
	 * Alto de la imagen satelital <b>original</b>, no
	 * la de la porci贸n con la que se trabaje por defecto.
	 */
	private int height;
	// private byte[][][] rawData;
	// private Hashtable<String,String> properties;
	// private double[][][] rayleighMatrix;

	// private List<ImageCorrector> correctors;
	// private SignatureCreator signatureCreator;

	/** porci贸n con la que se trabaja por defecto para esta imagen
	 * satelital. En caso de que aquellos m茅todos que extraigan datos
	 * de la imagen no sobreescriban esta porci贸n con par谩metros como
	 * <i>fromX, toX, formY, toY</i> entonces se va a usar esta porci贸n.
	 */
	private Rectangle portion;

	/** La lista con las direcciones de <b>todas</b> las bandas */
	private List<String> allBands;

	/** Las bandas actuales de la imagen. Sirven para generar la imagen con una determinada firma*/
	private List<String> currentBands;


	private Decoder decoder;

	/**
	 * El constructor de la clase. Recibe un directorio en el cual se encuentran
	 * todos los archivos de las bandas y el header.
	 * @param allBands La lista de <b>todas</b> las imagen. Despu茅s siempre se puede usar
	 * una cantidad de bandas distinta para pedir im谩genes, pero el BandsManager necesita
	 * saber de la existencia de todas las bandas
	 */
	public BandsManager(Decoder decoder, List<String> allBands, Rectangle portion,int width, int height) {
		super();
		// this.directoryPath = directoryPath;
		this.allBands = allBands;
		init(decoder, portion, width, height);		
	}

	/**
	 * Constructor que no define el tipo de imagen satelital usado. Luego de usar este constructor es necesario usar uno de los 
	 * 2 m閠odos: {@code openLandsat5} u {@code openLandsat7}.
	 * @param portion porcion de la imagen que se va a leer
	 * @param width ancho de la imagen satelital
	 * @param height alto de la imagen satelital
	 */
	public BandsManager(Decoder decoder, Rectangle portion,int width, int height) {
		init(decoder, portion, width, height);		
	}
	
	private void init(Decoder decoder, Rectangle portion, int width, int height) {
		this.decoder = decoder;
		this.width = width;
		this.height = height;
		this.portion = portion;
	}
	/**
	 * Setea los datos internos de este {@code BandsManager} para que lea archivos pertenecientes a imagenes
	 * del <b>Landsat 5</b>.
	 * @param directoryPath El directorio en el cual se encuentran los archivos de las bandas y el header de la
	 * imagen satelital.
	 */
	public void openLandsat5(String directoryPath){
		for (int i=1;i<=SatelliteNamingUtils.L5_CANT_BANDS; ++i){
			this.allBands.add(directoryPath+"/"+SatelliteNamingUtils.getL5BandFilename(i));
		}
	}
	
	/**
	 * Setea los datos internos de este {@code BandsManager} para que lea archivos pertenecientes a imagenes
	 * del <b>Landsat 7</b>.
	 * @param directoryPath El directorio en el cual se encuentran los archivos de las bandas y el header de la
	 * imagen satelital.
	 */
	public void openLandsat7(String directoryPath){
		for (int i=1;i<=SatelliteNamingUtils.L7_CANT_BANDS; ++i){
			this.allBands.add(directoryPath+"/"+SatelliteNamingUtils.getL7BandFilename(i));
		}
	}
	
	/**
	 * Obtiene la data para la imagen en forma de un vector aplanado. Tiene la
	 * informaci贸n de una cantidad de bandas dependiente de la cantidad de
	 * {@codeString}s que tenga el par谩metro <b>bands</b>.
	 */
	public byte[] getRawData(List<String> bands, int fromX, int toX, int fromY,
			int toY) {

		
		int numBands = bands.size();

		byte[] data = new byte[numBands * (toY - fromY) * (toX - fromX)];

		int con, pasada = 0;

		// List<String> sources = new ArrayList<String>();
		// for (Integer band : bands){
		// sources.add(getDirectoryPath() + "/" + getBandPrefix() +
		// band.intValue() + getBandExtension());
		// }

		try {
			List<FileSeekableStream> listFss = new ArrayList<FileSeekableStream>(
					getMaxBands());
			for (String s : bands) {
				listFss.add(new FileSeekableStream(s));
			}

			for (FileSeekableStream fss : listFss) {
				con = pasada;
				fss.skip(width * fromY + fromX); // salteo los primeros datos
													// que no se van a leer
				for (int y = fromY; y < toY; y++) {
					for (int x = fromX; x < toX; x++) {
						data[con] = (byte) fss.read();
						con += numBands;
					}
					fss.skip(width - toX + fromX);// salteo espacios verticales
													// (filas completas)
				}
				fss.close();
				pasada++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;

	}

	/** Devuelve el ancho de la imagen satelital <b>completa</b>.*/
	public int getWidth() {
		return width;
	}

	/** Devuelve el alto de la imagen satelital <b>completa</b>.*/
	public int getHeight() {
		return height;
	}

	/** 
	 * Devuelve el ancho de la porci贸n por defecto que se
	 * usa de la imagen satelital.
	 */
	public int getPortionWidth() {
		return portion.width;
	}

	/** 
	 * Devuelve el alto de la porci贸n por defecto que se
	 * usa de la imagen satelital.
	 */
	public int getPortionHeight() {
		return portion.height;
	}
	
	/** 
	 * Devuelve una imagen de las dimensiones de la imagen satelital
	 * pero solamente con las bandas especificadas por par谩metro.
	 */
	public SatelliteImage getRawImage(List<String> bands) {
		return getRawImage(bands, 0, getWidth(), 0, getHeight());
	}

	/**
	 * An谩logo al m茅todo {@code getRawImage}.
	 *  
	 * Recorta una porci贸n de la imagen satelital,
	 * pero solamente con las bandas especificadas por par谩metro.
	 */
	public SatelliteImage getImage(List<String> bands, int fromX, int toX,
			int fromY, int toY) {
		
		return getRawImage(bands, fromX, toX, fromY, toY);
	}

	/**
	 * Retorna una imagen estilo <b>falso color</b> con las bandas especificadas
	 * por el usuario. No hace ning煤n tipo de correcci贸n en esta imagen.
	 * 
	 * @param bandPaths
	 *            Lista de bandas que formar谩n la imagen. El orden <b>es
	 *            relevante</b>
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @return Una imagen con "falso color"
	 */
	public SatelliteImage getRawImage(List<String> bandPaths, int fromX, int toX,
			int fromY, int toY) {
		SatelliteImage satImage = null;
		/*
		 * int toY = height; int fromY = 0; int stepY = 1; int toX = width; int
		 * fromX = 0; int stepX = 1;
		 */
		currentBands = bandPaths;
		
		byte[] data = getRawData(bandPaths, fromX, toX, fromY, toY);
		// ahora tengo toda la data necesaria para hacer la imagen, pero primero
		// hay q corregirla

		satImage = ImageFactory.makeSatelliteImage(decoder, data, fromX, toX, fromY, toY, currentBands);

		return satImage;
	}
	
	/**Obtiene la firma digital en un punto dado de la imagen */
	public byte[] getSignature(Point p) {
		return getSignature(getAllBands(), p);
	}
	
	/**
	 * Obtiene una firma digital que s髄o involucra a las bandas especificadas
	 * por par醡etro. La firma va a ser m醩 corta que una firma con todas las
	 * bandas, pero puede servir en determinados casos... queda por ver.
	 */
	public byte[] getSignature(List<String> bands, Point p) {
		
		return getRawData(bands, p.getX(), p.getX()+1, p.getY(), p.getY()+1);
	}

	/**
	 * Obtiene una firma para una determinada regi贸n. La forma de la firma
	 * resultante va a estar definida por el {@link SignatureCreator} que est茅
	 * usando este <i>BandsManager</i>. 
	 */
	@Deprecated
	public byte[] getSignature(int fromX, int toX, int fromY, int toY) {
		return getSignature(getAllBands(), fromX, toX, fromY, toY);
	}
	
	private byte[] getSignature(List<String> bands, int fromX, int toX,
			int fromY, int toY) {
		return getRawData(bands, fromX, toX, fromY, toY);
	}

	

	public int getMaxBands() {
		return getAllBands().size();
	}

//	private int[] getBandsArray(){
//		int[] bands = new int[allBands.size()];
//		
//		for (int i=0; i<allBands.size(); ++i){
//			
//			String bandFileName = allBands.get(i).substring(allBands.get(i).lastIndexOf("/")+1);
//			try {
//				bands[i] = SatelliteNamingUtils.getBandNumber(decoder.getSatelliteId(), bandFileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return bands;
//	}
	
	public List<String> getAllBands() {
		return allBands;
	}

	// public String getDirectoryPath() {
	// return directoryPath;
	// }
	public String getBandPrefix() {
		return SatelliteNamingUtils.L5_BAND_PREFIX;
	}

	public void setBandPrefix(String bandPrefix) {
		SatelliteNamingUtils.L5_BAND_PREFIX = bandPrefix;
	}

	public String getBandExtension() {
		return SatelliteNamingUtils.L5_BAND_EXTENSION;
	}

	public void setBandExtension(String bandExtension) {
		SatelliteNamingUtils.L5_BAND_EXTENSION = bandExtension;
	}

	/**
	 * Devuelve una imagen en la cual s贸lo se ven los pixeles que tengan una
	 * firma digital como la que se pasa por par谩metro. Sirve para identificar
	 * cultivos en la imagen satelital.
	 * 
	 * @param signature
	 *            La firma digital que debe tener la imagen satelital en un
	 *            punto para que este sea agregado a la imagen devuelta.
	 * @return Una {@code SatelliteImage} con pixeles en falso color o con un
	 *         color por defecto si es que no tienen la firma deseada.
	 */

	public void setSignatureComparator(SignatureComparator signatureComparator) {
		this.signatureComparator = signatureComparator;
	}

	public SignatureComparator getSignatureComparator() {
		return signatureComparator;
	}

	/**
	 * Retorna una imagen con una determinada firma. La firma no se pasa por par醡etro porque todos los
	 * datos necesarios pueden obtenerse por medio del {@code SignatureComparator} que posee esta clase.<br>
	 * Por esto es que se debe haber usado el m閠odo {@code setSignatureComparator} aunque sea una vez
	 * antes de llamar a este m閠odo.
	 * @return Una imagen satelital.
	 */
	public SatelliteImage getImageWithThisSignature(){
		return getImageWithThisSignature(portion.x, portion.x + portion.width, portion.y, portion.y + portion.height);
	}
	
	public SatelliteImage getImageWithThisSignature(int fromX, int toX, int fromY, int toY) {

		byte[] originalData = getRawData(allBands, fromX, toX, fromY, toY);
		byte[] newData = new byte[originalData.length];

		// ahroa voy leyendo arreglos del tama帽o de la cantidad de bandas y cada
		// uno de esos
		// es una firma de un pixel. La tengo que comparar con la firma que me
		// pasaron.
		int i = 0;
		int posInterna = 0;
		byte[] currentSign = new byte[getMaxBands()];
		int signSize = currentSign.length;
		
		
		while (i < originalData.length) {
			if (posInterna < signSize) {
				currentSign[posInterna] = originalData[i];
				++posInterna;
				++i;
			} else {
				if (getSignatureComparator().areEquivalent(currentSign)) {
					// las firmas son parecidas, se agrega el pixel.
					RawDataUtils.copyPixel(currentSign, newData, i - posInterna);
				} else {
					// las firmas son distintas. Se llena el pixel con un color
					// por defecto
					RawDataUtils.erasePixel(newData, i - posInterna + 1, signSize);
				}
				posInterna = 0;
			}
			
		}
		int numBands = signSize;
		if (numBands > 3)
			numBands = 3;
		byte[] flatData = RawDataUtils.flatImage(newData, fromX, toX, fromY, toY, numBands, currentBands, getMaxBands());
		
		SatelliteImage ret = ImageFactory.makeSatelliteImage(decoder, flatData, fromX, toX, fromY, toY, currentBands);

		return ret;
	}

	

	

	/**
	 * Forma una cuadr韈ula de 3x3 con centro en el pixel especificado y devuelve la firma promedio para
	 * esa cuadr韈ula.
	 * @param x columna del centro
	 * @param y fila del centro
	 * @return una firma promedio
	 */
	public byte []getSignatureAround(Point p){
		byte[] ret = new byte[getMaxBands()];
		int con=0;
		for (int x=p.getX()-1; x<=p.getX()+1;++x){
			if (x>0 && x<portion.getWidth())
			for (int y=p.getY()-1; y<=p.getY()+1;++y){
				if (y>0 && y<portion.getHeight()){
					Point p2 = new Point(x,y);
					byte[] sign2 = getSignature(p2);
					
//					ahora sumo la firma e incremento el contador
					++con;
					sumarFirmas(ret,sign2);
				}
				
			}
		}
//		ahora divido para hacer la firma promedio
		promediarFirma(ret,con);
		return ret;
	}
	
	/**
	 * Promedia las firmas de todos los puntos presentes en la lista pasada por par醡etro y devuelve
	 * la firma promedio.
	 * @param puntos Lista de puntos de la imagen. Est醤 en coordenadas <i>(x,y)</i> y van desde 0 hasta
	 * el ancho y el alto del recorte de la imagen.
	 * @return firma promedio
	 */
	public byte[] getAverageSignature(List<Point> puntos){
		byte []ret = new byte[getMaxBands()];
		int con=0;
		for (Point point : puntos) {
			byte []sign = getSignature(point);
			++con;
			sumarFirmas(ret, sign);
		}
		promediarFirma(ret, con);
		return ret;
	}
	
	private void promediarFirma(byte[] sign, int con) {
		for (int i=0;i<sign.length; ++i)
			sign[i] /= con;
	}


	private void sumarFirmas(byte[] sign1, byte[] sign2) {
		for (int i=0;i<sign1.length; ++i)
			sign1[i] += sign2[i];
	}


	

	
	
}
