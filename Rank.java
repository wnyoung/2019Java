import java.util.*; 
import java.io.*;
 
public class Rank {
   int[] scoreArray = new int[6];
   String[] nameArray = new String[6];
   int ranking =7;


   
    public Rank(int score){
        try{
            //파일 객체 생성
            File file = new File("input_data.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
         int i =0;
            while((line = bufReader.readLine()) != null){
            StringTokenizer split = new StringTokenizer(line, ",");
            while(split.hasMoreTokens()){
               nameArray[i]=split.nextToken();
               //System.out.println("\n" + nameArray[i]);

               scoreArray[i]=Integer.parseInt(split.nextToken());
               //System.out.println("\n" + scoreArray[i]);
            }
            i++;
            }
         
         for(int j=0;j<5;j++){
            if((score)>=scoreArray[j]){
               for(int k=4; k>j; k--){
                  scoreArray[k] = scoreArray[k-1];
                  nameArray[k] = nameArray[k-1];
               }
               scoreArray[j]=score;
               nameArray[j]=Front.userID;
               ranking = j;
               break;
            }
         }

         FileWriter filewriter = new FileWriter(file);
         BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

         bufferedWriter.write(nameArray[0]+","+scoreArray[0]);bufferedWriter.newLine();
         bufferedWriter.write(nameArray[1]+","+scoreArray[1]);bufferedWriter.newLine();
         bufferedWriter.write(nameArray[2]+","+scoreArray[2]);bufferedWriter.newLine();
         bufferedWriter.write(nameArray[3]+","+scoreArray[3]);bufferedWriter.newLine();
         bufferedWriter.write(nameArray[4]+","+scoreArray[4]);

         bufferedWriter.flush();
         bufferedWriter.close();
         



        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
    }

   public int[] getScoreArray()
   {
      return scoreArray;
   }

   public String[] getNameArray()
   {
      return nameArray;
   }

   public int getRanking()
   {
      return ranking;
   }

}