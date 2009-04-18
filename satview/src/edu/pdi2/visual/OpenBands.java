package edu.pdi2.visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.pdi2.constants.AppConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class OpenBands extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7884212932131066271L;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField jTextField8;
	private JButton jButton8;
	private JLabel jLabel7;
	private JTextField jTextField7;
	private JButton jButton7;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JButton jButton1;
	private JLabel jLabel8;
	private JTextField jTextField5;
	private JTextField jTextField6;
	private JButton jButton10;
	private JButton jButton9;
	private JLabel jLabel6;
	private JButton jButton6;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JFileChooser jFileChooser1;
	private File[] files;
	private PDI pdi;

	public OpenBands(JFrame frame) {
		super(frame);
		pdi = (PDI) frame;
		initGUI();
		jFileChooser1 = new JFileChooser();
		files = new File[8];
		this.setTitle(AppConstants.getString("M2")); //$NON-NLS-1$
		this.setVisible(true);
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Procesamiento Digital de Imagenes");
					jLabel1.setBounds(12, 34, 61, 14);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("File");
					jLabel2.setBounds(12, 64, 61, 14);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("filestore");
					jLabel3.setBounds(12, 94, 61, 14);
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4);
					jLabel4.setText("c.jpg");
					jLabel4.setBounds(11, 124, 61, 14);
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5);
					jLabel5.setText("JPEG");
					jLabel5.setBounds(12, 154, 61, 14);
				}
				{
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1);
					jTextField1.setBounds(63, 31, 280, 21);
					jTextField1.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					jTextField2 = new JTextField();
					getContentPane().add(jTextField2);
					jTextField2.setBounds(63, 61, 280, 21);
					jTextField2.setBorder(BorderFactory
							.createTitledBorder("Lsat: "));
				}
				{
					jTextField3 = new JTextField();
					getContentPane().add(jTextField3);
					jTextField3.setBounds(63, 91, 280, 21);
					jTextField3
							.setBorder(BorderFactory.createTitledBorder(" "));
				}
				{
					jTextField4 = new JTextField();
					getContentPane().add(jTextField4);
					jTextField4.setBounds(63, 121, 280, 21);
					jTextField4.setBorder(BorderFactory
							.createTitledBorder("Lr: "));
				}
				{
					jTextField5 = new JTextField();
					getContentPane().add(jTextField5);
					jTextField5.setBounds(63, 151, 280, 21);
					jTextField5.setBorder(BorderFactory
							.createTitledBorder("bandas: "));
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("l5-bandas-");
					jButton1.setBounds(365, 31, 102, 21);
					jButton1
							.setBorder(BorderFactory.createTitledBorder(".jpg"));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("filestore");
					jButton2.setBounds(365, 61, 102, 21);
					jButton2
							.setBorder(BorderFactory.createTitledBorder("JPEG"));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("Examinar...");
					jButton3.setBounds(365, 91, 102, 21);
					jButton3.setBorder(BorderFactory.createTitledBorder(""));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton3ActionPerformed(evt);
						}
					});
				}
				{
					jButton4 = new JButton();
					getContentPane().add(jButton4);
					jButton4.setText("Examinar...");
					jButton4.setBounds(365, 121, 102, 21);
					jButton4.setBorder(BorderFactory.createTitledBorder(""));
					jButton4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton4ActionPerformed(evt);
						}
					});
				}
				{
					jButton5 = new JButton();
					getContentPane().add(jButton5);
					jButton5.setText("Examinar...");
					jButton5.setBounds(365, 151, 102, 21);
					jButton5.setBorder(BorderFactory.createTitledBorder(""));
					jButton5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton5ActionPerformed(evt);
						}
					});
				}
				{
					jButton7 = new JButton();
					getContentPane().add(jButton7);
					jButton7.setText("Examinar...");
					jButton7.setBounds(365, 181, 102, 21);
					jButton7.setBorder(BorderFactory.createTitledBorder(""));
					jButton7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton7ActionPerformed(evt);
						}
					});
				}
				{
					jTextField7 = new JTextField();
					getContentPane().add(jTextField7);
					jTextField7.setBounds(63, 181, 280, 21);
					jTextField7.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					jLabel7 = new JLabel();
					getContentPane().add(jLabel7);
					jLabel7.setText("Band 6:");
					jLabel7.setBounds(12, 184, 61, 14);
				}
				{
					jButton8 = new JButton();
					getContentPane().add(jButton8);
					jButton8.setText("Examinar...");
					jButton8.setBounds(365, 211, 102, 21);
					jButton8.setBorder(BorderFactory.createTitledBorder(""));
					jButton8.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton8ActionPerformed(evt);
						}
					});
				}
				{
					jTextField8 = new JTextField();
					getContentPane().add(jTextField8);
					jTextField8.setBounds(63, 211, 280, 21);
					jTextField8.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					jLabel8 = new JLabel();
					getContentPane().add(jLabel8);
					jLabel8.setText("Band 7:");
					jLabel8.setBounds(12, 214, 61, 14);
				}
				{
					jButton6 = new JButton();
					getContentPane().add(jButton6);
					jButton6.setText("Examinar...");
					jButton6.setBounds(365, 241, 102, 21);
					jButton6.setBorder(BorderFactory.createTitledBorder(""));
					jButton6.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton6ActionPerformed(evt);
						}
					});
				}
				{
					jTextField6 = new JTextField();
					getContentPane().add(jTextField6);
					jTextField6.setBounds(65, 241, 280, 21);
					jTextField6.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					jLabel6 = new JLabel();
					getContentPane().add(jLabel6);
					jLabel6.setText("Header:");
					jLabel6.setBounds(12, 244, 61, 14);
				}
				{
					jButton9 = new JButton();
					getContentPane().add(jButton9);
					jButton9.setText("Ok");
					jButton9.setBounds(295, 286, 70, 21);
					jButton9.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton9ActionPerformed(evt);
						}
					});
				}
				{
					jButton10 = new JButton();
					getContentPane().add(jButton10);
					jButton10.setText("Cancel");
					jButton10.setBounds(379, 286, 70, 21);
					jButton10.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton10ActionPerformed(evt);
						}
					});
				}
			}
			this.setSize(507, 341);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jButton1ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 1");
		jFileChooser1.showOpenDialog(this);
		files[1] = jFileChooser1.getSelectedFile();
		jTextField1.setText(files[1].getAbsolutePath());
	}

	private void jButton2ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 2");
		jFileChooser1.showOpenDialog(this);
		files[2] = jFileChooser1.getSelectedFile();
		jTextField2.setText(files[2].getAbsolutePath());
	}

	private void jButton3ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 3");
		jFileChooser1.showOpenDialog(this);
		files[3] = jFileChooser1.getSelectedFile();
		jTextField3.setText(files[3].getAbsolutePath());
	}

	private void jButton4ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 4");
		jFileChooser1.showOpenDialog(this);
		files[4] = jFileChooser1.getSelectedFile();
		jTextField4.setText(files[4].getAbsolutePath());
	}

	private void jButton5ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 5");
		jFileChooser1.showOpenDialog(this);
		files[5] = jFileChooser1.getSelectedFile();
		jTextField5.setText(files[5].getAbsolutePath());
	}

	private void jButton6ActionPerformed(ActionEvent evt) {
		jFileChooser1.setDialogTitle("Select Header");
		jFileChooser1.showOpenDialog(this);
		files[0] = jFileChooser1.getSelectedFile();
		jTextField6.setText(files[0].getAbsolutePath());
	}

	public File[] getFiles() {
		return files;
	}

	private void jButton7ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 6");
		jFileChooser1.showOpenDialog(this);
		files[6] = jFileChooser1.getSelectedFile();
		jTextField7.setText(files[6].getAbsolutePath());
	}

	private void jButton8ActionPerformed(ActionEvent evt) {

		jFileChooser1.setDialogTitle("Select Band 7");
		jFileChooser1.showOpenDialog(this);
		files[7] = jFileChooser1.getSelectedFile();
		jTextField8.setText(files[7].getAbsolutePath());
	}

	/**
	 * @deprecated Esto ya no se usa. Se reemplazó por el método
	 *             <i>setDirectory</i> en la clase {@code PDI}
	 */
	private void jButton9ActionPerformed(ActionEvent evt) {
		pdi.setFiles(files);
		List<String> filesList = new ArrayList<String>();
		// filesList.add(files[1].getAbsolutePath());

		// filesList.add(
		// "C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND2.dat"
		// );
		// filesList.add(
		// "C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND3.dat"
		// );
		// filesList.add(
		// "C:\\Documents and Settings\\Administrador\\Mis documentos\\l5\\BAND1.dat"
		// );
		filesList.add(AppConstants.getString("band2")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band3")); //$NON-NLS-1$
		filesList.add(AppConstants.getString("band1")); //$NON-NLS-1$
		new CropImage(pdi, filesList);
		this.dispose();
	}

	private void jButton10ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
