package edu.pdi2.visual;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import edu.pdi2.math.indexes.Rayleigh.L5Rayleigh;
import edu.pdi2.math.indexes.Rayleigh.Rayleigh;

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
public class RayleighData extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JTextField jZSolar;
	private JTextField jJulianDay;
	private JLabel jLabel20;
	private JButton jButton2;
	private JButton jButton1;
	private JTextField jaSensor;
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JLabel jLabel19;
	private JLabel jLabel18;
	private JTextField jZSensor;
	private JLabel jLabel17;
	private JLabel jLabel16;
	private JTextField jaSolar;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel jLabel15;
	private JTextField jtaur5;
	private JTextField jtaur4;
	private JTextField jtaur3;
	private JTextField jtaur2;
	private JTextField jtaur1;
	private JTextField jtaug5;
	private JTextField jtaug4;
	private JTextField jtaug3;
	private JTextField jtaug2;
	private JTextField jtaug1;
	private JTextField jsolst5;
	private JTextField jsolst4;
	private JTextField jsolst3;
	private JTextField jsolst2;
	private JTextField jsolst1;
	private Rayleigh r;
	

	
	public RayleighData(JFrame frame, Rayleigh r) {
		super(frame);
		initGUI();
		this.r=r;
		loadData();
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("Rayleigh Data Editor");
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBounds(12, 17, 483, 176);
					jPanel1.setEnabled(false);
					jPanel1.setBorder(BorderFactory.createTitledBorder("Atmospheric Optical Thickness"));
					jPanel1.setLayout(null);
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("Tau g 1");
						jLabel1.setBounds(5, 27, 65, 14);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Tau g 2");
						jLabel2.setBounds(5, 57, 65, 14);
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("Tau g 3");
						jLabel3.setBounds(5, 87, 65, 14);
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Tau g 4");
						jLabel4.setBounds(5, 117, 65, 14);
					}
					{
						jLabel5 = new JLabel();
						jPanel1.add(jLabel5);
						jLabel5.setText("Tau g 5");
						jLabel5.setBounds(5, 147, 65, 14);
					}
					{
						jtaug1 = new JTextField();
						jPanel1.add(jtaug1);
						jtaug1.setBounds(61, 24, 151, 21);
					}
					{
						jtaug2 = new JTextField();
						jPanel1.add(jtaug2);
						jtaug2.setBounds(61, 54, 151, 21);
					}
					{
						jtaug3 = new JTextField();
						jPanel1.add(jtaug3);
						jtaug3.setBounds(61, 84, 151, 21);
					}
					{
						jtaug4 = new JTextField();
						jPanel1.add(jtaug4);
						jtaug4.setBounds(61, 114, 151, 21);
					}
					{
						jtaug5 = new JTextField();
						jPanel1.add(jtaug5);
						jtaug5.setBounds(61, 144, 151, 21);
					}
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Tau r 1");
						jLabel6.setBounds(259, 27, 65, 14);
					}
					{
						jtaur1 = new JTextField();
						jPanel1.add(jtaur1);
						jtaur1.setBounds(315, 24, 151, 21);
					}
					{
						jtaur2 = new JTextField();
						jPanel1.add(jtaur2);
						jtaur2.setBounds(315, 54, 151, 21);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("Tau r 2");
						jLabel7.setBounds(259, 57, 65, 14);
					}
					{
						jLabel8 = new JLabel();
						jPanel1.add(jLabel8);
						jLabel8.setText("Tau r 3");
						jLabel8.setBounds(259, 87, 65, 14);
					}
					{
						jtaur3 = new JTextField();
						jPanel1.add(jtaur3);
						jtaur3.setBounds(315, 84, 151, 21);
					}
					{
						jtaur4 = new JTextField();
						jPanel1.add(jtaur4);
						jtaur4.setBounds(315, 114, 151, 21);
					}
					{
						jLabel9 = new JLabel();
						jPanel1.add(jLabel9);
						jLabel9.setText("Tau r 4");
						jLabel9.setBounds(259, 117, 65, 14);
					}
					{
						jLabel10 = new JLabel();
						jPanel1.add(jLabel10);
						jLabel10.setText("Tau r 5");
						jLabel10.setBounds(259, 147, 65, 14);
					}
					{
						jtaur5 = new JTextField();
						jPanel1.add(jtaur5);
						jtaur5.setBounds(315, 144, 151, 21);
					}
				}
					jPanel2 = new JPanel();
					getContentPane().add(jPanel2);
					jPanel2.setBounds(12, 199, 235, 204);
					jPanel2.setLayout(null);
					jPanel2.setBorder(BorderFactory.createTitledBorder("Solar Irradiance"));
				
				{
					jLabel11 = new JLabel();
					jPanel2.add(jLabel11);
					jLabel11.setText("Solct 1");
					jLabel11.setBounds(5, 41, 65, 14);
				}
				{
					jLabel12 = new JLabel();
					jPanel2.add(jLabel12);
					jLabel12.setText("Solct 2");
					jLabel12.setBounds(5, 71, 65, 14);
				}
				{
					jLabel13 = new JLabel();
					jPanel2.add(jLabel13);
					jLabel13.setText("Solct 3");
					jLabel13.setBounds(5, 101, 65, 14);
				}
				{
					jLabel14 = new JLabel();
					jPanel2.add(jLabel14);
					jLabel14.setText("Solct 4");
					jLabel14.setBounds(5, 131, 65, 14);
				}
				{
					jLabel15 = new JLabel();
					jPanel2.add(jLabel15);
					jLabel15.setText("Solct 5");
					jLabel15.setBounds(5, 161, 65, 14);
				}
				{
					jsolst1 = new JTextField();
					jPanel2.add(jsolst1);
					jsolst1.setBounds(61, 38, 151, 21);
				}
				{
					jsolst2 = new JTextField();
					jPanel2.add(jsolst2);
					jsolst2.setBounds(61, 68, 151, 21);
				}
				{
					jsolst3 = new JTextField();
					jPanel2.add(jsolst3);
					jsolst3.setBounds(61, 98, 151, 21);
				}
				{
					jsolst4 = new JTextField();
					jPanel2.add(jsolst4);
					jsolst4.setBounds(61, 128, 151, 21);
				}
				{
					jsolst5 = new JTextField();
					jPanel2.add(jsolst5);
					jsolst5.setBounds(61, 158, 151, 21);
				}
				}
			{
				jPanel3 = new JPanel();
				getContentPane().add(jPanel3);
				jPanel3.setBounds(270, 198, 225, 44);
				jPanel3.setBorder(BorderFactory.createTitledBorder("Julian Day"));
				jPanel3.setLayout(null);
				{
					jLabel19 = new JLabel();
					jPanel3.add(jLabel19);
					jLabel19.setText("Day");
					jLabel19.setBounds(17, 17, 38, 14);
				}
				{
					jJulianDay = new JTextField();
					jPanel3.add(jJulianDay);
					jJulianDay.setBounds(61, 14, 151, 21);
				}
			}
			{
				jPanel4 = new JPanel();
				getContentPane().add(jPanel4);
				jPanel4.setBounds(270, 248, 225, 75);
				jPanel4.setLayout(null);
				jPanel4.setBorder(BorderFactory.createTitledBorder("Zenith Angle"));
				{
					jLabel18 = new JLabel();
					jPanel4.add(jLabel18);
					jLabel18.setText("Solar");
					jLabel18.setBounds(10, 19, 65, 14);
				}
				{
					jZSolar = new JTextField();
					jPanel4.add(jZSolar);
					jZSolar.setBounds(63, 16, 151, 21);
				}
				{
					jZSensor = new JTextField();
					jPanel4.add(jZSensor);
					jZSensor.setBounds(64, 46, 151, 21);
				}
				{
					jLabel17 = new JLabel();
					jPanel4.add(jLabel17);
					jLabel17.setText("Sensor");
					jLabel17.setBounds(8, 49, 65, 14);
				}
			}
			{
				jPanel5 = new JPanel();
				getContentPane().add(jPanel5);
				jPanel5.setBounds(270, 329, 225, 74);
				jPanel5.setLayout(null);
				jPanel5.setBorder(BorderFactory.createTitledBorder("Azimuth Angle"));
				{
					jLabel16 = new JLabel();
					jPanel5.add(jLabel16);
					jLabel16.setText("Solar");
					jLabel16.setBounds(8, 23, 65, 14);
				}
				{
					jaSolar = new JTextField();
					jPanel5.add(jaSolar);
					jaSolar.setBounds(69, 20, 151, 21);
				}
				{
					jaSensor = new JTextField();
					jPanel5.add(jaSensor);
					jaSensor.setBounds(69, 50, 151, 21);
				}
				{
					jLabel20 = new JLabel();
					jPanel5.add(jLabel20);
					jLabel20.setText("Sensor");
					jLabel20.setBounds(8, 53, 65, 14);
				}
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Ok");
				jButton1.setBounds(324, 415, 76, 23);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Cancel");
				jButton2.setBounds(419, 415, 76, 23);
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton2ActionPerformed(evt);
					}
				});
			}

			this.setSize(531, 476);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

