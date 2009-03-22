package edu.pdi2.forms;

public class Angle {

	public static Double angle(VectorA l1, VectorA l2){
		return Math.acos(ProductoEscalar(l1, l2) / (Math.abs(l1.module()) * Math.abs(l2.module())));
	
	}

	public static int ModuloDelProductoVectorialConSigno(VectorA l1, VectorA l2){
		return (int) ((l1.getX() * l2.getY()) - (l1.getY() * l2.getX()));
	}
	public static int ProductoEscalar(VectorA l1, VectorA l2){
		return (int) ((l1.getX() * l2.getX()) + (l1.getY() * l2.getY()));
	}
}
