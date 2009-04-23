package edu.pdi2.visual;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
public class ManyTabsDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7984971832261741998L;
	private JPanel thumPanel;
	
//	private JPanel jPanel7;
//	private JPanel jPanel6;
//	private JPanel jPanel4;	
//	private JPanel jPanel3;
	
	private JPanel[] tabs;
	
	private JTabbedPane jTabbedPane1;
	
	
	private JPanel image;
	private static int dWidth = 590;
	private static int dHeight = 490;
	private int lastx=0, lasty=0;
	private List<String> titles;
	
	List<SatelliteImage> si = null;
	private int x0, y0;

//	private JPanel thumPanel1;
	
//	private JPanel image1;
	
	private DisplayThumbnail[] dts;
	
	private DisplayJAIWithAnnotations[] djs;
	
	public ManyTabsDialog(JFrame frame,int x0, int y0, List<SatelliteImage> si, List<String> titles,String titulo_ventana) {
		super(frame);
		djs = new DisplayJAIWithAnnotations[si.size()];
		dts = new DisplayThumbnail[si.size()];
		tabs = new JPanel[si.size()];
		
		this.titles=titles;
		initGUI();
		this.x0=x0;
		this.y0=y0;
		setSi(si);
		this.setTitle(titulo_ventana);
		this.setVisible(true);
		PDI.td.setDj(djs[0]);
		PDI.td.setSI(si.get(0));
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				this.setResizable(false);
				getContentPane().add(this.getJTabbedPane1());
				
			}
			this.setSize(600, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	private void thumPanelMouseDragged(MouseEvent evt) {
//		//DisplayThumbnail src = (DisplayThumbnail) evt.getSource();
//		for (int i=0; i<djs.length; ++i){
//			//dts[i].updateLocation(src.getLastX(), src.getLastY());
//			//djs[i].set(dts[i].getImage());
//			//djs[i].setRectangle(dts[i].getCroppedImageBounds());
//		
//		}
//		
//
//		
//	}
//	private void thumPanelMousePressed(MouseEvent evt) {
//		thumPanelMouseDragged(evt);		
//	}

	public void setSi(List<SatelliteImage> si) {
		this.si = si;
		PDI.td.setDj(djs[0]);
		PDI.td.setSI(si.get(0));
//		for (int i=0; i<djs.length; ++i){
//			//dts[i].set(si.get(i), dWidth, dHeight);
//			//djs[i].set(dts[i].getImage());
//			//djs[i].setRectangle(getCrop());
//			
//		}
		
		repaint();
	}
//	public Rectangle getCrop() {
//		//Rectangle crop = dts[0].getCroppedImageBounds();
//		return new Rectangle(x0 + crop.x, y0 + crop.y, crop.width, crop.height);
//	}
	
	private JTabbedPane getJTabbedPane1() {
		if(jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(0, 0, 600, 555);
			jTabbedPane1.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					actualizarViewPort(e);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					actualizarViewPort(e);
				}
			});
			jTabbedPane1.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					thisFocusGained(evt);
				}

				private void thisFocusGained(FocusEvent evt) {
					for (int i=0; i<tabs.length; ++i){
						if(tabs[i].isShowing()){
							PDI.td.setDj(djs[i]);
							PDI.td.setSI(si.get(i));
						}
					}
					
				}
			});
			for (int i=0; i<tabs.length; ++i){
				jTabbedPane1.addTab(titles.get(i), null, getTab(i), null);
			}

		}
		return jTabbedPane1;
	}
	
	private JPanel getTab(int numTab){
		if(tabs[numTab] == null) {
			tabs[numTab] = new JPanel();
			tabs[numTab].setLayout(null);
			
			
//			{
//				thumPanel = new JPanel();
//				tabs[numTab].add(thumPanel);
//				thumPanel.setBounds(599, 4, 166, 123);
//				{
//					dts[numTab] = new DisplayThumbnail(0.1f);
//					thumPanel.add(dts[numTab]);
//					dts[numTab].addMouseMotionListener(new MouseMotionAdapter() {
//						public void mouseDragged(MouseEvent evt) {
//							thumPanelMouseDragged(evt);
//						}
//					});
//					dts[numTab].addMouseListener(new MouseAdapter() {
//						public void mousePressed(MouseEvent evt) {
//							thumPanelMousePressed(evt);
//						}
//					});
//				}
//			}
			{
				image = new JPanel();
				tabs[numTab].add(image);
				getContentPane().add(getJTabbedPane1());
				image.setBounds(-1, 4, 590, 488);
				image.setBorder(BorderFactory.createTitledBorder(""));
				{
					djs[numTab] = new DisplayJAIWithAnnotations();
					image.add(djs[numTab]);
				}
			}
		}
		return tabs[numTab];
	}

	protected void actualizarViewPort(MouseEvent e) {
		for (int i=0; i<tabs.length; ++i){
			if(tabs[i].isShowing()){
				PDI.td.setDj(djs[i]);
				PDI.td.setSI(si.get(i));
			}
		}
		//System.out.println("Lalalalalalala");
		
	}


}
