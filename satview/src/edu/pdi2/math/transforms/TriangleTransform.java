package edu.pdi2.math.transforms;

public class TriangleTransform implements ElasticTransform{
	private double c1,c2,c3,c4,c5,c6;
	
	public TriangleTransform(double a1,double a2,double a3,
			double b1,double b2,double b3,
			double a1_,double a2_,double a3_,
			double b1_,double b2_,double b3_){
		
		c3 = (a3_-a1_+(a2_*a1 - a2_*a3+a1_*a3-a1_*a1)/(a2-a1)) / ((b1-b2)*(a3-a1)/(a2-a1)+(b3-b1));
		c2 = (b2_-a1_+c3*(b1-b2))/(b1-a1);
		c1 = a1_-a1*c2-b1*c3;
		c6 = (a3_-a1_+(b2_*a1-b2_*a3+b1_*a3-b1_*a1)/(a2-a1))/((b1-b2)*(a3-a1)/(a2-a1)+(b3-b1));
		c5 = (b2_-b1_+c6*(b1-b2))/(a2-a1);
		c4 = a1_-a1*c5-b1*c6;
	}
	
	public double getXi_(double xi,double yi){
		return   c1 - c2 * xi - c3 * yi;
	}
	
	public double getYi_(double xi,double yi){
		return   c4 - c5 * xi - c6 * yi;
	}
	
}
