package edu.pdi2.visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.filechooser.FileFilter;

import edu.pdi2.forms.GeographicPoint;
import edu.pdi2.forms.Polygon;
import edu.pdi2.xml.Decoder;
import edu.pdi2.xml.Encoder;

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
public class CreateMesh extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2797708846455598702L;
	private JList jListForms;
	private JLabel jLabel1;
	private PDI pdi;
	private JButton jButton2;
	private JButton jButton1;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	private JMenu jMenu2;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private SaveTriangle st;
	private SavePolygon sp;

	public CreateMesh(JFrame frame) {
		super(frame);
		pdi = (PDI) frame;
		initGUI();
		this.setVisible(true);
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
				this.setTitle("Create Mesh");
				{
					jMenuBar1 = new JMenuBar();
					setJMenuBar(jMenuBar1);
					jMenuBar1.add(getJMenu1());
					jMenuBar1.add(getJMenu2());
				}
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});
				{
					ListModel jListFormsModel = new DefaultComboBoxModel(
							generateList());
					jListForms = new JList();
					getContentPane().add(jListForms);
					jListForms.setModel(jListFormsModel);
					jListForms.setBounds(12, 22, 566, 201);
					jListForms.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					getContentPane().add(getJButton1());
					getContentPane().add(getJButton2());
					jLabel1.setText("Mesh:");
					jLabel1.setBounds(12, 5, 50, 14);
				}
			}
			this.setSize(608, 343);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPoint(int i, int j) {
		if (st != null) {
			st.addPoint(i, j);
		}
		if (sp != null) {
			sp.addPoint(i, j);
		}

	}

	public void setNullST() {
		st = null;
		refreshList();
	}

	public void setNullSP() {
		sp = null;
		refreshList();
	}

	private void thisWindowClosing(WindowEvent evt) {
		pdi.setNullCM();
	}

	@SuppressWarnings("unchecked")
	private String[] generateList() {
		Vector<Polygon> mesh = pdi.getMesh();
		String[] list = new String[mesh.size()];
		Polygon p;
		Vector<GeographicPoint> gPoints;
		for (int i = 0; i < mesh.size(); i++) {
			p = mesh.get(i);
			gPoints = p.getGPoints();
			String puntos = "";
			for (Iterator<GeographicPoint> iterator = gPoints.iterator(); iterator
					.hasNext();) {
				GeographicPoint point = iterator.next();
				puntos += "(" + point.getP().getX() + "," + point.getP().getY()
						+ ")";
			}
			list[i] = "Polygon " + puntos;
		}
		return list;
	}

	public void refreshList() {
		ListModel jListFormsModel = new DefaultComboBoxModel(generateList());
		jListForms.setModel(jListFormsModel);
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("File");
			jMenu1.add(getJMenuItem1());
			jMenu1.add(getJMenuItem2());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Save Mesh");
			jMenuItem1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuItem1ActionPerformed(evt);
				}
			});
		}
		return jMenuItem1;
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Load Mesh");
			jMenuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuItem2ActionPerformed(evt);
				}
			});
		}
		return jMenuItem2;
	}

	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("Add");
			jMenu2.add(getJMenuItem3());
			jMenu2.add(getJMenuItem4());
		}
		return jMenu2;
	}

	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Triangle");
			jMenuItem3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuItem3ActionPerformed(evt);
				}
			});
		}
		return jMenuItem3;
	}

	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("Quadrilateral");
			jMenuItem4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jMenuItem4ActionPerformed(evt);
				}
			});
		}
		return jMenuItem4;
	}

	private void jMenuItem2ActionPerformed(ActionEvent evt) {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Load Mesh");
		jfc.showOpenDialog(this);
		File file = jfc.getSelectedFile();
		if (file != null) {
			pdi.setMesh(Decoder.decode(file.getAbsolutePath()));
			refreshList();
		}
	}

	private void jMenuItem3ActionPerformed(ActionEvent evt) {
		st = new SaveTriangle(pdi, this);
	}

	private void jMenuItem4ActionPerformed(ActionEvent evt) {
		sp = new SavePolygon(pdi, this);
	}

	private void jMenuItem1ActionPerformed(ActionEvent evt) {
		// String
		// file=JOptionPane.showInputDialog(this,"File Name: ","Save Mesh..."
		// ,JOptionPane.PLAIN_MESSAGE);
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File f) {
				try {
					return f.getCanonicalPath().endsWith(".xml");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "XML files";
			}

		};
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			File file = chooser.getSelectedFile();
			// if(file!=null)
			if (!file.equals(""))
				Encoder.encode(pdi.getMesh(), file.getAbsolutePath());
			else
				JOptionPane.showMessageDialog(this, "Invalid name", "Warning",
						JOptionPane.WARNING_MESSAGE);
		}
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Ok");
			jButton1.setBounds(396, 245, 80, 21);
			jButton1.setBorder(BorderFactory.createTitledBorder(""));
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton1ActionPerformed(evt);
				}
			});
		}
		return jButton1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Cancel");
			jButton2.setBorder(BorderFactory.createTitledBorder(""));
			jButton2.setBounds(495, 245, 80, 21);
			jButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton2ActionPerformed(evt);
				}
			});
		}
		return jButton2;
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
