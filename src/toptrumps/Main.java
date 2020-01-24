package toptrumps;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		DataGame model = DataGame.getInstance(4);
		ViewCLI view = new ViewCLI(model); // we need to pass in view here
		Controller controller = new Controller(model, view);
		controller.startGame();
	}

}