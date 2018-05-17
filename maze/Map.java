public class Map extends Area{
    public Map(int x, int y){
	super(x,y);
	generateArea();
    }

    public void generateArea(){
	int x = getX();
	int y = getY();
	
	for(int j = 0; j < y; ++j){
	    super.set(x-1, j, 1);
	    super.set(0, j, 1);
	}
	
	for(int i = 1; i < x-1; ++i){
	    super.set(i, 0, 1);
	    for(int j = 1; j < y-1; ++j){
		if(Math.random() < 0.1)
		    super.set(i, j, 1);
		else
		    super.set(i, j, 0);
	    }
	    super.set(i, y-1, 1);
	}
    }

    public int getX(){ return super.getX();}

    public int getY(){ return super.getY();}

    public int get(double x, double y){
	return get((int)x, (int)y);
    }

    public int get(int x, int y){ return super.get(x,y); }
    
}
