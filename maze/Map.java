public class Map implements Drawable{
    protected int x, y;
    protected int[][] map;
    private final int[][] colors = new int[][]{{25,25,0},{0,255,0},{0,0,200}};

    public Map(int x, int y){
	this.x = x;
	this.y = y;
	this.map = new int[x][y];
    }

    public Map(){
	this(10,8);
    }

    public int getX(){ return x;}

    public int getY(){ return y;}

    public int get(double x, double y){
	return get((int)x, (int)y);
    }

    public int get(int x, int y){
	return map[x][y];
    }
    
    public void fillMap(double seuil){
	for(int j = 0; j < y; ++j){
	    map[x-1][j] = 1;
	    map[0][j] = 1;
	}
	
	for(int i = 1; i < x-1; ++i){
	    map[i][0] = 1;
	    for(int j = 1; j < y-1; ++j){
		if(Math.random() < seuil)
		    map[i][j] = 1;
		else
		    map[i][j] = 0;
	    }
	    map[i][y-1] = 1;
	}
    }

    public void drawMap(){
	for(int i = 0; i < x; ++i){
	    for(int j = 0; j < y; ++j){
		System.out.print(map[i][j]);
	    }
	    System.out.println();
	}
    }

    /* Graphics */
    public void draw(SimpleInterface inter, double posX, double posY){
	int i_, j_;
	for(int i = 0; i < 3*x; ++i){
	    i_ = i/3;
	    for(int j = 0; j < 3*y; ++j){
		j_ = j/3;
		inter.setRGB(i,j,colors[map[i_][j_]]);
	    }
	}
	inter.setRGB((int)posX*3,(int)posY*3,inter.RED);
	inter.setRGB((int)posX*3,(int)posY*3+1,inter.RED);
	inter.setRGB((int)posX*3+1,(int)posY*3+1,inter.RED);
	inter.setRGB((int)posX*3+1,(int)posY*3,inter.RED);
	inter.refresh();
    }
}
