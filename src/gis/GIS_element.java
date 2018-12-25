package gis;

import geom.Geom_element;
import geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Shayke Shok and Omer Edut
 *
 */
public interface GIS_element {
	public Geom_element getGeom();
	public Meta_data getData();
	public void translate(Point3D vec);
}
