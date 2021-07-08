import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
//Áß¾Ó 280 250
class End extends JPanel
{
   Integer score=0;
   Font font = new Font("³ª´®½ºÄù¾î_ac Bold", Font.PLAIN, 20);
   ImageIcon[] img = {new ImageIcon("pic/background/lolvictory.png"),
      new ImageIcon("pic/background/loldefeat.png")};
   Image[] logo = { img[0].getImage(), img[1].getImage()};
   int[] scoreArray;
   String[] nameArray;
   int ranking;

   public End(Integer[] s) 
   {
      this.score = s[0];
      setBackground(new Color(0,0,0,150));
      Rank rank = new Rank(score);
      scoreArray = rank.getScoreArray();
      nameArray = rank.getNameArray();
      ranking = rank.getRanking();
   }

   public void paintComponent(Graphics g){
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setFont(font);
      g2.setColor(Color.red);
      g2.drawString(Front.userID+" ´Ô", 320, 275);
      g2.drawString("Á¡¼ö: "+score+"Á¡", 320, 305);
      g2.setColor(Color.yellow);
      g2.drawString("RANKING", 50, 280);
      for(int i=0;i<5;i++){
         if(ranking == i ){
            g2.setColor(Color.red);
            g2.drawString((i+1)+"µî: "+nameArray[i]+"  "+scoreArray[i]+"Á¡" +"(NEW)", 50, 280+(i+1)*30);
         }
         else{
            g2.setColor(Color.yellow);
            g2.drawString((i+1)+"µî: "+nameArray[i]+"  "+scoreArray[i]+"Á¡", 50, 280+(i+1)*30);
		}
	  }

      if(ranking < 5){
         g.drawImage(logo[0], 120, 20, null);
      }else{
         g.drawImage(logo[1], 120, 20, null);
      }

   }

}