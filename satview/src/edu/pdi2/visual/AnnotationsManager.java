package edu.pdi2.visual;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.pdi2.forms.Polygon;

/**
 * Se encarga de las tareas más comunes para trabajar con anotaciones.
 * 
 * @author fede
 *
 */
public class AnnotationsManager {
	List<DrawableAnnotation> annotations = new ArrayList<DrawableAnnotation>();
	
	/**
	 * Agrega una {@code DrawableAnnotation}.
	 */
	public void add(DrawableAnnotation a) {
		annotations.add(a);
	}
	
	/**
	 * Toma los puntos de adentro de un {@code Polygon} y con ellos arma una
	 * {@code Annotation}. Los puntos que extrae son los de la clase {@code java.awt.Point},
	 * no los <i>gPoints</i>.
	 * @param poly un Pol�gono con puntos en coordenadas <i>(x,y)</i> y puntos en <i>lat-lon</i>.
	 * Solamente se van a extraer los primeros.
	 */
	public void add(Polygon poly){
		annotations.add(new Annotation(poly));
	}
	
	/**
	 * Devuelve todas las anotaciones que se encuentren dentro de determinado
	 * rectángulo. Están desplazadas de acuerdo con el rectángulo y listas para dibujar.
	 * @param r El rectángulo que indica la proyección de la imagen satelital que
	 * se está viendo en ste momento. Está en coordenadas <i>(x,y)</i>.
	 */
	public List<DrawableAnnotation> getAnnotationsWithin(Rectangle r){
		List<DrawableAnnotation> ret = new ArrayList<DrawableAnnotation>();
		for (int i=0; i<annotations.size(); ++i) {
			DrawableAnnotation da = annotations.get(i).getPortionWithin(r);
			if (da.size() > 0){
				ret.add(da);
			}
		}
		return ret;
	}
}
