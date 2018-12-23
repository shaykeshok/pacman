package game;

import geom.Point3D;

public class Pacman implements ElementGame {
	private int id;
	private Point3D mikum;
	private int speed;
	private int radius;
	private Object orientation;
	private final static String ICON="icons\\pacman.png";
	private double color;
	
	public Pacman(int _id,Point3D _startPoint,int _speed,int _radius,Object _orientation) {
		id=_id;
		mikum=_startPoint;
		speed=_speed;
		radius=_radius;
		orientation=_orientation;
	}
	
	/*****************************Getters****************************/
	public Point3D getPoint() {
		return mikum;
	}
	public int getId() {
		return id;
	}
	@Override
	public int[] getAttribute() {
		int[] attribute= {speed,radius};
		return attribute;
	}
	public double getColor() {
		return 0;
	}
	
	/***************************Setters******************************/
	public void setSpeed(int newSpeed) {
		speed=newSpeed;
	}
	public void setRadius(int newRadius) {
		radius=newRadius;
	}
	public void setPoint(Point3D point) {
		mikum=point;
	}
	@Override
	public String toString() {
		return "p,"+id + "," + mikum.x()+","+mikum.y()+","+mikum.z() + "," + speed + "," + radius;
	}

	

}
