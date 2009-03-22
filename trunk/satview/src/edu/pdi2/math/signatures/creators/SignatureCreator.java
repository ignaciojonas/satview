package edu.pdi2.math.signatures.creators;
/**
 * Se encarga de formar una firma dados los datos en forma de arreglos.<br>
 * Los datos van a llegar en forma de arreglos de 1 o 3 dimensiones. Los de 1 dimensión significan
 * que la firma se está pidiendo para un solo punto de la imagen y que se están recibiendo
 * los valores de todas las bandas de la imagen de <b>ese</b> punto.<br>
 * Si el arreglo es de 3 dimensiones quiere decir que se quiere sacar la firma de una <b>región</b>
 * de posiciones de la imagen. En este caso, dependiendo de la implementación de esta interfaz,
 * se puede optar por sacar un <i>promedio</i>, <i>máximo</i>, <i>mínimo</i>, etc entre la matriz de puntos
 * que se muestreen en cada una de las bandas.
 * @author fede
 *
 */
public abstract class SignatureCreator {
	
	//np hay niguna mística acá: la firma son los datos de las bandas en ese punto
	public byte[] getSignature(byte[] datos){
		return datos;
	}
	/**
	 * Este es el método que las subclases van a implementar para que tome promedios de los ND's, o
	 * máximos o mínimos, o cualquier otro estilo de medida derivada.
	 * @param datos la matriz de ND para una región de la imagen satelital.
	 */
	public abstract byte[] getSignature(byte [][][]datos);
}
