package edu.pdi2.visual;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
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
import edu.pdi2.forms.GeographicPoint;
import edu.pdi2.forms.Point;
import edu.pdi2.forms.Polygon;
import edu.pdi2.imaging.ImageFactory;
import edu.pdi2.imaging.RawDataUtils;
import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.math.signatures.comparators.EqualSignature;
import edu.pdi2.math.signatures.comparators.SimilarSignatures;
import edu.pdi2.math.transforms.ElasticTransform;
import edu.pdi2.math.transforms.RectangleTransform;
import edu.pdi2.visual.extradialogs.BandsThumbnailsDialog;
import edu.pdi2.visual.extradialogs.PositionDialog;
import edu.pdi2.visual.extradialogs.SignatureDialog;
import edu.pdi2.visual.extradialogs.ThumnailDialog;

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

	private JMenuItem jMenuItemOpenL5;
	private JMenu jMenuOpenImage;
	private JMenuItem jMenuItem2;
	// private JLabel jLabel1;
	// private JPanel jPanel1;
	public static ThumnailDialog td;
	private JPanel latLon;
	private JLabel jLong;
	private JCheckBoxMenuItem jViewThumnail;
	private JMenuItem JGenerarSignature;
	private JMenuItem jMenuItemExit;
	// private JMenu jMenuExit;
	private JSeparator jSeparator1;
	private JCheckBoxMenuItem jCheckBoxMISignature;
	private JCheckBoxMenuItem jCheckBoxMIThumbs;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItemSacc;
	private JMenuItem jMenuItemLandsat7;
	private JLabel jLat;
	private JMenuItem jmiCorrectedRadiance;
	private JMenu jMenu4;
	private JMenuItem jmiCorrectedReflectance;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	private JMenu jMenu3;
	// private JFileChooser jFileChooser2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JPanel image;
	public static int dWidth = 590;
	public static int dHeight = 470;
	private Decoder decoder;
	private ElasticTransform et;
	private File[] files;
	private DisplayJAIWithAnnotations dj;
	private SatelliteImage si = null;
	@SuppressWarnings("unchecked")
	private Vector mesh;
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
	private PositionDialog posDialog = new PositionDialog(this);

	private JMenu jMenuView;

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
		td.setDj(dj);
		td.setSI(si);
		if (tnDialog.isVisible())
			updateThumbnails();
		repaint();
	}

	@SuppressWarnings("unchecked")
	public void setMesh(Vector mesh) {
		this.mesh = mesh;
	}

	@SuppressWarnings("unchecked")
	public Vector getMesh() {
		return mesh;
	}

	@SuppressWarnings("unchecked")
	public void addForm(Polygon p) {
		mesh.add(p);
		dj.addAnnotation(p);
		// System.out.println("X0:"+x0+" - Y0= "+y0);
		// System.out.println("Agergue una anotacion X:"+p.getPoints().get(0).x+
		// " - "+p.getPoints().get(0).y);
		repaint();
	}

	@SuppressWarnings("unchecked")
	public PDI() {
		// super();
		signatureG = ChartFactory.createXYLineChart("Signature ", "Bands", //$NON-NLS-1$ //$NON-NLS-2$
				"Valory", null, PlotOrientation.VERTICAL, true, true, false); //$NON-NLS-1$
		initGUI();
		files = new File[7];
		mesh = new Vector();
		selectedBands = new ArrayList<String>();

		si = null;
		signDialog = new SignatureDialog(this, signatureG);
		td=new ThumnailDialog(this);
		td.setDj(dj);
		td.setUpperLeftX(upperLeftX);
		td.setUpperLeftY(upperLeftY);
		td.setPixelsWidth(pixelsWidth);
		td.setPixelsHeight(pixelsHeight);
		
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
			this.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					thisFocusGained(evt);
				}
			});
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
							jmiCorrectedReflectance = new JMenuItem();
							jMenu4.add(jmiCorrectedReflectance);
							jmiCorrectedReflectance
									.setText("Corrected Reflectance"); //$NON-NLS-1$
							jmiCorrectedReflectance.setAccelerator(KeyStroke
									.getKeyStroke("ctrl pressed 1")); //$NON-NLS-1$
							jmiCorrectedReflectance
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jmiCorrectedRadianceActionPerformed(evt);
										}
									});
						}
						{
							jmiCorrectedRadiance = new JMenuItem();
							jMenu4.add(jmiCorrectedRadiance);
							jmiCorrectedRadiance.setText("Corrected Radiance"); //$NON-NLS-1$
							jmiCorrectedRadiance.setAccelerator(KeyStroke
									.getKeyStroke("ctrl pressed 2"));
							jmiCorrectedRadiance
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											jmiCorrectedReflectanceActionPerformed(evt);
										}
									});
						}
					}
				}
			}
			// First we create the instance of DisplayThumbnail with a 0.1
			// scale.
			// dt.setBorder(BorderFactory.createTitledBorder(""));
			// We must register mouse motion listeners to it !

			// Now we create the instance of DisplayJAI to show the region
			// corresponding to the viewport.

			// Set it size.
			{
				image = new JPanel();

				getContentPane().add(image);
				image.setBounds(1, 10, 590, dHeight + 10);
				{
					dj = new DisplayJAIWithAnnotations();
					image.add(dj);
					dj.setBounds(0, 0, dWidth, dHeight);
					dj.setPreferredSize(new Dimension(dWidth, dHeight));
					dj.setMinimumSize(new Dimension(dWidth, dHeight));
					dj.setMaximumSize(new Dimension(dWidth, dHeight));
					dj.setBorder(BorderFactory.createTitledBorder(""));
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
				getContentPane().add(getLatLon());
			}

			pack();
			this.setSize(604, 579);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private JFileChooser getJFileChooser2() {
	// if (jFileChooser2 == null) {
	// jFileChooser2 = new JFileChooser();
	// }
	// return jFileChooser2;
	// }




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
			onMouseAction(evt, td.dt.getCroppedImageBounds(), true);
		}
	}

	private void djMousePressed(MouseEvent evt) {
		Rectangle crop = td.dt.getCroppedImageBounds();
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
			XYSeries signature1 = new XYSeries("Signature");
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
		double lat = et.getXi_(x, y);
		double lon = et.getYi_(x, y);
		posDialog.setLat(lat);
		posDialog.setLon(lon);
		jLat.setText("Lat: " + ToDegrees(lat / 10000));
		jLong.setText("Long: " + ToDegrees(lon / 10000));

		for (int i = 0; i < mesh.size(); i++) {
			Polygon p = (Polygon) mesh.get(i);
			// Vector gPoints = p.getGPoints();
			if (p.isIn(point)) {
				p.changeColor();

				ElasticTransform etP = p.getET();
				lat = etP.getYi_(x, y);
				lon = etP.getXi_(x, y);
				posDialog.setLat(lat);
				posDialog.setLon(lon);
				jLat.setText("Lat: " + ToDegrees(lat));
				jLong.setText("Long: " + ToDegrees(lon));
				break;
			}

		}

	}

	private String ToDegrees(double ang) {
		double angle = ang;
		String angleS = Double.toString(angle);
		String grados = angleS.substring(0, angleS.indexOf('.'));
		double c = Double.parseDouble(angleS.substring(angleS.indexOf('.')));
		c = c * 60;
		angleS = Double.toString(c);
		String minutos = angleS.substring(0, angleS.indexOf('.'));
		c = Double.parseDouble(angleS.substring(angleS.indexOf('.')));
		double segundos = c * 60;
		return new String(grados + "º " + minutos + "\' " + segundos + "\""); //$NON-NLS-2$ //$NON-NLS-3$
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

	private void jmiCorrectedRadianceActionPerformed(ActionEvent evt) {
		jmiCorrectedRadiance.setSelected(false);
		// ---------------- Corrected Reflectance.
		// if (corrected_reflectance == null) {
		corrected_reflectance = si.getReflectanceCorrected(si.getBounds().x, si
				.getBounds().width, si.getBounds().y, si.getBounds().height);

		showInManyTabsDialog(corrected_reflectance, "Corrected By Reflectance");
//		int[] currentBands = si.getBands();
//
//		List<String> titles = new ArrayList<String>();
//		
//		List<SatelliteImage> allImages = new ArrayList<SatelliteImage>();
//		
//		for (int i=0; i<si.getNumBands(); ++i){
//			titles.add("B"+ currentBands[i]);
//			allImages.add(si.getOneBand(i));
//			
//			titles.add("B"+ currentBands[i] + " corrected");
//			allImages.add(corrected_reflectance.get(i));
//		}
//		
//		titles.add("All");
//		allImages.add(si);
//		
//		byte[] data = RawDataUtils.mergeBands(corrected_reflectance);
//		
//		titles.add("All corrected");
//		allImages.add(ImageFactory.makeSatelliteImage(decoder,
//				data, si.getBounds().x, si.getBounds().width, si.getBounds().y,
//				si.getBounds().height, selectedBands));
//		
//		new ManyTabsDialog(
//				this,
//				upperLeftX,
//				upperLeftY,
//				allImages,
//				titles, "Corrected Reflectance"); //$NON-NLS-2$ //$NON-NLS-3$

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
				0, 0, decoder.getLPI(), decoder.getLPI(), decoder.getUL_lat(),
				decoder.getUR_lat(), decoder.getLR_lat(), decoder.getLL_lat(),
				decoder.getUL_lon(), decoder.getUR_lon(), decoder.getLR_lon(),
				decoder.getLL_lon());
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

	private void jmiCorrectedReflectanceActionPerformed(ActionEvent evt) {
		// ---------------- Corrected Radiance.
		// if (corrected_radiance == null)
		corrected_radiance = (List<SatelliteImage>) si.getRadianceCorrected(si
				.getBounds().x, si.getBounds().width, si.getBounds().y, si
				.getBounds().height);

		showInManyTabsDialog(corrected_radiance, "Corrected By Radiance");
	}

	private void showInManyTabsDialog(List<SatelliteImage> correctedImgsList, String dialogTitle) {
		int[] currentBands = si.getBands();

		List<String> titles = new ArrayList<String>();
		
		List<SatelliteImage> allImages = new ArrayList<SatelliteImage>();
		
		for (int i=0; i<si.getNumBands(); ++i){
			titles.add("B"+ currentBands[i]);
			allImages.add(si.getOneBand(i));
			
			titles.add("B"+ currentBands[i] + " corrected");
			allImages.add(correctedImgsList.get(i));
		}
		
		titles.add("All");
		allImages.add(si);
		
		byte[] data = RawDataUtils.mergeBands(correctedImgsList);
		
		titles.add("All corrected");
		allImages.add(ImageFactory.makeSatelliteImage(decoder,
				data, si.getBounds().x, si.getBounds().width, si.getBounds().y,
				si.getBounds().height, selectedBands));

		
		new ManyTabsDialog(this, upperLeftX, upperLeftY, allImages,
				titles,
				dialogTitle);
	}

	private JLabel getJLabel1() {
		if (jLat == null) {
			jLat = new JLabel();
			jLat.setText("Lat:");
			jLat.setBounds(10, 6, 198, 21);
		}
		return jLat;
	}

	private JLabel getJLabel2() {
		if (jLong == null) {
			jLong = new JLabel();
			jLong.setText("Long:");
			jLong.setBounds(245, 6, 198, 21);
		}
		return jLong;
	}


	private JPanel getLatLon() {
		if (latLon == null) {
			latLon = new JPanel();
			latLon.setBounds(0, 480, 591, 27);
			latLon.setLayout(null);
			// latLon.setBorder(BorderFactory.createTitledBorder(""));
			latLon.add(getJLabel1());
			latLon.add(getJLabel2());

		}
		return latLon;
	}

	private void djMouseMoved(MouseEvent evt) {
		if (si != null)
			onMouseAction(evt, td.dt.getCroppedImageBounds(), false);
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Edit Rayleigh Data");
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
		List<String> titles = new ArrayList<String>();
		titles.add("Band "+ band1);
		titles.add("Band "+ band2);
		titles.add("Band "+ band3);
		titles.add("All bands");
		
		new ManyTabsDialog(this, upperLeftX, upperLeftY, siList, titles,
				"Generated with digital signature");

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
			jMenuView.add(getJViewThumnail());
			jMenuView.setEnabled(false);
		}
		return jMenuView;
	}

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
		if (jCheckBoxMISignature == null) {
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

	public void unselectSignatureMenuItem() {
		jCheckBoxMISignature.setSelected(false);
	}
	
	public void unselectThumbnailMenuItem() {
		jViewThumnail.setSelected(false);//FIXME
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JMenuItem getJMenuItemExit() {
		if (jMenuItemExit == null) {
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
		if (JGenerarSignature == null) {
			JGenerarSignature = new JMenuItem();
			JGenerarSignature.setText("Same Signature");
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
			// byte[] bottom = new byte[signature.length];
			// byte[] top = new byte[signature.length];
			//
			// for (int i = 0; i < signature.length; ++i) {
			// bottom[i] = (byte) (signature[i] - 30);
			//
			// top[i] = (byte) (signature[i] + 30);
			// if (top[i] < 0)
			// top[i] = 127;
			//
			// }
			bandsManager.setSignatureComparator(new EqualSignature(signature));
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
			List<String> titles = new ArrayList<String>();
			titles.add("Band "+ band1);
			titles.add("Band "+ band2);
			titles.add("Band "+ band3);
			titles.add("All bands");
			
			new ManyTabsDialog(this, upperLeftX, upperLeftY, siList, titles,
					"Generated with digital signature");
			// this.setSi(signature_image);

		}
	}
	
	private JCheckBoxMenuItem getJViewThumnail() {
		if(jViewThumnail == null) {
			jViewThumnail = new JCheckBoxMenuItem();
			jViewThumnail.setText("Thumbnail");
			jViewThumnail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jViewThumnailActionPerformed(evt);
				}
			});
		}
		return jViewThumnail;
	}
	public void unselectViewThumbnail(){
		jViewThumnail.setSelected(false);
	}
	
	private void jViewThumnailActionPerformed(ActionEvent evt) {
		if (jViewThumnail.isSelected())
			td.setVisible(true);
		else
			td.setVisible(false);
	}
	
	private void thisFocusGained(FocusEvent evt) {
		if(si!=null){
			td.setDj(dj);
			td.setSI(si);
			
			
		}
	}

	/**
	 * Devuelve la lista de puntos georeferenciados que contiene la imagen satelital.
	 */
	public List<GeographicPoint> getPoints() {
		
		return si.getPoints();
	}

}
