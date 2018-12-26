package coords;

import geom.Point3D;

/**
 * This interface represents a basic coordinate system converter, including:
 * 1. The 3D vector between two lat,lon, alt points 
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates
 * @author Shayke Shok and Omer Edut
 *
 */
public interface Coords_converter {
	
	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param gps to point
	 * @param local_vector_in_meter the vector to add
	 * @return new point after add
	 */
	public Point3D add(Point3D gps, Point3D local_vector_in_meter);
	

	/**
	 * This method computes the 3D distance (in meters) between the two gps like points
	 * @param gps0 first pioint
	 * @param gps1 second point
	 * @return the distance between 2 points
	 */
	public double distance3d(Point3D gps0, Point3D gps1);
	
	
	/**
	 * This method computes the 3D vector (in meters) between two gps like points
	 * @param gps0 first point
	 * @param gps1 second point
	 * @return vector between 2 points
	 */
	public Point3D vector3D(Point3D gps0, Point3D gps1);
	
	
	/**
	 * computes the polar representation of the 3D vector be gps0 to gps1 
	 * @param gps0 src point
	 * @param gps1 dst point
	 * @return an azimuth (aka yaw), elevation (pitch), and distance
	 */
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1);
	
	/**
	 * return true if this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p point to check
	 * @return true if is valid point otherwise false
	 */
	public boolean isValid_GPS_Point(Point3D p);
	
}
