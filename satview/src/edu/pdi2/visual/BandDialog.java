package edu.pdi2.visual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;


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
public class BandDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3722465768602809902L;
	private JList jList1;
	private JButton btUp;
	private JButton btCancel;
	private JButton btGenerate;
	private JButton btDown;
	private String[] bands;
	private PDI pdi;

	
	
	public BandDialog(JFrame frame, int[] bands) {
		super(frame);
		pdi=(PDI) frame;
		this.bands=new String[bands.length];
		for (int i = 0; i < bands.length; i++) {
			this.bands[i]="Band "+Integer.toString(bands[i]);
		}
		initGUI();
		this.setTitle("Select Bands");
		this.setVisible(true);
		
		
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setModal(true);
				this.setResizable(false);
				{
					ListModel jList1Model = 
						new DefaultComboBoxModel(
								bands);
					jList1 = new JList();
					getContentPane().add(jList1);
					jList1.setModel(jList1Model);
					jList1.setBounds(12, 16, 170, 208);
					jList1.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					btUp = new JButton();
					getContentPane().add(btUp);
					btUp.setText("Up");
					btUp.setBounds(188, 16, 67, 21);
					btUp.setBorder(BorderFactory.createTitledBorder(""));
					btUp.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btUpMouseClicked(evt);
						}
					});
				}
				{
					btDown = new JButton();
					getContentPane().add(btDown);
					btDown.setText("Down");
					btDown.setBounds(188, 48, 67, 21);
					btDown.setBorder(BorderFactory.createTitledBorder(""));
					btDown.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btDownMouseClicked(evt);
						}
					});
				}
				{
					btGenerate = new JButton();
					getContentPane().add(btGenerate);
					btGenerate.setText("Generate");
					btGenerate.setBounds(52, 237, 85, 21);
					btGenerate.setBorder(BorderFactory.createTitledBorder(""));
					btGenerate.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btGenerateMouseClicked(evt);
						}
					});
				}
				{
					btCancel = new JButton();
					getContentPane().add(btCancel);
					btCancel.setText("Cancel");
					btCancel.setBounds(142, 237, 80, 21);
					btCancel.setBorder(BorderFactory.createTitledBorder(""));
					btCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btCancelActionPerformed(evt);
						}
					});
				}
			}
			this.setSize(275, 314);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btUpMouseClicked(MouseEvent evt) {
		int selected=jList1.getSelectedIndex();
		if(selected>0){
			String a=bands[selected];
			String b=bands[selected-1];
			bands[selected]=b;
			bands[selected-1]=a;
			ListModel jList1Model = 
				new DefaultComboBoxModel(
						bands);
			jList1.setModel(jList1Model);
			jList1.setSelectedIndex(selected-1);
		}
		
	}
	
	private void btDownMouseClicked(MouseEvent evt) {
		int selected=jList1.getSelectedIndex();
		if(selected<bands.length-1){
			String a=bands[selected];
			String b=bands[selected+1];
			bands[selected]=b;
			bands[selected+1]=a;
			ListModel jList1Model = 
				new DefaultComboBoxModel(
						bands);
			jList1.setModel(jList1Model);
			jList1.setSelectedIndex(selected+1);
		}
		
	}
	
	private void btGenerateMouseClicked(MouseEvent evt) {
		int[] bandsNumbers = new int[bands.length];
		for (int i=0; i<bands.length; ++i){
			bandsNumbers[i] = Integer.valueOf(bands[i].substring(bands[i].length() - 1));
		}
		pdi.changeBands(bandsNumbers);
		this.dispose();
	}
	
	private void btCancelActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
