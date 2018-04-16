import java.awt.event.*;

public class Game extends SimpleInterface{
    private Player player;
    private World world;
    public static final int width = 300, height = 200;
    
    public Game(){
	this(new Player(), new World(new Maze(25,25),width,height));
    }

    public Game(Player p, World w){
	super("Maze Walker",width,height);
	player = p;
	world = w;

	super.createArea(width,height);
	super.refresh();
    }

    public Game(Player p){
	this(p, new World(new Maze(25,25),width,height));
    }

    /************************************/

    private boolean draw(){
	int key = lastKey();
	
	if(key != 'o' && world.walk(player,key))
	    world.fillScreen(this,player);
	else if(key == 'o')
	    return false;
      	
	refresh();
	pause(12);
       
	//	super.emptyKey(super.lastKey);
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
