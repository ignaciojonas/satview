package edu.pdi2.math.indexes.satellite;

import java.awt.Point;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;

import edu.pdi2.constants.SatelliteNamingUtils;
import edu.pdi2.decoders.Decoder;
import edu.pdi2.decoders.DecoderFactory;
import edu.pdi2.imaging.ImageFactory;
import edu.pdi2.math.indexes.Rayleigh.L5Rayleigh;
import edu.pdi2.math.indexes.Rayleigh.Rayleigh;

public class SatelliteImage extends TiledImage {
	// private Hashtable<String,String> properties;
	private List<Integer> bands;
	private Rayleigh rayleigh;
	private Decoder decoder;

	/** Identifica al satÈlite del cual proviene esta imagen */
	private String satelliteId;

	// TODO hacer esto flexible
	// private SignatureComparator signatureComparator = new
	// SimilarSignatures();

	public SatelliteImage(Decoder decoder, int minX, int minY, int width,
			int height, int tileGridXOffset, int tileGridYOffset,
			SampleModel tileSampleModel, ColorModel colorModel,
			List<String> bandsPaths) {
		super(minX, minY, width, height, tileGridXOffset, tileGridYOffset,
				tileSampleModel, colorModel);
		// properties = new Hashtable<String, String>();
		// properties.put("PIXELS PER LINE", "9516");
		// properties.put("LINES PER IMAGE", "8616");
		// properties.put("GAINS/BIASES",
		// "1.26880/-0.0100  2.98126/-0.0232  1.76186/-0.0078  2.81771/-0.0193  0.65277/-0.0080  3.20107/0.25994  0.44375/-0.0040"
		// );
		this.decoder = decoder;
		this.bands = new ArrayList<Integer>();
		int con = 0;
		for (String string : bandsPaths) {
			if (con < 3) {
				// FIXME esto no tiene q "adivinar" el n∞ de banda por sÌ mismo.
				// tiene q usar
				// la clase SatelliteNamingUtils para pedirle el numero a partir
				// del nombre del archivo
				// this.bands.add(Integer.valueOf(string.substring(
				// string.length() - 5, string.length() - 4)));
				String bandFileName = string.substring(string.lastIndexOf("/")+1);
				try {
					this.bands.add(SatelliteNamingUtils.getBandNumber(decoder
							.getSatelliteId(), bandFileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			con++;
		}

		rayleigh = new L5Rayleigh();
	}

	public Rayleigh getRayleigh() {
		return rayleigh;
	}

	public void setRayleigh(Rayleigh rayleigh) {
		this.rayleigh = rayleigh;
	}

	public double[] getRadiance(int fromX, int toX, int fromY, int toY, int band) {

		double[] ret = new double[(toY - fromY) * (toX - fromX)];

		double[] gains = getGains();
		double[] biases = getBiases();
		int b = getIndexForBand(band);
		byte[] nds = getNDs(fromX, toX, fromY, toY, b);

		for (int i = 0; i < ret.length; ++i)
			ret[i] = nds[i] * gains[b] + biases[b];

		return ret;
	}

	private int getIndexForBand(int band) {
		int[] bands = getBands();
		for (int i = 0; i < bands.length; i++) {
			if (bands[i] == band)
				return i;
		}
		// para que se rompa BIEN ROTO!
		return Integer.MAX_VALUE;
	}

	private byte[] getNDs(int fromX, int toX, int fromY, int toY, int band) {
		int cantBandas = getCantBands();

		byte[] ret = new byte[(toY - fromY) * (toX - fromX)];
		int i = 0;
		DataBufferByte db = (DataBufferByte) getData().getDataBuffer();
		for (int y = fromY; y < toY; ++y)
			for (int x = fromX; x < toX; ++x)

				ret[i++] = (byte) db.getElem(band + x * cantBandas + getWidth()
						* y * cantBandas);

		return ret;
	}

	public double[] getRadiance(int fromX, int toX, int fromY, int toY) {
		int cantBandas = getCantBands();
		double[] ret = new double[(toY - fromY) * (toX - fromX) * cantBandas];

		double[] gains = getGains();
		double[] biases = getBiases();

		byte[] nds = getNDs(fromX, toX, fromY, toY);

		for (int i = 0; i < ret.length; ++i)
			ret[i] = nds[i] * gains[i % cantBandas] + biases[i % cantBandas];

		return ret;
	}

	/**
	 * Devuelve la cantidad de bandas de esta imagen.
	 * 
	 * @return 1 o 3... si d· otra cosa, est· mal!
	 */
	private int getCantBands() {
		return bands.size();
	}

	private boolean isBandPresent(int banda) {
		for (int i = 0; i < bands.size(); ++i) {
			Integer s = bands.get(i);
			if (s.intValue() == banda)
				return true;
		}

		return false;
	}

	private double[] getBiases() {
		double[] ret = new double[getCantBands()];
		// String gainsBiases = properties.get("GAINS/BIASES");
		String gainsBiases = decoder.getGainsBiases();
		// extraigo los biases, que est√°n a la derecha de la "/"
		String[] gbArr = gainsBiases.split("  ");
		int con = 0;

		for (int i = 0; i < gbArr.length; ++i)
			if (isBandPresent(i))
				ret[con++] = Double.valueOf(gbArr[i].split("/")[1])
						.doubleValue();

		return ret;
	}

	/*
	 * private String getBaseDir() { return baseDir; }
	 * 
	 * public void setBaseDir(String baseDir) { this.baseDir = baseDir; }
	 * 
	 * private int getMaxBandsAllowed() { return maxBandsAllowed; }
	 */
	private double[] getGains() {
		double[] ret = new double[getCantBands()];
		// String gainsBiases = properties.get("GAINS/BIASES");
		String gainsBiases = decoder.getGainsBiases();
		// extraigo los biases, que est√°n a la derecha de la "/"
		String[] gbArr = gainsBiases.split("  ");
		int con = 0;

		for (int i = 0; i < gbArr.length; ++i)
			if (isBandPresent(i))
				ret[con++] = Double.valueOf(gbArr[i].split("/")[0])
						.doubleValue();

		return ret;
	}

	// /**
	// * Agrega una banda a la imagen.
	// *
	// * @param bandsList
	// * @param numBand
	// * El n√∫mero de banda, de 1 a 7 en <i>Landsat 5</i>, de 1 a 8 en
	// * <i>Landsat 7</i>
	// * @param pos
	// * Posici√≥n en la que se quiere poner esta banda, para jugar con
	// * el <i>falso color</i>
	// * @throws TooManyBandsException
	// * Si se intenta agregar una banda cuando ya se lleg√≥ al
	// * l√≠mite para esta imagen
	// */
	/*
	 * public void addBand(int numBand, int pos) throws TooManyBandsException{
	 * int cantBandas = getCantBands(); //primero hay que fijarse que la imagen
	 * no est√© llena if (cantBandas == getMaxBandsAllowed()) throw new
	 * TooManyBandsException();
	 * 
	 * String bandString = getBaseDir()+"/BAND"+numBand+".DAT"; //ahora, 1 de
	 * cada X bytes va a ser el byte nuevo que voy a sacar de la banda que
	 * agrego bandsFiles.add(pos, bandString);
	 * 
	 * int width = Integer.valueOf(properties.get("PIXELS PER LINE")); int
	 * height = Integer.valueOf(properties.get("LINES PER IMAGE"));
	 * 
	 * PlanarImage pi = fcr.read(bandsFiles, width, height);
	 * 
	 * 
	 * Raster r = pi.getAsBufferedImage().getRaster(); super.setData(r);
	 * 
	 * 
	 * }
	 */

	/**
	 * @deprecated Usar el constructor que recibe un {@code Decoder}.
	 */
	public SatelliteImage(int minX, int minY, int width, int height,
			int tileGridXOffset, int tileGridYOffset,
			SampleModel tileSampleModel, ColorModel colorModel, int[] bands) {
		super(minX, minY, width, height, tileGridXOffset, tileGridYOffset,
				tileSampleModel, colorModel);

		// properties = new Hashtable<String, String>();
		// properties.put("PIXELS PER LINE", "9516");
		// properties.put("LINES PER IMAGE", "8616");
		// properties.put("GAINS/BIASES",
		// "1.26880/-0.0100  2.98126/-0.0232  1.76186/-0.0078  2.81771/-0.0193  0.65277/-0.0080  3.20107/0.25994  0.44375/-0.0040"
		// );
		int cantBands = bands.length;
		if (cantBands > 3)
			cantBands = 3;
		this.bands = new ArrayList<Integer>(cantBands);
		for (int i = 0; i < cantBands; ++i) {
			this.bands.add(new Integer(bands[i]));
		}

		// rayleigh = new L5Rayleigh();
	}

	private byte[] getNDs(int fromX, int toX, int fromY, int toY) {
		int cantBandas = getCantBands();

		byte[] ret = new byte[(toY - fromY) * (toX - fromX) * cantBandas];
		int i = 0;
		DataBufferByte db = (DataBufferByte) getData().getDataBuffer();
		for (int y = fromY; y < toY; ++y)
			for (int x = fromX; x < toX; ++x)
				for (int b = 0; b < cantBandas; ++b) {
					ret[i++] = (byte) db.getElem(b + x * cantBandas
							+ getWidth() * y * cantBandas);
				}

		return ret;
	}

	public int[] getBands() {
		int[] ret = new int[getCantBands()];
		for (int i = 0; i < getCantBands(); i++) {

			ret[i] = bands.get(i).intValue();
		}
		return ret;
	}

	/**
	 * Crea una copia de esta imagen y le aplica la correcciÛn en base a su
	 * <b>Reflectancia</b>.
	 * 
	 * @return Una lista de im·genes satelitales. Cada una de las im·genes de la
	 *         lista corresponde a una banda de la imagen corregida por
	 *         reflectancia.
	 */
	public List<SatelliteImage> getReflectanceCorrected() {
		return getReflectanceCorrected(0, getWidth(), 0, getHeight());
	}

	/**
	 * Crea una copia de esta imagen y le aplica la correcciÛn en base a su
	 * <b>Reflectancia</b>.
	 * 
	 * @return Una lista de im·genes satelitales. Cada una de las im·genes de la
	 *         lista corresponde a una banda de la imagen corregida por
	 *         reflectancia.
	 */
	public List<SatelliteImage> getReflectanceCorrected(int fromX, int toX,
			int fromY, int toY) {
		List<SatelliteImage> ret = new ArrayList<SatelliteImage>();
		// Rectangle crop = new Rectangle(fromX,fromY,toX - fromX,toY - fromY);

		// DataBufferByte db = (DataBufferByte)getData(crop).getDataBuffer();
		int bufSize = (toX - fromX) * (toY - fromY);
		int[] bands = getBands();
		for (int b = 0; b < bands.length; ++b) {
			int[] bandsAux = new int[1];
			bandsAux[0] = bands[b];
			double[] radiances = getRadiance(fromX, toX, fromY, toY, bands[b]);

			double[] rhos = rayleigh.getReflectance(bandsAux, radiances);

			double minRho = Double.MAX_VALUE;
			double maxRho = Double.MIN_VALUE;
			for (int i = 0; i < rhos.length; ++i) {
				if (rhos[i] > maxRho)
					maxRho = rhos[i];
				if (rhos[i] < minRho)
					minRho = rhos[i];
			}

			double difRhos = maxRho - minRho;
			double factor = 255.0 / difRhos;

			System.err.println("maxRho= " + maxRho + " | minRho= " + minRho);

			// FIXME hacer minimos y maximos para el ajuste de la escala
			DataBufferByte dbCorrected = new DataBufferByte(bufSize);
			for (int i = 0; i < rhos.length; ++i) {
				// dbCorrected.setElem(i, (byte) (rhos[i]*255.0));
				dbCorrected.setElem(i, (byte) ((rhos[i] - minRho) * factor));
			}

			// ret.add(makeCopy(dbCorrected,fromX,toX,fromY,toY,b));
			ret.add(ImageFactory.makeOneBandSatelliteImage(decoder,
					dbCorrected, fromX, toX, fromY, toY, b));
		}
		/*
		 * double[] radiances = getRadiance(fromX, toX, fromY, toY); int
		 * cantBands = getCantBands(); int bufSize = db.getSize()/cantBands;
		 * 
		 * double []rhos = rayleigh.getReflectance(bands, radiances); for (int
		 * band=0; band<cantBands; ++band){ DataBufferByte dbCorrected = new
		 * DataBufferByte(bufSize); for (int i = band; i < rhos.length;
		 * i+=cantBands) { dbCorrected.setElem(i/cantBands, (byte)
		 * (rhos[i]255.0)); }
		 * 
		 * ret.add(makeCopy(dbCorrected,fromX,toX,fromY,toY,band)); }
		 */

		return ret;
	}

	/**
	 * Crea una copia de esta imagen y le aplica la correcciÛn en base a su
	 * <b>Radiancia</b>.
	 * 
	 * @return Una lista de im·genes satelitales. Cada una de las im·genes de la
	 *         lista corresponde a una banda de la imagen corregida por
	 *         radiancia.
	 */
	public List<SatelliteImage> getRadianceCorrected() {
		return getRadianceCorrected(0, getWidth(), 0, getHeight());
	}

	// /**
	// * Dada una firma y un comparador, retorna 4 imagenes que corresponden a
	// cada una de las 3 bandas de esta imagen
	// * y a todas ellas unidas en una sola. Listas para poner en solapas ;)
	// *
	// * @param comparator comparador de las firmas. Ya tiene adentro la firma
	// original, por eso es que esta no se recibe como par·metro
	// * @return una lista con 4 imagenes: 3 de una sola banda y una de 3 bandas
	// */
	// public List<SatelliteImage> getImageWithThisSignature(SignatureComparator
	// comparator){
	// List<SatelliteImage> ret = new ArrayList<SatelliteImage>(4);
	//		
	// int[] originalData = getData().getDataBuffer().getOffsets();
	//		
	// byte []correctedData = getByteRadiance(0, width, 0,
	// height);//correctByRadiance(originalData);
	//		
	// byte[] newData = new byte[originalData.length];
	//
	// // ahroa voy leyendo arreglos del tama√±o de la cantidad de bandas y cada
	// // uno de esos
	// // es una firma de un pixel. La tengo que comparar con la firma que me
	// // pasaron.
	// int i = 0;
	// int posInterna = 0;
	// byte[] currentSign = new byte[getCantBands()];
	// int signSize = currentSign.length;
	//		
	//		
	// while (i < correctedData.length) {
	// if (posInterna < signSize) {
	// currentSign[posInterna] = correctedData[i];
	// ++posInterna;
	// ++i;
	// } else {
	// if (comparator.areEquivalent(currentSign)) {
	// // las firmas son parecidas, se agrega el pixel.
	// copyData(currentSign, newData, i - posInterna);
	// } else {
	// // las firmas son distintas. Se llena el pixel con un color
	// // por defecto
	// erasePixel(newData, i - posInterna + 1, posInterna - 1);
	// }
	// posInterna = 0;
	// }
	//			
	// }
	// //agrego las 3 imagenes de las bandas a la lista
	// for (int index = 0; index < getCantBands(); ++i){
	// ret.add(makeCopy(newData, 0, width, 0, height, index));
	// }
	// ret.add(makeCopy(newData, 0, width, 0, height));
	// JAI.create("filestore", ret,"signature.jpg","JPEG");
	//		
	//		
	// return ret;
	// }

	// private byte[] getByteRadiance(int i, int width, int j, int height) {
	// double[] dRads = getRadiance(i,i+width,j,j+height);
	// byte[] bRads = new byte[dRads.length];
	// for (int k = 0; k < dRads.length; k++) {
	// bRads[i] = (byte)dRads[i];
	// }
	// return bRads;
	// }

	// /**
	// * Hace una imagen nueva a partir de los datos crudos
	// * @param newData datos crudos
	// * @return
	// */
	// private SatelliteImage makeCopy(byte[] newData, int fromX, int toX, int
	// fromY,
	// int toY) {
	// int cantBands = getCantBands();
	// int size = (toX - fromX) * (toY - fromY) * cantBands;
	//		
	// return makeCopy(new DataBufferByte(newData,size),fromX,toX,fromY,toY);
	// }

	// private SatelliteImage makeCopy(DataBufferByte dbCorrected, int fromX,
	// int toX, int fromY, int toY) {
	// // Create an pixel interleaved data sample model.
	// int cantBands = dbCorrected.getSize() / (toX-fromX) / (toY-fromY);
	// SampleModel sampleModel =
	// RasterFactory.
	// createPixelInterleavedSampleModel(DataBuffer.TYPE_BYTE,
	// //createBandedSampleModel(DataBuffer.TYPE_FLOAT,
	// toX-fromX,toY-fromY,cantBands);
	// // Create a compatible ColorModel.
	// ColorModel colorModel = PlanarImage.createColorModel(sampleModel);
	// // Create a WritableRaster.
	// Raster raster =
	// RasterFactory.createWritableRaster(sampleModel,dbCorrected,
	// new Point(0,0));
	//		
	// int []bands = getBands();
	//		
	// // Create a TiledImage using the SampleModel.
	// SatelliteImage ret = new SatelliteImage(0,0,toX-fromX,toY-fromY,0,0,
	// sampleModel,colorModel,bands);
	// // Set the data of the tiled image to be the raster.
	// ret.setData(raster);
	// return ret;
	// }

	// /**
	// * Hace una imagen nueva a partir de los datos crudos
	// * @param newData datos crudos
	// * @param indexBand el numero de la banda que se quiere copiar
	// * @return
	// */
	// private SatelliteImage makeCopy(byte[] newData, int fromX, int toX,
	// int fromY, int toY, int indexBand) {
	//		
	//		
	// int size = (toX - fromX) * (toY - fromY);
	// byte[] oneBandData = new byte[size];
	// int cantBands = getCantBands();
	//		
	// int maxSize = cantBands * size;
	// int j=0;
	// for (int i=indexBand; i<maxSize; i += cantBands){
	// oneBandData[j] = newData[i];
	// ++j;
	// }
	//		
	//		
	// return makeCopy(new
	// DataBufferByte(oneBandData,size),fromX,toX,fromY,toY,getBands
	// ()[indexBand]);
	// }
	//
	// private byte[] correctByRadiance(int[] originalData) {
	// double[] radiances = getRadiance(0, width, 0, height);
	//		
	//		
	//		
	// return null;
	// }
	//
	// /**
	// * Pinta un pixel (todas sus bandas) con un color por defecto para indicar
	// * que el pixel no fue seleccionado y que no cumple con la condici√≥n de
	// * similitud con una <i>firma digital</i>.
	// *
	// * @param newImage
	// * El arreglo de bytes que representa a la imagen que se est√°
	// * armando. En √©l se va a poner el pixel con el color por
	// * defecto.
	// * @param beginningPos
	// * beginningPos Posici√≥n a partir de la cual se van a empezar a
	// * guardar los pixeles en la {@code newImage}.
	// * @param cantBands
	// * La cantidad de bandas que tiene la imagen satelital.
	// */
	// private void erasePixel(byte[] newImage, int beginningPos, int cantBands)
	// {
	// for (int j = 0; j < cantBands; ++j)
	// newImage[beginningPos + j] = 0;
	// }

	// /**
	// * Copia los datos de todas las bandas para un pixel.
	// *
	// * @param pixel
	// * El pixel en forma de {@code byte[]}, tiene los valores para
	// * todas las bandas en √©l.
	// * @param newImage
	// * El arreglo de bytes que representa a la imagen que se est√°
	// * armando. En √©l se van a copiar los datos del {@code pixel}.
	// * @param beginningPos
	// * Posici√≥n a partir de la cual se van a empezar a guardar los
	// * pixeles en la {@code newImage}.
	// */
	// private void copyData(byte[] pixel, byte[] newImage, int beginningPos) {
	// for (int j = 0; j < pixel.length; ++j)
	// newImage[beginningPos + j] = pixel[j];
	// }

	/**
	 * Crea una copia de esta imagen y le aplica la correcciÛn en base a su
	 * <b>Radiancia</b>.
	 * 
	 * @return Una lista de im·genes satelitales. Cada una de las im·genes de la
	 *         lista corresponde a una banda de la imagen corregida por
	 *         radiancia.
	 */
	public List<SatelliteImage> getRadianceCorrected(int fromX, int toX,
			int fromY, int toY) {
		List<SatelliteImage> ret = new ArrayList<SatelliteImage>();

		// Rectangle crop = new Rectangle(fromX,fromY,toX - fromX,toY - fromY);
		//		
		//		
		// int []bands = getBands();
		// int i = 0;
		int[] bands = getBands();
		for (int i = 0; i < getCantBands(); ++i) {
			// int band = 0;
			DataBufferByte db = getOneBandData(i);
			int size = width * height;
			DataBufferByte dbCorrected = new DataBufferByte(size);

			double[] rads = getRadiance(fromX, toX, fromY, toY, bands[i]);
			for (int j = 0; j < rads.length; ++j) {
				// byte b = (byte)(db.getElem(j)-rads[j]);
				dbCorrected.setElem(j, db.getElem(j) - (int) rads[j]);
			}
			// ret.add(makeCopy2(db,fromX,toX,fromY,toY,band));
			ret.add(ImageFactory.makeOneBandSatelliteImage(decoder,
					dbCorrected, fromX, toX, fromY, toY, i));
		}
		return ret;
	}

	/**
	 * @deprecated Sacar esto de ac· cuando haya corroborado que funciona bien
	 *             la generacion de imagenes corregidas por reflectancia
	 * 
	 */
	private SatelliteImage makeCopy(DataBufferByte dbCorrected, int fromX,
			int toX, int fromY, int toY, int band) {
		// Create an pixel interleaved data sample model.
		int cantBands = dbCorrected.getSize() / (toX - fromX) / (toY - fromY);
		SampleModel sampleModel = RasterFactory
				.createPixelInterleavedSampleModel(DataBuffer.TYPE_BYTE,
				// createBandedSampleModel(DataBuffer.TYPE_FLOAT,
						toX - fromX, toY - fromY, cantBands);
		// Create a compatible ColorModel.
		ColorModel colorModel = PlanarImage.createColorModel(sampleModel);
		// Create a WritableRaster.
		Raster raster = RasterFactory.createWritableRaster(sampleModel,
				dbCorrected, new Point(0, 0));

		int[] bands = new int[cantBands];
		bands[0] = band;

		// Create a TiledImage using the SampleModel.
		SatelliteImage ret = new SatelliteImage(0, 0, toX - fromX, toY - fromY,
				0, 0, sampleModel, colorModel, bands);
		// Set the data of the tiled image to be the raster.
		ret.setData(raster);
		return ret;
	}

	public DataBufferByte getOneBandData(int band) {
		int size = getWidth() * getHeight();
		// byte[] data = new byte[size];
		DataBufferByte dbb = new DataBufferByte(size);
		DataBufferByte dbOrig = (DataBufferByte) getData().getDataBuffer();
		int cantBands = dbOrig.getSize() / size;
		int j = 0;
		for (int i = band; i < dbOrig.getSize(); i += cantBands) {
			dbb.setElem(j, (byte) dbOrig.getElem(i));
			++j;
		}
		return dbb;
	}

	/**
	 * Devuelve una imagen satelital con una sola banda presente.
	 * 
	 * @param band
	 *            El Ìndice de la banda que se quiere extraer de <b>esta</b>
	 *            imagen satelital.<br>
	 *            Si <b>esta</b> imagen posee las bandas <i>2, 4 y 3</i> (en ese
	 *            orden), el Ìndice <code>0</code> corresponde a la banda
	 *            <code>2</code>, el <code>1</code> a la <code>4</code> y el
	 *            <code>2</code> a la <code>3</code>.
	 * @return Una imagen satelital de una sola banda
	 */
	public SatelliteImage getOneBand(int band) {

		return ImageFactory.makeOneBandSatelliteImage(decoder,
				getOneBandData(band), 0, getWidth(), 0, getHeight(), band);
	}

	/** Devuelve el identificador del satÈlite del cual proviene esta iamgen */
	public String getSatelliteId() {
		return satelliteId;
	}
}
