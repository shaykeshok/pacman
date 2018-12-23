package gui;

import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import file_format.CsvReader;
import game.Game;

public class MainWindow extends JFrame implements MouseListener {
	private MenuBar menuBar;
	private Menu fileMenu, helpMenu, gameMenu, createGame, addObjects;
	private MenuItem openItem, saveItem, clearItem, fruitItem, pacmanItem, saveKmlItem;
	private MenuItem aboutUsItem, conItem, playGameItem, simulationItem, stopDrawObjectsItem;
	private Game game;
	private Image pacman, fruit, icon;
	public BufferedImage myImage;
	private boolean stop;

	public MainWindow() {
		stop = true;
		try {
			pacman = ImageIO.read(new File("icons\\pacman.png"));
			fruit = ImageIO.read(new File("icons\\pineapple.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		initGUI();
		this.addMouseListener(this);
		createActions();
	}

	private void initGUI() {
		// set file menu
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		openItem = new MenuItem("Open csv file");
		saveItem = new MenuItem("Save file to csv");
		saveKmlItem = new MenuItem("Save file to kml file");

		menuBar.add(fileMenu);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveKmlItem);
		this.setMenuBar(menuBar);

		// set game menu
		gameMenu = new Menu("Game options");
		createGame = new Menu("Create new game");
		addObjects = new Menu("Add objects");
		clearItem = new MenuItem("Clear game");
		pacmanItem = new MenuItem("Pacman");
		fruitItem = new MenuItem("Fruit");
		stopDrawObjectsItem = new MenuItem("Stop add objects");
		simulationItem = new MenuItem("start simulation");
		playGameItem = new MenuItem("start game");
		addObjects.add(pacmanItem);
		addObjects.add(fruitItem);
		addObjects.add(stopDrawObjectsItem);
		createGame.add(addObjects);
		gameMenu.add(createGame);
		gameMenu.add(clearItem);
		gameMenu.add(playGameItem);
		gameMenu.add(simulationItem);
		menuBar.add(gameMenu);

		// set help menu
		helpMenu = new Menu("Help");
		conItem = new MenuItem("Connection");
		aboutUsItem = new MenuItem("About us");
		helpMenu.add(conItem);
		helpMenu.add(aboutUsItem);
		menuBar.add(helpMenu);

		try {
			myImage = ImageIO.read(new File("images\\ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	int x = -1;
	int y = -1;
	List<int[]> lst = new ArrayList<int[]>();

	public void paint(Graphics g) {
		g.drawImage(myImage, 0, 0, this);

		if (x != -1 && y != -1) {
			int r = 20;
			 x = x - (r / 2);
			 y = y - (r / 2);
			int sug = 0;
			ImageObserver observer = null;
			if (icon == pacman)
				sug = 1;
			else
				sug = 0;
			int[] arr = { x, y, sug };
			lst.add(arr);
			
			/*
			String str;
			if(icon==pacman)
				str="pacman";
			else
				str="fruit";
			ObjectParam objectParam=new ObjectParam(x,y,str);
			*/
			for (int[] a : lst) {
				if (a[2] == 1)
					icon = pacman;
				else
					icon = fruit;
				g.drawImage(icon, a[0], a[1], 30, 30, observer);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		if (!stop) {
			System.out.println("mouse Clicked");
			System.out.println("(" + arg.getX() + "," + arg.getY() + ")");
			x = arg.getX();
			y = arg.getY();
			repaint();
		}
	}

	public void createActions() {
		pacmanItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop = false;
				icon = pacman;
			}

		});
		fruitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop = false;
				icon = fruit;
			}
		});
		stopDrawObjectsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				icon = null;
				stop = true;
			}
		});
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("open file");
				try {
					readFileDialog();
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				}
			}
		});
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save file");
				writeFileDialog();
			}
		});
		saveKmlItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save file");
				//writeFileDialog();
				//Path2kml;
			}
		});
	}

	public void readFileDialog() throws FileNotFoundException {
		// try read from the file

		FileDialog fd = new FileDialog(this, "Open csv file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:\\");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			CsvReader csvReader = new CsvReader();
			csvReader.init(folder + fileName, ",");
			game = csvReader.read(1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void writeFileDialog() {
		// try write to the file
		FileDialog fd = new FileDialog(this, "Save the game", FileDialog.SAVE);
		fd.setFile("*.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			game.saveGame(folder + fileName);
		} catch (IOException ex) {
			System.out.print("Error writing file  " + ex);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
