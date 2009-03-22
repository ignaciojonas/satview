package edu.pdi2.visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import edu.pdi2.forms.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.RenderedImage;
import java.util.List;

import com.sun.media.jai.widget.DisplayJAI;

import edu.pdi2.forms.Polygon;

public class DisplayJAIWithAnnotations extends DisplayJAI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -439979435884835632L;
	//protected ArrayList annotations;
	protected AnnotationsManager annMan;
	private Rectangle rectangle;
	private int currentX;
	private int currentY;
	
	public DisplayJAIWithAnnotations()
	{
		super(); // calls the constructor for DisplayJAI
		init();
	}

	public DisplayJAIWithAnnotations(RenderedImage image)
	{
		super(image); // calls the constructor for DisplayJAI
		init();
	}

	private void init(){
		annMan = new AnnotationsManager();
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent evt) {
				djMouseMoved(evt);
			}

		});

	}

	private void djMouseMoved(MouseEvent evt) {
		this.currentX = evt.getX();
		this.currentY = evt.getY();
		this.repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		List<DrawableAnnotation> annotations = annMan.getAnnotationsWithin(rectangle);
		Point p = new Point(currentX,currentY);
		for (int a=0;a<annotations.size();a++) // For each annotation.
		{
			Annotation element = (Annotation)annotations.get(a);
			if (element.isIn(p))
				g2d.setColor(new Color(255,255,0));
			else
				g2d.setColor(new Color(0,255,0));
			element.paint(g2d);
		}
		g.dispose();
		
	}
	
	/**
	 * Agrega una {@code DrawableAnnotation}.
	 */
	public void addAnnotation(DrawableAnnotation a) {
		annMan.add(a);
	}
	
	/**
	 * Toma los puntos de adentro de un {@code Polygon} y con ellos arma una
	 * {@code Annotation}. Los puntos que extrae son los de la clase {@code java.awt.Point},
	 * no los <i>gPoints</i>.
	 * @param poly un Pol�gono con puntos en coordenadas <i>(x,y)</i> y puntos en <i>lat-lon</i>.
	 * Solamente se van a extraer los primeros.
	 */
	public void addAnnotation(Polygon poly){
		annMan.add(new Annotation(poly));
	}

	
	/**
	 * Este método es muy importante: para que las anotaciones solamente se dibujen
	 * <b>si deben ser vistas</b> es necesario conocer la porción actual de la
	 * {@code SatelliteImage} que se está mostrando en la {@code DisplayJAIWithAnnotations}.
	 * Este método es el que se usa para mantener actualizada constantemente esa
	 * porción (también llamada <i>proyección</i>).<br>
	 * Se me ocurre que lo que se puede hacer es agregar funcionalidad en la clase
	 * {@linkplain PDI} para que cuando se haga un <i>Drag & Drop</i> sobre el {@code DisplayThumbnail}
	 * se tome la nueva porción y se actualice a través de <b>este</b> método.
	 * @param rectangle la nueva proyección de la {@code SatelliteImage} (la que está
	 * encerrada en el {@code DisplayThumbnail} y que se ve completita en la
	 * {@code DisplayJAIWithAnnotations})
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
}
