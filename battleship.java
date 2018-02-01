/*--------------------------------------------------------
Name(s) and ID(s): Li Sun(40017648),Siyun Liao(25658306);
COMP249 Section PP
Assignment #1
Due Date: February 4, 2017
--------------------------------------------------------*/

package assignment1;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Li Sun(40017648),Siyun Liao(25658306);
 */

public class battleship {
	/**
	 * @see Array of positions initialize all the position element
	 */
	static int countSinkShipHuman = 0;
	static int countSinkShipComputer = 0;
	private static boolean skipTurnHuman = false;
	private static boolean skipTurnComp = false;
	static int countLoseTurnHuman = 0;
	static int countLoseTurnComp = 0;

	/**
	 * 
	 * start method(while loop)
	 * 
	 * @param grid
	 *            Position array
	 */
	public void run(Position[][] grid) {
		while (true) {

			if (!skipTurnHuman) {
				shootHuman(grid);// shoot start
				print(grid);// print grid
				if (checkWinH() || checkWinC()) {
					break;// out of loop
				}
			} else {
				skipTurnHuman = false;
			}

			if (!skipTurnComp) {
				shootComputer(grid);
				print(grid);
				if (checkWinH() || checkWinC()) {
					break;
				}
			} else {
				skipTurnComp = false;
			}

		}
		// while loop return winner;
	}

	/**
	 * computer shoot method
	 * 
	 * @param grid
	 *            position by random
	 * @return grid Position [][]
	 */
	public Position[][] shootComputer(Position[][] grid) {
		Random random = new Random();
		int row = random.nextInt(8);// random row
		int column = random.nextInt(8);// random column
		System.out.println("Computer shoots: " + (char) (65 + column) + row);
		if (grid[row][column].getCalled()) {
			System.out.println("This place is alredy shoot");
		} else {
			if (grid[row][column].getElement() == 's') {
				countSinkShipComputer++;
				System.out.println("Computer shoots your ship!");
			} else if (grid[row][column].getElement() == 'S') {
				System.out.println("Computer shoots its own ship!");
				countSinkShipHuman++;
			} else if (grid[row][column].getElement() == 'g') {
				System.out.println("Computer shoots your grenade!");
				skipTurnComp = true;
				countLoseTurnComp++;// count grenade
			} else if (grid[row][column].getElement() == 'G') {
				System.out.println("Computer shoots its own grenade!");
				skipTurnComp = true;
				countLoseTurnComp++;// count grenade
			} else {
				System.out.println("Nothing!");
				grid[row][column].setElement('*');
			}
			grid[row][column].setCalled(true);
		}
		return grid;
	}

	/**
	 * human shoot method
	 * 
	 * @param grid
	 *            position by input
	 * @return grid Position[][]
	 */
	public Position[][] shootHuman(Position[][] grid) {
		Scanner scn = new Scanner(System.in);
		int row = -1;
		int column = -1;
		String input = "";
		System.out.println("Please shoot your rocket: ");
		input = scn.next();
		row = row(input);
		column = column(input);
		if (grid[row][column].getCalled()) {
			System.out.println("This place is alredy shoot");
		} else {
			if (grid[row][column].getElement() == 'S') {
				countSinkShipHuman++;
				System.out.println("Great! You sink computer's ship!");
			} else if (grid[row][column].getElement() == 's') {
				countSinkShipComputer++;
				System.out.println("You just sink your own ship!");
			} else if (grid[row][column].getElement() == 'G') {
				System.out.println("You hit Computer's grenade!");
				skipTurnHuman = true;
				countLoseTurnHuman++;
			} else if (grid[row][column].getElement() == 'g') {
				System.out.println("You hit your own grenade!");
				skipTurnHuman = true;
				countLoseTurnHuman++;
			} else {
				System.out.println("Nothing!");
				grid[row][column].setElement('*');
			}
			grid[row][column].setCalled(true);
		}

		return grid;

	}

