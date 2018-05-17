import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class Maze extends Area{
    public Maze(int x, int y){
	super(x,y);
	generateArea();
    }

    public int getX(){ return super.getX();}

    public int getY(){ return super.getY();}

    public int get(double x, double y){
	return get((int)x, (int)y);
    }

    public int get(int x, int y){ return super.get(x,y); }
    
    public void generateArea(){
	int height = getX();
	int width = getY();
	// Initialize
	for (int i = 0; i < height; ++i)
	    for (int j = 0; j < width; ++j)
		super.set(i, j, 1);

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
	super.set(r, c, 0);

	//　Allocate the maze with recursive method
	recursion(r, c);
	setExit();
    }

    private void recursion(int r, int c) {
	int height = getY();
	int width = getX();
	// 4 random directions
	Integer[] randDirs = generateRandomDirections();
	// Examine each direction
	for (int i = 0; i < randDirs.length; ++i) {

	    switch(randDirs[i]){
	    case 1: // Up
		//　Whether 2 cells up is out or not
		if (r - 2 <= 0)
		    continue;
		if (get(r - 2, c) != 0) {
		    super.set(r-2, c, 0);
		    super.set(r-1, c, 0);
		    recursion(r - 2, c);
		}
		break;
	    case 2: // Right
		// Whether 2 cells to the right is out or not
		if (c + 2 >= width - 1)
		    continue;
		if (get(r, c + 2) != 0) {
		    super.set(r, c + 2, 0);
		    super.set(r, c + 1, 0);
		    recursion(r, c + 2);
		}
		break;
	    case 3: // Down
		// Whether 2 cells down is out or not
		if (r + 2 >= height - 1)
		    continue;
		if (get(r + 2, c) != 0) {
		    super.set(r+2, c, 0);
		    super.set(r+1, c, 0);
		    recursion(r + 2, c);
		}
		break;
	    case 4: // Left
		// Whether 2 cells to the left is out or not
		if (c - 2 <= 0)
		    continue;
		if (get(r, c - 2) != 0) {
		    super.set(r, c - 2, 0);
		    super.set(r, c - 1, 0);
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
	int height = getY();
	int width = getX();

	switch ((int)(Math.random()*4)){
	case 0://UP
	    super.set(1, (int)(Math.random()*width), 2); 
	    break;
	case 1://DOWN
	    super.set(height - 2, (int)(Math.random()*width), 2); 
	    break;
	case 2://LEFT
	    super.set((int)(Math.random()*height), 1, 2); 
	    break;
	case 3://RIGHT
	    super.set((int)(Math.random()*height), width - 2, 2); 
	    break;
	}
    }
}
