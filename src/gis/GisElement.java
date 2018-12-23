package gis;

import geom.Geom_element;
import geom.Point3D;

import java.text.ParseException;
import java.util.Date;

import coords.MyCoords;

public class GisElement implements GIS_element {
	private MetaData metadata;
	private Point3D point;

	public GisElement(String _Mac, String _SSID, String _Authmode, String _firstSeen, String _Channel, String _rssi, String _CurrentLatitude, String _CurrentLongitude, String _AltitudeMeters,
			String _AccuracyMeters, String _Type) throws ParseException {
		point = new Point3D(Double.parseDouble(_CurrentLatitude), Double.parseDouble(_CurrentLongitude), Double.parseDouble(_AltitudeMeters));
		metadata = new MetaData(_Mac, _SSID, _Authmode, _firstSeen, _Channel, _rssi, _AccuracyMeters, _Type);
	}

	public GisElement(String[] s) throws ParseException {
		point = new Point3D(Double.parseDouble(s[6]), Double.parseDouble(s[7]), Double.parseDouble(s[8]));
		metadata = new MetaData(s[0], s[1], s[2], s[3], s[4], s[5], s[9], s[10]);
	}

	/********************* Getters **************************/
	public String getMac() {
		return metadata.getMac();
	}

	public String getSSID() {
		return metadata.getSSID();
	}

	public String getAuthmode() {
		return metadata.getAuthmode();
	}

	public Date getfirstSeen() {
		return metadata.getfirstSeen();
	}

	public String getChannel() {
		return metadata.getChannel();
	}

	public String getRssi() {
		return metadata.getRssi();
	}

	public double getCurrentLatitude() {
		return point.x();
	}

	public double getCurrentLongitude() {
		return point.y();
	}

	public double getAltitudeMeters() {
		return point.z();
	}

	public String getAccuracyMeters() {
		return metadata.getAccuracyMeters();
	}

	public String getType() {
		return metadata.getType();
	}

	public Point3D getPoint() {
		return point;
	}

	/******************************* Setters *****************************/
	public void setMac(String _Mac) {
		metadata.setMac(_Mac);
	}

	public void setSSID(String _SSID) {
		metadata.setSSID(_SSID);
	}

	public void setAuthmode(String _Authmode) {
		metadata.setAuthmode(_Authmode);
	}

	public void setfirstSeen(Date _firstSeen) {
		metadata.setfirstSeen(_firstSeen);
	}

	public void setChannel(String _Channel) {
		metadata.setChannel(_Channel);
	}

	public void setRssi(String _rssi) {
		metadata.setRssi(_rssi);
	}

	public void setAccuracyMeters(String _AccuracyMeters) {
		metadata.setAccuracyMeters(_AccuracyMeters);
	}

	public void setType(String _Type) {
		metadata.setType(_Type);
	}

	/****************************** functions *******************************/

	/**
	 * @return geom the data of the geom point
	 */
	public Geom_element getGeom() {
		return point;
	}

	/**
	 * @return metadata of the geom point
	 */
	public Meta_data getData() {
		return metadata;
	}

	/**
	 * computes a new point which is the gps point transformed by a 3D vector(in meters)
	 * @param vec is Point3D that I need to add to my Geom
	 */
	public void translate(Point3D vec) {
		MyCoords coords = new MyCoords();
		point = coords.add(point, vec);
	}

}
