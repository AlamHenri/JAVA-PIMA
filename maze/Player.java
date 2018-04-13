public class Player{
    private double x, y; // add an H after for the jump
    private double dirX = -1, dirY = 0;
    private double planeX = 0, planeY = 0.66;

    public Player(double x, double y, double angle){
	this.x = x;
	this.y = y;
    }

    public Player(double x, double y){
	this(x,y,0.0);
    }

    public Player(){
	this(5.0,5.0,0.0);
    }


    public double getX(){
	return x;
    }

    public double getY(){
	return y;
    }

    public double getDirX(){
	return dirX;
    }

    public double getDirY(){
	return dirY;
    }

    public double getPlaneX(){
	return planeX;
    }

    public double getPlaneY(){
	return planeY;
    }
    
    public void move(double x_, double y_){
	x += x_;
	y += y_;
    }

    public void rotate(double dirX, double dirY, double planeX, double planeY){
	this.dirX = dirX;
	this.dirY = dirY;
	this.planeX = planeX;
	this.planeY = planeY;
    }
}
