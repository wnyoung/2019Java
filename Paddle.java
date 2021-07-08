//package balloone_Shooting;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Paddle {
	private int x, y, velocity=2;
	private boolean left = false, right = false;

	Image volcano;
	ImageIcon big = new ImageIcon(this.getClass()
			.getResource("pic/obj/volcano_big.png"));
	ImageIcon small = new ImageIcon(this.getClass().getResource(
			"pic/obj/volcano_small.png"));

	public Paddle() {
		x = 395;
		y = 570;
		volcano = big.getImage();
	}
	public int getVelocity(){
		return velocity;
	}
	public void setVelocity(int v){
		velocity = v;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean getLeft() {
		return left;

	}

	public boolean getRight() {
		return right;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return volcano;
	}

	public void left() {
		x -= velocity;
		if (x <= 0)
			x = 0;
	}

	public void right() {
		x += velocity;
		if (x >= 706)
			x = 706;
	}

	public void volcano_Pressed() {
		y = 570;
		volcano = small.getImage();
	}

	public void volcano_Release() {
		y = 560;
		volcano = big.getImage();
	}

}
