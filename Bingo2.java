
public class Bingo2
{    
   // these are instance variables
    int size;    
    char [ ][ ] board;
    String result ="";
    char letter ='a';

    // constructor

    public Bingo2(int n, char c1, char c2)
    {
    	this.size = n;
    	this.board = new char[n][n];
    	if( n > 0)
    	{
	    	for(int r = 0; r < n; r++)
	    	{
	    		for(int c = 0; c < n; c++)
	    		{
	    			this.board[r][c] = this.pickOne(c1, c2);
	    		}
	    	}
	    }
	    else
	    	this.board = null;
    }
    
    public char pickOne(char c1, char c2)
  	{
  		int x = (int)(2*Math.random());
  		if(x ==0)
  		{
  			return c1;
  		}
  		else
  		{
  			return c2;
  		}
  	}
  	
  	public String printBoard()
  	{
      String board2 = "";
  		for(int r = 0; r < size; r++)
    	{
    		for(int c = 0; c < size; c++)
    		{
    			board2 += board[r][c] ;
    		}
    		board2 += "\n";

    	}
      return board2;
    }

    public  boolean isWinRow(char letter){

        // count a in rows

      boolean  same= true;
      for(int r = 0; r < board.length; r++)
      {
        same = true;
        for(int c = 0; c < board[r].length; c++)
        {
          if( this.board[r][c] != letter)
          {
            same = false;
            break;
          }
             
        }
        if(same)
        {
          return same;
        }      
      }

      return same;
    }




    public  boolean isWinCol(char letter){

        // count a in col

      boolean  same= true;
      for(int r = 0; r < board.length; r++)
      {
        same = true;
        for(int c = 0; c < board[r].length; c++)
        {
          if( this.board[c][r] != this.letter)
          {
            same = false;
            break;
          }
             
        }
        if(same)
        {
          return same;
        }      
      }

      return same;
    }


    public  boolean isWinDiagR( char letter)
    {
      for(int r = 0; r < this.board.length; r++)
      {
      if(this.board[r][r] != this.letter)
      {
         return false;
      }          
      }
      return true;    
    }

    public  boolean isWinDiagL( char letter)
    {
      for(int r = 0; r < board.length; r++)
      {
      if(board[r][board.length- 1 - r] != letter)
      {
         return false;
      }          
      }
      return true;    
    }

// print game information and board 
  public String toString() {

    System.out.println();

    if (this.isWinRow(letter))
     {

           result= letter + " has at least one row  with all a's - player wins";
        }

        else if (this.isWinCol(letter)){

          result= letter + " has at least one col  with all a's - player wins";
        }
        
        else if (this.isWinDiagR(letter)){

          result= letter + " has at least one diagonal Right leading with all a's - player wins";

        }

        else if (this.isWinDiagL(letter)){

          result= letter + " has at least one diagonal Left leading with all a's- player wins";

        }

        else{
          result= letter + " has no row or col or diagonal (left or right leading) with all a's - player lost game";
        }

      return (this.printBoard() + "\n" + result);
    }

      
}