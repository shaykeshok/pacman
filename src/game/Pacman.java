package game;

import java.util.ArrayList;
import java.util.List;

import geom.Point3D;

public class Pacman implements ElementGame {
	private int id;
	private Point3D mikum;
	private int speed;
	private int radius;
	private Object orientation;
	private final static String ICON="icons\\pacman.png";
	private double color,meterSum=0;
	private List<Point3D[]> pathPacman;
	
	
	/**
	 * Create Packman element 
	 * @param _id of packman
	 * @param _startPoint the start location of the packman
	 * @param _speed of packman in meter per second
	 * @param _radius of the eating
	 * @param _orientation of the packman
	 */
	public Pacman(int _id,Point3D _startPoint,int _speed,int _radius,Object _orientation) {
		id=_id;
		mikum=_startPoint;
		speed=_speed;
		radius=_radius;
		orientation=_orientation;
		pathPacman=new ArrayList<Point3D[]>();
	}
	
	/*****************************Getters****************************/
	
	/**
	 * @return the current location of packman
	 */
	public Point3D getPoint() {
		return mikum;
	}
	
	/**
	 * @return th id of packman
	 */
	public int getId() {
		return id;
	}
	
	
	@Override
	public int[] getAttribute() {
		int[] attribute= {speed,radius};
		return attribute;
	}
	
	/**
	 * @return the color of packman
	 */
	public double getColor() {
		return color;
	}
	
	/**
	 * @return path of packman
	 */
	public List<Point3D[]> getPath() {
		return pathPacman;
	}
	/**
	 * @return the sum of meters
	 */
	public double getmeterSum() {
		return meterSum;
	}
	
	/***************************Setters******************************/
	/**
	 * This method set the value of packman speed
	 * @param newSpeed the new speed of packman
	 */
	public void setSpeed(int newSpeed) {
		speed=newSpeed;
	}
	
	/**
	 * This method set the value of packman radius eating
	 * @param newRadius the new radius eating of packman
	 */
	public void setRadius(int newRadius) {
		radius=newRadius;
	}
	
	/**
	 * This method set value of packman location
	 * @param point new location
	 */
	public void setPoint(Point3D point) {
		mikum=point;
	}
	
	/**
	 * This method set sm of meters to packman
	 */
	public void setMeterSum(double meters) {
		meterSum+=meters;
	}
	
	
	@Override
	public String toString() {
		return "p,"+id + "," + mikum.x()+","+mikum.y()+","+mikum.z() + "," + speed + "," + radius;
	}
	
	/**
	 * @return String of packman values
	 */
	public String stringToFile() {
		return "p,"+id + "," + mikum.y()+","+mikum.x()+","+mikum.z() + "," + speed + "," + radius;
	}

	/**
	 * This method add path to packman
	 */
	public void addPath(Point3D[] points) {		
		pathPacman.add(points);
	}


	

}
