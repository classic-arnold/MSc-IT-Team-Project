package toptrumps;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/**
 * TestLog- creates a log of data created when model updates
 *  @author Team TRY-CATCH - Anne-Marie Gill 2431989G
 */
//NEED TO CONNECT TO MODEL AND CONTROLLER TO TEST.ALSO CLARIFY WHAT SHOULD BE IN EACH CATEGORY
public class TestLog {
	 private DataGame model;
	 
	//does this return shuffled or unshuffled deck currently?
	public void writeDeckContents() {
		  String outF = "testlog.txt";
          
          FileWriter fw = null;
          BufferedWriter bw = null;
          try {
              fw = new FileWriter(outF);
              bw = new BufferedWriter(fw);
               for(int i=0;i<model.getCompleteDeckAsArray().length;i++) {
            	   String nextLine =model.getCompleteDeckAsArray()[i].getDescription();
                   // write the line to the file
                   bw.write(nextLine);
                   bw.newLine();
               }
                 
            
          }catch(IOException e) {
              e.printStackTrace();
          }finally {
             
              if(fw!=null) {
                  try {
                      fw.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
	}
	public void writeShuffledDeckContents() {
		String outF = "testlog.txt";
        
        FileWriter fw = null;
        try {
        
            fw = new FileWriter(outF,true);
           
                String newLine ="goodbye";
                // write the line to the file
                fw.write(newLine);
          
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
           
            if(fw!=null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
	public void writeInitialPlayerDecks() {}
	public void writeCommunalPile() {}
	public void writeCardsInPlay() {}
	public void writeCategorySelected() {}
	public void writeCurrentPlayerDecks() {}
	public void writeGameWinner() {}
	
    public static void main(String[] args) {
          TestLog tester = new TestLog();
          tester.writeDeckContents();
          tester.writeShuffledDeckContents();

    }

}

