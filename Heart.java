import java.awt.*;
import javax.swing.*;

import javax.swing.ImageIcon;

public class Heart {
	private int heartNum, width, height;
	private Image img;

	public Heart() {
		heartNum =5;

		ImageIcon ic = new ImageIcon(this.getClass().getResource(
				"pic/obj/heart.png"));
		img = ic.getImage();
		width = img.getWidth(null);
		height = img.getHeight(null);

	}

	public void setHeartNum(int heartNum)
	{
		this.heartNum = heartNum;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Image getImage()
	{
		return img;
	}

}