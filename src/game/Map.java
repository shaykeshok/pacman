package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import coords.MyCoords;
import geom.Point3D;

public class Map {
	public String picPath;
	public Point3D rightCornerUp,leftCornerDown,rightCornerDown,leftCornerUp;
	public static final int WIDTHPIC=1433,HEIGHTPIC=642;
	
	
	 private double max_x = 35.212400;
	    private double max_y = 32.105740;
	    private double min_x = 35.202350;
	    private double min_y = 32.101900;

	    private double pixel_per_radian_x ;
	    private double pixel_per_radian_y ;


	    private double width_map_in_meters = 943;
	    private double height_map_in_meters = 427;
	    private double pixel_per_meter_x;
	    private double pixel_per_meter_y;


    private BufferedImage mapImage;
    private int mapWidth;
    private int mapHeight;
    private MyCoords myCoords;
	
	
	
    public Map() {
        try {
            mapImage = ImageIO.read(new File("images\\ariel1.png"));
            mapWidth = mapImage.getWidth();
            mapHeight = mapImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myCoords = new MyCoords();
        pixel_per_radian_x = mapWidth/(max_x-min_x);
        pixel_per_radian_y = mapHeight/(max_y-min_y);
        
        
        pixel_per_meter_x = mapWidth/width_map_in_meters;
        pixel_per_meter_y = mapHeight/height_map_in_meters;
    }
	
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
	
	
	public int distanceInPixels(Point3D src, Point3D dst) {
		MyCoords coords = new MyCoords();
		Point3D vector = coords.vector3D(src, dst);
		double d =  Math.sqrt(Math.pow(vector.x(), 2) + Math.pow(vector.y(), 2) + Math.pow(vector.z(), 2));
		double dx = vector.x()*pixel_per_meter_x;
		double dy = vector.y()*pixel_per_meter_y;
		int distance = (int)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(vector.z(), 2));
		return distance;
	}
	

	//convert pixels point to polar point
	public Point3D pixel2Polar(Point3D point) {
		
		double x = ((point.x()/pixel_per_radian_x) + min_x);
        double y = max_y - (point.y() / pixel_per_radian_y);
        return new Point3D(x,y,point.z());
		
		//double ratioHorizontal=leftCornerUp.distance3D(rightCornerUp);
		//double x=point.x()/WIDTHPIC*ratioHorizontal;
		//double ratioVertical=leftCornerUp.distance3D(leftCornerDown);
		//double y=point.y()/HEIGHTPIC*ratioVertical;
		//return new Point3D(x,y,point.z());
	}
	
	//convert polar point to pixels point
	public Point3D polar2Pixel(Point3D point) {
		double x = Math.abs(point.x() - min_x) * pixel_per_radian_x;
        double y = Math.abs(max_y - point.y()) * pixel_per_radian_y;
        return new Point3D(roundNumber(x), roundNumber(y), point.z());
	}
	
	private int roundNumber(double n){
        if ((n - (int)n) > 0.5){
            return (int)n + 1;
        }else {
            return (int)n;
        }
    }
	
	
	
	
	
	/*public static void main(String[] args) {
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

	}*/
	
	/*public Pacman PacPix2Gps(Pacman p) {
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
	}*/
	
	
	public BufferedImage getMap() {
        return mapImage;
    }
}
