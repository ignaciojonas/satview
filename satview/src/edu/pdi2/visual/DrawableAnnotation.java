package edu.pdi2.visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class DrawableAnnotation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5858680713538667214L;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void paint(Graphics g2d);
	
	public abstract Annotation getPortionWithin(Rectangle r);

	public abstract int size();

}
