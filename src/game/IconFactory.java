package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class IconFactory {
	private static HashMap<String, Image> icons = new HashMap<>();
	
	/**
	 * This function create image by path icon
	 * @param iconPath the path of the icon
	 * @return the image of the icon
	 */
	public static Image getIcon(String iconPath) {
		Image icon = icons.get(iconPath);
		
		if(icon == null) {
			BufferedImage img = null;
			try {
				img =  ImageIO.read(new File(iconPath));
			} catch (IOException e) {
				e.printStackTrace();
				icon= null;
			}
			icon=img;
			if(icon!=null)
				icons.put(iconPath, icon);
		}
		
		return icon;
	}
}
