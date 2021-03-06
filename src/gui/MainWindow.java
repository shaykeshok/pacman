package gui;

import java.awt.Color;
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
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.ShortestPath;
import coords.Path;
import file_format.CsvReader;
import game.Fruit;
import game.Game;
import game.Map;
import game.Pacman;
import geom.Point3D;

public class MainWindow extends JFrame implements MouseListener {
	private MenuBar menuBar;
	private Menu fileMenu, helpMenu, gameMenu, createGame, addObjects;
	private MenuItem openItem, saveItem, clearItem, fruitItem, pacmanItem, saveKmlItem;
	private MenuItem aboutUsItem, conItem, playGameItem, simulationItem, stopDrawObjectsItem;
	private Game game;
	private Image pacman, fruit, icon;
	public BufferedImage myImage;
	private boolean stop;
	private List<Object[]> pathSofi;
	private Map map;


	/**
	 * Create MainWindow object for GUI
	 */
	public MainWindow() {
		pathSofi = new ArrayList<Object[]>();
		game = new Game();
		stop = true;
		map = new Map();
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

	/**
	 * This method initialized the GUI arguments 
	 */
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

	}

	int x = -1;
	int y = -1;
	private boolean simulator = false;
	private boolean startGame=false;
	private long time;

	/**
	 * This method manage the graphic presentation
	 */
	public void paint(Graphics g) {
		int width = this.getContentPane().getWidth();
		int height = this.getContentPane().getHeight();
		int offsetWidth = 10;
		int offsetHeight = 65;
		map.setSize(width, height);
		g.drawImage(map.getImg(), offsetWidth, offsetHeight, width, height, this);
		ImageObserver observer = null;
		if(startGame==false) {
			if (simulator == false) {
				if (x != -1 && y != -1) {
					int r = 30;
					x = x - (r / 2);
					y = y - (r / 2);

					if (icon == pacman)
						game.add(new Pacman(game.getBigIdPacman() + 1, map.pixel2Polar(x, y), 1, 1, null));
					else
						game.add(new Fruit(game.getBigIdFruit() + 1, map.pixel2Polar(x, y), 1));
				}
				for (Pacman pacmanO : game.getPacman()) {
					icon = pacman;
					Point3D pix = map.polar2Pixel(pacmanO.getPoint());
					g.drawImage(icon, offsetWidth + (int) pix.x(), offsetHeight + (int) pix.y(), 30, 30, observer);
				}
				for (Fruit fruitO : game.getFruit()) {
					icon = fruit;
					Point3D pix = map.polar2Pixel(fruitO.getPoint());
					g.drawImage(icon, offsetWidth + (int) pix.x(), offsetHeight + (int) pix.y(), 30, 30, observer);
				}
			} else {
				for (Pacman pacmanO : game.getPacman()) {
					icon = pacman;
					Point3D pix = map.polar2Pixel(pacmanO.getPoint());
					g.drawImage(icon, (int) pix.x(), (int) pix.y(), 30, 30, observer);
					g.setColor(new Color(((int) (Math.random() * (256))), (int) (Math.random() * (256)),
							(int) (Math.random() * (256))));
					List<Point3D[]> points = pacmanO.getPath();
					for (Point3D[] line : points) {
						Point3D start = map.polar2Pixel(line[0]);
						Point3D end = map.polar2Pixel(line[1]);
						g.drawLine((int) start.x(), (int) start.y(), (int) end.x(), (int) end.y());
					}
				}
				for (Fruit fruitO : game.getFruit()) {
					icon = fruit;
					Point3D pix = map.polar2Pixel(fruitO.getPoint());
					g.drawImage(icon, (int) pix.x(), (int) pix.y(), 30, 30, observer);
				}
				simulator = false;
			}
		}else {
			for (Object[] objects : pathSofi) {
				if((long)objects[0]==time) {
					icon = pacman;
					Point3D pix = map.polar2Pixel((Point3D)objects[1]);
					g.drawImage(icon, (int) pix.x(), (int) pix.y(), 30, 30, observer);
				}else {
					for (Fruit fruitO : game.getFruit()) {
						icon = fruit;
						Point3D pix = map.polar2Pixel(fruitO.getPoint());
						g.drawImage(icon, (int) pix.x(), (int) pix.y(), 30, 30, observer);
					}
					time+=1000;
				}
			}
			startGame=false;
		}
	}



	/**
	 * Class for compare times 
	 */
	class TimeComperator implements Comparator<Object[]> {

		@Override
		public int compare(Object[] o1, Object[] o2) {
			return (int) ((long)o1[0] - (long)o2[0]);
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

	/**
	 * This method create action listener of all the operations
	 */
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
					repaint();
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
				System.out.println("save file to kml..");
				// writeFileDialog();
				// Path2kml;
			}
		});
		simulationItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start simulation..");
				simulator = true;
				ShortestPath shortestPath = new ShortestPath(game);
				shortestPath.pathSimulation();
				repaint();
			}
		});

		playGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start game..");
				simulator = true;
				ShortestPath shortestPath = new ShortestPath(game);
				shortestPath.pathSimulation();
				repaint();
				time = System.currentTimeMillis();
				for (Pacman pacmanO : game.getPacman()) {
					Path path = new Path();
					pathSofi.addAll(path.pathSofi(pacmanO, time));
				}
				pathSofi.sort(new TimeComperator());
				startGame=true;
			}
		});
		clearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clear map");
				game.clear();
				pathSofi.clear();
				x = y = -1;
				icon = null;
				stop = true;
				repaint();
			}
		});
	}

	/**
	 * This method read loaded csv file
	 * @throws FileNotFoundException if the file not found or not exist
	 */
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
			repaint();

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method write csv file
	 */
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
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
