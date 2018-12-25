package algorithms;

import java.util.ArrayList;
import java.util.List;

import game.ElementGame;
import game.Fruit;
import game.Game;
import game.Map;
import game.Pacman;
import geom.Point3D;

public class ShortestPath {

	private List<double[]> tempList;
	private List<double[]> path;
	private List<Pacman> pacmanLst;
	private List<Fruit> fruitLst;
	private List<Pacman> pacmanListOrg;
	private List<Fruit> fruitListOrg;

	public ShortestPath(List<Pacman> packman, List<Fruit> fruit, List<double[]> _path) {
		tempList = new ArrayList<double[]>();
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		deepCopyPacman(packman);
		deepCopyFruit(fruit);
		pacmanListOrg=packman;
		fruitListOrg=fruit;
		//path = new ArrayList<double[]>();
		//path = _path;
		
	}
	
	public ShortestPath(Game game) {
		tempList = new ArrayList<double[]>();
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		deepCopyPacman(game.getPacman());
		deepCopyFruit(game.getFruit());
		pacmanListOrg=game.getPacman();
		fruitListOrg=game.getFruit();
		//path = new ArrayList<double[]>();
		//path = _path;
	}

	public void pathSimulation() {
		while (fruitLst.size()>0) {
			chkDistanceFruits();
			//path.add(tempList.get(0));
			insertToPacmanPath(findPointsOfObjects());
			fixPacmanPointAndRemoveFruit();
			tempList.clear();
		}
	}

	private ElementGame[] findPointsOfObjects() {
		ElementGame[] element=new ElementGame[2];
		for(Pacman pac:pacmanLst)
			if(pac.getId()==(int)tempList.get(0)[0])
				element[0]=pac;
		for(Fruit fru:fruitLst)
			if(fru.getId()==(int)tempList.get(0)[1])
				element[1]=fru;			
		return element;
	}

	private void insertToPacmanPath(ElementGame[] element) {
		int id=(int) element[0].getId();
		for(Pacman pacman:pacmanListOrg) {
			if(pacman.getId()==id) {
				Point3D pStart=new Point3D(element[0].getPoint());
				Point3D pEnd=new Point3D(element[1].getPoint());
				Point3D[] points= {pStart,pEnd};
				pacman.addPath(points);
			}
		}
	}

	// change the point of the pacman to the fruit that the pacman need to eat
	// and remove the fruit from the list of the fruits
	private void fixPacmanPointAndRemoveFruit() {
		int index = 0, pos = 0;
		for (Pacman pacman : pacmanLst) {
			if (pacman.getId() == tempList.get(0)[0]) {
				for (Fruit fruit : fruitLst) {
					if (fruit.getId() == tempList.get(0)[1]) {
						pacman.setPoint(fruit.getPoint());
						pos = index;
					}
					index += 1;
				}
			}
		}
		fruitLst.remove(pos);
	}

	/*
	 * private List deepCopyPacman(List<ElementGame> oldList) { List newList = new
	 * ArrayList(); for(ElementGame object:oldList) { newList.add(); } return
	 * newList; }
	 */
	// deep copy to pacman
	private void deepCopyPacman(List<Pacman> pacmanList) {
		for (Pacman object : pacmanList) {
			Pacman p = new Pacman(object.getId(), object.getPoint(), object.getAttribute()[0], object.getAttribute()[1],
					null);
			pacmanLst.add(p);
		}
	}

	// deep copy to fruit
	private void deepCopyFruit(List<Fruit> fruitList) {
		for (Fruit object : fruitList) {
			Fruit f = new Fruit(object.getId(), object.getPoint(), object.getAttribute()[0]);
			fruitLst.add(f);
		}
	}

	// check for every fruit who's the nearest pacman to him
	public void chkDistanceFruits() {
		Map map=new Map();
		double closeFruit = 0, maxcloseFruit;
		for (Fruit fruit : fruitLst) {
			double[] arr = new double[3];
			maxcloseFruit =  Integer.MAX_VALUE;;
			for (Pacman pacman : pacmanLst) {
				closeFruit = pacman.getmeterSum()+(map.polar2Pixel(pacman.getPoint()).distance3D(map.polar2Pixel(fruit.getPoint())) - pacman.getAttribute()[1])/ pacman.getAttribute()[0];
				if (closeFruit < 0)
					closeFruit = 0;
				if (closeFruit < maxcloseFruit) {
					maxcloseFruit = closeFruit;
					arr[0] = pacman.getId();
				}
			}
			for (Pacman pacman : pacmanLst) {
				if (pacman.getId() == arr[0]) 
					pacman.setMeterSum(maxcloseFruit);
			}
			arr[1] = fruit.getId();
			arr[2] = maxcloseFruit;
			addItem(arr);
		}
	}

	// add item to the right position
	// save tempList sorted
	private void addItem(double[] arr) {
		int index = 0, pos = 0;
		boolean con = true;
		for (double[] d : tempList) {
			if (arr[2] < d[2] && con) {
				pos = index;
				con = false;
			}
			index += 1;
		}
		if (con)pos=index;
		tempList.add(pos, arr);
	}

}
