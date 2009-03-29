package edu.pdi2.imaging;

import java.util.List;

/**
 * Provee utilidades para trabajar con las estructuras internas de los datos que
 * luego ir�n a parar dentro de una imagen satelital. Por ejemplo, puede extraer
 * una banda desde adentro de un arreglo aplanado de pixeles, o recortar datos
 * de la imagen.
 * 
 * @author fede
 * 
 */
public class RawDataUtils {

	/**
	 * Crea una imagen con una cantidad de bandas distinta de la imagen original. Sirve para generar imagenes de 3 bandas a
	 * partir de una de 7 bandas.
	 * @param sourceData El contenido original de la imagen, con una cantidad de bandas posiblemente grande (por ejemplo: 7 bandas)
	 * @param numBands Cantidad de bandas de la imagen a generar.
	 * @param currentBands Las bandas que actualmente tiene la imagen
	 * @param maxBands El m�ximo de bandas de la imagen original
	 */
	public static byte[] flatImage(byte[] sourceData, int fromX, int toX, int fromY, int toY, int numBands, List<String> currentBands, int maxBands) {
		
		int[] jumps = getJumpsVector(currentBands, maxBands);
		int bandIndex = 0;
		
		String nextBand = currentBands.get(0);
		int currBand = Integer.valueOf(nextBand.substring(nextBand.lastIndexOf(".") - 1, nextBand.lastIndexOf("."))) -1;
		
		int retIndex = 0;
		
		
		byte[] ret = new byte[(toX - fromX) * (toY - fromY) * numBands];
		for (int y=fromY; y < toY; ++y){
			for (int x=fromX; x < toX; ++x){
				for (int b=0; b<jumps.length; ++b){
					ret[retIndex] = sourceData[currBand];
					++retIndex;
					currBand += jumps[bandIndex];
					bandIndex = (bandIndex + 1) % currentBands.size();
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Crea un vector que almacena en cada posici�n un n�mero, ese n�mero es la cantidad de bandas que hay
	 * entre la banda <i>i</i> y la <i>i + 1</i>. Por ejemplo, si hay 7 bandas en la imagen y las bandas actuales
	 * son:<br>
	 * <b>[1, 3, 6]</b><br>
	 * Entonces el vector resultante ser�:<br>
	 * <b>[2,3,2]</b> <i>(El �ltimo 2 es porque, al haber 7 bandas, hay que moverse 1 lugar para llegar de la banda
	 * 6 a la 7 y 1 lugar m�s para llegar de la 7 a la 1)</i><br>
	 * <br>
	 * Los contenidos del vector tambi�n pueden ser negativos. Por ejemplo, si las bandas de la imagen son:<br>
	 * <b>[4, 2, 6]</b><br>
	 * Entonces el vector resultante ser�:<br>
	 * <b>[-2, 4, 5]</b>
	 * 
	 * @param currentBands La lista de bandas actuales de la imagen. Debe mantenerse actualizada en todo momento.
	 * @param maxBands El m�ximo de bandas de la imagen original
	 * @return Un vector con �ndices de desplazamiento para leer la matriz con <b>todos</b> los datos (salteando
	 * aquellos que pertenezcan a bandas que no interesan en la imagen actual).
	 */
	private static int[] getJumpsVector(List<String> currentBands, int maxBands) {
		int []ret = new int[currentBands.size()];
		int []ints = new int[currentBands.size()];
		int i = 0;
//		primero paso las bandas a un arreglo de enteros
		for (i=0; i < currentBands.size(); ++i){
			String currBand = currentBands.get(i);
			int currIndex = Integer.valueOf(currBand.substring(currBand.lastIndexOf(".") - 1, currBand.lastIndexOf(".")));
			ints[i] = currIndex;
		}
		
		for (i=0; i<ret.length - 1; ++i){
			ret[i] = ints[i + 1] - ints[i];
		}
		ret [i] = maxBands - ints[i] + ints[0];
		return ret;
	}
	
	/**
	 * Copia los datos de todas las bandas para un pixel.
	 * 
	 * @param pixel
	 *            El pixel en forma de {@code byte[]}, tiene los valores para
	 *            todas las bandas en él.
	 * @param newImage
	 *            El arreglo de bytes que representa a la imagen que se está
	 *            armando. En él se van a copiar los datos del {@code pixel}.
	 * @param beginningPos
	 *            Posición a partir de la cual se van a empezar a guardar los
	 *            pixeles en la {@code newImage}.
	 */
	public static void copyPixel(byte[] pixel, byte[] newImage, int beginningPos) {
		for (int j = 0; j < pixel.length; ++j)
			newImage[beginningPos + j] = pixel[j];
	}
	
	/**
	 * Pinta un pixel (todas sus bandas) con un color por defecto para indicar
	 * que el pixel no fue seleccionado y que no cumple con la condición de
	 * similitud con una <i>firma digital</i>.
	 * 
	 * @param newImage
	 *            El arreglo de bytes que representa a la imagen que se está
	 *            armando. En él se va a poner el pixel con el color por
	 *            defecto.
	 * @param beginningPos
	 *            beginningPos Posición a partir de la cual se van a empezar a
	 *            guardar los pixeles en la {@code newImage}.
	 * @param cantBands
	 *            La cantidad de bandas que tiene la imagen satelital.
	 */
	public static void erasePixel(byte[] newImage, int beginningPos, int cantBands) {
		for (int j = 0; j < cantBands; ++j)
			newImage[beginningPos + j] = 0;
	}
}
