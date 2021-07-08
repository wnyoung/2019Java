import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

class Front extends JPanel
{	
	public static String userID = "이름없음";
	static JPanel front_page;
	JPanel game_page;
	JProgressBar progBar;
	ImageIcon img = new ImageIcon("pic/background/lollogo.jpg");
	Image logo = img.getImage();
	ImageIcon img2 = new ImageIcon("pic/background/front.jpg");
	Image logo2 = img2.getImage();
	JLabel back;

	public Front(){
		File file3 = new File("music/charge.wav");
		//System.out.println(file3.exists()); //true
        
	try {
	AudioInputStream stream3 = AudioSystem.getAudioInputStream(file3);
	Clip clip3 = AudioSystem.getClip();
	clip3.open(stream3);
	clip3.start();
	} catch(Exception e) {
            
	e.printStackTrace();
		}
		setLayout(null);
		setBackground(Color.yellow);  //로딩중 페이지 넣기
		front_page = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawImage(logo2, 0, 0, null);
			}
		};

		front_page.setLayout(null);
		front_page.setPreferredSize(new Dimension(800, 700));
//		front_page.setBackground(new Color(255, 255, 255, 0));
		front_page.setBackground(new Color(0, 0, 0, 0));
		progBar = new JProgressBar(0,100);
		front_page.add(progBar);
		progBar.setBounds(280, 600, 220, 20);
		front_page.setBounds(0, 0, 800, 700);
	

		JLabel iDlabel = new JLabel("아이디를 입력하세요");
		iDlabel.setFont(new Font("나눔스퀘어_ac Bold", Font.BOLD, 20));
		iDlabel.setForeground(Color.white);
		iDlabel.setBackground(new Color(0, 0, 0, 0));

		JTextField text = new JTextField(6);
		text.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 20));
		Button loginButton = new Button("go!");
		loginButton.setFont(new Font("나눔스퀘어_ac Bold", Font.BOLD, 20));
		front_page.add(iDlabel);
		front_page.add(text);
		front_page.add(loginButton);
		iDlabel.setBounds(300, 320, 200, 30);
		text.setBounds(320, 350, 100, 30);
		loginButton.setBounds(430, 350, 50, 30);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!text.getText().isEmpty())
					userID = text.getText();
				new Thread(new ProgControl()).start();
			}
		});

		front_page.setVisible(true);
		//game_page.setVisible(false);

		add("Center", front_page);
		//add("Center", game_page);

	}


	class ProgControl implements Runnable
	{
	
		public void run() {
			// 만약 증가 이벤트라면.
			for (int i = 0; i < 101; i++) {
				progBar.setValue(i);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		front_page.setVisible(false);
		game_page = new Combine();//
		game_page.setPreferredSize(new Dimension(800, 700));
		game_page.setBounds(0, 0, 800, 700);
		add(game_page);
		revalidate();


		game_page.setVisible(true);
		game_page.requestFocus();
		game_page.setFocusable(true);
		//revalidate();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(logo, 0, 0, null);
	}
}
