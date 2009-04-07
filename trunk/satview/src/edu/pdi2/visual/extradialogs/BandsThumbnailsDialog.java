package edu.pdi2.visual.extradialogs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.renderable.ParameterBlock;
import java.util.List;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JLabel;

import edu.pdi2.math.indexes.satellite.SatelliteImage;
import edu.pdi2.visual.DisplayJAIWithAnnotations;
import edu.pdi2.visual.PDI;

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
public class BandsThumbnailsDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2632089927784585072L;

//	private JLabel[] labels;
//	private DisplayJAIWithAnnotations[] thumbNails;
	private JLabel jLabel3;
	private DisplayJAIWithAnnotations tn3;
	private DisplayJAIWithAnnotations tn2;
	private JLabel jLabel2;
	private DisplayJAIWithAnnotations tn1;
	private JLabel jLabel1;

	private PDI pdi;

	public BandsThumbnailsDialog(PDI frame) {
		super(frame);
		this.pdi = frame;
//		int[] bands = {0};
		initGUI();
	}
	
	private void initGUI() {
//		int cantBands = bands.length;
		try {
			{
				
				getContentPane().setLayout(null);
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Band1");
					jLabel1.setBounds(12, 5, 69, 15);
				}
				{
					tn1 = new DisplayJAIWithAnnotations();
					tn1.setBounds(12, 28, 150, 150);
					getContentPane().add(tn1);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Band1");
					jLabel2.setBounds(12, 183, 69, 15);
				}
				{
					tn2 = new DisplayJAIWithAnnotations();
					tn2.setBounds(12, 210, 150, 150);
					getContentPane().add(tn2);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Band1");
					jLabel3.setBounds(12, 366, 69, 15);
				}
				{
					tn3 = new DisplayJAIWithAnnotations();
					tn3.setBounds(12, 393, 150, 150);
					getContentPane().add(tn3);
				}
//				labels = new JLabel[cantBands];
//				thumbNails = new DisplayJAIWithAnnotations[cantBands];
//				
//				for (int i=0; i<cantBands; ++i){
//					labels[i] = new JLabel();
//					getContentPane().add(labels[i]);
//					labels[i].setText("Band " + bands[i]);
//					labels[i].setBounds(12, i * 40 + 5, 37, 15);
//					
//					thumbNails[i] = new DisplayJAIWithAnnotations();
//					thumbNails[i].setBounds(12, i * 42 + 25, 160, 50);
//					getContentPane().add(thumbNails[i]);
//				}
			}
			this.setSize(180, 577);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setImages(List<SatelliteImage> imgsList) {
//		int[] bands = new int[imgsList.size()];
//		for (int i=0; i<imgsList.size(); ++i){
//			bands[i] = imgsList.get(i).getBands()[0];//se supone q las imagenes son de 1 sola banda
//		}
//		initGUI(bands);
		
		//ahora está creado el vector de thumbnails, tengo que hacer los thumbnails
		//y mostrarlos
//		for (int i=0; i<imgsList.size(); ++i){
//			thumbNails[i].set(getThumnail(imgsList.get(i)));
//		}
		
		jLabel1.setText("Band " + imgsList.get(0).getBands()[0]);
		tn1.set(getThumnail(imgsList.get(0)));
		
		jLabel2.setText("Band " + imgsList.get(1).getBands()[0]);
		tn2.set(getThumnail(imgsList.get(1)));
		
		jLabel3.setText("Band " + imgsList.get(2).getBands()[0]);
		tn3.set(getThumnail(imgsList.get(2)));
		
		
	}
	
	private PlanarImage getThumnail(PlanarImage pi) {
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
	
	private void thisWindowClosing(WindowEvent evt) {
		pdi.unselectThumbnailsMenuItem();
	}

}
