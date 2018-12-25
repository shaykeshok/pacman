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
	public Point3D pixel2Polar(int x,int y) {
		
		double xNew = ((x/pixel_per_radian_x) + min_x);
        double yNew = max_y - (y / pixel_per_radian_y);
        return new Point3D(xNew,yNew);
		
		
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

	public BufferedImage getImg() {
        return mapImage;
    }

	/**
	 * Get the angle between two points on screen (pixels), in degrees. <br>
	 * Note: Calculating Rescaled / non-original / non-Raw points. angle might be different with different screen sizes. unless
	 * given a Raw points as arguments.<br>
	 * the angle is in degrees and in clockwise <br>
	 * Directly Upwards = 0 degrees. <br>
	 * Directly on the Right = 90 degrees. <br>
	 * Directly Downwards = 180 degrees. <br>
	 * Directly on the Left = 270 degrees 
	 * @param p1 - point in pixels
	 * @param p2 - point in pixels
	 * @return angle in degrees
	 */
	public double getAngle(Point3D p1, Point3D p2) {
		double deltaX, deltaY, angle;
		deltaX = p2.x()-p1.x();
		deltaY = p2.y()-p1.y();

		angle = Math.toDegrees(Math.atan2(deltaX, deltaY));
		return (angle < 0)? angle+360 : angle;
	}
	
	/**
	 * Get the angle between two points on screen (RAW pixels), in degrees. <br>
	 * Note: calculating RAW points. <br>
	 * First the function reverting the points into a RAW-pixel points,
	 * if Raw pixel points are passed as argument, the function would still try to revert the point to RAW
	 * as it doesn't recognize which is RAW and which isn't, please provide only Non-RAW pixel coordinates.
	 * @param p1 - point in pixels
	 * @param p2 - point in pixels
	 * @return angle in degrees
	 */
	public double getAngleRaw(Point3D p1, Point3D p2) {
		double scaleFactorX=1, scaleFactorY=1;
		double deltaX, deltaY, angle;
		deltaX = (p2.x()-p1.x())/scaleFactorX;
		deltaY = (p2.y()-p1.y())/scaleFactorY;

		angle = Math.toDegrees(Math.atan2(deltaX, deltaY));
		return (angle < 0)? angle+360 : angle;
	}
	/*
	public static void main(String[] args) {
		Point3D p=new Point3D(35.2035022,32.1045513,10);
		Map map=new Map();
		System.out.println(map.polar2Pixel(p));
	}
	*/
}
