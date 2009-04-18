package edu.pdi2.visual.extradialogs;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

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
public class SignatureDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2323461838981889233L;
	private ChartPanel chartpanel;
	private JFreeChart signatureG;
	private PDI pdi;
	
	
	public SignatureDialog(PDI frame, JFreeChart signatureG) {
		super(frame);
		this.pdi = frame;
		this.signatureG=signatureG;
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
					chartpanel = new ChartPanel(signatureG);
					getContentPane().add(chartpanel);
					chartpanel.setBorder(BorderFactory.createTitledBorder(""));
					chartpanel.setBounds(6, 5, 386, 211);
				}
			}
			this.setSize(410, 254);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void thisWindowClosing(WindowEvent evt) {
		pdi.unselectSignatureMenuItem();
	}

}
