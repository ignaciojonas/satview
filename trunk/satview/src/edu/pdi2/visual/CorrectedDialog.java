package edu.pdi2.visual;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.pdi2.math.indexes.satellite.SatelliteImage;


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
public class CorrectedDialog extends javax.swing.JDialog {
	private JPanel thumPanel;
	private DisplayJAIWithAnnotations dj2;
	private JPanel jPanel7;
	private DisplayThumbnail dt2;
	private JPanel jPanel6;
	private JPanel jPanel5;
	private DisplayThumbnail dt1;
	private JPanel jPanel4;
	private DisplayJAIWithAnnotations dj1;
	private DisplayThumbnail dt;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JTabbedPane jTabbedPane1;
	private DisplayJAIWithAnnotations dj;
	private JPanel image;
	private static int dWidth = 590;
	private static int dHeight = 490;
	private String title1, title2,title3;
	List<SatelliteImage> si = null;
	private int x0, y0;
	
	
	public CorrectedDialog(JFrame frame,int x0, int y0, List<SatelliteImage> si, String title1, String title2, String title3) {
		super(frame);
		this.title1=title1;
		this.title2=title2;
		this.title3=title3;
		initGUI();
		this.x0=x0;
		this.y0=y0;
		setSi(si);
		this.setTitle("Corrected Image");
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				this.setResizable(false);
				getContentPane().add(this.getJTabbedPane1());
			}
			this.setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void thumPanelMouseDragged(MouseEvent evt) {
		dj.set(dt.getImage());
		dj.setRectangle(dt.getCroppedImageBounds());
	}
	private void thumPanelMousePressed(MouseEvent evt) {
		dj.set(dt.getImage());
		dj.setRectangle(dt.getCroppedImageBounds());
	}

	public void setSi(List<SatelliteImage> si) {
		this.si = si;
		dt.set(si.get(0), dWidth, dHeight);
		dj.set(dt.getImage());
		dj.setRectangle(getCrop());
		dt1.set(si.get(1), dWidth, dHeight);
		dj1.set(dt1.getImage());
		dj1.setRectangle(getCrop());
		dt2.set(si.get(2), dWidth, dHeight);
		dj2.set(dt2.getImage());
		dj2.setRectangle(getCrop());
		repaint();
	}
	public Rectangle getCrop() {
		Rectangle crop = dt.getCroppedImageBounds();
		return new Rectangle(x0 + crop.x, y0 + crop.y, crop.width, crop.height);
	}
	
	private JTabbedPane getJTabbedPane1() {
		if(jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(0, 0, 794, 555);
			jTabbedPane1.addTab(title1, null, getJPanel1(), null);
			jTabbedPane1.addTab(title2, null, getJPanel2(), null);
			jTabbedPane1.addTab(title3, null, getJPanel5(), null);
		}
		return jTabbedPane1;
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			{
				thumPanel = new JPanel();
				jPanel1.add(thumPanel);
				thumPanel.setBounds(599, 4, 166, 123);
				{
					dt = new DisplayThumbnail(0.1f);
					thumPanel.add(dt);
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
				}
			}
			{
				image = new JPanel();
				jPanel1.add(image);
				getContentPane().add(getJTabbedPane1());
				image.setBounds(-1, 4, 590, 488);
				image.setBorder(BorderFactory.createTitledBorder(""));
				{
					dj = new DisplayJAIWithAnnotations();
					image.add(dj);
				}
			}
		}
		return jPanel1;
	}
	
	private JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(getJPanel3());
			jPanel2.add(getJPanel4());
		}
		return jPanel2;
	}
	
	private JPanel getJPanel3() {
		if(jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createTitledBorder(""));
			jPanel3.setBounds(0, 4, 590, 488);
			jPanel3.add(getDisplayJAIWithAnnotations1());
		}
		return jPanel3;
	}
	
	private DisplayJAIWithAnnotations getDisplayJAIWithAnnotations1() {
		if(dj1 == null) {
			dj1 = new DisplayJAIWithAnnotations();
		}
		return dj1;
	}
	
	private JPanel getJPanel4() {
		if(jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBounds(599, 4, 166, 123);
			jPanel4.add(getDisplayThumbnail1());
		}
		return jPanel4;
	}
	
	private DisplayThumbnail getDisplayThumbnail1() {
		if(dt1 == null) {
			dt1 = new DisplayThumbnail(0.1f);
			dt1.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent evt) {
					displayThumbnail1MouseDragged(evt);
				}
			});
			dt1.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					displayThumbnail1MousePressed(evt);
				}
			});
		}
		return dt1;
	}
	

	private JPanel getJPanel5() {
		if(jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.add(getJPanel6());
			jPanel5.add(getJPanel7());
		}
		return jPanel5;
	}
	
	private JPanel getJPanel6() {
		if(jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setBounds(599, 4, 166, 123);
			jPanel6.add(getDisplayThumbnail1x());
		}
		return jPanel6;
	}
	
	private DisplayThumbnail getDisplayThumbnail1x() {
		if(dt2 == null) {
			dt2 = new DisplayThumbnail(0.1f);
			dt2.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent evt) {
					displayThumbnail2MouseDragged(evt);
				}
			});
			dt2.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					displayThumbnail2MousePressed(evt);
				}
			});
		}
		return dt2;
	}
	
	private JPanel getJPanel7() {
		if(jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setBorder(BorderFactory.createTitledBorder(""));
			jPanel7.setBounds(0, 4, 590, 488);
			jPanel7.add(getDisplayJAIWithAnnotations1x());
		}
		return jPanel7;
	}
	
	private DisplayJAIWithAnnotations getDisplayJAIWithAnnotations1x() {
		if(dj2 == null) {
			dj2 = new DisplayJAIWithAnnotations();
		}
		return dj2;
	}
	private void displayThumbnail1MouseDragged(MouseEvent evt) {
		dj1.set(dt1.getImage());
		dj1.setRectangle(dt1.getCroppedImageBounds());
	}
	private void displayThumbnail1MousePressed(MouseEvent evt) {
		dj1.set(dt.getImage());
		dj1.setRectangle(dt1.getCroppedImageBounds());
	}
	private void displayThumbnail2MouseDragged(MouseEvent evt) {
		dj2.set(dt2.getImage());
		dj2.setRectangle(dt2.getCroppedImageBounds());
	}
	private void displayThumbnail2MousePressed(MouseEvent evt) {
		dj2.set(dt2.getImage());
		dj2.setRectangle(dt2.getCroppedImageBounds());
	}

}
