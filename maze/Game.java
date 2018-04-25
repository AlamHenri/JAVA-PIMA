import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

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

	setResizable(false);
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
	    if(exitNear((int)(player.getX()), (int) (player.getY() ) ) ) {
		printSuccess();
	    }
	}
	// Display message when we found the exit
	closeWindow();
     }

    public void printSuccess(){ // Message quand on vient de trouver la sortie 
	JFrame frame = new JFrame("GAME OVER");
	JOptionPane d = new JOptionPane();
	d.showMessageDialog(frame, "Bien joué, vous avez gagné ! \n", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }

    // returns true if the exit is within a range of one cell radius
    public boolean exitNear(int x, int y){
	boolean res = false;
	for(int i = x-1; i<=x+1; i++){
	    for(int j = y-1; j<y+2; j++){
		if(world.getMapCell(i,j)==2) res = true;
	    }
	}
	return res;
    }
    
}

