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
	
	public double getC1() {
		return c1;
	}

	public void setC1(double c1) {
		this.c1 = c1;
	}

	public double getC2() {
		return c2;
	}

	public void setC2(double c2) {
		this.c2 = c2;
	}

	public double getC3() {
		return c3;
	}

	public void setC3(double c3) {
		this.c3 = c3;
	}

	public double getC4() {
		return c4;
	}

	public void setC4(double c4) {
		this.c4 = c4;
	}

	public double getC5() {
		return c5;
	}

	public void setC5(double c5) {
		this.c5 = c5;
	}

	public double getC6() {
		return c6;
	}

	public void setC6(double c6) {
		this.c6 = c6;
	}

	public double getYi_(double xi,double yi){
		return   c4 - c5 * xi - c6 * yi;
	}
	public TriangleTransform(){}
	
}
