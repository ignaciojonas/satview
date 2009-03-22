package edu.pdi2.visual;

import java.awt.Dimension;
import java.awt.image.RenderedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.media.jai.widget.DisplayJAI;


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
public class ViewSignatureImage extends javax.swing.JDialog {

	private DisplayJAI dj;
	private RenderedImage image;
	
	public ViewSignatureImage(JFrame frame,RenderedImage image) {
		super(frame);
		this.image=image;
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
			}
			dj=new DisplayJAI(image);
			dj.setPreferredSize(new Dimension(654, 429));
			dj.setMinimumSize(new Dimension(654, 429));
			dj.setMaximumSize(new Dimension(654, 429));
			getContentPane().add(dj);
			this.setSize(654, 429);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
