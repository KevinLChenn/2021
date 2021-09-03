/* 
Kevin Chen

March 24th, 2021

This program creates the battleships game and randomly generates a ship for the user to try and hit and 
the game continues until the user wins.
*/

import java.util.Scanner; 

public class battleShip{

	public static void main(String [] args){

		/* creates a standard shipsize and dimension for the battleship board */
		final int SHIPSIZE = 4;
		final int DIMENSION = 10; 

		/* initializes the board */
		char [][] board = new char [DIMENSION][DIMENSION];

		/* creates a board with a randomized ship */
		board = ship(board, SHIPSIZE, DIMENSION);

		/* shows the beginning board for the user */
		System.out.println(board(DIMENSION, board));

		Scanner input = new Scanner(System.in);

		String coord = "";

		int score = 0;

		/* while the user has not won yet the game will continue to run until the user has sunk the ship */
		while(win(board)!=true){
			/* prompts for the coordinates*/
			System.out.println("Enter coordinate to target (e.g. A1): ");
			coord = input.nextLine();
			/* checks for invalid and repeated guesses */
			while ( 0>(int)((coord.charAt(0))-65) || 9<(int)((coord.charAt(0))-65) || 0>(int)((coord.charAt(1))-48) 
			|| 9<(int)((coord.charAt(1))-48) || coord.length()!=2 || 
			board[(int)((coord.charAt(1))-48)][(int)((coord.charAt(0))-65)]=='X' || 
			board[(int)((coord.charAt(1))-48)][(int)((coord.charAt(0))-65)]=='#'){
				System.out.println("Invalid or duplicate input. Please enter a valid input.");
				System.out.println("Enter coordinate to target (e.g. A1): ");
				coord = input.nextLine();
			}

			/* if it is a valid guess marks the guess on array */
			board = hit(board, coord);

			/* prints out the updated board */
			System.out.println(board(DIMENSION, board));

			/* tallies the score */
			score ++;
		}

		/* prints out the end and user's score */
		System.out.println("Congradulations you have won! Youre score is " + score +"." );
	}

	/* determines whether or not the user has won */
	public static boolean win( char [][] board){
		boolean won = true;
		/* checks if there is any parts of the ship un hit yet */
		for (int i = 0 ; i<board.length ; i++){
			for (int j = 0 ; j<board.length ; j++){
				if (board[i][j] == 'O'){
					won = false;
				}
			}
		}
		return won;
	}

	/* marks the board with # if it didn't hit and a X if it hit */
	public static char [][] hit (char [][] board, String coord){
		int row = (int)((coord.charAt(0))-65);
		int col = (int)((coord.charAt(1))-48);

		if (board [col][row] == '\u0000'){
			board [col][row] = '#';
		}
		else{
			board [col][row] = 'X';
		}

		return board;
	}

	/* creates a random number for row and column within allowed boundaries and also creates a random number
	for the rotation of the ship, down or to the right and then it puts the ship into the array as O's */
	public static char [][] ship (char [][] board, int shipsize, int dim){

		int row = (int)(Math.random()*(dim-shipsize));
		int col = (int)(Math.random()*(dim-shipsize));

		int rotation = (int)(Math.random()*2);

		if (rotation<1){
			for (int i = row ; i<row + shipsize ; i++){
				board[col][i]= 'O';
			}
		}
		if (rotation>=1){
			for (int j = col ; j<col + shipsize ; j++){
				board[j][row]= 'O';
			}
		}
		return board;
	}

	/* creates a board for the game */
	public static String board (int dimen, char [][] battleShip){

		String output = "";

		/* creates the first line of letters */
		for (int i = 0 ; i<dimen ; i++){
			output += "   " + (char)(65 + i);
		}

		for (int j= 0 ; j<dimen ; j++){
			output += "\n +";
			for (int k= 0 ; k<dimen ; k++){
				output += "---+";
			}
			output += "\n" + Integer.toString(j) + "|";
			for (int l= 0 ; l<dimen ; l++){
				if (battleShip[j][l] != '\u0000' && battleShip[j][l] != 'O' ){
					output += " " + battleShip[j][l] + " |";
				}
				if (battleShip[j][l] == '\u0000' || battleShip[j][l] == 'O'){
					output += "   |";
				}
			}
		}
		/* creates last line of grid */
		output += "\n +";
		for (int k= 0 ; k<dimen ; k++){
				output += "---+";
		}
		return output;
	}

}