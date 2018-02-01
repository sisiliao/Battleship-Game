/*--------------------------------------------------------
Name(s) and ID(s): Li Sun(40017648),Siyun Liao(25658306);
COMP249 Section PP
Assignment #1
Due Date: February 4, 2017
--------------------------------------------------------*/

package assignment1;

/**
 * @author Li Sun(40017648),Siyun Liao(25658306);
 */

public class driver {
	public static void main(String[] args) {
		battleship game = new battleship();
		Position[][] grid = new Position[8][8];
		//initiate the grid
		grid = game.initial();	
		// print the grid
		System.out.println("Hi, let¡¯s play Battleship!");
		// human picks spots
		grid = game.pickSpotHuman(grid);
		// computer picks spots
		game.pickSpotComputer(grid);
		System.out.println("Ok, the computer placed its ships and grenades at random. Let's play.");
		// run the game
		game.run(grid);
		if (battleship.countSinkShipHuman == 6) {
			System.out.println("You win!");
		} else {
			System.out.println("Computer wins!");
		}
		System.out.println("You missed " + battleship.countLoseTurnHuman + " turns");
		System.out.println("Computer missed " + battleship.countLoseTurnComp + " turns");
        // show the final grid
		game.testPrint(grid);
		
	}
}
