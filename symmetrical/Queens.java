// javac Queens.java StdDraw.java
// java Queens 10


//Chase Anzelc
//Data Structures
//9/27/18
//Assignment 2 EXTRA CREDIT


class Queens
{
	public boolean solution = false;

	//draw board with queens
    void draw(int[] board)
    {
        int n = board.length;
        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, n+2);

        for (int x=1; x<=n; x++) {
            for (int y=1; y<=n; y++) {
                if ((y % 2) != 0) {
                    if ((x % 2) != 0)  {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.ORANGE);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                }
                else {
                    if ((x % 2) != 0)  {
                        StdDraw.setPenColor(StdDraw.ORANGE);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                }
            }
        }
        StdDraw.show();
        StdDraw.pause(200);

        StdDraw.setPenColor(StdDraw.RED);
        for (int x=0; x<n; x++) {
            StdDraw.filledCircle(x+1+0.5, board[x]+1+0.5, 0.375);
            StdDraw.show();
            StdDraw.pause(200);
        }
    }


//is the queen under attack
public boolean underAttack(int[] board, int k) {

        for (int i = 0; i < k; i++) {
            if (board[i] == board[k])             return false;   // in the same column
            if ((board[i] - board[k]) == (k - i)) return false;   // in the same major diagonal
            if ((board[k] - board[i]) == (k - i)) return false;   // in the same minor diagonal
        }
        return true;
    }

//is solution 180 degrees symmetrical
public boolean isSymmetrical(int[] board, int n){

			int[] mirror = new int[board.length]; //array for mirror of queen placed array
			int j = 0;

			//fill mirror array
	        for(int i=n-1; i>=0; i--){

	            int y = board.length -1;

	            for(int p=0; p<n-1; p++) {
	                if (board[i] == p){
	                    mirror[j] = y;
	                }
	                else
	                {
	                    y -= 1;
	                }
            }

            j += 1;
        }

		//is the mirror the same as the solution
        boolean isSymmetrical = true;
		for(int i=0; i<board.length; i++) {
		     if (board[i] != mirror[i]){
		           isSymmetrical = false;
		          }
		}
		if(isSymmetrical){
	    return true;
		}
		else{
	    return false;
        }




}





//find the places for the queens
//recursion
public void placeQueens(int[] board, int k) {
        Queens queen = new Queens();

        int n = board.length;
		

        if(solution == false){ //only find one solution

        if (k == n){
			//queen.draw(board);
			if(isSymmetrical(board,n)){//is the solution 180 degree symmetrical
			        queen.draw(board);
			        solution = true; //found 180 degree symmetrical solution
                    }
		}
        else {
            for (int i = 0; i < n; i++) {
                board[k] = i;
                if (underAttack(board, k)){ //is the queen under attack
                placeQueens(board, k+1); //recursion
				}
            }
        }
	}
    }



   static public void main (String args[])
   {
       if (args.length == 1) {
           int n = Integer.parseInt(args[0]);
		   Queens queen = new Queens();
		   int[] board = new int [n];


           queen.placeQueens(board, 0);

       }
       else
           System.out.println("Please input the size of the board ...");

   }
}


