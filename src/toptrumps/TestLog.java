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
	 private String outF="testlog.txt";
	 
	 //constructor
	 public TestLog(DataGame model) {
		 this.model=model;
	 }
	 

	public void writeDeckContents() {

          FileWriter fw = null;
          BufferedWriter bw = null;
          try {
              fw = new FileWriter(outF);
              bw = new BufferedWriter(fw);
              bw.write("Initial Unshuffled Deck");
              bw.newLine();
              bw.newLine();
              String header="Description Size Speed Range Firepower Cargo";
              bw.write(header);
              bw.newLine();
              bw.newLine();
             
               for(int i=0;i<model.getInitialUnshuffledDeck().length;i++) {
            	 
            	   String space=" ";
            	   String description =model.getInitialUnshuffledDeck()[i].getDescription();
            	   String size=String.valueOf(model.getInitialUnshuffledDeck()[i].getSize());
            	   String speed=String.valueOf(model.getInitialUnshuffledDeck()[i].getSpeed());
            	   String range=String.valueOf(model.getInitialUnshuffledDeck()[i].getRange());
            	   String firepower=String.valueOf(model.getInitialUnshuffledDeck()[i].getFirePower());
            	   String cargo=String.valueOf(model.getInitialUnshuffledDeck()[i].getCargo());
                   bw.write(description);
                   bw.write(space);
                   bw.write(size);
                   bw.write(space);
                   bw.write(speed);
                   bw.write(space);
                   bw.write(range);
                   bw.write(space);
                   bw.write(firepower);
                   bw.write(space);
                   bw.write(cargo);
                   bw.newLine();
                   bw.newLine();
               }
               bw.close();
                 
            
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
		 FileWriter fw = null;
         BufferedWriter bw = null;
         try {
             fw = new FileWriter(outF,true);
             bw = new BufferedWriter(fw);
             String separator="------------------------------------------------------------------------";
             bw.write(separator);
             bw.newLine();
             bw.newLine();
             bw.write("Initial Shuffled Deck");
             bw.newLine();
             bw.newLine();
             String header="Description Size Speed Range Firepower Cargo";
             
             bw.write(header);
             bw.newLine();
             bw.newLine();
            
              for(int i=0;i<model.getInitialShuffledDeck().length;i++) {
           	 
           	   String space=" ";
           	   String description =model.getInitialShuffledDeck()[i].getDescription();
           	   String size=String.valueOf(model.getInitialShuffledDeck()[i].getSize());
           	   String speed=String.valueOf(model.getInitialShuffledDeck()[i].getSpeed());
           	   String range=String.valueOf(model.getInitialShuffledDeck()[i].getRange());
           	   String firepower=String.valueOf(model.getInitialShuffledDeck()[i].getFirePower());
           	   String cargo=String.valueOf(model.getInitialShuffledDeck()[i].getCargo());
                  bw.write(description);
                  bw.write(space);
                  bw.write(size);
                  bw.write(space);
                  bw.write(speed);
                  bw.write(space);
                  bw.write(range);
                  bw.write(space);
                  bw.write(firepower);
                  bw.write(space);
                  bw.write(cargo);
                  bw.newLine();
                  bw.newLine();
              }
              bw.close();
                
           
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
	public void writePlayerDecks() {
		FileWriter fw = null;
    BufferedWriter bw = null;
    try {
        fw = new FileWriter(outF,true);
        bw = new BufferedWriter(fw);
        String separator="------------------------------------------------------------------------";
        bw.write(separator);
        bw.newLine();
        bw.newLine();
        bw.write("Player Decks");
        bw.newLine();
        bw.newLine();
      
        for (int j=0;j<model.getActivePlayers().length;j++) {
        	String player=model.getActivePlayers()[j].getName();
        	  bw.write(player);
        	  bw.newLine();
        	  bw.newLine();
   		    String header="Description Size Speed Range Firepower Cargo";
            bw.write(header);
            bw.newLine();
            bw.newLine();
        	 for(int i=0;i<model.getActivePlayers()[j].getDeck().size();i++) {
            	   String space=" ";
            	   String description =model.getActivePlayers()[j].getDeck().get(i).getDescription();
            	   String size=String.valueOf(model.getActivePlayers()[j].getDeck().get(i).getSize());
            	   String speed=String.valueOf(model.getActivePlayers()[j].getDeck().get(i).getSpeed());
            	   String range=String.valueOf(model.getActivePlayers()[j].getDeck().get(i).getRange());
            	   String firepower=String.valueOf(model.getActivePlayers()[j].getDeck().get(i).getFirePower());
            	   String cargo=String.valueOf(model.getActivePlayers()[j].getDeck().get(i).getCargo());
                   bw.write(description);
                   bw.write(space);
                   bw.write(size);
                   bw.write(space);
                   bw.write(speed);
                   bw.write(space);
                   bw.write(range);
                   bw.write(space);
                   bw.write(firepower);
                   bw.write(space);
                   bw.write(cargo);
                   bw.newLine();
                   bw.newLine();
               }
        	 bw.write("----------------------------");
        	 bw.newLine();
        	 
        	 
        }
        
         bw.close();
           
      
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
    }}

	public void writeCommunalPile() {
		  FileWriter fw = null;
          BufferedWriter bw = null;
          try {
              fw = new FileWriter(outF,true);
              bw = new BufferedWriter(fw);
              bw.write("Communal Pile Contents");
              bw.newLine();
              bw.newLine();
              String header="Description Size Speed Range Firepower Cargo";
              bw.write(header);
              bw.newLine();
              bw.newLine();
             
               for(int i=0;i<model.getCardsInCommonPile().length;i++) {
            	 
            	   String space=" ";
            	   String description =model.getCardsInCommonPile()[i].getDescription();
            	   String size=String.valueOf(model.getCardsInCommonPile()[i].getSize());
            	   String speed=String.valueOf(model.getCardsInCommonPile()[i].getSpeed());
            	   String range=String.valueOf(model.getCardsInCommonPile()[i].getRange());
            	   String firepower=String.valueOf(model.getCardsInCommonPile()[i].getFirePower());
            	   String cargo=String.valueOf(model.getCardsInCommonPile()[i].getCargo());
                   bw.write(description);
                   bw.write(space);
                   bw.write(size);
                   bw.write(space);
                   bw.write(speed);
                   bw.write(space);
                   bw.write(range);
                   bw.write(space);
                   bw.write(firepower);
                   bw.write(space);
                   bw.write(cargo);
                   bw.newLine();
                   bw.newLine();
               }
               bw.close();
                 
            
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
	public void writeCardsInPlay() {
		FileWriter fw = null;
	    BufferedWriter bw = null;
	    try {
	        fw = new FileWriter(outF,true);
	        bw = new BufferedWriter(fw);
	        String separator="------------------------------------------------------------------------";
	        bw.write(separator);
	        bw.newLine();
	        bw.newLine();
	        bw.write("Player Decks");
	        bw.newLine();
	        bw.newLine();
	      
	        for (int j=0;j<model.getActivePlayers().length;j++) {
	        	String player=model.getActivePlayers()[j].getName();
	        	  bw.write(player);
	        	  bw.newLine();
	        	  bw.newLine();
	   		    String header="Description Size Speed Range Firepower Cargo";
	            bw.write(header);
	            bw.newLine();
	            bw.newLine();
	        
	            	   String space=" ";
	            	   String description =model.getActivePlayers()[j].getDeck().get(0).getDescription();
	            	   String size=String.valueOf(model.getActivePlayers()[j].getDeck().get(0).getSize());
	            	   String speed=String.valueOf(model.getActivePlayers()[j].getDeck().get(0).getSpeed());
	            	   String range=String.valueOf(model.getActivePlayers()[j].getDeck().get(0).getRange());
	            	   String firepower=String.valueOf(model.getActivePlayers()[j].getDeck().get(0).getFirePower());
	            	   String cargo=String.valueOf(model.getActivePlayers()[j].getDeck().get(0).getCargo());
	                   bw.write(description);
	                   bw.write(space);
	                   bw.write(size);
	                   bw.write(space);
	                   bw.write(speed);
	                   bw.write(space);
	                   bw.write(range);
	                   bw.write(space);
	                   bw.write(firepower);
	                   bw.write(space);
	                   bw.write(cargo);
	                   bw.newLine();
	                   bw.newLine();
	            
	        	 bw.write("----------------------------");
	        	 bw.newLine();
	        	 
	        	 
	        }
	        
	         bw.close();
	           
	      
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
	//HOW TO GET NON HUMAN CATEGORY SELECTIONS AND WHO SELECTED?
	public void writeCategorySelected() {}
	
	public void writeGameWinner() {
		FileWriter fw = null;
	    BufferedWriter bw = null;
	    try {
	        fw = new FileWriter(outF,true);
	        bw = new BufferedWriter(fw);
	        String separator="------------------------------------------------------------------------";
	        bw.write(separator);
	        bw.newLine();
	        bw.newLine();
	        bw.write("Game Winner");
	        bw.newLine();
	        bw.newLine();
	        String gameWinner=model.getGameWinner().getName();
	        bw.write(separator);
	         bw.close();
	           
	      
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
	
    public static void main(String[] args) {
    	
        
          DataGame model = DataGame.resetAndGetInstance(4);
			model.startGame();
			   TestLog tester = new TestLog(model);
		          tester.writeDeckContents();
		          tester.writeShuffledDeckContents();
		          tester.writePlayerDecks();
		          tester.writeCardsInPlay();
		          tester.writeCommunalPile();
		          

    }

}

