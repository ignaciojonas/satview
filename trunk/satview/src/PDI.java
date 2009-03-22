

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.TiledImage;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import com.sun.media.jai.widget.DisplayJAI;

import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.imaging.readers.FileChopReader;
import edu.pdi2.math.indexes.Rayleigh.L7Rayleigh;
import edu.pdi2.math.indexes.Rayleigh.Rayleigh;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.visual.AppConstants;
import edu.pdi2.visual.DisplayThumbnail;
import edu.pdi2.visual.JAIImageReader;


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
public class PDI extends javax.swing.JFrame {

	// The instance of DisplayThumbnail.
	private DisplayThumbnail dt;
	private JPanel thumPanel;
	private JFileChooser jFileChooser2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JPanel image;
	private static int dWidth = 590;
	private static int dHeight= 520;
	private static String pathFirstImage=AppConstants.getString("M2"); //$NON-NLS-1$

	// The instance of DisplayJAI.
	private DisplayJAI dj;

	// Two labels to show the world (original image) and thumbnail viewport
	// coordinates.
	private JLabel world, view;

	// to keep all the signatures
	private PlanarImage[] sources;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PDI inst = new PDI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public PDI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Procesamiento Digital de Imagenes"); 
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File"); 
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("filestore"); 
						jMenuItem1.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								jMenuItem1MouseReleased(evt);
							}
							
						});
					}
				}
			}
			// First we create the instance of DisplayThumbnail with a 0.1 scale.
			dt = new DisplayThumbnail(JAIImageReader.readImage(pathFirstImage), 0.1f, dWidth, dHeight);
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
			dj = new DisplayJAI(dt.getImage());
			// Set it size.
			dj.setPreferredSize(new Dimension(dWidth, dHeight));
			dj.setMinimumSize(new Dimension(dWidth, dHeight));
			dj.setMaximumSize(new Dimension(dWidth, dHeight));
			{
				image = new JPanel();
				image.add(dj);
				getContentPane().add(image);
				image.setBounds(12, 27, 590, 520);
			}
			{
				thumPanel = new JPanel();
				thumPanel.add(dt);
				getContentPane().add(thumPanel);
				thumPanel.setBounds(614, 18, 166, 123);
			}
			
			
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void debug(String arg) {
		System.out.println(arg);

	}
	

	
	private JFileChooser getJFileChooser2() {
		if(jFileChooser2 == null) {
			jFileChooser2 = new JFileChooser();
		}
		return jFileChooser2;
	}
	
	private void thumPanelMousePressed(MouseEvent evt) {
		
		// Change the image on the DisplayJAI component to correspond to the
		// viewport on the thumbnail.
		dj.set(dt.getImage());
		// Gets some information about the viewport and cropped image.
		/*Rectangle crop = dt.getCroppedImageBounds();
		Rectangle viewp = dt.getViewportBounds();
		// Change the labels' contents with this information.
		world.setText("" + crop.x + "," + crop.y + " (" + crop.width + "x"
				+ crop.height + ")");
		view.setText("" + viewp.x + "," + viewp.y + " (" + viewp.width + "x"
				+ viewp.height + ")");*/
		
	}
	
	private void thumPanelMouseDragged(MouseEvent evt) {
		// Change the image on the DisplayJAI component to correspond to the
		// viewport on the thumbnail.
		dj.set(dt.getImage());
		// Gets some information about the viewport and cropped image.
		/*Rectangle crop = dt.getCroppedImageBounds();
		Rectangle viewp = dt.getViewportBounds();
		// Change the labels' contents with this information.
		world.setText("" + crop.x + "," + crop.y + " (" + crop.width + "x"
				+ crop.height + ")");
		view.setText("" + viewp.x + "," + viewp.y + " (" + viewp.width + "x"
				+ viewp.height + ")");*/
	

	}
	
	
	
	private void jMenuItem1MouseReleased(MouseEvent evt) {
		JFileChooser jfc = getJFileChooser2();
		jfc.setDialogTitle("c.jpg"); 
		jfc.setMultiSelectionEnabled(true);
		jfc.showOpenDialog(this);
		File[] files=jfc.getSelectedFiles();
		
		//----- Procesamiento -----//
		System.out.println("JPEG"); 
		FileChopReader fcr = new FileChopReader();
		List<String> sources = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			sources.add(files[i].getAbsolutePath());
		}
		//sources.add(file.getAbsolutePath());
		//SatelliteImage si = fcr.read(sources, 9516, 8616,4756,4760,4306,4310);
		Rectangle porcion = new Rectangle(3000,3000,300,300);
		BandsManager bm = new BandsManager(AppConstants.getString("imageDir"),porcion); //$NON-NLS-1$
		List<Integer> bands = new ArrayList<Integer>();
		bands.add(1);
		bands.add(2);
		bands.add(3);
		TiledImage si = bm.getRawImage(bands);
		Point p = new Point(2,2);//centro de la imagen
		double[] lsat = bm.getRadiance(p);
		System.out.print("Lsat: "); 
		for (int i = 0; i < lsat.length; i++) {
			System.out.print(lsat[i] + " "); 
		}
		System.out.println();
		
		//ahora el sat�lite
		Rayleigh landsat = new L7Rayleigh();
		System.out.print("Lr: "); 
		/*
		for (int i = 1; i <= lsat.length; i++) {
			System.out.print(landsat.getRadiance(new Integer(i)) + " ");
		}
		*/
		System.out.println();
		System.out.print("bandas: "); 
		
		String nombre = "l5-bandas-"; 
		
		nombre += ".jpg"; 
		
		JAI.create("filestore",si,nombre,"JPEG");  
		//---- Visualizar ----//
		//Como el procesamiento genera una imagen muy chiquita lo comento, pero en teoria anda!
		/*
		dt.set(JAIImageReader.readImage(nombre), dWidth, dHeight);
		// below code dj.set() works well
		dj.set(dt.getImage());
		image.repaint();
		thumPanel.repaint();
		repaint();*/
		
	}
	
	
	

}
