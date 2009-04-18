package edu.pdi2.visual;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ViewSignature extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4455829839421588341L;

	public ViewSignature(String name,byte[] sign){
		super();
		XYSeries signature=new XYSeries("Signature");
		for (int i = 0; i < sign.length; i++) {
			signature.add(i, sign[i]);
		}
		
		
		JFrame frame = new JFrame();
		XYSeriesCollection dataset=new XYSeriesCollection();
		dataset.addSeries(signature);
		JFreeChart signatureG=ChartFactory.createXYLineChart("Signature "+name,"Valorx","Valory",dataset,PlotOrientation.VERTICAL, true, true, false);
	    ChartPanel chartpanel = new ChartPanel(signatureG);
	    chartpanel.setPreferredSize(new Dimension(390, 290));
	    frame.setContentPane(chartpanel);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		frame.pack();
		frame.setSize(400,300);
		frame.setVisible(true);
		if(name==" RED")
			frame.setLocation(0,300);
		else
			frame.setLocation(400,300);
			
	    
	    }

	   

}
