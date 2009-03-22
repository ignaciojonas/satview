package edu.pdi2.math.indexes;

/** Aquellas clases que implementen esta interfaz van a poder calcular la Radiancia L para
 * poder brindar su resultado. <br>
 * Algunas de las clases que deberían implementarla son: <br>
 * - Rayleigh <br>
 * - Satellite <br>
 * - Surface <br>
 * @author fede
 *
 */
public interface RadianceIndex {
	public double getRadiance(Object params);
}
