package edu.pdi2.visual;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.l2fprod.common.swing.JDirectoryChooser;

import edu.pdi2.constants.AppConstants;
import edu.pdi2.constants.SatelliteNamingUtils;
import edu.pdi2.decoders.Decoder;
import edu.pdi2.decoders.DecoderFactory;
import edu.pdi2.forms.Point;
import edu.pdi2.forms.Polygon;
import edu.pdi2.imaging.ImageFactory;
import edu.pdi2.imaging.RawDataUtils;
import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.math.signatures.comparators.SimilarSignatures;
import edu.pdi2.math.transforms.ElasticTransform;
import edu.pdi2.math.transforms.RectangleTransform;
import edu.pdi2.visual.extradialogs.BandsThumbnailsDialog;
import edu.pdi2.visual.extradialogs.PositionDialog;
import edu.pdi2.visual.extradialogs.SignatureDialog;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4943563588556465116L;

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
	private JMenuItem jMenuItemOpenL5;
	private JMenu jMenuOpenImage;
	private JMenuItem jMenuItem2;
//	private JLabel jLabel1;
	// private JPanel jPanel1;

	private JPanel latLon;
	private JLabel jLong;
	private JMenuItem JGenerarSignature;
	private JMenuItem jMenuItemExit;
	private JMenu jMenuExit;
	private JSeparator jSeparator1;
	private JCheckBoxMenuItem jCheckBoxMISignature;
	private JCheckBoxMenuItem jCheckBoxMIThumbs;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItemSacc;
	private JMenuItem jMenuItemLandsat7;
	private JLabel jLat;
	private JCheckBoxMenuItem jCheckBoxMenuItem3;
	private JCheckBoxMenuItem jCheckBoxMenuItem2;
	private JMenu jMenu4;
	private JCheckBoxMenuItem jCheckBoxMenuItem1;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	private JMenu jMenu3;
	private JPanel thumPanel;
//	private JFileChooser jFileChooser2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JPanel image;
	private static int dWidth = 590;
	private static int dHeight = 470;
	private Decoder decoder;
	private ElasticTransform et;
	private File[] files;
	private DisplayJAIWithAnnotations dj;
	private SatelliteImage si = null;
	private Vector<Polygon> mesh;
	private BandsManager bandsManager;
	private CreateMesh cm;
	private int upperLeftX, pixelsWidth, upperLeftY, pixelsHeight;
	private JFreeChart signatureG;
	private byte[] signature = null;
	private List<SatelliteImage> corrected_reflectance = null;
	private SatelliteImage signature_image = null;
	private List<SatelliteImage> corrected_radiance = null;

	private List<String> selectedBands;

	private BandsThumbnailsDialog tnDialog = new BandsThumbnailsDialog(this);
	private SignatureDialog signDialog;
	private PositionDialog posDialog=new PositionDialog(this);

	// FIXME
	// private DisplayJAIWithAnnotations band1TN;
	// private DisplayJAIWithAnnotations band2TN;
	// private DisplayJAIWithAnnotations band3TN;
//	private JMenuItem jMenuItemBandsThumbnails;
	private JMenu jMenuView;

