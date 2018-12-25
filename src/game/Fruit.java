package game;

import geom.Point3D;

public class Fruit implements ElementGame{
	private int id;
	private Point3D mikum;
	private int weight;
	private final static String ICON="icons\\pineapple.png";
	
	
	/**
	 * Create Fruit object
	 * @param _id of fruit
	 * @param _startPoint is the location of fruit
	 * @param _weight value of the fruit
	 */
	public Fruit(int _id,Point3D _startPoint,int _weight) {
		id=_id;
		mikum=_startPoint;		
		weight=_weight;
	}
	
	/**************************getters******************************/
	
	/**
	 * @return the location Fruit
	 */
	public Point3D getPoint() {
		return mikum;
	}
	/**
	 * @return the id Fruit
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the attribute Fruit
	 */
	@Override
	public int[] getAttribute() {
		int[] attribute={weight};
		return attribute;
	}
	
	/*************************setters*********************************/
	
	
	public String toString() {
		return "f,"+id + "," + mikum.x()+","+mikum.y()+","+mikum.z() + "," + weight;
	}
	/**
	 * @return string data of Fruit
	 */
	public String stringToFile() {
		return "f,"+id + "," + mikum.y()+","+mikum.x()+","+mikum.z() + "," + weight;
	}
}
