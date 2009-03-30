package edu.pdi2.visual;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ThresholdSignature extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7449518342734020733L;
	
	/** La desviación inicial respecto de la firma*/
	private static final int INITIAL_DEVIATION = 10;
	
//	private JScrollBar jScrollBar1;
//	private JLabel jLabel1;
	private ChartPanel chartpanel;

	private JLabel[] bandLabels;
	private JLabel[] valueLabels;

//	private JLabel jLabel2;
//	private JLabel jLabel3;
//	private JLabel jLabel4;
//	private JLabel jLabel5;
//	private JLabel jLabel14;
//	private JLabel jLabel13;
//	private JLabel jLabel12;
//	private JLabel jLabel11;
//	private JLabel jLabel10;
//	private JLabel jLabel9;
//	private JLabel jLabel8;

	private JScrollBar[] scrollbars;

//	private JScrollBar jScrollBar7;
//	private JLabel jLabel7;
//	private JScrollBar jScrollBar6;
//	private JLabel jLabel6;
//	private JScrollBar jScrollBar5;
//	private JScrollBar jScrollBar4;
//	private JScrollBar jScrollBar3;
//	private JScrollBar jScrollBar2;
	private JFreeChart signatureG;
	private byte[] signature = null;

	public void setSignature(byte[] signature) {
		this.signature = signature;
		plot();
	}

	private int[] banda;
	private JButton btOk;
	private JButton btCancel;

	/** A este le vamos a indicar que el usuario quiere generar la imagen respecto de la firma digital*/
	private PDI pdi;

	public ThresholdSignature(PDI frame, int cantBands) {
		super(frame);
		this.pdi = frame;
		banda = new int[cantBands];
		for (int i = 0; i < banda.length; i++) {
			banda[i] = INITIAL_DEVIATION;//arranca con un poco de margen
		}
		scrollbars = new JScrollBar[cantBands];
		valueLabels = new JLabel[cantBands];
		bandLabels = new JLabel[cantBands];
		initGUI(cantBands);
		this.setVisible(true);

	}

	private void initGUI(int cantBands) {
		try {
			{
				getContentPane().setLayout(null);
				this.setPreferredSize(new java.awt.Dimension(359, 457));
				{
					for (int i = 0; i < cantBands; ++i) {
						scrollbars[i] = new JScrollBar();
						getContentPane().add(scrollbars[i]);
						scrollbars[i].setBounds(50 * i + 20, 42, 17, 71);
						scrollbars[i].setValue(INITIAL_DEVIATION);
						
						final Integer index = new Integer(i);
						scrollbars[i]
								.addAdjustmentListener(new AdjustmentListener() {
									public void adjustmentValueChanged(
											AdjustmentEvent evt) {
										evt.setSource(new Integer(index));
										scrollBarAdjustmentValueChanged(evt);
									}

								});

					}
				}
				// {
				// jScrollBar1 = new JScrollBar();
				// getContentPane().add(jScrollBar1);
				// jScrollBar1.setBounds(20, 42, 17, 71);
				// jScrollBar1.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar1AdjustmentValueChanged(evt);
				// }
				// });
				// }
				{
					for (int i = 0; i < cantBands; ++i) {
						bandLabels[i] = new JLabel();
						getContentPane().add(bandLabels[i]);
						bandLabels[i].setText("Band " + i);
						bandLabels[i].setBounds(50 * i + 12, 23, 33, 14);

					}
				}

				// {
				// jLabel1 = new JLabel();
				// getContentPane().add(jLabel1);
				// jLabel1.setText("Band 1");
				// jLabel1.setBounds(12, 23, 33, 14);
				// }
				{
					signatureG = ChartFactory.createXYLineChart("Signature ",
							"Bands", "Valory", null, PlotOrientation.VERTICAL,
							true, true, false);
					chartpanel = new ChartPanel(signatureG);
					getContentPane().add(chartpanel);
					chartpanel.setBorder(BorderFactory.createTitledBorder(""));
					chartpanel.setBounds(20, 157, 45 * cantBands + 20, 210);
				}
				// {
				// jLabel2 = new JLabel();
				// getContentPane().add(jLabel2);
				// jLabel2.setText("Band 2");
				// jLabel2.setBounds(62, 23, 33, 14);
				// }
				// {
				// jScrollBar2 = new JScrollBar();
				// getContentPane().add(jScrollBar2);
				// jScrollBar2.setBounds(70, 42, 17, 71);
				// jScrollBar2.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar2AdjustmentValueChanged(evt);
				// }
				// });
				// }
				// {
				// jLabel3 = new JLabel();
				// getContentPane().add(jLabel3);
				// jLabel3.setText("Band 3");
				// jLabel3.setBounds(112, 23, 33, 14);
				// }
				// {
				// jScrollBar3 = new JScrollBar();
				// getContentPane().add(jScrollBar3);
				// jScrollBar3.setBounds(120, 42, 17, 71);
				// jScrollBar3.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar3AdjustmentValueChanged(evt);
				// }
				// });
				// }
				// {
				// jLabel4 = new JLabel();
				// getContentPane().add(jLabel4);
				// jLabel4.setText("Band 4");
				// jLabel4.setBounds(162, 23, 33, 14);
				// }
				// {
				// jScrollBar4 = new JScrollBar();
				// getContentPane().add(jScrollBar4);
				// jScrollBar4.setBounds(170, 42, 17, 71);
				// jScrollBar4.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar4AdjustmentValueChanged(evt);
				// }
				// });
				// }
				// {
				// jLabel5 = new JLabel();
				// getContentPane().add(jLabel5);
				// jLabel5.setText("Band 5");
				// jLabel5.setBounds(212, 23, 33, 14);
				// }
				// {
				// jScrollBar5 = new JScrollBar();
				// getContentPane().add(jScrollBar5);
				// jScrollBar5.setBounds(220, 42, 17, 71);
				// jScrollBar5.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar5AdjustmentValueChanged(evt);
				// }
				// });
				// }
				// {
				// jLabel6 = new JLabel();
				// getContentPane().add(jLabel6);
				// jLabel6.setText("Band 6");
				// jLabel6.setBounds(262, 23, 33, 14);
				// }
				// {
				// jScrollBar6 = new JScrollBar();
				// getContentPane().add(jScrollBar6);
				// jScrollBar6.setBounds(270, 42, 17, 71);
				// jScrollBar6.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar6AdjustmentValueChanged(evt);
				// }
				// });
				// }
				// {
				// jLabel7 = new JLabel();
				// getContentPane().add(jLabel7);
				// jLabel7.setText("Band 7");
				// jLabel7.setBounds(312, 23, 33, 14);
				// }
				// {
				// jScrollBar7 = new JScrollBar();
				// getContentPane().add(jScrollBar7);
				// jScrollBar7.setBounds(320, 42, 17, 71);
				// jScrollBar7.addAdjustmentListener(new AdjustmentListener() {
				// public void adjustmentValueChanged(AdjustmentEvent evt) {
				// jScrollBar7AdjustmentValueChanged(evt);
				// }
				// });
				// }
				{
					for (int i = 0; i < cantBands; ++i) {
						valueLabels[i] = new JLabel();
						getContentPane().add(valueLabels[i]);
						valueLabels[i].setText(String.valueOf(INITIAL_DEVIATION));
						valueLabels[i].setBounds(50 * i + 20, 119, 33, 19);

					}
				}
				// {
				// jLabel8 = new JLabel();
				// getContentPane().add(jLabel8);
				// jLabel8.setBounds(20, 119, 33, 19);
				// jLabel8.setText("0");
				// }
				// {
				// jLabel9 = new JLabel();
				// getContentPane().add(jLabel9);
				// jLabel9.setText("0");
				// jLabel9.setBounds(70, 119, 33, 19);
				// }
				// {
				// jLabel10 = new JLabel();
				// getContentPane().add(jLabel10);
				// jLabel10.setText("0");
				// jLabel10.setBounds(120, 119, 33, 19);
				// }
				// {
				// jLabel11 = new JLabel();
				// getContentPane().add(jLabel11);
				// jLabel11.setText("0");
				// jLabel11.setBounds(170, 119, 33, 19);
				// }
				// {
				// jLabel12 = new JLabel();
				// getContentPane().add(jLabel12);
				// jLabel12.setText("0");
				// jLabel12.setBounds(220, 119, 33, 19);
				// }
				// {
				// jLabel13 = new JLabel();
				// getContentPane().add(jLabel13);
				// jLabel13.setText("0");
				// jLabel13.setBounds(270, 119, 33, 19);
				// }
				// {
				// jLabel14 = new JLabel();
				// getContentPane().add(jLabel14);
				// jLabel14.setText("0");
				// jLabel14.setBounds(320, 119, 33, 19);
				// }
				{
					btCancel = new JButton();
					getContentPane().add(btCancel);
					btCancel.setText("Cancel");
					btCancel.setBounds(51 * cantBands - 75, 379, 75, 25);
					btCancel.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btCancelMouseClicked(evt);
						}
					});
				}
				{
					btOk = new JButton();
					getContentPane().add(btOk);
					btOk.setText("Generate");
					btOk.setBounds(51 * cantBands - 155, 379, 75, 25);
					btOk.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btOkMouseClicked(evt);
						}
					});
				}
			}
			this.setSize(51 * cantBands + 40, 457);
			this.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private void jScrollBar1AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel8.setText(Integer.toString(evt.getValue()));
