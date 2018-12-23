package coords;

import java.util.ArrayList;
import java.util.List;

import geom.Point3D;

public class Path {
	private Point3D startPoint;
	private Point3D endPoint;
	private List<Point3D> path;
	private double len;
	
	public Path(Point3D _startPoint,Point3D _endPoint) {
		startPoint=_startPoint;
		endPoint=_endPoint;
		path=new ArrayList<Point3D>();
		len=0;
	}
	
	public double getLen() {
		return startPoint.distance3D(endPoint);
	}

	//צריך לבדוק אם נקודה מתחת לנקודה בקו ישר יש את אותה זוית בGOOGLE EARTH
	public List<Point3D> createPath() {
		return null;
	}
	

}