private void jButton1ActionPerformed(ActionEvent evt) {
	r.setAzimuthSen(Double.parseDouble(jaSensor.getText()));
	r.setAzimuthSolar(Double.parseDouble(jaSolar.getText()));
	r.setCenitSen(Double.parseDouble(jZSensor.getText()));
	r.setCenitSolar(Double.parseDouble(jZSolar.getText()));
	r.setDiaJuliano(Integer.parseInt(jJulianDay.getText()));
	double[] solct={Double.parseDouble(jsolst1.getText()),Double.parseDouble(jsolst2.getText()),Double.parseDouble(jsolst3.getText()),Double.parseDouble(jsolst4.getText()),Double.parseDouble(jsolst5.getText())};
	r.setSolct(solct);
	double[] taug = {Double.parseDouble(jtaug1.getText()),Double.parseDouble(jtaug2.getText()),Double.parseDouble(jtaug3.getText()),Double.parseDouble(jtaug4.getText()),Double.parseDouble(jtaug5.getText())};
	r.setTaug(taug);
	double[] taur = {Double.parseDouble(jtaur1.getText()),Double.parseDouble(jtaur2.getText()),Double.parseDouble(jtaur3.getText()),Double.parseDouble(jtaur4.getText()),Double.parseDouble(jtaur5.getText())};
	r.setTaur(taur);
}

