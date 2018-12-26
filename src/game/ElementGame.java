package game;

import geom.Point3D;

public interface ElementGame {

	/**
	 * @return id of element
	 */
	public int getId();
	/**
	 * @return location of element
	 */
	public Point3D getPoint();
	/**
	 * @return the attributes of element
	 */
	public int[] getAttribute();
	
}
