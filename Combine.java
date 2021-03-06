//package balloone_Shooting;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Dimension;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

import javax.swing.*;

public class Combine extends JPanel implements ActionListener {
	Heart heart_obj = new Heart();

	ImageIcon[] back = {
		new ImageIcon(this.getClass().getResource("pic/background/skybackground1.jpg")),
		new ImageIcon(this.getClass().getResource("pic/background/skybackground2.jpg")),
		new ImageIcon(this.getClass().getResource("pic/background/skybackground3.jpg"))
	};
	Image[] back_Ground = {
		back[0].getImage(), back[1].getImage(), back[2].getImage()
	};
	Timer timer;
	Integer[] score = {0};
	int heart = 5, play_time=0;

	Paddle paddle;
	ArrayList<FireBall> fireballs_array;
	ArrayList<Cloudball> cloud_array;
	ArrayList<Itemball> item_array;
	FireBall fireBall;
	private boolean fire;
	static boolean flag = false;
	JPanel end_page;
	JButton suspendBt, resumeBt;
	Thread startTime, startCloud, startFire, movePaddle, collide;

	public void makeResume() {
		setEnd(false);
		startTime.resume();
		startCloud.resume();
		startFire.resume();		
		movePaddle.resume();
		collide.resume();
	}
	public void makeSuspend() {
		setEnd(true);
		startTime.suspend();
		startCloud.suspend();
		startFire.suspend();		
		movePaddle.suspend();
		collide.suspend();
	}
	public static boolean isEnd(){
		return flag;
	}
	public void setEnd(boolean flag){
		this.flag = flag;
	}
	public Combine() {

		File file = new File("music/test.wav");
		//System.out.println(file.exists()); //true
        
        try {            
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);            
        } catch(Exception e) {            
            e.printStackTrace();
        }		addKeyListener(new Action());

		addKeyListener(new Action());
		fireballs_array = new ArrayList<FireBall>();
		cloud_array = new ArrayList<Cloudball>();
		item_array = new ArrayList<Itemball>();

