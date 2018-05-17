public abstract class Grid{
    private int x, y;
    private int[][] map;

    public Grid(int x, int y){
	this.x = x;
	this.y = y;
	this.map = new int[x][y];
    }

    public int getX(){ return x;}

    public int getY(){ return y;}

    public int get(int x, int y){ return map[x][y]; }

    protected void set(int x, int y, int value){
	map[x][y] = value;
    }
   
}
