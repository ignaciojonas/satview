package edu.pdi2.visual;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class CopyOfCorrectedDialog extends javax.swing.JDialog {
	private JPanel thumPanel;
	private DisplayThumbnail dt;
	private DisplayJAIWithAnnotations dj;
	private JPanel image;
	private static int dWidth = 590;
	private static int dHeight = 490;
	private SatelliteImage si = null;
	private int x0, y0;
	
	
	public CopyOfCorrectedDialog(JFrame frame,int x0, int yo, SatelliteImage si, String title) {
		super(frame);
		initGUI();
		this.x0=x0;
		this.y0=y0;
		setSi(si);
		this.setTitle(title);
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				this.setResizable(false);
				{
					thumPanel = new JPanel();
					getContentPane().add(thumPanel);
					thumPanel.setBounds(601, 27, 166, 123);
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
					getContentPane().add(image);
					image.setBounds(1, 27, 590, 488);
					image.setBorder(BorderFactory.createTitledBorder(""));
					{
						dj = new DisplayJAIWithAnnotations();
						image.add(dj);
					}
				}
			}
			this.setSize(794, 555);
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

	public void setSi(SatelliteImage si) {
		this.si = si;
		dt.set(si, dWidth, dHeight);
		dj.set(dt.getImage());
		dj.setRectangle(getCrop());
		repaint();
	}
	public Rectangle getCrop() {
		Rectangle crop = dt.getCroppedImageBounds();
		return new Rectangle(x0 + crop.x, y0 + crop.y, crop.width, crop.height);
	}

}
