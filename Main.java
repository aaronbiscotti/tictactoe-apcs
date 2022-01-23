 import java.util.Scanner;

class Main {
	public static String[][] ticboard = new String[3][3];
	public static Scanner sc = new Scanner(System.in);
	private static String letter = "";
	private static int row = 0;
	private static int column = 0;

	public static void main(String[] args) {
		System.out.println("Let's play Tic Tac Toe! I am X and you are O!");
		System.out
				.println("You have to enter coordinates for your location! Rows(1-3) Col(1-3) e.g. 1 3 for the first row, third column) COMPUTER GOES FIRST" );
		for (int i = 0; i < ticboard.length; i++) {
			for (int j = 0; j < ticboard.length; j++) {
				ticboard[i][j] = " ";
			}
		}

			
		while (!winCondition()) {
			Computer();
			if (winCondition() || Tie())
				break;
			UserInput();
			if (winCondition() || Tie())
				break;

		}
		if (Tie()) {
			System.out.println("Tie");
		}
		if (winCondition() && letter.equals("X")) {
			System.out.println("Computer won");
		}
		if (winCondition() && letter.equals("O")) {
			System.out.println("You won");
		}
	}

	public static void Display() {
		System.out.println(ticboard[0][0] + " | " + ticboard[0][1] + " | "
				+ ticboard[0][2]);
		System.out.println("---------");
		System.out.println(ticboard[1][0] + " | " + ticboard[1][1] + " | "
				+ ticboard[1][2]);
		System.out.println("---------");
		System.out.println(ticboard[2][0] + " | " + ticboard[2][1] + " | "
				+ ticboard[2][2]);
	}

	public static void UserInput() {
    boolean doPrint = true;
		System.out
				.println("Enter your coordinates so that there is a space between each coordinate (e.g. 1 3)");
		int rows = sc.nextInt() - 1;
		int columns = sc.nextInt() - 1;
		if (rows > 2 || columns > 2 || rows == -1 || columns == -1) {
      doPrint = false;
			System.out.println("Not valid numbers");
      UserInput();
		}
		if (!ticboard[rows][columns].equals(" ")) {
      doPrint = false;
			System.out.println("Place already taken");
      UserInput();
		}
    if (doPrint) {
      ticboard[rows][columns] = "O";
      Display();
    }
	}

	public static boolean Tie() {
		boolean isTie = true;
		boolean didBreak = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (ticboard[i][j].equals(" ")) {
					isTie = false;
					didBreak = true;
					break;
				}
			}
			if (didBreak)
				break;
		}
		return isTie;
	}

	public static boolean winCondition() {
		for (int i = 0; i < 3; i++) { // horizontal win condition
			if (!ticboard[i][0].equals(" ")
					&& ticboard[i][0].equals(ticboard[i][1])
					&& ticboard[i][1].equals(ticboard[i][2])) {
				letter = ticboard[i][0];
				return true;
			}
		}
		for (int i = 0; i < 3; i++) { // vertical win condition
			if (!ticboard[0][i].equals(" ")
					&& ticboard[0][i].equals(ticboard[1][i])
					&& ticboard[1][i].equals(ticboard[2][i])) {
				letter = ticboard[0][i];
				return true;
			}
		}

		// diagonal win condition left-right
		if (!ticboard[0][0].equals(" ")
				&& ticboard[0][0].equals(ticboard[1][1])
				&& ticboard[1][1].equals(ticboard[2][2])) {
			letter = ticboard[0][0];
			return true;
		}
		// diagonal win condition right-left
		if (!ticboard[0][2].equals(" ")
				&& ticboard[0][2].equals(ticboard[1][1])
				&& ticboard[1][1].equals(ticboard[2][0])) {
			letter = ticboard[0][2];
			return true;
		}
		return false;
	}

	public static void Computer() { // maximizer
		System.out.println("Computer turn:");
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (ticboard[i][j].equals(" ")) {
					ticboard[i][j] = "X";
					int score = minimax(0, 0);
					ticboard[i][j] = " ";

					if (score > max) {
						max = score;
						row = i;
						column = j;
					}
				}
			}
		}
		ticboard[row][column] = "X";

		Display();
	}
	// 0 for player, 1 for computer

	public static int minimax(int searchDepth, int player) {
		if (Tie())
			return 0;
		if (winCondition() && letter.equals("X"))
			return 1;
		if (winCondition() && letter.equals("O"))
			return -1;
		if (player == 1) { // computer move
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (ticboard[i][j].equals(" ")) {
						ticboard[i][j] = "X";
						max = Math.max(minimax(searchDepth + 1, 0),
								max);
						ticboard[i][j] = " ";
					}
				}
			}
			return max;
		} else {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (ticboard[i][j].equals(" ")) {
						ticboard[i][j] = "O";
						min = Math.min(minimax(searchDepth + 1, 1),
								min);
						ticboard[i][j] = " ";
					}
				}
			}
			return min;
		}
	}

}
