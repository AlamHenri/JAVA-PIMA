import java.awt.event.*;

public class Game extends SimpleInterface{
    private Player player;
    private World world; 
    public static final int width = 600, height = 400;
    
    public Game(){
	this(new Player(), new World(new Maze(15,15),width,height));
    }

    public Game(Player p, World w){
	super("Maze Walker",width,height);
	player = p;
	world = w;

	super.createArea(width,height);
	super.refresh();
    }

    public Game(Player p){
	this(p, new World(new Maze(15,15),width,height));
    }

    /************************************/

    private boolean draw(){
       	int key = waitKey(2);

	do{
	    if(key == 'o')
		return false;
	    else if(world.walk(player,key)) {
		world.fillScreen(this,player);
	    }

	    refresh();
	    pause(7);
	}while(keyPressed && (key == lastKey()));
	
	return true;
    }

    public void run(){
	boolean exit = false;
       
	//Welcome screen
	drawImage(loadImage("mazeWalker.jpg"), 0, 0, width, height);
	refresh();
	waitClick();
	world.fillScreen(this,player);
	
	//Start the game
	while(!exit){

	    //Draw the result
	    exit = !draw();
	}

	dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	
    }
}