private void jButton2ActionPerformed(ActionEvent evt) {
	this.dispose();
}
private void loadData(){
	jaSensor.setText(String.valueOf(r.getAzimuthSen()));
	jaSolar.setText(String.valueOf(r.getAzimuthSolar()));
	jZSensor.setText(String.valueOf(r.getCenitSen()));
	jZSolar.setText(String.valueOf(r.getCenitSolar()));
	jJulianDay.setText(String.valueOf(r.getDiaJuliano()));
	double[] solct=r.getSolct();
	double[] taug =r.getTaug();
	double[] taur =r.getTaur();
	jsolst1.setText(String.valueOf(solct[0]));
	jsolst2.setText(String.valueOf(solct[1]));
	jsolst3.setText(String.valueOf(solct[2]));
	jsolst4.setText(String.valueOf(solct[3]));
	jsolst5.setText(String.valueOf(solct[4]));
	jtaug1.setText(String.valueOf(taug[0]));
	jtaug2.setText(String.valueOf(taug[1]));
	jtaug3.setText(String.valueOf(taug[2]));
	jtaug4.setText(String.valueOf(taug[3]));
	jtaug5.setText(String.valueOf(taug[4]));
	jtaur1.setText(String.valueOf(taur[0]));
	jtaur2.setText(String.valueOf(taur[1]));
	jtaur3.setText(String.valueOf(taur[2]));
	jtaur4.setText(String.valueOf(taur[3]));
	jtaur5.setText(String.valueOf(taur[4]));
}
}

