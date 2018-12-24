package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import coords.MyCoords;
import game.Map;
import geom.Point3D;
/**
 * Junit to test MyCoords class
 * @author Shayke Shok and Omer Edut
 */
class Test_Map {
	Map map;
	/*@Befor
	public void before() {
		map = new Map("images\\ariel1.png");
	}*/
	
	
	@Test
    public void latLon2Pixel() {
		map = new Map();
        Point3D point3D = new Point3D(35.2035022,32.1045513,0);
        System.out.println(map.polar2Pixel(point3D));
    }

    @Test
    public void pixel2LatLon() {
    	map = new Map();
        Point3D point3D = new Point3D(145.31665125900736,251.726461222863,0.0);
        System.out.println("p2l: " + map.pixel2Polar(point3D));
    }

    @Test
    public void distansPixelsTest(){
    	map = new Map();
        Point3D point1 = new Point3D(35.206338, 32.104733, 0);//32.104733,35.206338
        Point3D point2 = new Point3D(35.2026475, 32.105137, 0);//32.105137, 35.202647
        System.out.println("distance: "+map.distanceInPixels(point2, point1));
    }
	
}
