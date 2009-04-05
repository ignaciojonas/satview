package edu.pdi2.visual;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.TiledImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import edu.pdi2.constants.SatelliteNamingUtils;
import edu.pdi2.decoders.Decoder;
import edu.pdi2.imaging.readers.BandsManager;
import edu.pdi2.imaging.readers.FileChopReader;
import edu.pdi2.math.indexes.satellite.SatelliteImage;

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
public class CropImage extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1704379686589210233L;
	private DisplayThumbnail djai;
	private TiledImage image;
	private List<String> filesList;
	private Decoder decoder;
	private JButton jButton1;
	private JButton jButton2;
	private PDI pdi;

	/**
	 * Construye un corte de imagen a partir de una lista de los archivos de
	 * banda
	 * 
	 * @param filesList
	 *            Lista con las direcciones de los archivos de banda
	 * 
	 * @deprecated Es necesario pasar el identificador de satélite para brindar
	 *             más flexibilidad al programa. Usar el otro constructor.
	 */
	public CropImage(JFrame frame, List<String> filesList) {
		super(frame);
		init(frame, filesList);
	}

	/**
	 * Construye un corte de imagen a partir de una direccion
	 * 
	 * @param absolutePath
	 *            La dirección de la carpeta que contiene los archivos de la
	 *            Imagen Satelital.
	 * @param satelliteId
	 *            El identificador del satélite de donde proviene la imagen.
	 *            Puede ser uno de los siguientes valores:<br> {@code
	 *            SatelliteNamingUtils.LANDSAT5_ID}<br> {@code
	 *            SatelliteNamingUtils.LANDSAT7_ID}<br> {@code
	 *            SatelliteNamingUtils.SACC_ID}
	 */
	public CropImage(PDI frame, String absolutePath, String satelliteId) {
		super(frame);
		List<String> bandsFiles = new ArrayList<String>(3);

		for (int i = 1; i <= 3; ++i) {
			bandsFiles.add(absolutePath
					+ SatelliteNamingUtils.getBandFilename(i, satelliteId));
		}
		init(frame, bandsFiles);
	}

	private void init(JFrame frame, List<String> filesList) {
		pdi = (PDI) frame;

		FileChopReader fcr = new FileChopReader();

		this.decoder = pdi.getDecoder();

		image = fcr.read(decoder, filesList, decoder.getPPL(),
				decoder.getLPI(), 8, decoder.getPPL() - 8, 8,
				decoder.getLPI() - 8, 25, 25);
		initGUI();
		this.setSize(429, 434);
		this.setVisible(true);
		this.filesList = filesList;
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(429, 434));
			this.setLayout(null);
			{
				djai = new DisplayThumbnail(image, 1f,
						(image.getWidth() * 1024) / 9516,
						(image.getHeight() * 768) / 8616);
				this.add(djai);
				djai.setBounds(0, 0, 400, 350);
				djai.setBorder(BorderFactory.createTitledBorder(""));
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("Ok");
					jButton1.setBounds(171, 369, 99, 21);
					jButton1.setBorder(BorderFactory.createTitledBorder(""));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btOkActionPerformed(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Cancel");
					jButton2.setBounds(301, 369, 99, 21);
					jButton2.setBorder(BorderFactory.createTitledBorder(""));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void btOkActionPerformed(ActionEvent evt) {
		Rectangle b = djai.getViewportBounds();
		FileChopReader fcr = new FileChopReader();
		int x = (b.x * 9516) / image.getWidth();
		if (x < 0)
			x = 0;
		int w = (b.width * 9516) / image.getWidth();
		if (w < 0)
			w = 0;
		int y = (b.y * 8616) / image.getHeight();
		if (y < 0)
			y = 0;
		int h = (b.height * 8616) / image.getHeight();
		if (h < 0)
			h = 0;
		BandsManager bandsManager = pdi.getBandsManager();
		SatelliteImage si = bandsManager.getRawImage(filesList, x, x + w, y, y
				+ h);
		// SatelliteImage si = fcr.read(filesList, 9516, 8616, x, x + w, y, y +
		// h);
		pdi.setUpperLeftX(x);
		pdi.setPixelsWidth(w);
		pdi.setUpperLeftY(y);
		pdi.setPixelsHeight(h);
		pdi.setSi(si);
		pdi.setSelectedBands(filesList);
		this.dispose();
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
