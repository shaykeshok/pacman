package gis;

import geom.Point3D;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MetaData implements Meta_data {

	private String mac, ssid, authmode, channel, rssi, accuracyMeters, type;
	private Date firstSeen;
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);

	public MetaData(String _mac, String _ssid, String _authmode, String _firstSeen, String _channel, String _rssi, String _accuracyMeters, String _type) throws ParseException {
		mac = _mac;
		ssid = _ssid;
		authmode = _authmode;
		channel = _channel;
		rssi = _rssi;
		accuracyMeters = _accuracyMeters;
		type = _type;
		firstSeen=format.parse(_firstSeen);
	}
	
	/** 
	 * @return date that is the Universal Time Clock associated with this data in milliseconds
	 */
	public long getUTC() {
		return firstSeen.getTime();
	}
	/**
	 *  @return str a String representing this data 
	 */
	public String toString() {
		String str ="META-DATA:  Mac: "+mac+", SSID: "+ssid+", authmode: "+authmode+", channel: "+channel+", rssi: "+rssi+", accuracyMeters: "+accuracyMeters+", type: "+type+", firstSeen: "+firstSeen;
		return str;
		
	}
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data
	 */
	public Point3D get_Orientation() {
		return null;
	}
	
	/**********************Getters*****************/
	public String getMac() {
		return mac;
	}
	public String getSSID() {
		return ssid;
	}
	public String getAuthmode() {
		return authmode;
	}
	public Date getfirstSeen() {
		return firstSeen;
	}
	public String getChannel() {
		return channel;
	}
	public String getRssi() {
		return rssi;
	}
	
	public String getAccuracyMeters() {
		return accuracyMeters;
	}
	public String getType() {
		return type;
	}
	
	/*******************************Setters*****************************/
	public void setMac(String _Mac) {
		mac=_Mac;
	}
	public void setSSID(String _SSID) {
		ssid=_SSID;
	}
	public void setAuthmode(String _Authmode) {
		authmode=_Authmode;
	}
	public void setfirstSeen(Date _firstSeen) {
		firstSeen=_firstSeen;
	}
	public void setChannel(String _Channel) {
		channel=_Channel;
	}
	public void setRssi(String _rssi) {
		rssi=_rssi;
	}
	public void setAccuracyMeters(String _AccuracyMeters) {
		accuracyMeters=_AccuracyMeters;
	}
	public void setType(String _Type) {
		type=_Type;
	}
}
