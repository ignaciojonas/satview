package edu.pdi2.visual;

import java.awt.AWTEvent;
import java.awt.Dimension;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.pdi2.decoders.Decoder;
import edu.pdi2.decoders.DecoderFactory;
import edu.pdi2.forms.Point;
import edu.pdi2.forms.Polygon;
import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.math.signatures.comparators.EqualSignature;
import edu.pdi2.math.signatures.comparators.SimilarSignatures;
import edu.pdi2.math.transforms.ElasticTransform;
import edu.pdi2.math.transforms.RectangleTransform;

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
public class PDI extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DisplayThumbnail dt;
	private JMenuItem jMenuItem2;
	private JLabel jLabel1;
	private JPanel jPanel1;

	private JPanel latLon;
	private JButton jButton1;
	private ChartPanel chartpanel;
	private JLabel jLong;
	private JLabel jLat;
	private JCheckBoxMenuItem jCheckBoxMenuItem3;
	private JCheckBoxMenuItem jCheckBoxMenuItem2;
	private JMenu jMenu4;
	private JCheckBoxMenuItem jCheckBoxMenuItem1;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	private JMenu jMenu3;
	private JPanel thumPanel;
	private JFileChooser jFileChooser2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JPanel image;
	private static int dWidth = 590;
	private static int dHeight = 490;
	private Decoder decoder;
	private ElasticTransform et;
	private File[] files;
	private DisplayJAIWithAnnotations dj;
	private SatelliteImage si = null;
	private Vector mesh;
	private BandsManager bandsManager;
	private CreateMesh cm;
	private int x0, x1, y0, y1;
	private JFreeChart signatureG;
	private byte[] signature = null;
	private List<SatelliteImage> corrected_reflectance = null;
	private SatelliteImage signature_image = null;
	private List<SatelliteImage> corrected_radiance = null;
	private List<String> selectedBands;
	private DisplayJAIWithAnnotations displayJAIWithAnnotations1;
	private DisplayJAIWithAnnotations displayJAIWithAnnotations2;
	private DisplayJAIWithAnnotations displayJAIWithAnnotations3;
	private JLabel jLabel2;
	private JLabel jLabel3;

	public int getX0() {
		return x0;
	}

	public int getX1() {
		return x1;
	}

	public int getY0() {
		return y0;
	}

	public int getY1() {
		return y1;
	}

	public BandsManager getBandsManager() {
		return bandsManager;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	private PlanarImage getThumnail(PlanarImage pi){
		ParameterBlock pb;
		pb = new ParameterBlock();
		pb.addSource(pi);
		pb.add(0.15F);
		pb.add(0.15F);
		pb.add(0.0F);
		pb.add(0.0F);
		pb.add(new InterpolationNearest());
		return JAI.create("scale", pb, null); //$NON-NLS-1$
	}
	public void setSi(SatelliteImage si) {
		this.si = si;
		dt.set(si, dWidth, dHeight);
		dj.set(dt.getImage());
		dj.setRectangle(getCrop());
		String band1=selectedBands.get(0);
		band1=band1.substring(band1.length()-5, band1.length()-4);
		String band2=selectedBands.get(1);
		band2=band2.substring(band2.length()-5, band2.length()-4);
		String band3=selectedBands.get(2);
		band3=band3.substring(band3.length()-5, band3.length()-4);
		displayJAIWithAnnotations1.set(this.getThumnail(si.getOneBand(Integer.parseInt(band1))));
		jLabel1.setText("Band "+band1+":"); //$NON-NLS-1$ //$NON-NLS-2$
		displayJAIWithAnnotations2.set(this.getThumnail(si.getOneBand(Integer.parseInt(band2))));
		jLabel2.setText("Band "+band2+":"); //$NON-NLS-1$ //$NON-NLS-2$
		displayJAIWithAnnotations3.set(this.getThumnail(si.getOneBand(Integer.parseInt(band3))));
		jLabel3.setText("Band "+band3+":"); //$NON-NLS-1$ //$NON-NLS-2$

		repaint();
	}

	public void setMesh(Vector mesh) {
		this.mesh = mesh;
	}

	public Vector getMesh() {
		return mesh;
	}

	public void addForm(Polygon p) {
		mesh.add(p);
		dj.addAnnotation(p);
		// System.out.println("X0:"+x0+" - Y0= "+y0);
		// System.out.println("Agergue una anotacion X:"+p.getPoints().get(0).x+
		// " - "+p.getPoints().get(0).y);
		repaint();
	}

	public PDI() {
		// super();
		signatureG = ChartFactory.createXYLineChart("Signature ", "Bands", //$NON-NLS-1$ //$NON-NLS-2$
				"Valory", null, PlotOrientation.VERTICAL, true, true, false); //$NON-NLS-1$
		initGUI();
		files = new File[7];
		mesh = new Vector();
		selectedBands = new ArrayList<String>();
		decoder = DecoderFactory
//				.getDecoder("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\header.dat");
				.getDecoder(AppConstants.getString("header")); //$NON-NLS-1$
		si = null;
	}

	public void setSelectedBands(List<String> sb) {
		this.selectedBands = sb;
		corrected_reflectance = null;
		signature_image = null;
		corrected_radiance = null;
	}

	public List<String> getSelectedBands() {
		return selectedBands;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);

			this.setTitle("Procesamiento Digital de Imagenes"); //$NON-NLS-1$
			getContentPane().setBackground(new java.awt.Color(212, 208, 200));
			this.setResizable(false);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File"); //$NON-NLS-1$
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Open Image"); //$NON-NLS-1$
						jMenuItem1.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								jMenuItem1MouseReleased(evt);
							}

						});
					}
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Options"); //$NON-NLS-1$
					jMenu3.setEnabled(false);
					{
						jMenuItem3 = new JMenuItem();
						jMenu3.add(getJMenuItem2());
						jMenu3.add(jMenuItem3);
						jMenuItem3.setText("False Color Image"); //$NON-NLS-1$
						jMenuItem3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem3ActionPerformed(evt);
							}
						});
					}
					{
						jMenuItem4 = new JMenuItem();
						jMenu3.add(jMenuItem4);
						jMenuItem4.setText("Mesh"); //$NON-NLS-1$
						jMenuItem4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem4ActionPerformed(evt);
							}
						});
					}
					{
						jMenu4 = new JMenu();
						jMenu3.add(jMenu4);
						jMenu4.setText("Image"); //$NON-NLS-1$
						{
							jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
							jMenu4.add(jCheckBoxMenuItem1);
							jCheckBoxMenuItem1.setText("Corrected Reflectance"); //$NON-NLS-1$
							jCheckBoxMenuItem1.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 1")); //$NON-NLS-1$
							jCheckBoxMenuItem1
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jCheckBoxMenuItem1ActionPerformed(evt);
										}
									});
						}
						{
							jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
							jMenu4.add(jCheckBoxMenuItem2);
							jCheckBoxMenuItem2.setText("RAW"); //$NON-NLS-1$
							jCheckBoxMenuItem2.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 2")); //$NON-NLS-1$
							jCheckBoxMenuItem2
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jCheckBoxMenuItem2ActionPerformed(evt);
										}
									});
						}
						{
							jCheckBoxMenuItem3 = new JCheckBoxMenuItem();
							jMenu4.add(jCheckBoxMenuItem3);
							jCheckBoxMenuItem3.setText("Corrected Radiance"); //$NON-NLS-1$
							jCheckBoxMenuItem3.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 3")); //$NON-NLS-1$
							jCheckBoxMenuItem3
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jCheckBoxMenuItem3ActionPerformed(evt);
										}
									});
						}
					}
				}
			}
			// First we create the instance of DisplayThumbnail with a 0.1
			// scale.
			dt = new DisplayThumbnail(0.1f);
			dt.setPreferredSize(new Dimension(150, 100));
			dt.setMinimumSize(new Dimension(150, 100));
			dt.setMaximumSize(new Dimension(150, 100));
			// dt.setBorder(BorderFactory.createTitledBorder(""));
			// We must register mouse motion listeners to it !
			dt.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent evt) {
					thumPanelMouseDragged(evt);
				}
			});
			dt.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					thumPanelMousePressed(evt);
				}
			});

			// Now we create the instance of DisplayJAI to show the region
			// corresponding to the viewport.

			// Set it size.
			{
				image = new JPanel();

				getContentPane().add(image);
				image.setBounds(1, 10, 590, 525);
				{
					dj = new DisplayJAIWithAnnotations();
					image.add(dj);
					dj.setBounds(0,0,dWidth, 520);
					dj.setPreferredSize(new Dimension(dWidth, 520));
					dj.setMinimumSize(new Dimension(dWidth, 520));
					dj.setMaximumSize(new Dimension(dWidth, 520));
					dj.setBorder(BorderFactory.createTitledBorder("")); //$NON-NLS-1$
					dj.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent evt) {
							djMousePressed(evt);
						}
					});
					dj.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseMoved(MouseEvent evt) {
							djMouseMoved(evt);
						}

						public void mouseDragged(MouseEvent evt) {
							djMouseDragged(evt);
						}
					});
				}
			}
			{
				thumPanel = new JPanel();
				thumPanel.add(dt);
				getContentPane().add(thumPanel);
				getContentPane().add(getChartpanel());
				getContentPane().add(getJButton1());
				getContentPane().add(getLatLon());
				getContentPane().add(getDisplayJAIWithAnnotations1());
				getContentPane().add(getDisplayJAIWithAnnotations2());
				getContentPane().add(getDisplayJAIWithAnnotations3());
				getContentPane().add(getJLabel1x());
				getContentPane().add(getJLabel2x());
				getContentPane().add(getJLabel3x());
				thumPanel.setBounds(636, 18, 166, 123);
			}

			pack();
			this.setSize(870, 768);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JFileChooser getJFileChooser2() {
		if (jFileChooser2 == null) {
			jFileChooser2 = new JFileChooser();
		}
		return jFileChooser2;
	}

	private void thumPanelMousePressed(MouseEvent evt) {
		dj.set(dt.getImage());
		Rectangle r = this.getCrop();
		if (dt.inViewport() == 0)
			dj.setRectangle(r);
		else {
			if (dt.inViewport() == 1) {
				r.x = x0 + x1 - dWidth;
				dj.setRectangle(r);
			} else {
				r.y = y0 + y1 - dHeight;
				dj.setRectangle(r);
			}
		}
	}

	private void thumPanelMouseDragged(MouseEvent evt) {
		dj.set(dt.getImage());
		Rectangle r = this.getCrop();
		if (dt.inViewport() == 0)
			dj.setRectangle(r);
		else {
			if (dt.inViewport() == 1) {
				r.x = x0 + x1 - dWidth;
				dj.setRectangle(r);
			} else {
				r.y = y0 + y1 - dHeight;
				dj.setRectangle(r);
			}
		}
	}

	private void jMenuItem1MouseReleased(MouseEvent evt) {
		new OpenBands(this);
		jCheckBoxMenuItem1.setSelected(false);
		jCheckBoxMenuItem2.setSelected(false);
		jCheckBoxMenuItem3.setSelected(false);

	}

	private void djMouseDragged(MouseEvent evt) {
		if (si != null) {
			onMouseAction(evt,dt.getCroppedImageBounds(),true);
		}
	}

	private void djMousePressed(MouseEvent evt) {
		Rectangle crop =dt.getCroppedImageBounds();
		if (si != null) {
			if (cm != null) {
				cm.addPoint(evt.getX() + crop.x + x0, evt.getY() + crop.y + y0);
				return;
			}
			onMouseAction(evt, crop,true);
		}
	}

	private void onMouseAction(MouseEvent evt, Rectangle crop,boolean chart) {
		/*System.out.println("En la Imagen Completa:");
		System.out.println("El X es:" + (evt.getX() + crop.x + x0)
				+ " El Y es:" + (evt.getY() + crop.y + y0));*/
		if(chart){
			signature = bandsManager.getSignature(new Point(evt.getX() + crop.x
					+ x0, evt.getY() + crop.y + y0));
			XYSeries signature1 = new XYSeries("Signature"); //$NON-NLS-1$
			for (int i = 0; i < signature.length; i++) {
				signature1.add(i, signature[i]);
			}
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(signature1);
			XYPlot plot = (XYPlot) signatureG.getPlot();
			plot.setDataset(dataset);
			}
		int x = evt.getX() + crop.x + x0;
		int y = evt.getY() + crop.y + y0;
		edu.pdi2.forms.Point point = new Point(x, y);
		double lat=et.getXi_(evt.getX() + crop.x + x0, evt.getY()
				+ crop.y + y0);
		double lon=et.getYi_(evt.getX() + crop.x + x0, evt.getY()
				+ crop.y + y0);
		jLat.setText("Lat: "+ToDegrees(lat)); //$NON-NLS-1$
		jLong.setText("Long: "+ToDegrees(lon)); //$NON-NLS-1$

		for (int i = 0; i < mesh.size(); i++) {
			Polygon p = (Polygon) mesh.get(i);
			Vector gPoints = p.getGPoints();
			if (p.isIn(point)) {
				p.changeColor();
				System.err.println("Estoy dentro de un pfoygon!"); //$NON-NLS-1$
				ElasticTransform etP = p.getET();
				lat=etP.getYi_(x,y);
				lon=etP.getXi_(x,y);
				jLat.setText("Lat: "+ToDegrees(lat)); //$NON-NLS-1$
				jLong.setText("Long: "+ToDegrees(lon)); //$NON-NLS-1$
				break;
			}

		}
		
	}

	public void setNullCM() {
		cm = null;
	}

	private void jMenuItem3ActionPerformed(ActionEvent evt) {
		int[] bands = new int[bandsManager.getMaxBands()];
		for (int i = 1; i < bandsManager.getMaxBands() + 1; i++) {
			bands[i - 1] = i;
		}
		new BandDialog(this, bands);
	}

	private void jMenuItem4ActionPerformed(ActionEvent evt) {
		cm = new CreateMesh(this);
	}

	private void jCheckBoxMenuItem1ActionPerformed(ActionEvent evt) {
		jCheckBoxMenuItem2.setSelected(false);
		jCheckBoxMenuItem3.setSelected(false);
		// ---------------- Corrected Reflectance.
//		if (corrected_reflectance == null) {
			corrected_reflectance = si.getReflectanceCorrected(
					si.getBounds().x, si.getBounds().width, si.getBounds().y,
					si.getBounds().height);
//		}
		String band1=selectedBands.get(0);
		band1=band1.substring(band1.length()-5, band1.length()-4);
		String band2=selectedBands.get(1);
		band2=band2.substring(band2.length()-5, band2.length()-4);
		String band3=selectedBands.get(2);
		band3=band3.substring(band3.length()-5, band3.length()-4);
		new CorrectedDialog(this,x0,y0,corrected_reflectance,"Band "+band1,"Band "+band2,"Band "+band3); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		
	}

	private void jCheckBoxMenuItem2ActionPerformed(ActionEvent evt) {
		jCheckBoxMenuItem1.setSelected(false);
		jCheckBoxMenuItem3.setSelected(false);
		// ---------------- RAW.
		SatelliteImage s = bandsManager.getRawImage(selectedBands, x0, x0 + x1,
				y0, y0 + y1);
		this.setSi(s);

	}

	public List<String> getListFiles() {
		List<String> filesList = new ArrayList<String>();
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND1.dat");
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND2.dat");
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND3.dat");
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND4.dat");
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND5.dat");
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND6.dat");
//		filesList
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND7.dat");
		filesList.add(AppConstants.getString("band1")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band2")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band3")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band4")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band5")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band6")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band7")); //$NON-NLS-1$
		/*
		 * filesList.add(files[1].getAbsolutePath());
		 * System.out.println("filesList.add("+files[1].getAbsolutePath()+");");
		 * filesList.add(files[2].getAbsolutePath());
		 * System.out.println("filesList.add("+files[2].getAbsolutePath()+");");
		 * filesList.add(files[3].getAbsolutePath());
		 * System.out.println("filesList.add("+files[3].getAbsolutePath()+");");
		 * filesList.add(files[4].getAbsolutePath());
		 * System.out.println("filesList.add("+files[4].getAbsolutePath()+");");
		 * filesList.add(files[5].getAbsolutePath());
		 * System.out.println("filesList.add("+files[5].getAbsolutePath()+");");
		 * filesList.add(files[6].getAbsolutePath());
		 * System.out.println("filesList.add("+files[6].getAbsolutePath()+");");
		 * filesList.add(files[7].getAbsolutePath());
		 * System.out.println("filesList.add("+files[7].getAbsolutePath()+");");
		 */
		return filesList;
	}

	public void setFiles(File[] f) {
		files = f;
		// decodifico el header
		// decoder= DecoderFactory.getDecoder(files[0].getAbsolutePath());
		decoder = DecoderFactory
//				.getDecoder("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\header.dat");
				.getDecoder(AppConstants.getString("header")); //$NON-NLS-1$
		Rectangle r = new Rectangle(0, 0, decoder.getPPL(), decoder.getLPI());
		bandsManager = new BandsManager(getListFiles(), r, decoder.getPPL(),
				decoder.getLPI());
		selectedBands = new ArrayList<String>();
		// selectedBands.add(files[1].getAbsolutePath());
		// selectedBands.add(files[2].getAbsolutePath());
		// selectedBands.add(files[3].getAbsolutePath());
		selectedBands.add(AppConstants.getString("band1")); //$NON-NLS-1$
		selectedBands.add(AppConstants.getString("band2")); //$NON-NLS-1$
		selectedBands.add(AppConstants.getString("band3")); //$NON-NLS-1$
//		selectedBands
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND1.dat");
//		selectedBands
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND2.dat");
//		selectedBands
//				.add("C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND3.dat");
		et = new RectangleTransform(0, decoder.getPPL(), decoder.getPPL(), 0,
				0, 0, decoder.getLPI(), decoder.getLPI(), decoder.getUL_lon(),
				decoder.getUR_lon(), decoder.getLR_lon(), decoder.getLL_lon(),
				decoder.getUL_lat(), decoder.getUR_lat(), decoder.getLR_lat(),
				decoder.getLL_lat());
		jMenu3.setEnabled(true);
	}

	public Decoder getDecoder() {
		return decoder;
	}

	private void jCheckBoxMenuItem3ActionPerformed(ActionEvent evt) {
		jCheckBoxMenuItem2.setSelected(false);
		jCheckBoxMenuItem1.setSelected(false);
		// ---------------- Corrected Radiance.
//		if (corrected_radiance == null)
			corrected_radiance = (List<SatelliteImage>) si.getRadianceCorrected(si.getBounds().x,
							si.getBounds().width, si.getBounds().y, si
									.getBounds().height);
		String band1=selectedBands.get(0);
		band1=band1.substring(band1.length()-5, band1.length()-4);
		String band2=selectedBands.get(1);
		band2=band2.substring(band2.length()-5, band2.length()-4);
		String band3=selectedBands.get(2);
		band3=band3.substring(band3.length()-5, band3.length()-4);
		new CorrectedDialog(this,x0,y0,corrected_radiance,"Band "+band1,"Band "+band2,"Band "+band3); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//		List<SatelliteImage> s= new ArrayList<SatelliteImage>();
//		s.add(si);
//		s.add(si);
//		s.add(si);
//		
//		new CorrectedDialog(this,x0,y0,s,"Band 1","Band 2","Band 3");
	}
	

	private JLabel getJLabel1() {
		if (jLat == null) {
			jLat = new JLabel();
			jLat.setText("Lat:"); //$NON-NLS-1$
			jLat.setBounds(5, 5, 198, 14);
		}
		return jLat;
	}

	private JLabel getJLabel2() {
		if (jLong == null) {
			jLong = new JLabel();
			jLong.setText("Long:"); //$NON-NLS-1$
			jLong.setBounds(5, 25, 198, 14);
		}
		return jLong;
	}

	private ChartPanel getChartpanel() {
		if (chartpanel == null) {
			chartpanel = new ChartPanel(signatureG);
			chartpanel.setBounds(605, 154, 229, 155);
			chartpanel.setBorder(BorderFactory.createTitledBorder("")); //$NON-NLS-1$
		}
		return chartpanel;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Generate"); //$NON-NLS-1$
			jButton1.setBounds(749, 320, 85, 21);
			jButton1.setBorder(BorderFactory.createTitledBorder("")); //$NON-NLS-1$
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton1ActionPerformed(evt);
				}
			});
		}
		return jButton1;
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		
		if (signature != null) {
			//FIXME hacer que esto se haga por IGUALDAD o por SIMILITUD de firmas
			byte[] bottom = new byte[signature.length];
			byte[] top = new byte[signature.length];
			
			for (int i=0; i<signature.length; ++i){
				bottom [i] = (byte) (signature[i] - 30);
				
					
				top[i] = (byte) (signature[i] + 30);
				if (top[i] < 0)
					top[i] = 127;
				
			}
			bandsManager.setSignatureComparator(new SimilarSignatures(top, bottom));
			signature_image = bandsManager.getImageWithThisSignature(x0, x1 + x0, y0, y1 + y0);
			this.setSi(signature_image);

		}
	}

	public Rectangle getCrop() {
		Rectangle crop = dt.getCroppedImageBounds();
		return new Rectangle(x0 + crop.x, y0 + crop.y, crop.width, crop.height);
	}

	private JPanel getLatLon() {
		if (latLon == null) {
			latLon = new JPanel();
			latLon.setBounds(620, 386, 200, 46);
			latLon.setLayout(null);
			latLon.setBorder(BorderFactory.createTitledBorder("")); //$NON-NLS-1$
			latLon.add(getJLabel2());
			latLon.add(getJLabel1());

		}
		return latLon;
	}

	private void djMouseMoved(MouseEvent evt) {
		if (si != null)
			onMouseAction(evt,dt.getCroppedImageBounds(),false);
	}
	
	private String ToDegrees(double ang ){
		double angle=ang/10000;
		String angleS=Double.toString(angle);
		String grados=angleS.substring(0,angleS.indexOf('.'));
		double c=Double.parseDouble(angleS.substring(angleS.indexOf('.')));
		c= c*60;
		angleS=Double.toString(c);
		String minutos=angleS.substring(0,angleS.indexOf('.'));
		c=Double.parseDouble(angleS.substring(angleS.indexOf('.')));
		double segundos= c*60;
		return new String(grados+"º "+minutos+"\' "+segundos+"\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	private DisplayJAIWithAnnotations getDisplayJAIWithAnnotations1() {
		if(displayJAIWithAnnotations1 == null) {
			displayJAIWithAnnotations1 = new DisplayJAIWithAnnotations();
			displayJAIWithAnnotations1.setBounds(10, 560, 150, 150);
		}
		return displayJAIWithAnnotations1;
	}
	private DisplayJAIWithAnnotations getDisplayJAIWithAnnotations2() {
		if(displayJAIWithAnnotations2 == null) {
			displayJAIWithAnnotations2 = new DisplayJAIWithAnnotations();
			displayJAIWithAnnotations2.setBounds(210, 560, 150, 150);
		}
		return displayJAIWithAnnotations2;
	}
	private DisplayJAIWithAnnotations getDisplayJAIWithAnnotations3() {
		if(displayJAIWithAnnotations3 == null) {
			displayJAIWithAnnotations3 = new DisplayJAIWithAnnotations();
			displayJAIWithAnnotations3.setBounds(410, 560, 150, 150);
		}
		return displayJAIWithAnnotations3;
	}
	
	private JLabel getJLabel1x() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Band 1:"); //$NON-NLS-1$
			jLabel1.setBounds(10, 540, 37, 14);
		}
		return jLabel1;
	}
	private JLabel getJLabel2x() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Band 1:"); //$NON-NLS-1$
			jLabel2.setBounds(210, 540, 37, 14);
		}
		return jLabel2;
	}
	private JLabel getJLabel3x() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Band 1:"); //$NON-NLS-1$
			jLabel3.setBounds(410, 540, 37, 14);
		}
		return jLabel3;
	}
	
	private JMenuItem getJMenuItem2() {
		if(jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Edit Rayleigh Data"); //$NON-NLS-1$
			jMenuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuItem2ActionPerformed(evt);
				}
			});
		}
		return jMenuItem2;
	}
	
	private void jMenuItem2ActionPerformed(ActionEvent evt) {
		new RayleighData(this,si.getRayleigh());
	}

}

