package toptrumps;

public class Main {
    public static void main(String[] args) {
        DataGame dataGame = new DataGame(4);
        ViewCLI viewCli = new ViewCLI();
        Controller controller = new Controller(dataGame,viewCli);
        controller.startGame();
    }
}
