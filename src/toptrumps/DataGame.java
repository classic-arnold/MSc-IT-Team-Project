package toptrumps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class DataGame {

    /** represents a list of players in the game */
    private ArrayList<DataPlayer> players = new ArrayList<DataPlayer>();

    /** represents the current round number */
    private int roundNumber;

    /** represents the common deck */
    private DataCommonDeck commonDeck = new DataCommonDeck();

    /** represents the deck of cards */
    private ArrayList<DataCard> originalDeck = new ArrayList<DataCard>();

    /** represents the winner */
    private DataPlayer winner;

    /** represents the status of last round */
    private boolean lastRoundWasDraw;

    /** represents the status of last round */
    private DataPlayer lastWinner;

    /** represents the status of last round */
    private DataCard[] lastRoundWinningCards;

    /**
     * creates a new DataGame Object
     * @param numberOfArtificialIntelligencePlayers represents the number of AI player in the game
     */
    public DataGame(int numberOfArtificialIntelligencePlayers) {

        this.players.add(new DataPlayer(DataPlayer.PlayerType.HUMAN, this)); // add one human player

        // add the artificial intelligence players to the DataGame players depending on the number specified above
        for(int i=0;i<numberOfArtificialIntelligencePlayers;i++) {
            this.players.add(new DataPlayer(DataPlayer.PlayerType.AI, this));
        }
    }

    /**
     * starts the game
     */
    public void startGame() {

        numberOfGames++;
        avgNumberOfDraws = 0;
        this.roundNumber = 0;

        DataCardCache.loadCardFromFileAndCache();

        this.originalDeck = this.shuffleDeck(this.getNewDeck()); // shuffle the deck

        int numberOfCardsPerPlayer = this.originalDeck.size()/this.players.size();

        for(DataPlayer player : this.players) {
            for(int i = 0; i<numberOfCardsPerPlayer; i++) {
                player.addCardToDeck(this.originalDeck.remove(0));
                ;
            }
        }

        this.originalDeck.clear(); // clear the original deck

        this.incrementRound(); // increase the round number

    }

    /**
     * shuffles the deck by rearranging the cards randomly
     * @param deck the list of cards that needs to be shuffled
     * @return ArrayList<DataCard> representing a new deck
     */
    public ArrayList<DataCard> shuffleDeck(ArrayList<DataCard> deck) {
        // if deck.size() is 0, throw exception as there is nothing to shuffle
        if(deck.size()==0) {
//            throw new exceptions.NoCardInDeckException();
            throw new RuntimeException("No Card In Deck");
        }

        ArrayList<DataCard> shuffledDeck = new ArrayList<DataCard>();

        HashSet<Integer> listOfRandoms = new HashSet<Integer>();

        Random r = new Random();

        // run this loop until the shuffledDeck has 40 cards
        while(shuffledDeck.size()<40){
            int randomNumber;

            // check if this random number has been used before, and keep getting random numbers until we find one that hasn't
            do{
                randomNumber = r.nextInt(deck.size());
            } while(listOfRandoms.contains(randomNumber));

            shuffledDeck.add(deck.get(randomNumber));
            listOfRandoms.add(randomNumber);
        }

        return shuffledDeck;
    }

    /**
     * used to get a fresh new deck
     * @return ArrayList representing lists of cards in new deck
     */
    public ArrayList<DataCard> getNewDeck() {
        return DataGame.arrayToArrayList(DataCardCache.getAllCardsInOrder());
    }

    public void incrementRound() {
        this.roundNumber += 1;
    }

    /**
     * Static method used to convert an array of generic objects to an ArrayList
     * @param <t> generic type
     * @param array original array
     * @return an ArrayList shallow copy of array
     */
    public static <t> ArrayList<t> arrayToArrayList(t[] array){
        ArrayList<t> arrayList = new ArrayList<t>();
        for(int i = 0; i<array.length;i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    /**
     * Static method used to convert an ArrayList of DataCard to an array
     * @param arrayList original array list
     * @return an array shallow copy of ArrayList
     */
    public static DataCard[] arrayListToArrayCard(ArrayList<DataCard> arrayList){
        int length = arrayList.size();
        DataCard[] array = new DataCard[length];
        for(int i = 0; i<length;i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    /**
     * Static method used to convert an ArrayList of DataPlayer to an array
     * @param arrayList original array list
     * @return an array shallow copy of ArrayList
     */
    public static DataPlayer[] arrayListToArrayPlayer(ArrayList<DataPlayer> arrayList){
        int length = arrayList.size();
        DataPlayer[] array = new DataPlayer[length];
        for(int i = 0; i<length;i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    /**
     * plays a round
     * @param category string representing the chosen category
     */
    public void playRound(String category) {

        ArrayList<DataCard> roundCards = new ArrayList<DataCard>();

        for(DataPlayer player : this.players) {
            if(!player.isEmpty()){
                roundCards.add(player.getDataCard());
            }
        }

        HashMap<String, Object> winningCardsAndPlayers = this.getWinningCardsAndPlayers(DataGame.arrayListToArrayCard(roundCards), category);

        this.lastRoundWinningCards = DataGame.arrayListToArrayCard((ArrayList<DataCard>)winningCardsAndPlayers.get("winning cards"));

        HashSet<DataPlayer> dataPlayers = (HashSet<DataPlayer>) winningCardsAndPlayers.get("winning players");

        for(DataPlayer player : this.players) {
            if(!player.isEmpty()){
                player.popDataCard();
            }
        }
        //The winner will own the Card
        if(lastRoundWinningCards.length == 1 && !dataPlayers.isEmpty()){
            lastWinner = dataPlayers.iterator().next();
            if(!this.commonDeck.isEmpty()){
                lastWinner.addCardsToDeck(commonDeck.pop());
            }
            lastWinner.addCardsToDeck(roundCards);
        }else {
            this.commonDeck.addAll(roundCards);
            lastRoundWasDraw = true;
            avgNumberOfDraws++;
        }

        this.incrementRound(); // increase the round number

    }

    /**
     * takes an array of cards and return the winning cards
     * @param cards the array of all cards
     * @param category the category selected
     * @return HashMap<String, Object> containing list of winning cards and a set of winning players
     */
    public HashMap<String, Object> getWinningCardsAndPlayers(DataCard[] cards, String category) {
        HashMap<String, Object> results = new HashMap<String, Object>();
        ArrayList<DataCard> winningCards = new ArrayList<DataCard>(); // initialize winning cards list
        HashSet<DataPlayer> winningPlayers = new HashSet<DataPlayer>(); // initialize winning cards list

        DataCard lastWinnerCard = cards[0]; //store 1st card as winning card

        // check each card against last winning card
        for(int i=0; i<cards.length; i++) {
            // if both cards equal, else if current card greater than last winning card
            if (cards[i].compare(lastWinnerCard, category) == 2) {
                lastWinnerCard = cards[i];
                winningCards.add(cards[i]); // add new higher card

                // store winning players
                for (DataPlayer player : this.players) {
                    if(player.getDeck().contains(cards[i])) {
                        winningPlayers.add(player);
                    }
                }

            } else if (cards[i].compare(lastWinnerCard, category) == 1) {
                lastWinnerCard = cards[i];
                winningCards.clear(); // reset list of winning cards, because we have a new higher card
                winningCards.add(cards[i]); // add new higher card

                // store winning players
                for (DataPlayer player : this.players) {
                    if(player.getDeck().contains(cards[i])) {
                        winningPlayers.clear();
                        winningPlayers.add(player);
                    }
                }
            }
        }

        // store both lists in results
        results.put("winning cards", winningCards);
        results.put("winning players", winningPlayers);
        return results;
    }

    public boolean isGameOver(){
        if(this.originalDeck.size() == 0) {
            // check if any player has all cards. If they do, game is over, and that player is the winner
            for(DataPlayer player : players) {
                if(player.getDeck().size() == 40) {
                    // store winner and game state
                    if(player.isHuman()){
                        numberOfHumanWins++;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumberOfCardsInCommonPile() {
        return this.commonDeck.size();
    }

    public DataPlayer[] getPlayers() {
        return DataGame.arrayListToArrayPlayer(this.players);
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public DataCommonDeck getCommonDeck() {
        return commonDeck;
    }

    public ArrayList<DataCard> getOriginalDeck() {
        return originalDeck;
    }

    public DataPlayer getWinner() {
        return winner;
    }

    public boolean isLastRoundWasDraw() {
        return lastRoundWasDraw;
    }

    public DataPlayer getLastRoundWinner() {
        return lastWinner;
    }

    public DataCard[] getLastRoundWinningCards() {
        return lastRoundWinningCards;
    }

    private int numberOfGames;
    private int numberOfHumanWins;
    private int avgNumberOfDraws;

    public int getNumberOfGames(){
        return numberOfGames;
    }

    public int getLongestGame(){
        return roundNumber-1;
    }

    public int getAvgNumberOfDraws(){
        return avgNumberOfDraws;
    }

    public int getNumberOfHumanWins(){
        return numberOfHumanWins;
    }

    public int getNumberOfAIWins(){
        return getNumberOfGames() - getNumberOfHumanWins();
    }
}
