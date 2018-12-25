package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import file_format.CsvWriter;
import geom.Point3D;

public class Game {
	private List<Pacman> pacmanLst;
	private List<Fruit> fruitLst;
	private int maxIdPacman=-1;
	private int maxIdFruit=-1;

	/************************** constructors *************************/
	/**
	 * Create Game object
	 */
	public Game() {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
	}

	/**
	 * Create Game object, this constructor gets
	 * @param picPath the path of the map image
	 * @param rightCornerUp the right and up corner limit of the map 
	 * @param leftCornerDown the left and down corner limit of the map
	 */
	public Game(String picPath, Point3D rightCornerUp, Point3D leftCornerDown) {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		maxIdPacman=maxIdFruit=0;
	}

	/**
	 * Create Game object, this constructor gets
	 * @param rightCornerUp the right and up corner limit of the map 
	 * @param leftCornerDown the left and down corner limit of the map
	 */
	public Game(double rightCornerUp, double leftCornerDown) {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		maxIdPacman=maxIdFruit=0;
	}
	
	/***************************Getters***************************/
	/**
	 * @return the list of the packmans in the game
	 */
	public List<Pacman> getPacman() {
		return pacmanLst;
	}
	/**
	 * @return the list of the fruits in the game
	 */
	public List<Fruit> getFruit() {
		return fruitLst;
	}
	/*************************** Methodes ************************/
	
	/**
	 * This method get String of element game and convert it to element game
	 * @param s sting of element game (packman or fruit)
	 */
	public void addString(String s[]) {
		int tempId=Integer.parseInt(s[1]);
		if (s[0].equals("p") || s[0].equals("P")) {
			pacmanLst.add(new Pacman(Integer.parseInt(s[1]),
					new Point3D(Double.parseDouble(s[3]), Double.parseDouble(s[2]), Double.parseDouble(s[4])),
					Integer.parseInt(s[5]), Integer.parseInt(s[6]), null));
			if(tempId>maxIdPacman)maxIdPacman=tempId;
		}
		else if (s[0].equals("f") || s[0].equals("F")) {
			fruitLst.add(new Fruit(Integer.parseInt(s[1]),
					new Point3D(Double.parseDouble(s[3]), Double.parseDouble(s[2]), Double.parseDouble(s[4])),
					Integer.parseInt(s[5])));
			if(tempId>maxIdFruit)maxIdFruit=tempId;

		}
	}
	
	/**
	 * This method get object of element game and add it to the game
	 * @param object to add
	 */
	public void add(Object object) {
		int tempId=0;
		String nameClass = object.getClass().getSimpleName();
		if (nameClass.equals("Fruit")) { 
			Fruit f=(Fruit) object;
			tempId=f.getId();
			fruitLst.add((Fruit) object);	
			if(tempId>maxIdFruit)maxIdFruit=tempId;
		}
		else if (nameClass.equals("Pacman")) {
			Pacman p=(Pacman) object;
			tempId=p.getId();
			pacmanLst.add((Pacman) object);
			if(tempId>maxIdPacman)maxIdPacman=tempId;
		}

	}
	
	/**
	 * This method save to csv file all the elements of the game
	 * @param path to save the game
	 * @throws FileNotFoundException if the file is not exist
	 */
	public void saveGame(String path) throws FileNotFoundException {
		String header = "Type,id,Lat,Lon,Alt,speed/weight,radius";
		CsvWriter csvWriter = new CsvWriter();
		csvWriter.write(header, pacmanLst, fruitLst, path);
	}
	
	/**
	 * @return the max id packman
	 */
	public int getBigIdPacman() {
		return maxIdPacman;
	}
	/**
	 * @return the max id fruit
	 */
	public int getBigIdFruit() {
		return maxIdFruit;
	}

	/**
	 * This method clear the packmans and fruits lists
	 */
	public void clear() {
		fruitLst.clear();
		pacmanLst.clear();
	}
}
