package edu.pdi2.visual;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import com.sun.media.jai.widget.DisplayJAI;

import edu.pdi2.imaging.readers.BandsManager;
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
public class BandDialog extends javax.swing.JDialog {
	private JList jList1;
	private JButton jButton1;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
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
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("Up");
					jButton1.setBounds(188, 16, 67, 21);
					jButton1.setBorder(BorderFactory.createTitledBorder(""));
					jButton1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jButton1MouseClicked(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Down");
					jButton2.setBounds(188, 48, 67, 21);
					jButton2.setBorder(BorderFactory.createTitledBorder(""));
					jButton2.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jButton2MouseClicked(evt);
						}
					});
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("Generate");
					jButton3.setBounds(52, 237, 85, 21);
					jButton3.setBorder(BorderFactory.createTitledBorder(""));
					jButton3.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jButton3MouseClicked(evt);
						}
					});
				}
				{
					jButton4 = new JButton();
					getContentPane().add(jButton4);
					jButton4.setText("Cancel");
					jButton4.setBounds(142, 237, 80, 21);
					jButton4.setBorder(BorderFactory.createTitledBorder(""));
					jButton4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton4ActionPerformed(evt);
						}
					});
				}
			}
			this.setSize(296, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButton1MouseClicked(MouseEvent evt) {
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
	
	private void jButton2MouseClicked(MouseEvent evt) {
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
	
	private void jButton3MouseClicked(MouseEvent evt) {
		List<String> filesList=pdi.getListFiles();
		List<String> selectedBands=new ArrayList<String>();
		selectedBands.add(filesList.get(Integer.parseInt(bands[0].substring(5))-1));
		System.out.println("Banda "+Integer.parseInt(bands[0].substring(5))+"File :"+filesList.get(Integer.parseInt(bands[0].substring(5))-1));
		selectedBands.add(filesList.get(Integer.parseInt(bands[1].substring(5))-1));
		System.out.println("Banda "+Integer.parseInt(bands[1].substring(5))+"File :"+filesList.get(Integer.parseInt(bands[1].substring(5))-1));
		selectedBands.add(filesList.get(Integer.parseInt(bands[2].substring(5))-1));
		System.out.println("Banda "+Integer.parseInt(bands[2].substring(5))+"File :"+filesList.get(Integer.parseInt(bands[2].substring(5))-1));
		pdi.setSelectedBands(selectedBands);
		/* GENERAR LA IMAGEN A FALSO COLOR */
		BandsManager bandsManager = pdi.getBandsManager();
		SatelliteImage si=bandsManager.getRawImage(selectedBands, pdi.getX0(), pdi.getX1()+ pdi.getX0(), pdi.getY0(), pdi.getY1()+ pdi.getY0());
		pdi.setSi(si);
		this.dispose();
	}
	
	private void jButton4ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
