

import java.awt.Point;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;

import com.sun.media.jai.codec.FileSeekableStream;

import edu.pdi2.math.indexes.satellite.SatelliteImage;

/**
 * Lee archivos de bandas pasados por parámetro y los une en una PlanarImage
 * @author fede
 *
 */
public class FileChopReader {

	/**
	 * Lee los archivos de las bandas y los une en una imagen. Las dimensiones de la imagen
	 * devuelta dependen de la cantidad de datos que se quieran leer por filas y por columnas. Por
	 * ejemplo, si se lee desde la columna c1 hasta c2 y desde la fila f1 hasta la f2, el tamaño
	 * de la matriz será de (f2-f1+1) X (c2-c1+1).
	 * Como se puede observar por los "+1", la fila y columna final (f2 y c3 respectivamente) también
	 * son leídas.
	 * @param sources Direcciones de los archivos de las bandas que se quiere leer
	 * @param width ancho de la imagen
	 * @param height alto de la imagen
	 * @param fromX columna desde la cual se quiere comenzar a leer
	 * @param toX columna hasta la cual se quiere leer (inclusive)
	 * @param fromY fila desde la cual se quiere comenzar a leer
	 * @param toY fila hasta la cual se quiere leer (inclusive)
	 * @param stepX saltear X bites horizontalmente (se lee 1 de cada X bytes en las filas)
	 * @param stepY saltear X bites vericalmente (se lee 1 de cada X bytes en las columnas)
	 * @return una imagen con la información recortada mediante los parámetros anteriores.
	 */
	public SatelliteImage read(List<String> sources,int width,int height,int fromX,int toX,int fromY,int toY,int stepX,int stepY){
		SatelliteImage tiledImage = null;
		try {
			int numBands = sources.size();

			byte[] data = new byte[numBands * (toY - fromY)/stepY * (toX - fromX)/stepX];
			
			List<FileSeekableStream> listFss = new ArrayList<FileSeekableStream>(sources.size());
			for (String s : sources) {
				listFss.add(new FileSeekableStream(s));
			}
			
			int con,pasada=0;
			
			for (FileSeekableStream fss: listFss) {
				fss.skip(width * fromY + fromX); //salteo los primeros datos que no se van a leer
				con = pasada;
				for (int y=fromY;y<toY;y+=stepY){
					for (int x=fromX;x<toX;x+=stepX){
						data[con] = (byte) fss.read();
						con+=numBands;
						fss.skip(stepX-1);//salteo espacios horizontales, dado un stepX
					}
					fss.skip((stepY -1) * width + width - toX + fromX);//salteo espacios verticales (filas completas)
				}
				fss.close();
				pasada++;
			}
			// Create a Data Buffer from the values on the single image array.
			DataBufferByte dbuffer = new DataBufferByte(data,data.length);
			// Create an pixel interleaved data sample model.
			SampleModel sampleModel =
				RasterFactory.
				createPixelInterleavedSampleModel(DataBuffer.TYPE_BYTE,
						(toX-fromX)/stepX,(toY-fromY)/stepY,numBands);
			// Create a compatible ColorModel.
			ColorModel colorModel = PlanarImage.createColorModel(sampleModel);
			// Create a WritableRaster.
			Raster raster = RasterFactory.createWritableRaster(sampleModel,dbuffer,
					new Point(0,0));
			// Create a TiledImage using the SampleModel.
			tiledImage = new SatelliteImage(0,0,(toX-fromX)/stepX,(toY-fromY)/stepY,0,0,
					sampleModel,colorModel,sources);
			// Set the data of the tiled image to be the raster.
			tiledImage.setData(raster);
			// Save the image on a file.
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tiledImage;
	}
	
	public SatelliteImage read(List<String> sources,int width,int height,int fromX,int toX,int fromY,int toY){
		return read(sources,width,height,fromX,toX,fromY,toY,1,1);
	}
	
	public SatelliteImage read(List<String> sources,int width,int height){
		return read(sources,width,height,0,width,0,height,1,1);
	}
}
