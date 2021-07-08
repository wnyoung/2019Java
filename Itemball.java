//package balloone_Shooting;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Itemball {
	private int x, y, width, height, velocity;
	private Image img;
	String[] item_img = {"pic/falls/item1.png", "pic/falls/item2.png", "pic/falls/item3.png"};

	public Itemball(int x) {
		ImageIcon ic = new ImageIcon(this.getClass().getResource(
				item_img[x%3]));
		img = ic.getImage();
		this.x = x;
		y = 0;
		width = img.getWidth(null);
		height = img.getHeight(null);
		this.velocity=3;
	}

	public int getVelocity(){
		return velocity;
	}
	public void setVelocity(int v){
		velocity = v;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void move() {
		y+=velocity;
	}
	public Image getImage()
	{
		return img;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
