/**
 *
 */
package unsw.automata;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
	
	boolean array[][];

    public GameOfLife() {
		array = new boolean[10][10];
    }

    public void ensureAlive(int x, int y) {
    	array[x][y] = true;
    }

    public void ensureDead(int x, int y) {
    	array[x][y] = false;
    }

    public boolean isAlive(int x, int y) {
    	if (array[x][y] == true) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void tick() {

    	// determine number of rows and columns in board (10 x 10)
        int rows = array.length;
        int cols = array[0].length;

        // Create a copy of the board
        boolean[][] copyBoard = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = array[row][col];
            }
        }

        // Now we go through each cell of the board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell, we determine the number of live neighbours
                int liveNeighbours = 0;
                
                int a = row - 1;
    			for (int counter = 0; counter < 3; counter++) {
    				
    				int b = col - 1;
    				for (int innerCounter = 0; innerCounter < 3; innerCounter++) {
        					
        					if (a < 0) {
        						a = 9;
        					} else if (a > 9) {
        						a = 0;
        					}
        					
        					if (b < 0) {
        						b = 9;
        					} else if (b > 9) {
        						b = 0;
        					}
        					
        					if (a == row && b == col) {
        						b++;
        						continue;
        					}
        					
        					// if this neighbour is alive, we add to the count of live neighbours
        					if (copyBoard[a][b] == true) {
        						liveNeighbours++;
        					}
        					b++;
    				}
    				a++;
    			}

                // Testing the rules to see whether we change the current cell's state
                if ((copyBoard[row][col] == true) && (liveNeighbours < 2 || liveNeighbours > 3)) {
                    array[row][col] = false;
                } else if (copyBoard[row][col] == false && liveNeighbours == 3) {
                    array[row][col] = true;
                }
            }
        }
    }
}