		paddle = new Paddle();
		setFocusable(true);
		timer = new Timer(70, this);
		timer.start();
		startTime = new Thread(new Runnable() {
			public void run() {
				while (true){
					if(!isEnd()){
					try{
					play_time++;
					Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					}
				}
				}
		});
		startCloud = new Thread(new Runnable() {
			public void run() {
				while (true){
					if(!isEnd()){
					try {
						if(play_time <= 5){
							cloudBalls(3, 5);
							Thread.sleep(1000);	
						}else if(play_time > 5 && play_time <= 18){
							paddle.setVelocity(3);
							itemBalls(5, 10);
							Thread.sleep(300);
							cloudBalls(3, 10);
							Thread.sleep(500);	
						}else{
							paddle.setVelocity(6);
							itemBalls(13, 17);
							Thread.sleep(80);
							cloudBalls(10, 15);
							Thread.sleep(10);	
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
				}
			}
		});
		startFire = new Thread(new Runnable() {
			public void run() {
				while (true){
					if(!isEnd()){
					try {
						if (fire == true) {
							fire();
						}
						if(play_time <= 5){
							Thread.sleep(200);
						}else if(play_time > 5 && play_time <= 18){
							Thread.sleep(150);
						}else{
							Thread.sleep(100);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
				}
			}
		});
		movePaddle = new Thread(new Runnable() {
			public void run() {
				while (true)
				{
					if(!isEnd()){
					try {
						if (paddle.getLeft() == true) {
							paddle.left();
						}
						if (paddle.getRight() == true) {
							paddle.right();
						}
						if(play_time <= 5){
							Thread.sleep(9);	
						}else if(play_time > 5 && play_time <= 18){
							Thread.sleep(7);	
						}else{
							Thread.sleep(2);	
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
				}
			}
		});
		startTime.start();
		startCloud.start();
		startFire.start();		
		movePaddle.start();
		if(isEnd()){
			startTime.suspend();
			startCloud.suspend();
			startFire.suspend();		
			movePaddle.suspend();
			collide.suspend();
		}
		
		this.setLayout(null);
		ImageIcon m1 = new ImageIcon("pic/user/Gnar1.png");
		ImageIcon m2 = new ImageIcon("pic/user/Gnar2.png");
		ImageIcon m3 = new ImageIcon("pic/user/Nautilus1.png");
		ImageIcon m4 = new ImageIcon("pic/user/Nautilus2.png");
		suspendBt = new JButton();
		suspendBt.setMnemonic(KeyEvent.VK_Z); 
		suspendBt.setIcon(m1);
        suspendBt.setPressedIcon(m2);
        suspendBt.setBorderPainted(false);
        suspendBt.setFocusPainted(false);
        suspendBt.setContentAreaFilled(false);
		suspendBt.setForeground(Color.blue);
		suspendBt.setBounds(340, 0, 80, 80);
		this.add(suspendBt);
		suspendBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				makeSuspend();
			}
		});
		resumeBt = new JButton();
		resumeBt.setMnemonic(KeyEvent.VK_X); 
		resumeBt.setIcon(m3);
        resumeBt.setPressedIcon(m4);
        resumeBt.setBorderPainted(false);
        resumeBt.setFocusPainted(false);
        resumeBt.setContentAreaFilled(false);
		resumeBt.setForeground(Color.blue);
		resumeBt.setBounds(420, 0, 80, 80);
		this.add(resumeBt);
		resumeBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				makeResume();
			}
		});
		suspendBt.setFocusable(false);
		resumeBt.setFocusable(false);
		this.requestFocus();
		this.setFocusable(true);


	}

	public void actionPerformed(ActionEvent arg0) {	
		if(!isEnd()){
		for (int k = 0; k < fireballs_array.size(); k++) {
			fireballs_array.get(k).move();
			if (fireballs_array.get(k).getY() < 1) {
				fireballs_array.remove(k);
			}
		}
		for (int i = 0; i < cloud_array.size(); i++) {
			cloud_array.get(i).move();
			if (cloud_array.get(i).getY() > 600) {
				cloud_array.remove(i);
				heart--;
				heart_obj.setHeartNum(heart);

				if(heart==0){			
					flag = true;
					end_page = new End(score);
					setLayout(null);
					end_page.setVisible(true);
					end_page.setBounds(120,100,550,500);
					
					add("Center", end_page);

					end_page.revalidate();
				}
			}
		}
		for (int i = 0; i < item_array.size(); i++) {
			item_array.get(i).move();
			if (item_array.get(i).getY() > 600) {
				item_array.remove(i);
				heart--;
				heart_obj.setHeartNum(heart);

				if(heart==0){		
					flag = true;
					end_page = new End(score);
					setLayout(null);
					end_page.setVisible(true);
					//end_page.setBounds(250,250,300,200);
					end_page.setBounds(120,100,550,500);
					
					add("Center", end_page);

					end_page.revalidate();
				}
			}
		}

		}
		collide = new Thread(new Runnable() {
			public void run() {
				try{collision();}
				catch(IndexOutOfBoundsException e){}
			}
		});
		collide.start();
		if(heart > -1) {
			repaint();
		}else if(heart == -1){
			do{
				repaint();
				setEnd(true);
			}while(isEnd() != true);
		}
		if(isEnd() == true) collide.suspend();
		else collide.resume();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
		if(play_time <= 5){
			g2.drawImage(back_Ground[0], 0, 0, null);
		}else if(play_time > 5 && play_time <= 18){
			g2.drawImage(back_Ground[1], 0, 0, null);
		}else{
			g2.drawImage(back_Ground[2], 0, 0, null);
		}
		g2.setColor(Color.red);
		g2.setFont(new Font("??????????_ac Bold", Font.BOLD, 30));
		g2.drawString("HP", 25,45);
		g2.setColor(Color.white);
		g2.setFont(new Font("??????????_ac Bold", Font.BOLD, 35));
		g2.drawString("SCORE", 510, 45);
		g2.setFont(new Font("??????????_ac Bold", Font.BOLD, 35));
		g2.drawString(score[0].toString(), 650, 45);
		g2.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), null);
		
		if (!cloud_array.isEmpty()) {
			for (int i = 0; i < cloud_array.size(); i++) {
				g2.drawImage(cloud_array.get(i).getImage(), cloud_array.get(i)
						.getX(), cloud_array.get(i).getY(), null);
			}
		}
		if (!item_array.isEmpty()) {
			for (int i = 0; i < item_array.size(); i++) {
				g2.drawImage(item_array.get(i).getImage(), item_array.get(i)
						.getX(), item_array.get(i).getY(), null);
			}
		}
		if (!fireballs_array.isEmpty()) {
			for (int k = 0; k < fireballs_array.size(); k++) {
				g2.drawImage(fireballs_array.get(k).getImage(), fireballs_array
						.get(k).getX(), fireballs_array.get(k).getY(), null);
			}
		}
		if (heart>-1) {
			for (int i = 0; i < heart; i++) {
				g2.drawImage(heart_obj.getImage(), 23+heart_obj.getWidth()*(i+1), 10, null);
				g2.setColor(Color.red);
				g2.fillRect(25+heart_obj.getWidth()*(i+1),55,heart_obj.getWidth()-5,5);
			}
		}
	}

	public void cloudBalls(int v1, int v2) {
		Random rn = new Random();
		int ran = rn.nextInt(730);
		Cloudball ball = new Cloudball(ran);
		Random rn2 = new Random();
		int a = v1, b = v2;
		ball.setVelocity(rn2.nextInt(b-a+1)+a);
		cloud_array.add(ball);
	}
	public void itemBalls(int v1, int v2) {
		Random rn = new Random();
		int ran = rn.nextInt(730);
		Itemball item = new Itemball(ran);
		Random rn2 = new Random();
		int a = v1, b = v2;
		item.setVelocity(rn2.nextInt(b-a+1)+a);
		item_array.add(item);
	}

	public void fire() {
		fireBall = new FireBall(paddle.getX() + 35);
		fireballs_array.add(fireBall);
	}

	public void collision() {
		for (int k = 0; k < fireballs_array.size(); k++) {
			for (int j = 0; j < cloud_array.size(); j++) {
				Rectangle r1;
				if(!fireballs_array.isEmpty()){
					 r1 = fireballs_array.get(k).getBounds();				
				Rectangle r2 = cloud_array.get(j).getBounds();
				if (r1.intersects(r2)) {  //fireball?? ????
					File file2 = new File("music/die.wav");
					//System.out.println(file2.exists()); //true
        
					try {
					AudioInputStream stream2 = AudioSystem.getAudioInputStream(file2);
					Clip clip2 = AudioSystem.getClip();
					clip2.open(stream2);
					clip2.start();            
					} catch(Exception e) {
            
		            e.printStackTrace();
				    }
					fireballs_array.remove(k);
					cloud_array.remove(j);
					score[0] ++;
				}
				}

			}
		}
		for (int k = 0; k < fireballs_array.size(); k++) {
			for (int j = 0; j < item_array.size(); j++) {
				Rectangle r1;
				if(!fireballs_array.isEmpty()){
					 r1 = fireballs_array.get(k).getBounds();				
				Rectangle r2 = item_array.get(j).getBounds();
				if (r1.intersects(r2)) {  //fireball?? ????
					File file2 = new File("music/die.wav");
					//System.out.println(file2.exists());
        
					try {
					AudioInputStream stream2 = AudioSystem.getAudioInputStream(file2);
					Clip clip2 = AudioSystem.getClip();
					clip2.open(stream2);
					clip2.start();            
					} catch(Exception e) {
            
		            e.printStackTrace();
				    }
					fireballs_array.remove(k);
					item_array.remove(j);
					if((item_array.get(j).getX())%3 == 0){
						score[0] += 10;
					} else if((item_array.get(j).getX())%3 == 0){
						score[0] += 7;
					} else{
						score[0] += 5;
					}
				}
				}

			}
		}
	}

	private class Action extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         int keyCode = e.getKeyCode();
         if (keyCode == KeyEvent.VK_SPACE)  {
			 if(Combine.isEnd() == false)  paddle.volcano_Pressed();
			 fire = true;
		 }
         if (keyCode == KeyEvent.VK_LEFT)  paddle.setLeft(true);
         if (keyCode == KeyEvent.VK_RIGHT)  paddle.setRight(true);
      }

      public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_SPACE)  {
			 paddle.volcano_Release();
			 fire = false;
		 }
         if (e.getKeyCode() == KeyEvent.VK_LEFT)  paddle.setLeft(false);
         if (e.getKeyCode() == KeyEvent.VK_RIGHT)  paddle.setRight(false);
      }
   }



}
