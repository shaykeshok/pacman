package game;

import geom.Point3D;

public class Map {
	public String picPath;
	public Point3D rightCornerUp,leftCornerDown,rightCornerDown,leftCornerUp;
	public static final int WIDTHPIC=1433,HEIGHTPIC=642;
	
	public Map(String _picPath) {
		leftCornerUp = new Point3D( 32.105394,  35.202532, 0);
		rightCornerUp = new Point3D( 32.105444,  35.212496, 0);
		leftCornerDown = new Point3D( 32.101899,  35.202447, 0);
		rightCornerDown = new Point3D( 32.101899,  35.212496, 0);
		picPath=_picPath;
	}
	public Map(Point3D _rightCornerUp,Point3D _leftCornerDown,Point3D _leftCornerUp,Point3D _rightCornerDown,String _picPath) {
		rightCornerUp=_rightCornerUp;
		leftCornerDown=_leftCornerDown;
		leftCornerUp=_leftCornerUp;
		rightCornerDown=_rightCornerDown;
		picPath=_picPath;
	}
	

	//convert pixels point to polar point
	public static Point3D pixel2Polar(Point3D point) {
		double ratioHorizontal=leftCornerUp.distance3D(rightCornerUp);
		double x=point.x()/WIDTHPIC*ratioHorizontal;
		double ratioVertical=leftCornerUp.distance3D(leftCornerDown);
		double y=point.y()/HEIGHTPIC*ratioVertical;
		return new Point3D(x,y,point.z());
	}
	
	//convert polar point to pixels point
	public Point3D polar2Pixel(Point3D point) {
		
		return point;
		
	}
	
	public static void main(String[] args) {
		Point3D p=new Point3D(32.10290524,35.20983303,694);
		System.out.println("point:"+p);

		
		Point3D leftCornerUp = new Point3D( 32.105394,  35.202532, 0);
		Point3D rightCornerUp = new Point3D( 32.105444,  35.212496, 0);
		Point3D leftCornerDown = new Point3D( 32.101899,  35.202447, 0);
		Point3D rightCornerDown = new Point3D( 32.101899,  35.212496, 0);
		
		double ratioHorizontal=leftCornerUp.distance3D(rightCornerUp);
		System.out.println(ratioHorizontal);
		double x=1149/1433*ratioHorizontal;
		System.out.println(Point3D.d2r(x));
		double ratioVertical=leftCornerUp.distance3D(leftCornerDown);
		double y=460/HEIGHTPIC*ratioVertical;
		
		System.out.println("x="+x+ ", y="+y);

	}
	
	public Pacman PacPix2Gps(Pacman p) {
		double x1 = 35.2024f; // upper left corner
		double y1 = 32.1056f;
		double x2 = 35.2121f; // lower right corner
		double y2 = 32.1019f;
		int mapWidth=1433;
		int mapHeight=642;
		double mapLongitudeStart=32.1056f;
		double mapLatitudeStart=35.2024f;
		double mapLongitudeEnd=32.1019f;
		double mapLatitudeEnd=35.2121f;
		double mapLongitude=mapLongitudeEnd-mapLongitudeStart;
		double mapLatitude=mapLatitudeStart-mapLatitudeEnd;
		double xPIX=p.Getpoint().x();
		double yPIX=p.Getpoint().y();
		   double x=xPIX*mapLongitude ;//;
		   //System.out.println(z);
		   x=x/(mapWidth);
		  // System.out.println(z);
		   x=x +mapLongitudeStart;
	       //System.out.println(x);
	       double y=yPIX*mapLatitude;
	       y=y/mapHeight;
	       y=y-mapLatitudeStart;
	       y=y*(-1);
	       //System.out.println(x+","+y);
	       Packman p1=new Packman(y,x,p.Getpoint().z(),p.Getspeed(),p.GetId(),p.GetRadius());
	       return p1;
	}
}
