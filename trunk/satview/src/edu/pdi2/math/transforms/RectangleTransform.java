package edu.pdi2.math.transforms;

/**
 * Toma todos los datos de los puntos originales de la imagen y los de la rotación, en base
 * a ellos reliza los cálculos necesarios para hacer la transformación elástica.
 * @author fede
 *
 */
public class RectangleTransform implements ElasticTransform{
	private double c1,c2,c3,c4,c5,c6,c7,c8,w1,w2,w3,w4,w5,w6,w7,w8;
	
	/**
	 * Toma todos los datos necesarios para hacer el cálculo de la transformación elástica.
	 * Los parámetros sin guiones bajos son los puntos del conjunto de salida, ellos conforman
	 * los 4 puntos (a1,b1) , (a2,b2) , (a3,b3) , (a4,b4) que hacen el cuadrado original. Los
	 * otros 8 parámetros (los que tienen guiones bajos) conforman los 4 puntos que tiene el
	 * cuadrado en el espacio de llegada (es decir, el espacio al cual se proyecta).
	 * Esto está más claro en la clase práctica 3, en donde habla de transformaciones elásticas-
	 * 
	 */
	public RectangleTransform(double a1,double a2,double a3,double a4,
			double b1,double b2,double b3,double b4,
			double a1_,double a2_,double a3_,double a4_,
			double b1_,double b2_,double b3_,double b4_){
		
        w8 = b1_ - ((b1_ - b2_)* (a1 - a4 ) / (a1 - a2));
        w7 = b3_ - b1_ + ((b1_ - b2_) * (a1 - a3 ) / (a1 - a2 ));
        w6 = ((b1 - b2 ) * (a1 - a4 ) / (a1 - a2 )) - (b1 - b4 );
        w5 = ((a1 * b1 - a2 * b2 ) * (a1 - a3 ) / (a1 - a2 )) - (a1 * b1 - a3 * b3 );
        w4 = a1_-((a1_-a2_ ) * (a1 - a4 ) / (a1 - a2 ));
        w3 = a3_- a1_+ ((a1_- a2_) * (a1 - a3 ) /( a1 - a2 ) );
        w2 = (((a1 * b1 - a2 * b2 ) * (a1 - a4 )) / (a1 - a2 )) - (a1 * b1 - a4 * b4 );
        w1 = ((b1 - b2 ) * (a1 - a3 ) / (a1 - a2 )) - (b1 - b3 );
        
        c8 = (b4_- w8 - (w6 * w7 / w1 )) / (w2 - ((w5 * w6 ) / w1 ));
        c7 = ( b3_ - b1_ + ((b1_ - b2_) * (a1 - a3 ) / (a1 - a2 )) - c8 * w5) / w1;
        c6 = (a1_ - a2_ - c7 * (b1 - b2 ) - c8 * (a1 * b1 - a2 * b2 )) / (a1 - a2 );
        c5 = b1_ - c6 * a1 - c7 * b1 - c8 * a1 * b1;
        c4 = (a4_ - w4 - (w6 * w3 / w1)) / (w2 - (w5 * w6 / w1));
        c3 = (a3_ - a1_ + ((a1_ - a2_)*(a1 - a3)/(a1-a2))-c4 * w5)/w1;
        c2 = (a1_ - a2_ - c3 * (b1 - b2 ) - c4 * (a1 * b1 - a2 * b2 )) / (a1 - a2 );
        c1 = a1_ - c2 * a1 - c3 * b1 - c4 * a1 * b1;
	}
	
	/**
	 * Dados los puntos X e Y en el espacio de Salida, devuelve el Xi' ubicado en el 
	 * espacio de llegada.
	 * @param xi La abcisa en el cuadrilátero que está en el espacio a proyectar
	 * @param yi La ordenada en el cuadrilátero que está en el espacio a proyectar
	 * @return La abcisa en el cuadrilátero que está en el espacio en que se proyecta
	 */
	public double getXi_(double xi,double yi){
		return   c1 + c2 * xi + c3 * yi + c4 * xi * yi;
	}
	
	/**
	 * Dados los puntos X e Y en el espacio de Salida, devuelve el Yi' ubicado en el 
	 * espacio de llegada.
	 * @param xi La abcisa en el cuadrilátero que está en el espacio a proyectar
	 * @param yi La ordenada en el cuadrilátero que está en el espacio a proyectar
	 * @return La ordenada en el cuadrilátero que está en el espacio en que se proyecta
	 */

	public double getYi_(double xi,double yi){
		return   c5 + c6 * xi + c7 * yi + c8 * xi * yi;
	}
}
