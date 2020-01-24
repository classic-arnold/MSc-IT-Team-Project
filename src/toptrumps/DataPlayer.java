package toptrumps;

import java.util.ArrayList;
import java.util.Objects;

public class DataPlayer {
    enum PlayerType {
        AI, HUMAN
    }

    /** static int to keep track of AI player count */
    private static int ARTIFICIAL_INTELLIGENCE_ID = 1;

    /** DataCard array representing the deck of cards */
    private ArrayList<DataCard> cardDeck = new ArrayList<DataCard>();

    /** PlayerType enum representing the type of player - human or AI */
    private PlayerType type;

    /** integer representing the player's current score */
    private int score;

    /** String representing the player's name */
    private String name;

    DataPlayer(PlayerType type, DataGame game) {
        this.type = type;

        if (this.type == PlayerType.HUMAN) {
            this.name = "You";
            this.score = 0;
        } else if (this.type == PlayerType.AI) {
            this.name = "AI Player " + ARTIFICIAL_INTELLIGENCE_ID++;
            this.resetArtificialIntelligenceId();
            this.score = 0;
        }
    }

    public void resetArtificialIntelligenceId(){
        if(DataPlayer.ARTIFICIAL_INTELLIGENCE_ID>4) {
            DataPlayer.ARTIFICIAL_INTELLIGENCE_ID = 1;
        }
    }

    public void addCardsToDeck(ArrayList<DataCard> newCards) {
        for(DataCard card : newCards) {
            this.cardDeck.add(card);
        }
    }

    public void addCardToDeck(DataCard newCard) {
        this.cardDeck.add(newCard);
    }

    //First-in, first-out simulation queue
    public DataCard popDataCard(){
        //Determining empty throw exception
        Objects.requireNonNull(this.cardDeck);

        DataCard dataCard = this.cardDeck.remove(0);
        return dataCard;
    }

    //Get the head element without deleting
    public DataCard getDataCard(){
        Objects.requireNonNull(this.cardDeck);

        return this.cardDeck.get(0);
    }

    public boolean isEmpty(){
        return this.cardDeck.isEmpty();
    }

    public boolean isHuman(){
        return this.type.equals(PlayerType.HUMAN);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<DataCard> getDeck() {
        return this.cardDeck;
    }

    public int getNumberOfCardsInDeck() {
        return this.cardDeck.size();
    }

}
