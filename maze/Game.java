
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.lang.System.*;

public class Game extends SimpleInterface{
    private Player player;
    private World world; 
    public static final int width = 600, height = 400;
    public int score;
    
    public Game(){
	this(new Player(), new World(new Maze(15,15),width,height),100);
    }

    public Game(Player p, World w, int s){
	super("Maze Walker",width,height);
	player = p;
	world = w;
	score = s;

	setResizable(false);
       	super.createArea(width,height);
	super.refresh();
    }

    public Game(Player p){
	this(p, new World(new Maze(15,15),width,height),100);
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
	double start,end, time;
	//Welcome screen
	drawImage(loadImage("mazeWalker.jpg"), 0, 0, width, height);
	refresh();
	waitClick();
	world.fillScreen(this,player);
	
	//Start the game
	start = System.nanoTime();
	while(!exit){
	    //System.out.println("debut = " + start);
	    //Draw the result
	    exit = !draw();
	    // Check if the exit is near then display message box
	    if(exitNear((int)(player.getX()), (int) (player.getY() ) ) ) {
		end = System.nanoTime();
		//System.out.println("fin = " + end);
		time = end - start;
		//System.out.println("Vous avez mis "+ time/1000000000+"secondes nulos ...");
		printSuccess(time/1000000000);
		exit = true;
	    }
	}
	closeWindow();
     }

    public void printSuccess(double time){ // Message quand on vient de trouver la sortie 
	JFrame frame = new JFrame("GAME OVER");
	JOptionPane d = new JOptionPane();
	d.showMessageDialog(frame, "Bien joué, vous avez gagné en " +time+" secondes ! \n", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
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
