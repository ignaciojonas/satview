package edu.pdi2.shape.writers;

import java.io.FileOutputStream;
import java.io.IOException;

import com.bbn.openmap.dataAccess.shape.DbfTableModel;
import com.bbn.openmap.dataAccess.shape.EsriGraphicList;
import com.bbn.openmap.dataAccess.shape.output.DbfOutputStream;
import com.bbn.openmap.dataAccess.shape.output.ShpOutputStream;
import com.bbn.openmap.dataAccess.shape.output.ShxOutputStream;
import com.bbn.openmap.plugin.esri.EsriLayer;

public class ShapeWriter {
	public void write(EsriLayer layer,String path) throws IOException {
		DbfTableModel model = layer.getModel();

		// Setup table structure
		// Setup column 0 to be character
		model.setDecimalCount(0, (byte) 0);
		model.setLength(0, (byte) 20);
		model.setColumnName(0, "City");
		model.setType(0, (byte) DbfTableModel.TYPE_CHARACTER);

		// Setup column 1 to be character
		model.setDecimalCount(1, (byte) 3);
		model.setLength(1, (byte) 20);
		model.setColumnName(1, "Columna2");
		model.setType(1, (byte) DbfTableModel.TYPE_CHARACTER);

		//

		EsriGraphicList list = layer.getEsriGraphicList();

		ShpOutputStream pos;

		pos = new ShpOutputStream(new FileOutputStream(path + ".shp"));

		int[][] indexData = pos.writeGeometry(list);

		ShxOutputStream xos = new ShxOutputStream(new FileOutputStream(path
				+ ".shx"));
		xos.writeIndex(indexData, list.getType(), list.getExtents());

		DbfOutputStream dos = new DbfOutputStream(new FileOutputStream(path
				+ ".dbf"));
		dos.writeModel(model);

	}
}
