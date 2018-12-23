package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import coords.MyCoords;
import geom.Point3D;
/**
 * Junit to test MyCoords class
 * @author Shayke Shok and Itay Grinblat
 */
class Test_MyCoords {
	MyCoords coords=new MyCoords();
	Point3D vec;
	Point3D gps0;
	Point3D gps1;
	
	
	@Test
	void testAdd() { 
		boolean flag = false;
		Point3D gps=new Point3D(32.103315, 35.209039);
		Point3D vector=new Point3D(337.699,-359.249);
		MyCoords c = new MyCoords();
		Point3D p = new Point3D(c.add(gps,vector));
		if ((p.x()>32.1 &&p.x()<32.2) && (p.y()>35.2 &&p.y()<35.3)) {
			flag = true;
		}
		assertEquals(true, flag);
	}
	@Test
	void distance3D(){

		boolean flag = false;
		MyCoords c = new MyCoords();
		Point3D gps1=new Point3D(32.103315, 35.209039);
		Point3D gps2=new Point3D(32.106352, 35.205225);
		double ans = c.distance3d(gps1, gps2);
		if (ans < 493.1 && ans > 493)
			flag = true;

		assertEquals(true, flag);
	}
	@Test
	void vector3Dtest() {

		boolean flag=false;
		Point3D gps1=new Point3D(32.103315,35.209039);
		Point3D gps2=new Point3D(32.106352,35.205225);
		MyCoords c=new MyCoords();
		Point3D vec = c.vector3D(gps1, gps2);
		if((vec.x()<337.7 && vec.x()>337.6) && (vec.y()<-359.2 && vec.y()>-359.3))
			flag=true;
		assertEquals(true, flag);


	}
	
	@Test
	public void testAzimuth_elevation_dist() {
		gps0=new Point3D(32.103315,35.209039,670);
		gps1=new Point3D(32.106352,35.205225,650);
		vec=new Point3D(48,210,450);
		double[] actual=coords.azimuth_elevation_dist(gps0, gps1);
		double[] ans={313.23042032646896,
		              92.32476351738879,
		              493.0523318319326};
		assertArrayEquals(ans, actual);
	}
	
	
	@Test
	public void testIsValid_GPS_Point() {
		boolean flag=true;
		gps0=new Point3D(32.103315,35.209039,670);
		if(!coords.isValid_GPS_Point(gps0))
			flag=true;
		assertTrue(flag);
	}
	
	
	
}
