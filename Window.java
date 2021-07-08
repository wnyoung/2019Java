//package balloone_Shooting;

import javax.swing.JFrame;
import javax.swing.*;

public class Window extends JFrame{
	public Window(String msg) {
		super(msg);

		Front f = new Front();
		f.requestFocus();
		f.setFocusable(true);


		add(f);
		setResizable(false);
		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}