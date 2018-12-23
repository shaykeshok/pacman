package file_format;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gis.GIS_element;
import gis.GIS_layer;
import gis.GIS_project;
import gis.GisElement;

public class KmlWriter {

	private Document doc;
	private Element root;

	/**
	 * Create a KML object.
	 */
	public void kml() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element kml = doc.createElementNS("http://www.opengis.net/kml/2.2", "kml");
			doc.appendChild(kml);
			root = doc.createElement("Document");
			kml.appendChild(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a placemark to this KML object.
	 * @param mark
	 */
	public void addMarksFromList(GIS_project projectCSV) {
		if (projectCSV.isEmpty())
			return;
		for (GIS_layer gis_layer : projectCSV) {
			if (!gis_layer.isEmpty()) {
				for (GIS_element gis_element : gis_layer) {

					Element placemark = doc.createElement("Placemark");
					root.appendChild(placemark);

					Element name = doc.createElement("name");
					name.appendChild(doc.createTextNode(((GisElement) gis_element).getSSID()));
					placemark.appendChild(name);

					// SimpleDateFormat sdf = new
					// SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
					Element desc = doc.createElement("description");
					desc.appendChild(doc.createTextNode("SSID: " + ((GisElement) gis_element).getSSID() + "\n" + "MAC: " + ((GisElement) gis_element).getMac() + "\n" + "Signal: " + ((GisElement) gis_element).getRssi()
							+ "\n" + "Time: " + ((GisElement) gis_element).getfirstSeen().toString() + "\n" + "Latitude: " + ((GisElement) gis_element).getCurrentLatitude() + "\n" + "Longitude: "
							+ ((GisElement) gis_element).getCurrentLongitude() + "\n" + "Altitude: " + ((GisElement) gis_element).getAltitudeMeters()));
					placemark.appendChild(desc);

					Element point = doc.createElement("Point");
					placemark.appendChild(point);

					if (((GisElement) gis_element).getAltitudeMeters() > 0) {
						Element altitudeMode = doc.createElement("altitudeMode");
						altitudeMode.appendChild(doc.createTextNode("absolute"));
						point.appendChild(altitudeMode);
					}

					Element coords = doc.createElement("coordinates");
					coords.appendChild(doc.createTextNode(((GisElement) gis_element).getPoint().toFile()));
					point.appendChild(coords);

					Element TimeStamp = doc.createElement("TimeStamp");
					Element when = doc.createElement("when");
					TimeStamp.appendChild(when);
					String time = ((GisElement) gis_element).getData().getUTC() + "Z";
					when.appendChild(doc.createTextNode(time));
					TimeStamp.appendChild(when);
					placemark.appendChild(TimeStamp);
				}
			}
		}
	}

	
	public void addMarksFromList(List<double[]> lst) {
		if (lst.isEmpty())
			return;
		for (double[] d : lst) {
			if (d!=null) {
				for(int i=0;i<5;i++) {

					Element placemark = doc.createElement("Placemark");
					root.appendChild(placemark);

					Element name = doc.createElement("name");
					name.appendChild(doc.createTextNode(((GisElement) gis_element).getSSID()));
					placemark.appendChild(name);

					// SimpleDateFormat sdf = new
					// SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
					Element desc = doc.createElement("description");
					desc.appendChild(doc.createTextNode("SSID: " + ((GisElement) gis_element).getSSID() + "\n" + "MAC: " + ((GisElement) gis_element).getMac() + "\n" + "Signal: " + ((GisElement) gis_element).getRssi()
							+ "\n" + "Time: " + ((GisElement) gis_element).getfirstSeen().toString() + "\n" + "Latitude: " + ((GisElement) gis_element).getCurrentLatitude() + "\n" + "Longitude: "
							+ ((GisElement) gis_element).getCurrentLongitude() + "\n" + "Altitude: " + ((GisElement) gis_element).getAltitudeMeters()));
					placemark.appendChild(desc);

					Element point = doc.createElement("Point");
					placemark.appendChild(point);

					if (((GisElement) gis_element).getAltitudeMeters() > 0) {
						Element altitudeMode = doc.createElement("altitudeMode");
						altitudeMode.appendChild(doc.createTextNode("absolute"));
						point.appendChild(altitudeMode);
					}

					Element coords = doc.createElement("coordinates");
					coords.appendChild(doc.createTextNode(((GisElement) gis_element).getPoint().toFile()));
					point.appendChild(coords);

					Element TimeStamp = doc.createElement("TimeStamp");
					Element when = doc.createElement("when");
					TimeStamp.appendChild(when);
					String time = ((GisElement) gis_element).getData().getUTC() + "Z";
					when.appendChild(doc.createTextNode(time));
					TimeStamp.appendChild(when);
					placemark.appendChild(TimeStamp);
				}
			}
		}
	}

	/**
	 * Write this KML object to a file.
	 * @param file
	 * @return boolean true if file written
	 */
	public boolean writeFile(File file) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource src = new DOMSource(doc);
			StreamResult out = new StreamResult(file);
			transformer.transform(src, out);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("Kml saved");
		return true;
	}

	public static String addFilteringArea(double[] rectTop, double[] rectBot, String kml) {// adds
																							// the																					// area
		// rectTop = {xTopLeft, yTopLeft, xTopRight, yTopRight}
		// rectBot = {xBottomLeft, yBottomLeft, xBottomRight, yBottomRight}
		// <altitudeMode>relativeToGround</altitudeMode>

		kml += "<Placemark>\n      <name>Filtered Area</name>\n      <styleUrl>#msn_ylw-pushpin</styleUrl>\n "
				+ "     <Polygon>\n        <extrude>1</extrude>\n          		<tessellate>1</tessellate>\n" + "				\n 				<outerBoundaryIs>\n" + "					<LinearRing>\n           "
				+ " <coordinates>\n              "
				+ rectTop[0]
				+ ","
				+ rectTop[1]
				+ ",50\n              "
				+ rectTop[2]
				+ ","
				+ rectTop[3]
				+ ",50\n              "
				+ rectBot[2]
				+ ","
				+ rectBot[3]
				+ ",50\n              "
				+ rectBot[0]
				+ ","
				+ rectBot[1]
				+ ",50\n              "
				+ rectTop[0]
				+ ","
				+ rectTop[1]
				+ ",50\n              "
				+ "</coordinates>\n       "
				+ "   </LinearRing>\n        </outerBoundaryIs>\n      </Polygon>\n    </Placemark>";
		return kml;
	}

}
