package edu.pdi2.visual;
import edu.pdi2.forms.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.pdi2.forms.GeographicPoint;
import edu.pdi2.forms.Polygon;

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
public class SavePolygon extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel7;
	private JTextField jP1Lat;
	private JTextField jP2Lat;
	private JTextField jP3Lat;
	private JTextField jP4Lat;
	private JTextField jP2X;
	private JTextField jP3X;
	private JButton jButton1;
	private JLabel jLabel6;
	private JTextField jP1Lon;
	private JTextField jP2Lon;
	private JPanel jPanel1;
	private JButton jButton2;
	private JTextField jP3Lon;
	private JTextField jP4Lon;
	private JLabel jLabel9;
	private JLabel jLabel5;
	private JTextField jP4X;
	private JTextField jP1Y;
	private JTextField jP2Y;
	private JTextField jP3Y;
	private JTextField jP4Y;
	private JTextField jP1X;
	private int index=1;
	private boolean saved=false;
	private PDI jframe;
	private CreateMesh createMesh;
	public boolean isSaved() {
		return saved;
	}


	public SavePolygon(JFrame frame, CreateMesh cm) {
		super(frame);
		jframe=(PDI) frame;
		createMesh=cm;
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
				this.setTitle("Save Quadrilateral");
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});

				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("Save");
					jButton1.setBounds(159, 160, 85, 21);
					jButton1.setBorder(BorderFactory.createTitledBorder(""));
					jButton1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jButton1MouseClicked(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Cancel");
					jButton2.setBounds(269, 160, 85, 21);
					jButton2.setBorder(BorderFactory.createTitledBorder(""));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBounds(10, 12, 344, 142);
					jPanel1.setLayout(null);
					jPanel1.setBorder(BorderFactory.createTitledBorder(""));
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Longitude");
						jLabel6.setBounds(259, 8, 56, 14);
					}
					{
						jP1Lon = new JTextField();
						jPanel1.add(jP1Lon);
						jP1Lon.setBounds(250, 25, 78, 21);
					}
					{
						jP2Lon = new JTextField();
						jPanel1.add(jP2Lon);
						jP2Lon.setBounds(250, 52, 78, 21);
					}
					{
						jP3Lon = new JTextField();
						jPanel1.add(jP3Lon);
						jP3Lon.setBounds(250, 79, 78, 21);
					}
					{
						jP4Lon = new JTextField();
						jPanel1.add(jP4Lon);
						jP4Lon.setBounds(250, 106, 78, 21);
					}
					{
						jLabel9 = new JLabel();
						jPanel1.add(jLabel9);
						jLabel9.setText("Y");
						jLabel9.setBounds(116, 6, 12, 17);
					}
					{
						jLabel5 = new JLabel();
						jPanel1.add(jLabel5);
						jLabel5.setText("Latitude");
						jLabel5.setBounds(168, 8, 56, 14);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("X");
						jLabel7.setBounds(71, 7, 10, 14);
					}
					{
						jP1Lat = new JTextField();
						jPanel1.add(jP1Lat);
						jP1Lat.setBounds(155, 25, 78, 21);
					}
					{
						jP2Lat = new JTextField();
						jPanel1.add(jP2Lat);
						jP2Lat.setBounds(155, 52, 78, 21);
					}
					{
						jP3Lat = new JTextField();
						jPanel1.add(jP3Lat);
						jP3Lat.setBounds(155, 79, 78, 21);
					}
					{
						jP4Lat = new JTextField();
						jPanel1.add(jP4Lat);
						jP4Lat.setBounds(155, 106, 78, 21);
					}
					{
						jP2X = new JTextField();
						jPanel1.add(jP2X);
						jP2X.setBounds(59, 52, 34, 21);
					}
					{
						jP3X = new JTextField();
						jPanel1.add(jP3X);
						jP3X.setBounds(59, 79, 34, 21);
					}
					{
						jP4X = new JTextField();
						jPanel1.add(jP4X);
						jP4X.setBounds(59, 106, 34, 21);
					}
					{
						jP1Y = new JTextField();
						jPanel1.add(jP1Y);
						jP1Y.setBounds(103, 25, 34, 21);
					}
					{
						jP2Y = new JTextField();
						jPanel1.add(jP2Y);
						jP2Y.setBounds(103, 52, 34, 21);
					}
					{
						jP3Y = new JTextField();
						jPanel1.add(jP3Y);
						jP3Y.setBounds(103, 79, 34, 21);
					}
					{
						jP4Y = new JTextField();
						jPanel1.add(jP4Y);
						jP4Y.setBounds(103, 106, 34, 21);
					}
					{
						jP1X = new JTextField();
						jPanel1.add(jP1X);
						jP1X.setBounds(59, 25, 34, 21);
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Point 4:");
						jLabel4.setBounds(17, 109, 45, 14);
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("Point 3:");
						jLabel3.setBounds(17, 82, 37, 14);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Point 2:");
						jLabel2.setBounds(17, 55, 37, 14);
					}
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("Point 1:");
						jLabel1.setBounds(17, 28, 37, 14);
					}
				}
			}
			this.setSize(379, 219);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButton1MouseClicked(MouseEvent evt) {
		if(index==5){
			Rectangle r=jframe.getCrop();
			Point p1,p2,p3,p4;
			if((!jP1X.getText().equals(""))&&(!jP1Y.getText().equals(""))&&(!jP2X.getText().equals(""))&&(!jP2Y.getText().equals(""))&&(!jP3X.getText().equals(""))&&(!jP3Y.getText().equals(""))&&(!jP4X.getText().equals(""))&&(!jP4Y.getText().equals(""))){
				Polygon pol;
				p1=new Point(Integer.parseInt(jP1X.getText()),Integer.parseInt(jP1Y.getText()));
				p2=new Point(Integer.parseInt(jP2X.getText()),Integer.parseInt(jP2Y.getText()));
				p3=new Point(Integer.parseInt(jP3X.getText()),Integer.parseInt(jP3Y.getText()));
				p4=new Point(Integer.parseInt(jP4X.getText()),Integer.parseInt(jP4Y.getText()));
				if((!jP1Lat.getText().equals(""))&&(!jP1Lon.getText().equals(""))&&(!jP2Lat.getText().equals(""))&&(!jP2Lon.getText().equals(""))&&(!jP3Lat.getText().equals(""))&&(!jP3Lon.getText().equals(""))&&(!jP4Lat.getText().equals(""))&&(!jP4Lon.getText().equals(""))){
					GeographicPoint gP1,gP2,gP3,gP4;
					gP1=new GeographicPoint(p1,Double.parseDouble(jP1Lat.getText()),Double.parseDouble(jP1Lon.getText()));
					gP2=new GeographicPoint(p2,Double.parseDouble(jP2Lat.getText()),Double.parseDouble(jP2Lon.getText()));
					gP3=new GeographicPoint(p3,Double.parseDouble(jP3Lat.getText()),Double.parseDouble(jP3Lon.getText()));
					gP4=new GeographicPoint(p4,Double.parseDouble(jP4Lat.getText()),Double.parseDouble(jP4Lon.getText()));
					Vector<GeographicPoint> gPoints=new Vector();
					gPoints.add(gP1);
					gPoints.add(gP2);
					gPoints.add(gP3);
					gPoints.add(gP4);
					pol = new Polygon(gPoints);
					jframe.addForm(pol);
					saved=true;
					createMesh.setNullSP();
					this.dispose();
				}
				}
		}
		else{
			JOptionPane.showMessageDialog( this, 
				      "You select only "+(index-1), 
				      "Warning", 
				      JOptionPane.WARNING_MESSAGE );
		}
	}
	
	public void addPoint(int x, int y){
		if(index==1){
			jP1X.setText(Integer.toString(x));
			jP1Y.setText(Integer.toString(y));
		}
		else
			if(index==2){
				jP2X.setText(Integer.toString(x));
				jP2Y.setText(Integer.toString(y));
			}
			else
				if(index==3){
					jP3X.setText(Integer.toString(x));
					jP3Y.setText(Integer.toString(y));
				}
				else
					if(index==4){
						jP4X.setText(Integer.toString(x));
						jP4Y.setText(Integer.toString(y));
					}
					else{
						return;
					}
		
		index++;
		
	}
	
	private void thisWindowClosing(WindowEvent evt) {
		createMesh.setNullSP();
	}
	
	private void jButton2ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
