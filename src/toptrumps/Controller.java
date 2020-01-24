package toptrumps;
/**
 * @author Try-Catch Team (Jialiang Song 2410536s)
 */
import toptrumps.DataGame;
import toptrumps.DataPlayer;

import java.util.HashMap;
import java.util.Random;

/**
 * The controller update the Model and View according to user input
 */
public class Controller {
    private DataGame dataGame;
    private ViewCLI viewCli;
    private DataPlayer humman;

    public Controller(DataGame dataGame, ViewCLI viewCli) {
        this.dataGame = dataGame;
        this.viewCli = viewCli;
        humman = dataGame.getPlayers()[0];
    }

    public void startGame() {
        running();
    }

    public void running() {
        viewCli.chooseDisplay();
        while (viewCli.getStartChoice() == 2 || viewCli.getStartChoice() == 1) {
            if(viewCli.getStartChoice() == 2){
                dataGame.startGame();
                while (!dataGame.isGameOver()){
                    int roundnumber = dataGame.getRoundNumber();
                    viewCli.displayRound(roundnumber);
                    //玩家卡片
                    if(!humman.getDeck().isEmpty()){
                        viewCli.displayPlayerCard(humman.getDataCard());
                        viewCli.displayNumDeckCards(humman.getNumberOfCardsInDeck());
                    }

                    DataPlayer dataPlayer = dataGame.getLastRoundWinner();
                    if (dataPlayer == null) {
                        int n = new Random().nextInt(5);
                        dataPlayer = dataGame.getPlayers()[n];
                    }
                    //获取属性，判断是否需要随机
                    int categoryChoice = -1;
                    if (dataPlayer.isHuman()) {
                        viewCli.displayCategorySelection();
                        categoryChoice = viewCli.getCategoryChoice();
                    } else {
                        categoryChoice = new Random().nextInt(5)+1;
                    }

                    //比较
                    dataGame.playRound(DataCard.CATEGORYNAMES[categoryChoice-1]);

                    //输出结果
                    if (dataGame.isLastRoundWasDraw()) {
                        viewCli.displayRoundResult(dataGame.getLastRoundWinner(),roundnumber,dataGame.getLastRoundWinningCards()[0],categoryChoice, dataGame.getNumberOfCardsInCommonPile());
                    } else {
                        viewCli.displayRoundResult(dataGame.getLastRoundWinner(),roundnumber,dataGame.getLastRoundWinningCards()[0],categoryChoice, 0);
                    }
                }
                viewCli.displayStats(dataGame);
            } else if(viewCli.getStartChoice() == 1){
                viewCli.displayStats(dataGame);
            }
            viewCli.chooseDisplay();
        }
        System.out.println("exit");
    }

}


