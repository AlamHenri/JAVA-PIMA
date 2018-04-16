import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class Maze extends Map{ //implements ??
    public Maze(int x, int y){
	super(x,y);
	generateMaze();
    }

    private void generateMaze(){
	int height = y;
	int width = x;
	// Initialize
	for (int i = 0; i < height; ++i)
	    for (int j = 0; j < width; ++j)
		map[i][j] = 1;

	Random rand = new Random();
	// r for row、c for column
	// Generate random r
	int r = rand.nextInt(height);
	while (r % 2 == 0) {
	    r = rand.nextInt(height);
	}
	// Generate random c
	int c = rand.nextInt(width);
	while (c % 2 == 0) {
	    c = rand.nextInt(width);
	}
	// Starting cell
	map[r][c] = 0;

	//　Allocate the maze with recursive method
	recursion(r, c);
	setExit();
    }

    public void recursion(int r, int c) {
	int height = y;
	int width = x;
	// 4 random directions
	Integer[] randDirs = generateRandomDirections();
	// Examine each direction
	for (int i = 0; i < randDirs.length; i++) {

	    switch(randDirs[i]){
	    case 1: // Up
		//　Whether 2 cells up is out or not
		if (r - 2 <= 0)
		    continue;
		if (map[r - 2][c] != 0) {
		    map[r-2][c] = 0;
		    map[r-1][c] = 0;
		    recursion(r - 2, c);
		}
		break;
	    case 2: // Right
		// Whether 2 cells to the right is out or not
		if (c + 2 >= width - 1)
		    continue;
		if (map[r][c + 2] != 0) {
		    map[r][c + 2] = 0;
		    map[r][c + 1] = 0;
		    recursion(r, c + 2);
		}
		break;
	    case 3: // Down
		// Whether 2 cells down is out or not
		if (r + 2 >= height - 1)
		    continue;
		if (map[r + 2][c] != 0) {
		    map[r+2][c] = 0;
		    map[r+1][c] = 0;
		    recursion(r + 2, c);
		}
		break;
	    case 4: // Left
		// Whether 2 cells to the left is out or not
		if (c - 2 <= 0)
		    continue;
		if (map[r][c - 2] != 0) {
		    map[r][c - 2] = 0;
		    map[r][c - 1] = 0;
		    recursion(r, c - 2);
		}
		break;
	    }
	}

    }

    /**
     * Generate an array with random directions 1-4
     * @return Array containing 4 directions in random order
     */
    public Integer[] generateRandomDirections() {
	ArrayList<Integer> randoms = new ArrayList<Integer>();
	for (int i = 0; i < 4; i++)
	    randoms.add(i + 1);
	Collections.shuffle(randoms);

	return randoms.toArray(new Integer[4]);
    }

    private void setExit(){
	int height = y;
	int width = x;

	switch ((int)(Math.random()*4)){
	case 0://UP
	    map[1][(int)(Math.random()*width)] = 99; 
	    break;
	case 1://DOWN
	    map[height - 2][(int)(Math.random()*width)] = 99; 
	    break;
	case 2://LEFT
	    map[(int)(Math.random()*height)][1] = 99; 
	    break;
	case 3://RIGHT
	    map[(int)(Math.random()*height)][width - 2] = 99; 
	    break;
	}
    }
}
