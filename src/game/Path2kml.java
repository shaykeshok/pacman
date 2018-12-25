package game;

import java.util.ArrayList;
import java.util.List;

import coords.Path;
import file_format.KmlWriter;
import geom.Point3D;

public class Path2kml {
	private List<double[]> pathArr;// הדרך של האכילה של פקמן לפרי
	private List<double[]> pathWay;// דרך סופית של כל הפקמנים
	private List<Pacman> pacmanLst;
	private List<Fruit> fruitLst;

	/**
	 * Create Path2kml object to save game data in kml format
	 * @param game to save
	 * @param _pathWay 
	 * @param _pathArr
	 */
	public Path2kml(Game game, List<Point3D> _pathWay, List<double[]> _pathArr) {
		pathWay = new ArrayList<double[]>();
		pacmanLst = game.getPacman();
		fruitLst = game.getFruit();
		pathArr = _pathArr;
	}

	/**
	 * This method create the kml file
	 */
	public void createPathSofi() {
		double[] arrToPath = new double[5];
		double color=0;
		Point3D start=new Point3D(0,0,0), end=new Point3D(0,0,0);
		for (double[] d : pathArr) {
			for (Pacman pacman : pacmanLst) {
				if (pacman.getId() == d[0]) {
					color = pacman.getColor();
					start = pacman.getPoint();
				}
			}
			for (Fruit fruit : fruitLst) {
				if (fruit.getId() == d[0])
					end = fruit.getPoint();
			}
			Path path = new Path(start, end);
			List<Point3D> pathPacman2Fruit = path.createPath();
			for (Point3D point : pathPacman2Fruit) {
				arrToPath[0] = d[0];
				arrToPath[1] = color;
				arrToPath[2] = point.x();
				arrToPath[3] = point.y();
				arrToPath[4] = point.z();

			}
			pathWay.add(arrToPath);	
		}
		
		

		/*
		 * על כל שורה בpatharr צריך להפעיל את PAth
		 */

	}

	/**
	 * This method write the kml file
	 */
	private void makeKml() {
		KmlWriter kmlWriter=new KmlWriter();
		kmlWriter.addMarksFromList(pathWay);
	}
}
