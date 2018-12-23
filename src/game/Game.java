package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import file_format.CsvWriter;
import geom.Point3D;

public class Game {
	private List<Pacman> pacmanLst;
	private List<Fruit> fruitLst;
	private int maxId;
	// private Map map;

	/************************** constructors *************************/
	public Game() {
	}

	public Game(String picPath, Point3D rightCornerUp, Point3D leftCornerDown) {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		maxId=0;
		// map=new Map(rightCornerUp,leftCornerDown,null,null,picPath);
	}

	public Game(double rightCornerUp, double leftCornerDown) {
		pacmanLst = new ArrayList<Pacman>();
		fruitLst = new ArrayList<Fruit>();
		maxId=0;
	}
/*
	public Game(String s[]) {
		int tempId=0;
		if (s[0].equals("p") || s[0].equals("P")) {
			tempId=Integer.parseInt(s[1]);
			pacmanSet.add(new Pacman(Integer.parseInt(s[1]),
					new Point3D(Double.parseDouble(s[2]), Double.parseDouble(s[3]), Double.parseDouble(s[4])),
					Integer.parseInt(s[5]), Integer.parseInt(s[6]), null));
		}
		else if (s[0].equals("f") || s[0].equals("F")) {
			tempId=Integer.parseInt(s[1]);
			fruitSet.add(new Fruit(Integer.parseInt(s[1]),
					new Point3D(Double.parseDouble(s[2]), Double.parseDouble(s[3]), Double.parseDouble(s[4])),
					Integer.parseInt(s[5])));
		}
		if(tempId>maxId)
			maxId=tempId;
	}
*/
	
	/***************************Getters***************************/
	public List<Pacman> getPacman() {
		return pacmanLst;
	}
	
	public List<Fruit> getFruit() {
		return fruitLst;
	}
	/*************************** Methodes ************************/
	public void addString(String s[]) {
		int tempId=0;
		if (s[0].equals("p") || s[0].equals("P")) {
			tempId=Integer.parseInt(s[1]);
			pacmanLst.add(new Pacman(Integer.parseInt(s[1]),
					new Point3D(Double.parseDouble(s[2]), Double.parseDouble(s[3]), Double.parseDouble(s[4])),
					Integer.parseInt(s[5]), Integer.parseInt(s[6]), null));
		}
		else if (s[0].equals("f") || s[0].equals("F")) {
			tempId=Integer.parseInt(s[1]);
			fruitLst.add(new Fruit(Integer.parseInt(s[1]),
					new Point3D(Double.parseDouble(s[2]), Double.parseDouble(s[3]), Double.parseDouble(s[4])),
					Integer.parseInt(s[5])));
		}
	}
		public void add(Object object) {
			int tempId=0;
			String nameClass = object.getClass().getSimpleName();
			if (nameClass.equals("Fruit")) { 
				Fruit f=(Fruit) object;
				tempId=f.getId();
				fruitLst.add((Fruit) object);	
			}
			else if (nameClass.equals("Pacman")) {
				Pacman p=(Pacman) object;
				tempId=p.getId();
				pacmanLst.add((Pacman) object);
			}
			if(tempId>maxId)maxId=tempId;
		
	}
	public void saveGame(String path) throws FileNotFoundException {
		String header = "Type,id,Lat,Lon,Alt,speed/weight,radius";
		CsvWriter csvWriter = new CsvWriter();
		csvWriter.write(header, pacmanLst, fruitLst, path);
	}
	public int getBiggestId() {
		return maxId;
	}
}
