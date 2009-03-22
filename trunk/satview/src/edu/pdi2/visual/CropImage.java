package edu.pdi2.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.TiledImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.Bounds;

import com.sun.media.jai.widget.DisplayJAI;

import edu.pdi2.decoders.Decoder;
import edu.pdi2.imaging.readers.FileChopReader;
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
public class CropImage extends javax.swing.JDialog {
	private DisplayThumbnail djai;
	private TiledImage image;
	private List<String> filesList;
	private Decoder decoder;
	private JButton jButton1;
	private JButton jButton2;
	private PDI pdi; 

	public CropImage(JFrame frame,List<String> filesList) {
		super(frame);
		FileChopReader fcr= new FileChopReader();
		pdi=(PDI) frame;
		this.decoder=pdi.getDecoder();
		this.filesList=filesList;
		image=fcr.read(filesList,9516,8616,8,9508,8,8608,25,25);
		initGUI();
		this.setSize(429, 434);
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(429, 434));
			this.setLayout(null);
			{
				djai = new DisplayThumbnail(image,1f,(image.getWidth()*1024)/9516,(image.getHeight()*768)/8616);
				this.add(djai);
				djai.setBounds(0, 0, 400, 350);
				djai.setBorder(BorderFactory.createTitledBorder(""));
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("Ok");
					jButton1.setBounds(171, 369, 99, 21);
					jButton1.setBorder(BorderFactory.createTitledBorder(""));
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
					jButton2.setBounds(301, 369, 99, 21);
					jButton2.setBorder(BorderFactory.createTitledBorder(""));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		Rectangle b= djai.getViewportBounds();		
		FileChopReader fcr= new FileChopReader();
		int x=(b.x*9516)/image.getWidth();
		if(x<0) x=0;
		int w=(b.width*9516)/image.getWidth();
		if(w<0) w=0;
		int y=(b.y*8616)/image.getHeight();
		if(y<0) y=0;
		int h=(b.height*8616)/image.getHeight();
		if(h<0) h=0;
		SatelliteImage si=fcr.read(filesList,9516,8616,x,x+w,y,y+h);
		pdi.setX0(x);
		pdi.setX1(w);
		pdi.setY0(y);
		pdi.setY1(h);
		pdi.setSi(si);
		pdi.setSelectedBands(filesList);
		this.dispose();
	}
	
	private void jButton2ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}

