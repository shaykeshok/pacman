package file_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import game.Fruit;
import game.Pacman;

public class CsvWriter {
	public int gameNum = 1;
	public Date date;
	static DateFormat dateFormat;

	public CsvWriter() {
		date = new Date();
		dateFormat = new SimpleDateFormat("dd.MM.yy");
	}

	public void write(String header, List<Pacman> PacmanSet, List<Fruit> FruitSet, String path)
			throws FileNotFoundException {
		if (path == null) {
			path = System.getProperty("user.dir");
			String fileName = dateFormat.format(date) + "-" + gameNum + ".csv";
			path = path.replaceAll("GIS-PacmanNew", fileName);
		}
		PrintWriter pw = new PrintWriter(new File(path));
		StringBuilder sb = new StringBuilder();
		sb.append(header);
		for (Pacman pacman : PacmanSet) {
			sb.append('\n');
			sb.append(pacman.toString());
		}
		for (Fruit fruit : FruitSet) {
			sb.append('\n');
			sb.append(fruit.toString());
		}

		pw.write(sb.toString());
		pw.close();
		gameNum++;
	}

}
