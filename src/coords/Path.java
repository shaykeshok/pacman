package coords;

import java.util.ArrayList;
import java.util.List;

import game.ElementGame;
import game.Pacman;
import geom.Point3D;

public class Path {
	private List<Object[]> path;

	public Path() {
		path = new ArrayList<Object[]>();
	}

	private void add(long _time, Point3D _point, int _objectId, ElementGame _element) {
		Object[] arr = { _time, _point, _objectId, _element };
		path.add(arr);
	}

	public List<Object[]> pathSofi(Pacman pacman,long time) {
		List<Point3D[]> lstPoints=pacman.getPath();
		for (Point3D[] points : lstPoints) {

			double xfoot = (Math.abs(points[0].x() - points[1].x())) / pacman.getAttribute()[0];
			double yfoot = (Math.abs(points[0].y() - points[1].y())) / pacman.getAttribute()[0];
			double zfoot;
			try {
				zfoot = (Math.abs(points[0].z() - points[1].z())) / pacman.getAttribute()[0];
			} catch (ArithmeticException e) {
				zfoot = 0;
			}
			Point3D point = new Point3D(points[0]);
			add(time, point, pacman.getId(), pacman);
			Point3D foot = new Point3D(xfoot, yfoot, zfoot);
			while (point.x() != points[1].x() && point.y() != points[1].y() && point.z() != points[1].z()) {
				point.add(foot);
				Point3D pointNew = new Point3D(point);
				add(time+1000, pointNew, pacman.getId(), pacman);
			}
		}
		return path;

	}

}