//		banda[0] = evt.getValue();
//		plot();
//
//	}
//
//	private void jScrollBar2AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel9.setText(Integer.toString(evt.getValue()));
//		banda[1] = evt.getValue();
//		plot();
//	}
//
//	private void jScrollBar3AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel10.setText(Integer.toString(evt.getValue()));
//		banda[2] = evt.getValue();
//		plot();
//	}
//
//	private void jScrollBar4AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel11.setText(Integer.toString(evt.getValue()));
//		banda[3] = evt.getValue();
//		plot();
//	}
//
//	private void jScrollBar5AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel12.setText(Integer.toString(evt.getValue()));
//		banda[4] = evt.getValue();
//		plot();
//	}
//
//	private void jScrollBar6AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel13.setText(Integer.toString(evt.getValue()));
//		banda[5] = evt.getValue();
//		plot();
//	}
//
//	private void jScrollBar7AdjustmentValueChanged(AdjustmentEvent evt) {
//		jLabel14.setText(Integer.toString(evt.getValue()));
//		banda[6] = evt.getValue();
//		plot();
//	}

	private void plot() {
		if (signature != null) {
			XYSeries signature1 = new XYSeries("Original");
			XYSeries signature2 = new XYSeries("Max");
			XYSeries signature3 = new XYSeries("Min");
			double aux = 0;
			for (int i = 0; i < signature.length; i++) {
				signature1.add(i + 1, signature[i]);
			}
			for (int i = 0; i < signature.length; i++) {
				aux = signature[i] + banda[i];
				if (aux > 255)
					aux = 255;
				signature2.add(i + 1, aux);
			}
			for (int i = 0; i < signature.length; i++) {
				aux = signature[i] - banda[i];
				if (aux < 0)
					aux = 0;
				signature3.add(i + 1, aux);
			}

			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(signature1);
			dataset.addSeries(signature2);
			dataset.addSeries(signature3);
			XYPlot plot = (XYPlot) signatureG.getPlot();
			plot.setDataset(dataset);
		}
	}

	private void scrollBarAdjustmentValueChanged(AdjustmentEvent evt) {
		Integer index = (Integer) evt.getSource();
		valueLabels[index].setText(Integer.toString(evt.getValue()));
		banda[index] = evt.getValue();
		plot();

	}

	/**
	 * Devuelve un vector que indica el tope de valores que puede tener una
	 * firma para ser considerada como "similar".
	 */
	public byte[] getTopSignature() {
		byte[] top = new byte[signature.length];

		for (int i = 0; i < top.length; ++i)
			top[i] = (byte) Math.min(Byte.MAX_VALUE, signature[i] + banda[i]);
		
		return top;
	}

	/**
	 * Devuelve un vector que indica el mínimo de valores que puede tener una
	 * firma para ser considerada como "similar".
	 */
	public byte[] getBottomSignature() {
		byte[] bottom = new byte[signature.length];

		for (int i = 0; i < bottom.length; ++i)
			bottom[i] = (byte) Math.max(0, signature[i] - banda[i]);

		return bottom;
	}

	private void btOkMouseClicked(MouseEvent evt) {
		this.dispose();
		pdi.generateImageByThresholdSignature(getBottomSignature(), getTopSignature());
	}
	
	private void btCancelMouseClicked(MouseEvent evt) {
		this.dispose();
	}
}
