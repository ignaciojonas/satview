package edu.pdi2.visual.extradialogs;
import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.visual.DisplayJAIWithAnnotations;
import edu.pdi2.visual.DisplayThumbnail;
import edu.pdi2.visual.PDI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


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
public class ThumnailDialog extends javax.swing.JDialog {
	private JPanel thumPanel;
	public DisplayThumbnail dt;
	private DisplayJAIWithAnnotations dj;
	private int upperLeftX, pixelsWidth, upperLeftY, pixelsHeight;
	private int lastX, lastY;
	public ThumnailDialog(JFrame frame) {
		super(frame);
		initGUI();
		lastX=0;
		lastY=0;
	}
	
	public void setDj(DisplayJAIWithAnnotations dj) {
		this.dj = dj;
	}

	public void setUpperLeftX(int upperLeftX) {
		this.upperLeftX = upperLeftX;
	}

	public void setPixelsWidth(int pixelsWidth) {
		this.pixelsWidth = pixelsWidth;
	}

	public void setUpperLeftY(int upperLeftY) {
		this.upperLeftY = upperLeftY;
	}

	public void setPixelsHeight(int pixelsHeight) {
		this.pixelsHeight = pixelsHeight;
	}



	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
				{
					thumPanel = new JPanel();
					getContentPane().add(thumPanel);
					thumPanel.setBounds(0, 0, 160, 120);
					{
						dt = new DisplayThumbnail(0.1f);
						dt.setPreferredSize(new Dimension(150, 100));
						dt.setMinimumSize(new Dimension(150, 100));
						dt.setMaximumSize(new Dimension(150, 100));
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
			}
			this.setSize(180, 140);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void thumPanelMouseDragged(MouseEvent evt) {
		DisplayThumbnail src = (DisplayThumbnail) evt.getSource();
		lastX=src.getLastX();
		lastY=src.getLastY();
		dj.set(dt.getImage());
		Rectangle r = this.getCrop();
		if (dt.inViewport() == 0)
			dj.setRectangle(r);
		else {
			if (dt.inViewport() == 1) {
				r.x = upperLeftX + pixelsWidth - PDI.dWidth;
				dj.setRectangle(r);
			} else {
				r.y = upperLeftY + pixelsHeight - PDI.dHeight;
				dj.setRectangle(r);
			}
		}
		
	}
	private void thumPanelMousePressed(MouseEvent evt) {
		DisplayThumbnail src = (DisplayThumbnail) evt.getSource();
		lastX=src.getLastX();
		lastY=src.getLastY();
		dj.set(dt.getImage());
		Rectangle r = this.getCrop();
		if (dt.inViewport() == 0)
			dj.setRectangle(r);
		else {
			if (dt.inViewport() == 1) {
				r.x = upperLeftX + pixelsWidth - PDI.dWidth;
				dj.setRectangle(r);
			} else {
				r.y = upperLeftY + pixelsHeight - PDI.dHeight;
				dj.setRectangle(r);
			}
		}
	}
	public Rectangle getCrop() {
		Rectangle crop = dt.getCroppedImageBounds();
		return new Rectangle(upperLeftX + crop.x, upperLeftY + crop.y,
				crop.width, crop.height);
	}
	public void setSI(SatelliteImage si){
		dt.set(si, PDI.dWidth, PDI.dHeight);
		dt.updateLocation(lastX, lastY);
		System.out.println(lastX);
		System.out.println(lastY);
		dj.set(dt.getImage());
		dj.setRectangle(getCrop());
		
		repaint();
	}

}
