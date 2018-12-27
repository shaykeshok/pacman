<b>Pacman</b>

Built with: 
eclipse - The Platform for Open Innovation and Collaboratio 

Date:
02/12/18 
 
Authors: 
Shayke Shok </br>
Omer Edut</br>
Description: 

This project simulates a Pacman game, with the possibility to load an existing game, save a game, create a game, run a game simulation and export the game to a KML file that can be displayed in Google Earth.

This project includes many classes and each class does something else.

The Pacman class - represents a Pacman object with position, move ability, speed, orientation.

Fruit class - represents a fruit object with position and weight.

Map class - represents a map object with an image, and the ability to convert polarity points into pixels and translate relative points on the given map.

Game class - A class that holds and manages all objects of the game, a list of Pacman, a list of fruits and a map, the class can load and save CSV files.

Path class - A class that represents a path with location, time, and other object data.

Path2kml class - A class that converts a given path to a KML file and exports it.

ShortestPath class - A class that gets a list of points and finds the shortest path.

MainWindow class - This class responsible for the visual display, the department wraps and connects all the other departments to view the game, with options for running, saving and creating a new game.
diagrm
