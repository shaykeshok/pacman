package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;

import javax.swing.JFrame;


public class ObjectParam extends JFrame {
	private Button done;
	public TextField weightText, weightTextEdit,yLoc,xLoc, yLocText,xLocText, raduisText,raduisTextEdit;
	private String objectName;
	

	public ObjectParam(int x, int y, String _objectName) {
		yLoc.setText(String.valueOf(y));
		yLocText.setText("y Point:");
		xLoc.setText(String.valueOf(x));
		xLocText.setText("x Point:");
		objectName = _objectName;
		initWindow();
		setTitle("parameters for object");
	}

	private void initWindow() {
		raduisText.setText("radius:");
		if (objectName.equals("pacman"))
			weightText.setText("speed:");
		else {
			weightText.setText("weight:");
			raduisText.hide();
		}
	}

	public void paint(Graphics g) {
		// Image image = Toolkit.getDefaultToolkit().getImage("example.jpg");
		int w = this.getWidth();
		int h = this.getHeight();
		g.setColor(Color.red);
		g.fillOval(w / 3, h / 3, w / 3, h / 3);
		g.setColor(Color.blue);
		String s = " [" + w + "," + h + "]";
		g.drawString(s, w / 3, h / 2);

	}
}
