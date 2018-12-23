package game;

import geom.Point3D;

public class GameElement {
	//private Packman packman;
	//private Fruit fruit;
	Object object;
	/*
	public GameElement(Packman _packman,Fruit _fruit) {
		packman=_packman;
		fruit=_fruit;
	}
	*/
	public GameElement(String type,String _id,String _lat,String _lon,String _alt,String _speed_weight,String _radius) {
		if(type.equals("p")||type.equals("P"))
			object=new Pacman(Integer.parseInt(_id),new Point3D(Double.parseDouble(_lat),Double.parseDouble(_lon),Double.parseDouble(_alt)),Integer.parseInt(_speed_weight),Integer.parseInt(_radius),null);
		else
			if(type.equals("f")||type.equals("F"))
				object=new Fruit(Integer.parseInt(_id),new Point3D(Double.parseDouble(_lat),Double.parseDouble(_lon),Double.parseDouble(_alt)),Integer.parseInt(_speed_weight));
	}
	public GameElement(String s[]) {
		if(s[0].equals("p")||s[0].equals("P"))
			object=new Pacman(Integer.parseInt(s[1]),new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])),Integer.parseInt(s[5]),Integer.parseInt(s[6]),null);
		else
			if(s[0].equals("f")||s[0].equals("F"))
				object=new Fruit(Integer.parseInt(s[1]),new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])),Integer.parseInt(s[5]));
	}
	public GameElement(Object _object){
		if(object.getClass().getName().charAt(0)=="f")
			object=new Fruit(1,_object.)
			
	}
	
	public String toString() {
		return object.toString();
		
	}
}
