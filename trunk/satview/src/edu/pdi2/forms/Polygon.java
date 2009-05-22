package edu.pdi2.forms;


import java.util.ArrayList;

import java.util.List;
import java.util.Vector;


import edu.pdi2.math.transforms.ElasticTransform;
import edu.pdi2.math.transforms.RectangleTransform;
import edu.pdi2.math.transforms.TriangleTransform;

public class Polygon  {
	private Vector<GeographicPoint> gPoints;
	
	public Vector<GeographicPoint> getGPoints() {
		return gPoints;
	}


	public void setGPoints(Vector<GeographicPoint> points) {
		gPoints = points;
	}


	/**
	 * Constructor
	 *
	 */
	public Polygon(Vector<GeographicPoint> gPoints) {
		super();
		this.gPoints=gPoints;
	}

	public Polygon() {}
	public boolean isIn(Point p) {
		Double angle=0.0;
		VectorA l1,l2;
		GeographicPoint gp1,gp2;
		if(gPoints.size()>=3){
		for (int i=0;i<gPoints.size()-1;i++) {
			gp1= (GeographicPoint) gPoints.get(i);
			l1= new VectorA(p,gp1.getP());
			gp2= (GeographicPoint) gPoints.get((i+1)%gPoints.size());
			l2= new VectorA(p,gp2.getP());
			angle= angle+Angle.angle(l1, l2);
			
		}
	}
		Double d=Math.abs(Math.toDegrees(angle));
		return (d.isNaN()||d>180.0);
	}
	
	@SuppressWarnings("unchecked")
	public double getVolume(){
		Vector<GeographicPoint> points = (Vector<GeographicPoint>) getGPoints().clone();
		GeographicPoint p0 = points.get(0);
		points.remove(0);
		GeographicPoint p1 = points.get(0);
		points.remove(0);
		GeographicPoint p2 = null;
		
		double area = 0;
		do {
			p2 = points.get(0);
			points.remove(0);
			area += (p1.getLon()-p0.getLon())*(p2.getLat()-p0.getLat()) - (p1.getLat()-p0.getLat())*(p2.getLon()-p0.getLon());
			p0 = p1;
			p1 = p2;
		}while (points.size() > 0);
		return area*0.5;
		

	}
	
	public ElasticTransform getET(){
		GeographicPoint gp1,gp2,gp3,gp4;
		if(gPoints.size()>3){
			gp1=gPoints.get(0);
			gp2=gPoints.get(1);
			gp3=gPoints.get(2);
			gp4=gPoints.get(3);
			double a1=gp1.getP().getX();
			double a2=gp2.getP().getX();
			double a3=gp3.getP().getX();
			double a4=gp4.getP().getX();
			double b1=gp1.getP().getY();
			double b2=gp2.getP().getY();
			double b3=gp3.getP().getY();
			double b4=gp4.getP().getY();
			double a1_=gp1.getLon();
			double a2_=gp2.getLon();
			double a3_=gp3.getLon();
			double a4_=gp4.getLon();
			double b1_=gp1.getLat();
			double b2_=gp2.getLat();
			double b3_=gp3.getLat();
			double b4_=gp4.getLat();
			return new RectangleTransform(a1,a2,a3,a4,b1,b2,b3,b4,a1_,a2_,a3_,a4_,b1_,b2_,b3_,b4_); 
		}
		else{
			gp1=gPoints.get(0);
			gp2=gPoints.get(1);
			gp3=gPoints.get(2);
			double a1=gp1.getP().getX();
			double a2=gp2.getP().getX();
			double a3=gp3.getP().getX();
			double b1=gp1.getP().getY();
			double b2=gp2.getP().getY();
			double b3=gp3.getP().getY();	
			double a1_=gp1.getLon();
			double a2_=gp2.getLon();
			double a3_=gp3.getLon();
			double b1_=gp1.getLat();
			double b2_=gp2.getLat();
			double b3_=gp3.getLat();
			return new TriangleTransform(a1,a2,a3,b1,b2,b3,a1_,a2_,a3_,b1_,b2_,b3_);
		}
	}
	
	/**
	 * Retorna los puntos que contiene este polígono. Los puntos están en el sistema
	 * <i>(x,y)</i> y pertenecen a coordenadas de pantalla (no a coordenadas geográficas, esas
	 * están en <i>Lat-Lon</i>).
	 */
	public List<Point> get_Points(){
		int size = gPoints.size();
		
		List<Point> ret = new ArrayList<Point>(size);
		for (int i=0; i<size; ++i){
			ret.add(gPoints.get(i).getP());
		}
		
		return ret;
	}


	public void changeColor() {
		// TODO Auto-generated method stub
		
	}

}

