/* 
Kevin Chen

April 12th, 2021

This program creates the word search game by allowing the user to interact with it using a scanner as well as the class wordGame.
*/

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.Scanner;

/*
UML
+-------------------------------------------------------------------------+					+--------------+
|                                 wordGame                                |					|     word     |
+-------------------------------------------------------------------------+					+--------------+
|+board(dimen: int, board: String []): String                             |					|+main(): void |
|+newboard(dimen: int, guess: String, board:String[][] ): String[][]      |					+--------------+
|+score(player: int, words: String [], wordBank: String[]): String        |
|+check(guess: String, words: String[]): boolean                          |
|+valid(guess: String , words: String[] , wordBank: String[]): boolean    |
|+win(words: String[], wordBank: String[]): boolean                       |
+-------------------------------------------------------------------------+
*/

public class word{

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		/* randomizing the csv file chosen */
		int csv = (int)(Math.random()*12+1);

		BufferedReader csvboard = new BufferedReader(new FileReader("board_" + csv + ".csv"));

		String [] first = csvboard.readLine().split(",");

		/* initializes the dimension variable */
		int dimen = Integer.parseInt(first[0]);

		String [][] board = new String[dimen][dimen];
		String line = "";
		int lineind = 0;

		while ((line = csvboard.readLine()) != null) {
			String [] boardline = line.split(",");
			board[lineind] = boardline;
			lineind++;
		}

		String [] words = new String[first.length - 2];

		/* creates a list of words */
		for (int k = 2; k<first.length; k++){
			words[k-2] = first[k];
		}
		
		/* copies the list of words in a new array as a word bank */
		String [] wordBank = new String[words.length];
		for(int m = 0 ; m<words.length ; m++){
			wordBank[m]=words[m];
		}

		wordGame ws = new wordGame();

		/* prompts the user for the number of players */
		System.out.println("Enter number of players: ");
		int numplay = input.nextInt();

		String guess = "";

		/* prints the initial board */
		System.out.println(ws.board(dimen,board));

		/* game continues as long as no one has won yet */
		while(ws.win(words, wordBank)==false){
			for(int player = 0; player<numplay ; player++){
				
				/* prints the scoreboard */
				System.out.println("Score:");
				for(int play = 0; play<numplay ; play++){
					System.out.println("Player " + play + ": " + ws.score(play,words,wordBank));

				}
				/* prompts the user for a word */
				System.out.println("Player " + player + ", please enter a word: ");
				guess = input.next();
				/* keeps prompting the user for a word while it is still invalid */
				while(ws.valid(guess,words,wordBank) == false){
					System.out.println("Please make sure the word is lowercase, more than three letters, does not include numbers, and was not already found: ");
					guess = input.next();
				}
				/* if the word is valid and a correct word it will be capitalized and counted as that player's word */
				if(ws.check(guess,words) == true){
					for (int l = 0 ; l<words.length ; l++){
						if(words[l].equals(guess)){
							words[l]=String.valueOf(player);
						}
					}
					System.out.println(ws.board(dimen,ws.newBoard (dimen,guess,board)));
				}
				else
					System.out.println(ws.board(dimen,board));

				/* if someone wins before all the players finish their turn then the game ends */
				if(ws.win(words,wordBank)==true){
					break;
				}
			
			}
		}
		
	}
}