package edu.pdi2.shape.writers;

import java.util.ArrayList;
import java.util.List;

import com.bbn.openmap.dataAccess.shape.EsriPoint;
import com.bbn.openmap.dataAccess.shape.ShapeConstants;
import com.bbn.openmap.plugin.esri.EsriLayer;

import edu.pdi2.forms.GeographicPoint;
import edu.pdi2.forms.Point;

/**
 * Representa los datos que se van a guardar en un shape y posee la habilidad de
 * guardar estos datos en un archivo. Actualmente sólo soporta shapes de puntos.
 * 
 * @author fede
 * 
 */
public class DataShape {
//	/** Los datos que van a ir en el registro del dbf. La lista tiene que tener la misma
//	 * cantidad de elementos que registros en el archivo.
//	 */
//	private List<Object> dbfRegisters;
//	
//	/**
//	 * El registro por defecto del dbf. Si la lista de registros del dbf está vacía
//	 * entonces se va a poner este registro en todos los registros del dbf.
//	 */
//	private Object defaultDbfRegister;
	
	public void writeWaypoints(List<GeographicPoint> points, String path) {
		EsriLayer layer = null;
		try {
			layer = new EsriLayer("name", ShapeConstants.SHAPE_TYPE_POINT, 0);

//			boolean defaultRegister = dbfRegisters == null;
//			
//			ArrayList<String> tabularData = new ArrayList<String>();
//			tabularData.add(0, "TO DO: la ciudad no fue agregada");
//			tabularData.add(1, "COLUMNA2: nada que mostrar");
			for (int i=0; i<points.size(); ++i){
				GeographicPoint point = points.get(i);
			
				
				layer.addRecord(new EsriPoint(point.getLat().floatValue(),
						point.getLon().floatValue()), null);
			}
			ShapeWriter writer = new ShapeWriter();
			writer.write(layer, path);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