	/**
	 * check human win by count sink ship
	 * 
	 * @return checkWinH boolean
	 */
	public boolean checkWinH() {
		if (countSinkShipHuman == 6) {
			// if human win
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check computer win by count sink ship
	 * 
	 * @return checkWinc boolean
	 */
	public boolean checkWinC() {
		if (countSinkShipComputer == 6) {
			// if human win
			return true;
		} else {
			return false;
		}
	}

	/**
	 * initial the grid
	 * 
	 * @return grid position[][]
	 */

	public Position[][] initial() {
		Position[][] grid = new Position[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Position p = new Position();
				grid[i][j] = p;
			}
		}
		return grid;
	}

	/**
	 * print current grid for current chess condition
	 * 
	 * @param grid
	 *            position[][]
	 */
	public void print(Position[][] grid) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// if not yet called, print '_'
				if (grid[i][j].getCalled() == false) {
					System.out.print("_");
					System.out.print(" ");
				} else {
					// if called already, print s, S or G, g or *
					System.out.print(grid[i][j].getElement());
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * print original grid for set chess condition
	 * 
	 * @param grid Position[][]
	 */
	public void testPrint(Position[][] grid) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j].getElement() == '*') {
					System.out.print('_');
					System.out.print(" ");
				} else {
					System.out.print(grid[i][j].getElement());
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * human pick position for battleship and grenede
	 * 
	 * @param grid Position[][]
	 * @return grid Position[][]
	 */
	public Position[][] pickSpotHuman(Position[][] grid) {
		Scanner scn = new Scanner(System.in);
		String input = "";
		int count1 = 0; // count 0-5 when place battle ship
		int count2 = 0; // count 0-3 when place grenades
		// place ships
		while (count1 < 6) {
			System.out.println("Enter the coordinates of your ship #" + (count1 + 1));
			input = scn.next();
			if (input.toCharArray().length != 2) {
				System.out.println("Sorry,coordinates input is invalid, try again.");
			} else {
				int column = column(input);
				int row = row(input);

				if (column < 0 || column > 7 || row < 0 || row > 7 || input.toCharArray().length > 2
						|| input.toCharArray().length < 1) {
					System.out.println("Sorry, coordinates outside the grid. try again.");
				} else if (grid[row][column].getElement() == '_') {
					// if this space is not yet used, place it
					grid[row][column].setElement('s');
					grid[row][column].setOwner('H');
					count1++;
				} else {
					System.out.println("Sorry, coordinates already used. try again.");
				}
			}

		}

		while (count2 < 4) {
			System.out.println("Enter the coordinates of your grenade #" + (count2 + 1));
			input = scn.next();
			if (input.toCharArray().length != 2) {
				System.out.println("Sorry,coordinates input is invalid, try again.");
			} else {
				int column = column(input);
				int row = row(input);

				if (input.toCharArray().length != 2 || column < 0 || column > 7 || row < 0 || row > 7) {
					System.out.println("Sorry, coordinates outside the grid. try again.");
				} else if (grid[row][column].getElement() == '_') {
					// if this space is not yet used, place it
					grid[row][column].setElement('g');
					grid[row][column].setOwner('H');
					count2++;
				} else {
					System.out.println("Sorry, coordinates already used. try again.");
				}
			}
		}
		return grid;
	}

	/**
	 * computer places ships and grenades
	 * 
	 * @param grid Position[][]
	 * @return grid position[][]
	 */
	public Position[][] pickSpotComputer(Position[][] grid) {
		int count1 = 0; // count 0-5 when place battle ship
		int count2 = 0; // count 0-3 when place grenedes
		Random random = new Random();
		// place ships
		while (count1 < 6) {
			int column = random.nextInt(8);
			int row = random.nextInt(8);

			if (column < 0 || column > 7 || row < 0 || row > 7) {
				// System.out.println("Sorry, coordinates outside the grid. try
				// again.");
			} else if (grid[row][column].getElement() == '_') {
				// if this space is not yet used, place it
				grid[row][column].setElement('S');
				grid[row][column].setOwner('C');
				grid[row][column].setOwner('C');
				count1++;
			} else {
				// System.out.println("Sorry, coordinates already used. try
				// again.");
			}
		}

		while (count2 < 4) {
			// System.out.println("Enter the coordinates of your grenade #" +
			// (count2 + 1));
			int column = random.nextInt(8);
			int row = random.nextInt(8);

			if (column < 0 || column > 7 || row < 0 || row > 7) {
				// System.out.println("Sorry, coordinates outside the grid. try
				// again.");
			} else if (grid[row][column].getElement() == '_') {
				// if this space is not yet used, place it
				grid[row][column].setElement('G');
				count2++;
			} else {
				// System.out.println("Sorry, coordinates already used. try
				// again.");
			}
		}
		return grid;
	}

	/**
	 * Separate input String to rows and columns
	 * 
	 * @param input String
	 * @return column integral
	 */
	public int column(String input) {
		char columnChar = input.toUpperCase().charAt(0);
		int column = -1;
		switch (columnChar) {
		case 'A':
			column = 0;
			break;
		case 'B':
			column = 1;
			break;
		case 'C':
			column = 2;
			break;
		case 'D':
			column = 3;
			break;
		case 'E':
			column = 4;
			break;
		case 'F':
			column = 5;
			break;
		case 'G':
			column = 6;
			break;
		case 'H':
			column = 7;
			break;
		default:
			break;
		}
		return column;
	}

	/**
	 * return rows
	 * 
	 * @param input String
	 * @return row integral
	 */
	public int row(String input) {
		char rowChar = input.charAt(1);
		int row = -1;
		switch (rowChar) {
		case '1':
			row = 0;
			break;
		case '2':
			row = 1;
			break;
		case '3':
			row = 2;
			break;
		case '4':
			row = 3;
			break;
		case '5':
			row = 4;
			break;
		case '6':
			row = 5;
			break;
		case '7':
			row = 6;
			break;
		case '8':
			row = 7;
			break;

		default:
			break;
		}

		return row;
	}

}
