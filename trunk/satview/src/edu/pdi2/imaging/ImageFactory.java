package edu.pdi2.imaging;

import java.awt.Point;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;

import edu.pdi2.decoders.Decoder;
import edu.pdi2.math.indexes.satellite.SatelliteImage;

/**
 * Hace imágenes satelitales a partir del contenido de estas imágenes (es decir,
 * transforma vectores de pixeles en {@code SatelliteImage}s
 * 
 * @author fede
 * 
 */
public class ImageFactory {
	/**
	 * Hace una imagen nueva a partir de los datos crudos
	 * 
	 * @param newData
	 *            datos crudos
	 * @param bands
	 *            Las bandas que está teniendo esta imagen satelital.
	 * @deprecated Usar el método que recibe un {@code Decoder}.
	 */
	public static SatelliteImage makeSatelliteImage(byte[] newData, int fromX,
			int toX, int fromY, int toY, int[] bands) {
		int cantBands = newData.length / (toX - fromX) / (toY - fromY);
		int size = (toX - fromX) * (toY - fromY) * cantBands;

		return makeSatelliteImage(new DataBufferByte(newData, size), fromX,
				toX, fromY, toY, bands);
	}

	/**
	 * Hace una imagen nueva a partir de los datos crudos
	 * 
	 * @param data
	 *            datos crudos
	 * @param bands
	 *            Las bandas que está teniendo esta imagen satelital.
	 * @deprecated Usar el método que recibe un {@code Decoder}.
	 */
	public static SatelliteImage makeSatelliteImage(DataBufferByte data,
			int fromX, int toX, int fromY, int toY, int[] bands) {
		// Create an pixel interleaved data sample model.
		int cantBands = data.getSize() / (toX - fromX) / (toY - fromY);
		SampleModel sampleModel = RasterFactory
				.createPixelInterleavedSampleModel(DataBuffer.TYPE_BYTE,
				// createBandedSampleModel(DataBuffer.TYPE_FLOAT,
						toX - fromX, toY - fromY, cantBands);
		// Create a compatible ColorModel.
		ColorModel colorModel = PlanarImage.createColorModel(sampleModel);
		// Create a WritableRaster.
		Raster raster = RasterFactory.createWritableRaster(sampleModel, data,
				new Point(0, 0));

		// Create a TiledImage using the SampleModel.
		SatelliteImage ret = new SatelliteImage(0, 0, toX - fromX, toY - fromY,
				0, 0, sampleModel, colorModel, bands);
		// Set the data of the tiled image to be the raster.
		ret.setData(raster);
		return ret;
	}

	/**
	 * Crea una imagen satelital de una sola banda.
	 */
	public static SatelliteImage makeOneBandSatelliteImage(Decoder decoder,
			DataBufferByte dbCorrected, int fromX, int toX, int fromY, int toY,
			int band) {
		int width = toX - fromX;
		int height = toY - fromY;
		// Create an pixel interleaved data sample model.
		SampleModel sampleModel = RasterFactory
				.createPixelInterleavedSampleModel(DataBuffer.TYPE_BYTE,
				// createBandedSampleModel(DataBuffer.TYPE_FLOAT,
						width, height, 1);
		// Create a compatible ColorModel.
		ColorModel colorModel = PlanarImage.createColorModel(sampleModel);
		// Create a WritableRaster.
		Raster raster = RasterFactory.createWritableRaster(sampleModel,
				dbCorrected, new Point(0, 0));

		int[] bands = new int[1];
		bands[0] = band;// FIXME aca van las bandas q va a tener la imagen

		// Create a TiledImage using the SampleModel.
		SatelliteImage ret = new SatelliteImage(decoder, 0, 0, width, height,
				0, 0, sampleModel, colorModel, new ArrayList<String>());
		// Set the data of the tiled image to be the raster.
		ret.setData(raster);
		return ret;
	}
	
	/**
	 * Crea una imagen satelital.
	 */
	public static SatelliteImage makeSatelliteImage(Decoder decoder, byte[] data, int fromX, int toX, int fromY,
			int toY, List<String> bands) {
		int numBands = bands.size();
		// Create a Data Buffer from the values on the single image array.
		DataBufferByte dbuffer = new DataBufferByte(data, data.length);
		// Create an pixel interleaved data sample model.
		SampleModel sampleModel = RasterFactory
				.createPixelInterleavedSampleModel(DataBuffer.TYPE_BYTE, toX
						- fromX, toY - fromY, numBands);
		// Create a compatible ColorModel.
		ColorModel colorModel = PlanarImage.createColorModel(sampleModel);
		// Create a WritableRaster.
		Raster raster = RasterFactory.createWritableRaster(sampleModel,
				dbuffer, new java.awt.Point(0, 0));
		// Create a TiledImage using the SampleModel.
		SatelliteImage satImage = new SatelliteImage(decoder, 0, 0, toX - fromX, toY - fromY,
				0, 0, sampleModel, colorModel,bands);
		// Set the data of the tiled image to be the raster.
		satImage.setData(raster);
		return satImage;
	}
}
