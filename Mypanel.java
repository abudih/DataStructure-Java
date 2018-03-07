/**
 * @author Andrew Budihardja
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Mypanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mypanel(){
		setBackground(Color.blue);
	}
	
	protected void paintComponent(Graphics g) {
		//Always call super.paintComponent on the first line
		super.paintComponent(g);
		//graphics2d has more functionalities than Graphics
		Graphics2D g2d = (Graphics2D) g;
		//paint a polygon
		//creates 15 random points in the panel
		int [] xp = new int[15];
		int[] yp = new int[15];
		for(int i =0;i<xp.length;i++){
			xp[i] = (int)(Math.random()*getWidth());
			yp[i] = (int)(Math.random()*getHeight());
		}
		
		//select a random color
		int alpha = (int)(Math.random() *256); //amount of transparency
		int red = (int)(Math.random() *256);
		int blue = (int)(Math.random() *256);
		int green = (int)(Math.random() *256);
		
		Color color = new Color(alpha,red,blue,green);
		g2d.setColor(color);
		g2d.fillPolygon(xp,yp,xp.length);
		
	}
	

}
