package edu.pdi2.visual.dialogs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import edu.pdi2.forms.GeographicPoint;
import edu.pdi2.shape.writers.DataShape;
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
public class ShapeExportDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 459565313135039532L;
	private JPanel jPanelFile;
	private JTextField jTextFile;
	private JButton btOk;
	private JButton btCancel;
	private JButton jButtonExamine;
	private PDI pdi;

	public ShapeExportDialog(PDI frame) {
		super(frame);
		this.pdi = frame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("Export to shape file");
				{
					jPanelFile = new JPanel();
					AnchorLayout jPanelFileLayout = new AnchorLayout();
					jPanelFile.setLayout(jPanelFileLayout);
					getContentPane().add(jPanelFile);
					jPanelFile.setPreferredSize(new java.awt.Dimension(394, 46));
					jPanelFile.setBounds(10, 0, 394, 46);
					{
						jButtonExamine = new JButton();
						jPanelFile.add(jButtonExamine, new AnchorConstraint(12, 22, 127, 878, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
						jButtonExamine.setText("...");
						jButtonExamine.setPreferredSize(new java.awt.Dimension(26, 22));
						jButtonExamine.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jButtonExamineActionPerformed(evt);
							}
						});
					}
					{
						jTextFile = new JTextField();
						jPanelFile.add(jTextFile, new AnchorConstraint(12, 60, 127, 12, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
						jTextFile.setPreferredSize(new java.awt.Dimension(318, 22));
					}
				}
				{
					btCancel = new JButton();
					getContentPane().add(btCancel);
					btCancel.setText("Cancelar");
					btCancel.setBounds(327, 54, 64, 22);
					btCancel.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							dispose();
						}
					});
				}
				{
					btOk = new JButton();
					getContentPane().add(btOk);
					btOk.setText("Exportar");
					btOk.setBounds(252, 54, 64, 22);
					btOk.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btOkMouseClicked(evt);
						}
					});
				}
			}
			this.setSize(404, 111);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButtonExamineActionPerformed(ActionEvent evt) {
		System.out.println("jButtonExamine.actionPerformed, event="+evt);
		
		JFileChooser fChooser = new JFileChooser();
		fChooser.setVisible(true);
		File sel = fChooser.getSelectedFile();
		if (sel != null){
			jTextFile.setText(sel.getAbsolutePath());
			
			
		}
	}
	
	private void btOkMouseClicked(MouseEvent evt) {
		DataShape ds = new DataShape();
		List<GeographicPoint> points = pdi.getPoints();
		ds.writeWaypoints(points, jTextFile.getText());
	}

}
