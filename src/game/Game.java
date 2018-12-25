package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import file_format.CsvWriter;
import geom.Point3D;

public class Game {
	private List<Pacman> pacmanLst;
	private List<Fruit> fruitLst;
	private int maxIdPacman;
	private int maxIdFruit;

	/************************** constructors *************************/
	public Game() {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
	}

	public Game(String picPath, Point3D rightCornerUp, Point3D leftCornerDown) {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		maxIdPacman=maxIdFruit=0;
	}

	public Game(double rightCornerUp, double leftCornerDown) {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		maxIdPacman=maxIdFruit=0;
	}
	
	/***************************Getters***************************/
	public List<Pacman> getPacman() {
		return pacmanLst;
	}
	
	public List<Fruit> getFruit() {
		return fruitLst;
	}
	/*************************** Methodes ************************/
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
	public void saveGame(String path) throws FileNotFoundException {
		String header = "Type,id,Lat,Lon,Alt,speed/weight,radius";
		CsvWriter csvWriter = new CsvWriter();
		csvWriter.write(header, pacmanLst, fruitLst, path);
	}
	public int getBigIdPacman() {
		return maxIdPacman;
	}
	public int getBigIdFruit() {
		return maxIdFruit;
	}

	public void clear() {
		fruitLst.clear();
		pacmanLst.clear();
	}
}
