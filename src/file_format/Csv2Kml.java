package file_format;

import java.io.File;
import java.text.ParseException;

import algorithms.MultiCSV;
import gis.GisLayer;
import gis.GisProject;

public class Csv2Kml {

	private CsvReader reader;
	private GisLayer gisLayer;
	private GisProject gisProject;
	
	public Csv2Kml(String filePath,boolean multicsv) throws ParseException {
		if(!multicsv) {
			reader= new CsvReader();
			reader.init(filePath, ",");
			gisLayer=(GisLayer) reader.read();
			gisProject.add(gisLayer);
		}
		else {
			MultiCSV multi=new MultiCSV();
			File folder = new File(filePath);
			gisProject=(GisProject) MultiCSV.listFilesForFolder(folder);
		}
		
		KmlWriter kmlWriter=new KmlWriter();
		File file=new File(System.getProperty("user.dir")+"\\file.kml");
		kmlWriter.kml();
		kmlWriter.addMarksFromList(gisProject);
		kmlWriter.writeFile(file);
	}

}