//	private JLabel jLabel2;
//	private JLabel jLabel3;

	/** El directorio en el que se encuentra la imagen satelital actual */
	private String directory;

	public int getUpperLeftX() {
		return upperLeftX;
	}

	public int getPixelsWidth() {
		return pixelsWidth;
	}

	public int getUpperLeftY() {
		return upperLeftY;
	}

	public int getPixelsHeight() {
		return pixelsHeight;
	}

	public BandsManager getBandsManager() {
		return bandsManager;
	}

	public void setUpperLeftX(int x0) {
		this.upperLeftX = x0;
	}

	public void setPixelsWidth(int x1) {
		this.pixelsWidth = x1;
	}

	public void setUpperLeftY(int y0) {
		this.upperLeftY = y0;
	}

	public void setPixelsHeight(int y1) {
		this.pixelsHeight = y1;
	}

	// private PlanarImage getThumnail(PlanarImage pi) {
	// ParameterBlock pb;
	// pb = new ParameterBlock();
	// pb.addSource(pi);
	// pb.add(0.15F);
	// pb.add(0.15F);
	// pb.add(0.0F);
	// pb.add(0.0F);
	// pb.add(new InterpolationNearest());
	//		return JAI.create("scale", pb, null); //$NON-NLS-1$
	// }

	public void setSi(SatelliteImage si) {
		this.si = si;
		dt.set(si, dWidth, dHeight);
		dj.set(dt.getImage());
		dj.setRectangle(getCrop());

		if (tnDialog.isVisible())
			updateThumbnails();
		// FIXME
		// band1TN.set(this.getThumnail(si.getOneBand(0)));
		// jLabel1.setText("Band " + si.getBands()[0]);
		// band2TN.set(this.getThumnail(si.getOneBand(1)));
		// jLabel2.setText("Band " + si.getBands()[1]);
		// band3TN.set(this.getThumnail(si.getOneBand(2)));
		// jLabel3.setText("Band " + si.getBands()[2]);

		repaint();
	}

	public void setMesh(Vector<Polygon> mesh) {
		this.mesh = mesh;
	}

	public Vector<Polygon> getMesh() {
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
		mesh = new Vector<Polygon>();
		selectedBands = new ArrayList<String>();

		si = null;
		signDialog=new SignatureDialog(this,signatureG);
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
						// jMenu1.add(jMenuItem1);
						jMenu1.add(getJMenuOpenImage());
						jMenu1.add(getJSeparator1());
						jMenu1.add(getJMenuItemExit());

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
					jMenuBar1.add(getJMenuView());
					jMenu3.setText("Options"); //$NON-NLS-1$
					jMenu3.setEnabled(false);
					{
						jMenuItem3 = new JMenuItem();
						jMenu3.add(getJMenuItem2());
						jMenu3.add(jMenuItem3);
						jMenuItem3.setText("False Color Image"); //$NON-NLS-1$
						jMenuItem3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuFalseColorActionPerformed(evt);
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
						jMenu3.add(getJMenuItem5());
						jMenu3.add(getJGenerarSignature());
						jMenu4.setText("Image"); //$NON-NLS-1$
						{
							jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
							jMenu4.add(jCheckBoxMenuItem1);
							jCheckBoxMenuItem1.setText("Corrected Reflectance"); //$NON-NLS-1$
							jCheckBoxMenuItem1.setAccelerator(KeyStroke
									.getKeyStroke("ctrl pressed 1")); //$NON-NLS-1$
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
							jCheckBoxMenuItem2.setAccelerator(KeyStroke
									.getKeyStroke("ctrl pressed 2")); //$NON-NLS-1$
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
							jCheckBoxMenuItem3.setAccelerator(KeyStroke
									.getKeyStroke("ctrl pressed 3")); //$NON-NLS-1$
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
				image.setBounds(1, 10, 590, dHeight+10);
				{
					dj = new DisplayJAIWithAnnotations();
					image.add(dj);
					dj.setBounds(0, 0, dWidth, dHeight);
					dj.setPreferredSize(new Dimension(dWidth, dHeight));
					dj.setMinimumSize(new Dimension(dWidth, dHeight));
					dj.setMaximumSize(new Dimension(dWidth, dHeight));
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
				getContentPane().add(getLatLon());
				// FIXME
				// getContentPane().add(getBand1TN());
				// getContentPane().add(getBand2TN());
				// getContentPane().add(getBand3TN());
				// getContentPane().add(getJLabel1x());
				// getContentPane().add(getJLabel2x());
				// getContentPane().add(getJLabel3x());
				thumPanel.setBounds(636, 18, 166, 123);
			}

			pack();
			this.setSize(866, 555);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private JFileChooser getJFileChooser2() {
//		if (jFileChooser2 == null) {
//			jFileChooser2 = new JFileChooser();
//		}
//		return jFileChooser2;
//	}

	private void thumPanelMousePressed(MouseEvent evt) {
		dj.set(dt.getImage());
		Rectangle r = this.getCrop();
		if (dt.inViewport() == 0)
			dj.setRectangle(r);
		else {
			if (dt.inViewport() == 1) {
				r.x = upperLeftX + pixelsWidth - dWidth;
				dj.setRectangle(r);
			} else {
				r.y = upperLeftY + pixelsHeight - dHeight;
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
				r.x = upperLeftX + pixelsWidth - dWidth;
				dj.setRectangle(r);
			} else {
				r.y = upperLeftY + pixelsHeight - dHeight;
				dj.setRectangle(r);
			}
		}
	}

	private void jMenuItem1MouseReleased(MouseEvent evt) {
		// JDirectoryChooser dc = new JDirectoryChooser();
		// int choice = dc.showOpenDialog(this);
		// if (choice != JDirectoryChooser.CANCEL_OPTION){
		// File sel = dc.getSelectedFile();
		// // new OpenBands(this);
		// new CropImage(this,sel.getAbsolutePath());
		// jCheckBoxMenuItem1.setSelected(false);
		// jCheckBoxMenuItem2.setSelected(false);
		// jCheckBoxMenuItem3.setSelected(false);
		// }

	}

	private void djMouseDragged(MouseEvent evt) {
		if (si != null) {
			onMouseAction(evt, dt.getCroppedImageBounds(), true);
		}
	}

	private void djMousePressed(MouseEvent evt) {
		Rectangle crop = dt.getCroppedImageBounds();
		if (si != null) {
			if (cm != null) {
				cm.addPoint(evt.getX() + crop.x + upperLeftX, evt.getY()
						+ crop.y + upperLeftY);
				return;
			}
			onMouseAction(evt, crop, true);
		}
	}

	private void onMouseAction(MouseEvent evt, Rectangle crop, boolean chart) {
		/*
		 * System.out.println("En la Imagen Completa:");
		 * System.out.println("El X es:" + (evt.getX() + crop.x + x0) +
		 * " El Y es:" + (evt.getY() + crop.y + y0));
		 */
		if (chart) {
			signature = bandsManager.getSignature(new Point(evt.getX() + crop.x
					+ upperLeftX, evt.getY() + crop.y + upperLeftY));
			XYSeries signature1 = new XYSeries("Signature"); //$NON-NLS-1$
			for (int i = 0; i < signature.length; i++) {
				signature1.add(i, signature[i]);
			}
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(signature1);
			XYPlot plot = (XYPlot) signatureG.getPlot();
			plot.setDataset(dataset);
		}
		int x = evt.getX() + crop.x + upperLeftX;
		int y = evt.getY() + crop.y + upperLeftY;
		edu.pdi2.forms.Point point = new Point(x, y);
		double lat = et.getXi_(evt.getX() + crop.x + upperLeftX, evt.getY()
				+ crop.y + upperLeftY);
		double lon = et.getYi_(evt.getX() + crop.x + upperLeftX, evt.getY()
				+ crop.y + upperLeftY);
		posDialog.setLat(lat);
		posDialog.setLon(lon);
		jLat.setText("Lat: " + ToDegrees(lat)); //$NON-NLS-1$
		jLong.setText("Long: " + ToDegrees(lon)); //$NON-NLS-1$

		for (int i = 0; i < mesh.size(); i++) {
			Polygon p = mesh.get(i);
//			Vector gPoints = p.getGPoints();
			if (p.isIn(point)) {
				p.changeColor();
				System.err.println("Estoy dentro de un pfoygon!"); //$NON-NLS-1$
				ElasticTransform etP = p.getET();
				lat = etP.getYi_(x, y);
				lon = etP.getXi_(x, y);
				posDialog.setLat(lat);
				posDialog.setLon(lon);
//				jLat.setText("Lat: " + ToDegrees(lat)); //$NON-NLS-1$
//				jLong.setText("Long: " + ToDegrees(lon)); //$NON-NLS-1$
				break;
			}

		}

	}
	private String ToDegrees(double ang) {
		double angle = ang / 10000;
		String angleS = Double.toString(angle);
		String grados = angleS.substring(0, angleS.indexOf('.'));
		double c = Double.parseDouble(angleS.substring(angleS.indexOf('.')));
		c = c * 60;
		angleS = Double.toString(c);
		String minutos = angleS.substring(0, angleS.indexOf('.'));
		c = Double.parseDouble(angleS.substring(angleS.indexOf('.')));
		double segundos = c * 60;
		return new String(grados + "º " + minutos + "\' " + segundos + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	public void setNullCM() {
		cm = null;
	}

	private void menuFalseColorActionPerformed(ActionEvent evt) {
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
		// if (corrected_reflectance == null) {
		corrected_reflectance = si.getReflectanceCorrected(si.getBounds().x, si
				.getBounds().width, si.getBounds().y, si.getBounds().height);
		// }
		// FIXME esto no se hace así...
		// String band1 = selectedBands.get(0);
		// band1 = band1.substring(band1.length() - 5, band1.length() - 4);
		// String band2 = selectedBands.get(1);
		// band2 = band2.substring(band2.length() - 5, band2.length() - 4);
		// String band3 = selectedBands.get(2);
		// band3 = band3.substring(band3.length() - 5, band3.length() - 4);

		// se hace así
		int[] currentBands = si.getBands();
		int band1 = currentBands[0];
		int band2 = currentBands[1];
		int band3 = currentBands[2];

		// FIXME Esta no era la idea. La idea era combinar las 3 bandas
		// corregidas en
		// una imagen nueva
		// corrected_reflectance.add(si);
		byte[] data = RawDataUtils.mergeBands(corrected_reflectance);
		corrected_reflectance.add(ImageFactory.makeSatelliteImage(decoder,
				data, si.getBounds().x, si.getBounds().width, si.getBounds().y,
				si.getBounds().height, selectedBands));
		new FourTabsDialog(
				this,
				upperLeftX,
				upperLeftY,
				corrected_reflectance,
				"Band " + band1, "Band " + band2, "Band " + band3, "All Bands", "Corrected Reflectance"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	}

	private void jCheckBoxMenuItem2ActionPerformed(ActionEvent evt) {
		jCheckBoxMenuItem1.setSelected(false);
		jCheckBoxMenuItem3.setSelected(false);
		// ---------------- RAW.
		SatelliteImage s = bandsManager
				.getRawImage(selectedBands, upperLeftX, upperLeftX
						+ pixelsWidth, upperLeftY, upperLeftY + pixelsHeight);
		this.setSi(s);

	}

	/**
	 * Devuelve las direcciones de los archivos de las bandas que actualmente
	 * tiene esta imagen.
	 * 
	 * @return Una lista con <b>3</b> elementos.
	 */
	public List<String> getListFiles() {
		List<String> filesList = new ArrayList<String>(files.length);

		for (File file : files) {
			filesList.add(file.getAbsolutePath());
		}
		return filesList;
	}

	public void setDirectory(String dirPath, String satelliteId) {
		this.directory = dirPath;
		File[] theFiles = new File[3];
		// obtengo los 3 archivos de las bandas de la imagen y los paso a
		// setFiles
		for (int i = 1; i <= 3; ++i) {
			theFiles[i - 1] = new File(dirPath
					+ SatelliteNamingUtils.getBandFilename(i, satelliteId));
		}
		setDecoder(dirPath, satelliteId);
		setBands(theFiles);
		setFiles(theFiles);
		setBandsManager(dirPath, satelliteId);
		setElasticTransform();
		jMenu3.setEnabled(true);
		jMenuView.setEnabled(true);
	}

	private void setElasticTransform() {
		// esto se tiene que hacer después de haber decodificado el header
		et = new RectangleTransform(0, decoder.getPPL(), decoder.getPPL(), 0,
				0, 0, decoder.getLPI(), decoder.getLPI(), decoder.getUL_lon(),
				decoder.getUR_lon(), decoder.getLR_lon(), decoder.getLL_lon(),
				decoder.getUL_lat(), decoder.getUR_lat(), decoder.getLR_lat(),
				decoder.getLL_lat());
	}

	private void setBandsManager(String dirPath, String satelliteId) {
		// esto se tiene que hacer después de haber decodificado el header
		// y de haber seteado los archivos
		int cantBands = SatelliteNamingUtils.getCantBands(satelliteId);

		List<String> allBandsPaths = new ArrayList<String>(cantBands);

		for (int i = 1; i <= cantBands; ++i) {
			allBandsPaths.add(dirPath
					+ SatelliteNamingUtils.getBandFilename(i, satelliteId));
		}

		Rectangle r = new Rectangle(0, 0, decoder.getPPL(), decoder.getLPI());
		bandsManager = new BandsManager(decoder, allBandsPaths, r, decoder
				.getPPL(), decoder.getLPI());
	}

	private void setBands(File[] files) {
		selectedBands = new ArrayList<String>();
		selectedBands.add(files[0].getAbsolutePath());
		selectedBands.add(files[1].getAbsolutePath());
		selectedBands.add(files[2].getAbsolutePath());

	}

	private void setDecoder(String dirPath, String satelliteId) {
		decoder = DecoderFactory.getDecoder(dirPath
				+ SatelliteNamingUtils.getHeaderFilename(satelliteId));
	}

	public void setFiles(File[] f) {
		files = f;

	}

	public Decoder getDecoder() {
		return decoder;
	}

	private void jCheckBoxMenuItem3ActionPerformed(ActionEvent evt) {
		jCheckBoxMenuItem2.setSelected(false);
		jCheckBoxMenuItem1.setSelected(false);
		// ---------------- Corrected Radiance.
		// if (corrected_radiance == null)
		corrected_radiance = (List<SatelliteImage>) si.getRadianceCorrected(si
				.getBounds().x, si.getBounds().width, si.getBounds().y, si
				.getBounds().height);

		// FIXME esto no se hace así...
		// String band1 = selectedBands.get(0);
		// band1 = band1.substring(band1.length() - 5, band1.length() - 4);
		// String band2 = selectedBands.get(1);
		// band2 = band2.substring(band2.length() - 5, band2.length() - 4);
		// String band3 = selectedBands.get(2);
		// band3 = band3.substring(band3.length() - 5, band3.length() - 4);

		// se hace así
		int[] currentBands = si.getBands();
		int band1 = currentBands[0];
		int band2 = currentBands[1];
		int band3 = currentBands[2];

		// FIXME Esta no era la idea. La idea era combinar las 3 bandas
		// corregidas en
		// una imagen nueva
		// corrected_radiance.add(si);
		byte[] data = RawDataUtils.mergeBands(corrected_radiance);

		corrected_radiance.add(ImageFactory.makeSatelliteImage(decoder, data,
				si.getBounds().x, si.getBounds().width, si.getBounds().y, si
						.getBounds().height, selectedBands));

		new FourTabsDialog(
				this,
				upperLeftX,
				upperLeftY,
				corrected_radiance,
				"Band " + band1, "Band " + band2, "Band " + band3, "All Bands", "Corrected Radiance"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// List<SatelliteImage> s= new ArrayList<SatelliteImage>();
		// s.add(si);
		// s.add(si);
		// s.add(si);
		//		
		// new CorrectedDialog(this,x0,y0,s,"Band 1","Band 2","Band 3");
	}

	private JLabel getJLabel1() {
		if (jLat == null) {
			jLat = new JLabel();
			jLat.setText("Lat:"); //$NON-NLS-1$
			jLat.setBounds(10, 6, 198, 14);
		}
		return jLat;
	}

	private JLabel getJLabel2() {
		if (jLong == null) {
			jLong = new JLabel();
			jLong.setText("Long:"); //$NON-NLS-1$
			jLong.setBounds(245, 6, 198, 14);
		}
		return jLong;
	}

	private void jButton1ActionPerformed(ActionEvent evt) {

		if (signature != null) {
			// FIXME hacer que esto se haga por IGUALDAD o por SIMILITUD de
			// firmas
			byte[] bottom = new byte[signature.length];
			byte[] top = new byte[signature.length];

			for (int i = 0; i < signature.length; ++i) {
				bottom[i] = (byte) (signature[i] - 30);

				top[i] = (byte) (signature[i] + 30);
				if (top[i] < 0)
					top[i] = 127;

			}
			bandsManager.setSignatureComparator(new SimilarSignatures(top,
					bottom));
			signature_image = bandsManager.getImageWithThisSignature(
					upperLeftX, pixelsWidth + upperLeftX, upperLeftY,
					pixelsHeight + upperLeftY);

			int[] currentBands = si.getBands();
			int band1 = currentBands[0];
			int band2 = currentBands[1];
			int band3 = currentBands[2];

			// ahora la separo en 3 imagenes
			List<SatelliteImage> siList = new ArrayList<SatelliteImage>();
			List<byte[]> dataList = RawDataUtils.splitBands(signature_image);
			for (int i = 0; i < dataList.size(); ++i) {
				byte[] bs = dataList.get(i);
				siList.add(ImageFactory.makeOneBandSatelliteImage(decoder, bs,
						si.getBounds().x, si.getBounds().width,
						si.getBounds().y, si.getBounds().height,
						currentBands[i]));
			}

			siList.add(signature_image);
			new FourTabsDialog(this, upperLeftX, upperLeftY, siList, "Band "
					+ band1, "Band " + band2, "Band " + band3, "All Bands",
					"Generated with digital signature");
			// this.setSi(signature_image);

		}
	}

	public Rectangle getCrop() {
		Rectangle crop = dt.getCroppedImageBounds();
		return new Rectangle(upperLeftX + crop.x, upperLeftY + crop.y,
				crop.width, crop.height);
	}

	private JPanel getLatLon() {
		if (latLon == null) {
			latLon = new JPanel();
			latLon.setBounds(0, 480, 591, 27);
			latLon.setLayout(null);
			//latLon.setBorder(BorderFactory.createTitledBorder("")); //$NON-NLS-1$
			latLon.add(getJLabel1());
			latLon.add(getJLabel2());

		}
		return latLon;
	}

	private void djMouseMoved(MouseEvent evt) {
		if (si != null)
			onMouseAction(evt, dt.getCroppedImageBounds(), false);
	}

//	private String ToDegrees(double ang) {
//		double angle = ang / 10000;
//		String angleS = Double.toString(angle);
//		String grados = angleS.substring(0, angleS.indexOf('.'));
//		double c = Double.parseDouble(angleS.substring(angleS.indexOf('.')));
//		c = c * 60;
//		angleS = Double.toString(c);
//		String minutos = angleS.substring(0, angleS.indexOf('.'));
//		c = Double.parseDouble(angleS.substring(angleS.indexOf('.')));
//		double segundos = c * 60;
//		return new String(grados + "º " + minutos + "\' " + segundos + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//	}

	// FIXME
	// private DisplayJAIWithAnnotations getBand1TN() {
	// if (band1TN == null) {
	// band1TN = new DisplayJAIWithAnnotations();
	// band1TN.setBounds(10, 560, 150, 150);
	// }
	// return band1TN;
	// }
	//
	// private DisplayJAIWithAnnotations getBand2TN() {
	// if (band2TN == null) {
	// band2TN = new DisplayJAIWithAnnotations();
	// band2TN.setBounds(210, 560, 150, 150);
	// }
	// return band2TN;
	// }
	//
	// private DisplayJAIWithAnnotations getBand3TN() {
	// if (band3TN == null) {
	// band3TN = new DisplayJAIWithAnnotations();
	// band3TN.setBounds(410, 560, 150, 150);
	// }
	// return band3TN;
	// }

//	private JLabel getJLabel1x() {
//		if (jLabel1 == null) {
//			jLabel1 = new JLabel();
//			jLabel1.setText("Band 1:"); //$NON-NLS-1$
//			jLabel1.setBounds(10, 540, 128, 14);
//		}
//		return jLabel1;
//	}
//
//	private JLabel getJLabel2x() {
//		if (jLabel2 == null) {
//			jLabel2 = new JLabel();
//			jLabel2.setText("Band 1:"); //$NON-NLS-1$
//			jLabel2.setBounds(210, 540, 128, 14);
//		}
//		return jLabel2;
//	}
//
//	private JLabel getJLabel3x() {
//		if (jLabel3 == null) {
//			jLabel3 = new JLabel();
//			jLabel3.setText("Band 1:"); //$NON-NLS-1$
//			jLabel3.setBounds(410, 540, 128, 14);
//		}
//		return jLabel3;
//	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
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
		new RayleighData(this, si.getRayleigh());
	}

	private JMenu getJMenuOpenImage() {
		if (jMenuOpenImage == null) {
			jMenuOpenImage = new JMenu();
			jMenuOpenImage.setText("Open Satellite Image");
			jMenuOpenImage.add(getJMenuItemOpenL5());
			jMenuOpenImage.add(getJMenuItemLandsat7());
			jMenuOpenImage.add(getJMenuItemSacc());
		}
		return jMenuOpenImage;
	}

	private JMenuItem getJMenuItemOpenL5() {
		if (jMenuItemOpenL5 == null) {
			jMenuItemOpenL5 = new JMenuItem();
			jMenuItemOpenL5.setText("Landsat 5");
			jMenuItemOpenL5.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					jMenuItemOpenL5MouseReleased(evt);
				}
			});
		}
		return jMenuItemOpenL5;
	}

	private JMenuItem getJMenuItemLandsat7() {
		if (jMenuItemLandsat7 == null) {
			jMenuItemLandsat7 = new JMenuItem();
			jMenuItemLandsat7.setText("Landsat 7");
			jMenuItemLandsat7.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					jMenuItemLandsat7MouseReleased(evt);
				}
			});
		}
		return jMenuItemLandsat7;
	}

	private String getSelectedDirectory() {
		JDirectoryChooser dc = new JDirectoryChooser();
		String defaultDir = AppConstants.getString("defaultDir");
		if (!(defaultDir.equals("!defaultDir!")))
			dc.setSelectedFile(new File(defaultDir));
		int choice = dc.showOpenDialog(this);
		if (choice != JDirectoryChooser.CANCEL_OPTION) {
			return dc.getSelectedFile().getAbsolutePath() + "/";
		}
		return null;
	}

	private JMenuItem getJMenuItemSacc() {
		if (jMenuItemSacc == null) {
			jMenuItemSacc = new JMenuItem();
			jMenuItemSacc.setText("SACC");
			jMenuItemSacc.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					jMenuItemSaccMouseReleased(evt);
				}
			});
		}
		return jMenuItemSacc;
	}

	private void jMenuItemOpenL5MouseReleased(MouseEvent evt) {
		String path = getSelectedDirectory();
		if (path != null) {
			setDirectory(path, SatelliteNamingUtils.LANDSAT5_ID);
			new CropImage(this, path, SatelliteNamingUtils.LANDSAT5_ID);
		}
	}

	private void jMenuItemLandsat7MouseReleased(MouseEvent evt) {
		String path = getSelectedDirectory();

		if (path != null) {
			setDirectory(path, SatelliteNamingUtils.LANDSAT7_ID);
			new CropImage(this, path, SatelliteNamingUtils.LANDSAT7_ID);
		}
	}

	private void jMenuItemSaccMouseReleased(MouseEvent evt) {
		String path = getSelectedDirectory();
		if (path != null) {
			setDirectory(path, SatelliteNamingUtils.SACC_ID);
			new CropImage(this, path, SatelliteNamingUtils.SACC_ID);
		}
	}

	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("Threshold Signature");
			jMenuItem5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuThresholdActionPerformed(evt);
				}
			});
		}
		return jMenuItem5;
	}

	private void menuThresholdActionPerformed(ActionEvent evt) {
		ThresholdSignature ts = new ThresholdSignature(this,
				SatelliteNamingUtils.getCantBands(decoder.getSatelliteId()));
		// ThresholdSignature ts=new ThresholdSignature(this, 7);
		ts.setSignature(signature);
	}

	/**
	 * A este método lo llama el diálogo de generación de imágenes con una
	 * determinada firma digital. Le pasa los límites de tolerancia para generar
	 * aceptar como <i>similar</i> a una firma, así que solamente tiene que
	 * llamar a la generación de imágen y pasarle los datos.
	 */
	public void generateImageByThresholdSignature(byte[] bottom, byte[] top) {
		bandsManager.setSignatureComparator(new SimilarSignatures(top, bottom));
		signature_image = bandsManager
				.getImageWithThisSignature(upperLeftX,
						pixelsWidth + upperLeftX, upperLeftY, pixelsHeight
								+ upperLeftY);

		int[] currentBands = si.getBands();
		int band1 = currentBands[0];
		int band2 = currentBands[1];
		int band3 = currentBands[2];

		// ahora la separo en 3 imagenes
		List<SatelliteImage> siList = new ArrayList<SatelliteImage>();
		List<byte[]> dataList = RawDataUtils.splitBands(signature_image);
		for (int i = 0; i < dataList.size(); ++i) {
			byte[] bs = dataList.get(i);
			siList.add(ImageFactory.makeOneBandSatelliteImage(decoder, bs, si
					.getBounds().x, si.getBounds().width, si.getBounds().y, si
					.getBounds().height, currentBands[i]));
		}

		siList.add(signature_image);
		new FourTabsDialog(this, upperLeftX, upperLeftY, siList, "Band "
				+ band1, "Band " + band2, "Band " + band3, "All Bands",
				"Generated with digital signature");

		// this.setSi(signature_image);
		// FIXME
	}

	/**
	 * Cambia las bandas de acuerdo al orden que le devuelvan. Es llamado cuando
	 * se cambian las bandas en el cuadro de generación de imágen de <b>falso
	 * color</b>.
	 * 
	 * @param bandsNumbers
	 *            Los números de las bandas. Es un arreglo que puede tener un
	 *            tamaño distinto a 3 y los números de banda comienzan en
	 *            <b>1</b> (no en cero).
	 */
	public void changeBands(int[] bandsNumbers) {

		List<String> sel = new ArrayList<String>(bandsNumbers.length);

		for (int i = 0; i < 3; ++i) {
			sel.add(directory
					+ SatelliteNamingUtils.getBandFilename(bandsNumbers[i],
							decoder.getSatelliteId()));
		}

		setSelectedBands(sel);
		BandsManager bandsManager = getBandsManager();
		SatelliteImage si = bandsManager.getRawImage(sel, getUpperLeftX(),
				getPixelsWidth() + getUpperLeftX(), getUpperLeftY(),
				getPixelsHeight() + getUpperLeftY());
		setSi(si);

	}

	private JMenu getJMenuView() {
		if (jMenuView == null) {
			jMenuView = new JMenu();
			jMenuView.setText("View");
			// jMenuView.add(getJMenuItemBandsThumbnails());
			jMenuView.add(getJCheckBoxMIThumbs());
			jMenuView.add(getJCheckBoxMISignature());
			jMenuView.setEnabled(false);
		}
		return jMenuView;
	}

	// private JMenuItem getJMenuItemBandsThumbnails() {
	// if(jMenuItemBandsThumbnails == null) {
	// jMenuItemBandsThumbnails = new JMenuItem();
	// jMenuItemBandsThumbnails.setText("Bands Thumbnails");
	// jMenuItemBandsThumbnails.addMouseListener(new MouseAdapter() {
	// public void mouseReleased(MouseEvent evt) {
	// jMenuItemBandsThumbnailsMouseReleased(evt);
	// }
	// });
	// }
	// return jMenuItemBandsThumbnails;
	// }

	private void showThumbnails(MouseEvent evt) {
		if (!tnDialog.isVisible()) {
			tnDialog.setVisible(true);

			updateThumbnails();
			
		} else {
			tnDialog.setVisible(false);
		}
	}

	private void updateThumbnails() {
		int[] currentBands = si.getBands();

		// ahora la separo en 3 imagenes
		List<SatelliteImage> imgsList = new ArrayList<SatelliteImage>();

		List<byte[]> dataList = RawDataUtils.splitBands(si);
		for (int i = 0; i < dataList.size(); ++i) {
			byte[] bs = dataList.get(i);
			imgsList.add(ImageFactory.makeOneBandSatelliteImage(decoder, bs, si
					.getBounds().x, si.getBounds().width, si.getBounds().y, si
					.getBounds().height, currentBands[i]));
		}

		tnDialog.setImages(imgsList);
	}

	private JCheckBoxMenuItem getJCheckBoxMIThumbs() {
		if (jCheckBoxMIThumbs == null) {
			jCheckBoxMIThumbs = new JCheckBoxMenuItem();
			jCheckBoxMIThumbs.setText("Bands Thumbnails");
			jCheckBoxMIThumbs.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					showThumbnails(evt);
				}
			});
		}
		return jCheckBoxMIThumbs;
	}

	public void unselectThumbnailsMenuItem() {
		jCheckBoxMIThumbs.setSelected(false);
		
	}
	
	private JCheckBoxMenuItem getJCheckBoxMISignature() {
		if(jCheckBoxMISignature == null) {
			jCheckBoxMISignature = new JCheckBoxMenuItem();
			jCheckBoxMISignature.setText("Pixel Signature");
			jCheckBoxMISignature.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jCheckBoxMISignatureActionPerformed(evt);
				}
			});
		}
		return jCheckBoxMISignature;
	}
	
	private void jCheckBoxMISignatureActionPerformed(ActionEvent evt) {
		if (!signDialog.isVisible()) {
			signDialog.setVisible(true);
			jCheckBoxMISignature.setSelected(true);
			
		} else {
			signDialog.setVisible(false);
			jCheckBoxMISignature.setSelected(false);
		}
	}

	private void JMenuPositionActionPerformed(ActionEvent evt) {
		if(posDialog.isVisible()){
			posDialog.setVisible(false);
		}
		else{
			posDialog.setVisible(true);
		}
	}

	public void unselectPositionMenuItem() {
		JMenuPosition.setSelected(false);
	}

	public void unselectSignatureMenuItem() {
		jCheckBoxMISignature.setSelected(false);
	}
	
	private JSeparator getJSeparator1() {
		if(jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}
	
	private JMenuItem getJMenuItemExit() {
		if(jMenuItemExit == null) {
			jMenuItemExit = new JMenuItem();
			jMenuItemExit.setText("Exit");
			jMenuItemExit.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					jMenuItemExitMouseReleased(evt);
				}
			});
		}
		return jMenuItemExit;
	}
	
	private void jMenuItemExitMouseReleased(MouseEvent evt) {
		System.exit(0);
	}
	
	private JMenuItem getJGenerarSignature() {
		if(JGenerarSignature == null) {
			JGenerarSignature = new JMenuItem();
			JGenerarSignature.setText("Generate Image With Signatures");
			JGenerarSignature.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					JGenerarSignatureActionPerformed(evt);
				}
			});
		}
		return JGenerarSignature;
	}
	
	private void JGenerarSignatureActionPerformed(ActionEvent evt) {
		if (signature != null) {
			// FIXME hacer que esto se haga por IGUALDAD o por SIMILITUD de
			// firmas
			byte[] bottom = new byte[signature.length];
			byte[] top = new byte[signature.length];

			for (int i = 0; i < signature.length; ++i) {
				bottom[i] = (byte) (signature[i] - 30);

				top[i] = (byte) (signature[i] + 30);
				if (top[i] < 0)
					top[i] = 127;

			}
			bandsManager.setSignatureComparator(new SimilarSignatures(top,
					bottom));
			signature_image = bandsManager.getImageWithThisSignature(
					upperLeftX, pixelsWidth + upperLeftX, upperLeftY,
					pixelsHeight + upperLeftY);

			int[] currentBands = si.getBands();
			int band1 = currentBands[0];
			int band2 = currentBands[1];
			int band3 = currentBands[2];

			// ahora la separo en 3 imagenes
			List<SatelliteImage> siList = new ArrayList<SatelliteImage>();
			List<byte[]> dataList = RawDataUtils.splitBands(signature_image);
			for (int i = 0; i < dataList.size(); ++i) {
				byte[] bs = dataList.get(i);
				siList.add(ImageFactory.makeOneBandSatelliteImage(decoder, bs,
						si.getBounds().x, si.getBounds().width,
						si.getBounds().y, si.getBounds().height,
						currentBands[i]));
			}

			siList.add(signature_image);
			new FourTabsDialog(this, upperLeftX, upperLeftY, siList, "Band "
					+ band1, "Band " + band2, "Band " + band3, "All Bands",
					"Generated with digital signature");
			// this.setSi(signature_image);

		}
	}

}
