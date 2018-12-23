package algorithms;

import java.util.ArrayList;
import java.util.List;

import game.Fruit;
import game.Game;
import game.Pacman;

public class ShortestPath {

	private List<double[]> tempList;
	private List<double[]> path;
	private List<Pacman> pacmanLst;
	private List<Fruit> fruitLst;

	public ShortestPath(List<Pacman> packman, List<Fruit> fruit, List<double[]> _path) {
		tempList = new ArrayList<double[]>();
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		deepCopyPacman(packman);
		deepCopyFruit(fruit);
		path = new ArrayList<double[]>();
		path = _path;
		
	}
	
	public ShortestPath(Game game, List<double[]> _path) {
		tempList = new ArrayList<double[]>();
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		deepCopyPacman(game.getPacman());
		deepCopyFruit(game.getFruit());
		path = new ArrayList<double[]>();
		path = _path;
	}

	public void pathSimulation() {
		while (fruitLst != null) {
			chkDistanceFruits();
			path.add(tempList.get(0));
			fixPacmanPointAndRemoveFruit();
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
		double[] arr = new double[3];
		double distance = 0, maxDistance;
		for (Fruit fruit : fruitLst) {
			maxDistance = 0;
			for (Pacman pacman : pacmanLst) {
				distance = pacman.getPoint().distance3D(fruit.getPoint());
				if (distance > maxDistance) {
					maxDistance = distance;
					arr[0] = pacman.getId();
				}
			}
			arr[1] = fruit.getId();
			arr[2] = maxDistance;
			addItem(arr);
		}
	}

	// add item to the right position
	// save tempList sorted
	private void addItem(double[] arr) {
		int index = 0, pos = 0;
		boolean contionue = true;
		for (double[] d : tempList) {
			if (arr[3] < d[3] && contionue) {
				pos = index;
				contionue = false;
			}
			index += 1;
		}
		tempList.add(pos, arr);
	}

}
