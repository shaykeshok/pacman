package algorithms;

import java.io.File;
import java.text.ParseException;

import file_format.CsvReader;
import gis.GIS_layer;
import gis.GIS_project;
import gis.GisProject;

public class MultiCSV {
	public static GIS_project project;

	public MultiCSV() { 
		project = new GisProject();
	}

	public static GIS_project listFilesForFolder(final File folder) throws ParseException {
		CsvReader csvReader = new CsvReader();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				String fileName = fileEntry.getName();
				if (fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()).equals("csv")) {
					csvReader.init(fileEntry.getPath(), ",");
					GIS_layer layer = csvReader.read();
					project.add(layer);

				}
			}
		}
		return project;
	}

	public static void main(String[] args) throws ParseException {

		final File folder = new File(".");
		listFilesForFolder(folder);
	}
}
