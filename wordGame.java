/* 
Kevin Chen

April 12th, 2021

This program has all the methods for the word search game in the class wordGame.
*/

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.Scanner;

public class wordGame{

	/* creates the board given an array board and its dimension */
	public static String board (int dimen, String [][] board){

		String output = "";

		for (int j= 0 ; j<dimen ; j++){
			output += "\n+";
			for (int k= 0 ; k<dimen ; k++){
				output += "---+";
			}
			output += "\n" + "|";
			for (int l= 0 ; l<dimen ; l++){
					output += " " + board[j][l] + " |";
			}
		}
		output += "\n+";
		for (int k= 0 ; k<dimen ; k++){
				output += "---+";
		}
		return output;
	}

	/* creates a array with capital letters from the correct guess */
	public static String [][] newBoard (int dimen, String guess, String [][] board){

	boolean right = true;
	boolean left = true;
	boolean down = true;
	boolean up = true;
	boolean rightup = true;
	boolean rightdown = true;
	boolean leftup = true;
	boolean leftdown = true;
	String letter = "";

	/* for loops to go through all the elements in the 2D array */
	for(int i = 0 ; i<dimen ; i++){
		for(int j = 0 ; j<dimen ; j++){
			/* sets the element in comparision to lowercase in case it was already set to uppercase */
			letter = board[i][j].toLowerCase();
			if (letter.equals(guess.substring(0,1).toLowerCase())){
				right = true;
				left = true;
				down = true;
				up = true;
				rightup = true;
				rightdown = true;
				leftup = true;
				leftdown = true;
				/* checking for left to right possiblity */
				if (j <= dimen-guess.length()){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i][j + index]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							right = false;
						}
					}
					if(right == true){
						for (int col = 0 ; col < guess.length() ; col++) {
							String cap = guess.substring(col,col+1).toUpperCase();
							board[i][j + col] = cap;
						}
					}
				}
				/* checking for right to left possibility */
				if(j >= guess.length()-1){
					for(int index = 1 ; index<guess.length(); index++){
						letter = (board[i][j - index]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							left = false;
						}
					}
					if(left == true){
						for (int col = 0 ; col < guess.length() ; col++) {
							String cap = guess.substring(col,col+1).toUpperCase();
							board[i][j - col] = cap;
						}
					}
				}
				/* checking for up to down possibility */
				if(i <= dimen-guess.length()){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i+index][j]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							down = false;
						}
					}
					if(down == true){
						for (int row = 0 ; row < guess.length() ; row++) {
							String cap = guess.substring(row,row+1).toUpperCase();
							board[i+row][j] = cap;
						}
					}
				}
				/* checking for down to up possiblity */
				if(i >= guess.length()-1){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i-index][j]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							up = false;
						}
					}
					if(up == true){
						for (int row = 0 ; row < guess.length() ; row++) {
							String cap = guess.substring(row,row+1).toUpperCase();
							board[i-row][j] = cap;
						}
					}
				}
				/* checking for rightdown diagonal possibility */
				if(j <= dimen-guess.length() && i <= dimen-guess.length()){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i+index][j+index]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							rightdown = false;
						}
					}
					if(rightdown == true){
						for (int rc = 0 ; rc < guess.length() ; rc++) {
							String cap = guess.substring(rc,rc+1).toUpperCase();
							board[i+rc][j+rc] = cap;
						}
					}
				}
				/* checking for rightup diagonal possibility */
				if(j <= dimen-guess.length() && i >= guess.length()-1){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i-index][j+index]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							rightup = false;
						}
					}
					if(rightup == true){
						for (int rc = 0 ; rc < guess.length() ; rc++) {
							String cap = guess.substring(rc,rc+1).toUpperCase();
							board[i-rc][j+rc] = cap;
						}
					}
				}
				/* checking for leftup diagonal possibility */
				if(j >= guess.length()-1 && i >= guess.length()-1){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i-index][j-index]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							leftup = false;
						}
					}
					if(leftup == true){
						for (int rc = 0 ; rc < guess.length() ; rc++) {
							String cap = guess.substring(rc,rc+1).toUpperCase();
							board[i-rc][j-rc] = cap;
						}
					}
				}
				/* checking for leftdown diagonal possibility */
				if(j >= guess.length()-1 && i <= dimen-guess.length()){
					for(int index = 1 ; index<guess.length() ; index++){
						letter = (board[i+index][j-index]).toLowerCase();
						if (!letter.equals(guess.substring(index,index+1))) {
							leftdown = false;
						}
					}
					if(leftdown == true){
						for (int rc = 0 ; rc < guess.length() ; rc++) {
							String cap = guess.substring(rc,rc+1).toUpperCase();
							board[i+rc][j-rc] = cap;
						}
					}
				}
			}
		
		}
	}
		return board;
	}

	/* calculating the score of given player */
	public static String score (int player, String [] words, String [] wordBank){
		String scor = "[";
		int count = 0;
		for (int i = 0 ; i<words.length ; i++){
			if(words[i].equals(String.valueOf(player))){
				if(count>0){
					scor += ", ";
				}
				scor += "'" + wordBank[i] + "'";
				count++;
			}
		}
		return String.valueOf(count) + " " + scor + "]";
	}

	/* checks if the guessed word is a correct word */
	public static boolean check (String guess, String [] words){
		boolean correct = false;
		for (int i = 0; i < words.length ; i++ ){
			if (guess.equals(words[i])){
				correct = true;
			}
		}
		return correct;
	}

	/* checks if the guess is valid and abiding by the guidelines */
	public static boolean valid (String guess, String [] words, String [] wordBank){
		boolean isValid = true;
		if(guess.length()<3)
			isValid = false;
		for(int i = 0; i<guess.length() ; i++){
			if(Character.isUpperCase(guess.charAt(i)) == true)
				isValid = false;
		}
		char[] guessChar = guess.toCharArray();
      	for (int j = 0 ; j < guessChar.length ; j++) {
        	char ch = guessChar[j];
        	if(ch==' ')
        		isValid = false;
        	if (!(ch >= 'a' && ch <= 'z'))
            	isValid = false;
        }
        for(int l = 0 ; l<words.length ; l++){
        	if(guess.equals(wordBank[l]) && !guess.equals(words[l]))
        		isValid = false;
        }
		return isValid;
	}

	public static boolean win (String [] words, String [] wordBank){
		boolean won = true;
		for (int i = 0; i < words.length ; i++ ){
			if (words[i]==wordBank[i]){
				won = false;
			}
		}
		return won;
	}
}