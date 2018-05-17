public abstract class Area extends Grid implements Drawable{
    private final int[][] colors = new int[][]{{0,255,0},{25,25,0},{0,0,200}};

    public Area(int x, int y){
	super(x,y);
    }

    public int getX(){ return super.getX();}

    public int getY(){ return super.getX();}

    public int get(double x, double y){ return get((int)x, (int)y);}
    
    public int get(int x, int y){ return super.get(x,y); }

    protected void set(int x, int y, int value){
	super.set(x,y,value);
    }

    public abstract void generateArea();
    
    /* Graphics */
    public void draw(SimpleInterface inter, double posX, double posY){
	int i_, j_;
	int x = getX();
	int y = getY();
	
	for(int i = 0; i < 3*x; ++i){
	    i_ = i/3;
	    for(int j = 0; j < 3*y; ++j){
		j_ = j/3;
		inter.setRGB(i,j,colors[get(i_,j_)]);
	    }
	}
	inter.setRGB((int)posX*3,(int)posY*3,inter.RED);
	inter.setRGB((int)posX*3,(int)posY*3+1,inter.RED);
	inter.setRGB((int)posX*3+1,(int)posY*3+1,inter.RED);
	inter.setRGB((int)posX*3+1,(int)posY*3,inter.RED);
	inter.refresh();
    }
}
