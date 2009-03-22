package edu.pdi2.math.signatures.comparators;

/**
 * Interfaz definida para manejar la comparación entre <b>firmas digitales</b>. Las clases
 * que la implementen se van a encargar de determinar si dos firmas son iguales (<i>equivalentes</i>,
 * en realidad) basándose en algún criterio de similitud arbitrario. Por ejemplo, pueden ser iguales
 * si cada uno de los puntos de la firma no se aleja más de un cierto porcentaje a su
 * par en la otra firma (sería como una firma por <i>igualdad</i> pero con una determinada
 * <b>tolerancia</b>).
 * 
 * @author fede
 *
 */
public interface SignatureComparator {
	public boolean areEquivalent(byte[] signature);
}
