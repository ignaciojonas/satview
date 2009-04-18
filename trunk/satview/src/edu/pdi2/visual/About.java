package edu.pdi2.visual;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
public class About extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3357903995563447375L;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;


	public About(JFrame frame) {
		super(frame);
		initGUI();
		this.setLocationRelativeTo(frame);
		this.setTitle("About...");
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(522, 300));
			this.setSize(new java.awt.Dimension(522, 300));
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("Procesamiento Digital de Imagenes II");
				jLabel1.setFont(new java.awt.Font("Arial",1,28));
				jLabel1.setBounds(10, 12, 500, 33);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("2008");
				jLabel2.setFont(new java.awt.Font("Arial",1,28));
				jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
				jLabel2.setBounds(10, 57, 500, 33);
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setIcon(new ImageIcon("images"+File.separator+"escudo-universidad.gif"));
				jLabel3.setBounds(337, 131, 155, 138);
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4);
				jLabel4.setText("Jeanne,Federico <fedejeanne@gmail.com>");
				jLabel4.setBounds(12, 96, 281, 29);
				jLabel4.setFont(new java.awt.Font("Arial",0,14));
				jLabel4.setBounds(6, 186, 281, 29);
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5);
				jLabel5.setText("Jonas, Ignacio <ignaciojonas@gmail.com>");
				jLabel5.setFont(new java.awt.Font("Arial",0,14));
				jLabel5.setBounds(6, 212, 281, 29);
			}
			{
				jLabel6 = new JLabel();
				this.add(jLabel6);
				jLabel6.setText("Alumnos:");
				jLabel6.setBounds(12, 161, 75, 14);
				jLabel6.setFont(new java.awt.Font("Arial",1,14));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
