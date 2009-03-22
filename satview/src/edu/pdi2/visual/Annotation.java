package edu.pdi2.visual;

import java.awt.BasicStroke;
import java.awt.Graphics;
import edu.pdi2.forms.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.pdi2.forms.GeographicPoint;
import edu.pdi2.forms.Polygon;

/**
 * Representa una anotación que se puede dibujar dentro de la imagen satelital. Para
 * no dibujar anotaciones que no estén dentro del recorte actual de la imagen se va a
 * tener que brindar soporte para <b>conocer</b> ese recorte en todo tiempo.<br>
 * La forma de lograr esto es preguntar a esta {@code Annotation} si se encuentra dentro
 * de un determinado rectángulo, y para eso se usa el método <i>getPortionWithin(Rectangle r)</i>.
 * @author fede
 *
 */
public class Annotation extends DrawableAnnotation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3411886181940922421L;
	private List<Point> puntos;
	private Polygon poly;

	public Annotation(Polygon poly){
		super();
		this.puntos = poly.getPoints();
		this.poly = poly;
		new BasicStroke((float) 5);
	}
	
	public Annotation(List<Point> puntos) {
		super();
		this.puntos = puntos;
		Vector<GeographicPoint> gPs = new Vector<GeographicPoint>(puntos.size());
		for (Point point : puntos) {
			GeographicPoint gp = new GeographicPoint(point,0.0,0.0);
			gPs.add(gp);
		}
		this.poly = new Polygon(gPs);
		new BasicStroke((float) 5);
	}


	@Override
	public void paint(Graphics g2d) {
		//g2d.setStroke(stroke);
		g2d.setColor(getColor());
		
		
		for (int i = 1; i < puntos.size(); ++i) {
			
			g2d.drawLine((int)(puntos.get(i-1).getX()), 
					(int)(puntos.get(i-1).getY()), 
					(int)(puntos.get(i).getX()), 
					(int)(puntos.get(i).getY()));
			
		}
		g2d.drawLine((int)(puntos.get(0).getX()), 
				(int)(puntos.get(0).getY()), 
				(int)(puntos.get(puntos.size()-1).getX()), 
				(int)(puntos.get(puntos.size()-1).getY()));
	}
	
	/**
	 * Retorna una {@code Annotation} cuyos puntos estén completamente dentro
	 * de un rectángulo <b>y sus coordenadas adaptadas de acuerdo al rectángulo</b>.
	 * @param r el rectángulo actual descrito por el recorte que se vé de la
	 * {@code SatelliteImage} (sería la imagen que se encuentra dentro del rectángulo
	 * verde de la {@code DisplayThumbnail}).
	 * @return una {@code Annotation}, lista para ser dibujada. Lo único que hay
	 * que hacer es mandarla adentro de la {@code DisplayJAIWithAnnotations} y 
	 * <i>voilà</i>! :)
	 */
	public Annotation getPortionWithin(Rectangle r){
		int minX = r.x;
		int maxX = r.x + r.width;
		
		int minY = r.y;
		int maxY = r.y + r.height;
		
		List<Point> nuevosPuntos = new ArrayList<Point>();
		
		for (int i = 0; i < puntos.size(); ++i) {
			Point p = puntos.get(i);
			if (p.getX() <= maxX && p.getX() >= minX && p.getY() <= maxY && p.getY() >= minY){
				//el punto está dentro, ahora hay que correrlo para poder dibujarlo en el recorte
				//actual de la imagen satelital
				Point desplazado = new Point((int)(p.getX()-minX),(int)(p.getY()-minY));
				nuevosPuntos.add(desplazado);
			}
		}
		
		return new Annotation(nuevosPuntos);
	}

	@Override
	public int size() {
		return puntos.size();
	}

	public boolean isIn(Point p) {
		return poly.isIn(p);
	}

	

}
