package edu.pdi2.visual.extradialogs;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class PositionDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2122848969086647677L;
	private JPanel jPanel1;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private PDI pdi;

	
	public PositionDialog(PDI frame) {
		super(frame);
		this.pdi = frame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});

				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBorder(BorderFactory.createTitledBorder(""));
					jPanel1.setLayout(null);
					jPanel1.setBounds(7, 7, 200, 46);
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setBounds(5, 25, 198, 14);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setBounds(5, 5, 198, 14);
					}
				}
			}
			this.setSize(227, 91);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLat(double lat){
		jLabel2.setText("Lat: " + ToDegrees(lat));
	}
	public void setLon(double lon){
		jLabel1.setText("Lon: " + ToDegrees(lon));
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
	
	private void thisWindowClosing(WindowEvent evt) {
		pdi.unselectPositionMenuItem();
	}
}
