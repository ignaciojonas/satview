package edu.pdi2.forms;



public class VectorA {
private Point p1,p2;

public Point getP1() {
	return p1;
}

public void setP1(Point p1) {
	this.p1 = p1;
}

public Point getP2() {
	return p2;
}

public void setP2(Point p2) {
	this.p2 = p2;
}

public VectorA(Point p, Point point) {
	super();
	this.p1 = p;
	this.p2 = point;
}
public double getX(){
	return p2.getX()-p1.getX();
}
public double getY(){
	return p2.getY()-p1.getY();
}

public double module(){
	return Math.sqrt(Math.pow(getX(), 2)+Math.pow(getY(), 2));
}
public VectorA() {

}


}
