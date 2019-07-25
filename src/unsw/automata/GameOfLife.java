/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
	
	BooleanProperty[][] array;

    public GameOfLife() {
    	// initialising array of SimpleBooleanProperty objects
		array = new BooleanProperty[10][10];
		for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                array[row][col] = new SimpleBooleanProperty();
            }
        }
    }

    public void ensureAlive(int x, int y) {
    	array[x][y].set(true);
    }

    public void ensureDead(int x, int y) {
    	array[x][y].set(false);
    }
    
    public BooleanProperty cellProperty(int x, int y) {
    	return BooleanProperty.booleanProperty(array[x][y]);
    }

    public boolean isAlive(int x, int y) {

    	if (array[x][y].get() == true) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void tick() {

    	// sets dimensions of board (10 x 10)
        int rows = 10;
        int cols = 10;

        // Create a copy of the board
        BooleanProperty[][] copyBoard = new BooleanProperty[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
            	copyBoard[row][col] = new SimpleBooleanProperty();
                copyBoard[row][col].set(array[row][col].get());
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
        					if (copyBoard[a][b].get() == true) {
        						liveNeighbours++;
        					}
        					b++;
    				}
    				a++;
    			}

                // Testing the rules to see whether we change the current cell's state
                if ((copyBoard[row][col].get() == true) && (liveNeighbours < 2 || liveNeighbours > 3)) {
                    array[row][col].set(false);
                } else if (copyBoard[row][col].get() == false && liveNeighbours == 3) {
                    array[row][col].set(true);
                }
            }
        }
    }
}
