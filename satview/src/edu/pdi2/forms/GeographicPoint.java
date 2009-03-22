package edu.pdi2.forms;



public class GeographicPoint {
private Point p;
private Double lat, lon;


public Point getP() {
	return p;
}

public void setP(Point p) {
	this.p = p;
}

public Double getLat() {
	return lat;
}

public void setLat(Double lat) {
	this.lat = lat;
}

public Double getLon() {
	return lon;
}

public void setLon(Double lon) {
	this.lon = lon;
}

public GeographicPoint(Point p, Double lat, Double lon) {
	super();
	this.p = p;
	this.lat = lat;
	this.lon = lon;
}



}
