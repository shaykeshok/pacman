package game;

import geom.Point3D;

public class Fruit implements ElementGame{
	private int id;
	private Point3D mikum;
	private int weight;
	private final static String ICON="icons\\pineapple.png";
	
	public Fruit(int _id,Point3D _startPoint,int _weight) {
		id=_id;
		mikum=_startPoint;		
		weight=_weight;
	}
	
	/**************************getters******************************/
	public Point3D getPoint() {
		return mikum;
	}
	public int getId() {
		return id;
	}
	@Override
	public int[] getAttribute() {
		int[] attribute={weight};
		return attribute;
	}
	
	/*************************setters*********************************/
	
	public String toString() {
		return "f,"+id + "," + mikum.x()+","+mikum.y()+","+mikum.z() + "," + weight;
	}
	public String stringToFile() {
		return "f,"+id + "," + mikum.y()+","+mikum.x()+","+mikum.z() + "," + weight;
	}
}
