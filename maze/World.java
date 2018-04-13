public class World{
    private Maze map;
    private double posX, posY;
    private int dimx, dimy;
    private int[] wallColor = new int[]{0,150,0};
    private int[] floorColor = new int[]{160,82,45};
    private int[] skyColor = new int[]{119, 181, 254};    
    /*    public World(){
	this(new Maze());
    }

    public World(Maze m){
	map = m;
	}*/

    public World(Maze m, int width, int height){
	map = m;
	//	m.fillMap(0.1);
	dimx = width;
	dimy = height;
    }

    public int getMapCell(int x, int y){
	return map.get(x,y);
    }


    public boolean walk(Player p, int key){
	double moveSpeed = 0.2;     // Paramètres à modifier 
	double rotSpeed = 0.10;

	//move forward if no wall
	if(key == 'z'){ //up
	    if(map.get((p.getX() + p.getDirX() * moveSpeed),(p.getY())) == 0)
		p.move(p.getDirX()*moveSpeed,0);
	    if(map.get(p.getX(),(p.getY() + p.getDirY() * moveSpeed)) == 0)
		p.move(0,p.getDirY()*moveSpeed);
	}
	else if(key == 's'){ //down
	    if(map.get((p.getX() - p.getDirX() * moveSpeed),(p.getY())) == 0)
		p.move(-p.getDirX()*moveSpeed,0);
	    if(map.get(p.getX(),(p.getY() - p.getDirY() * moveSpeed)) == 0)
		p.move(0,-p.getDirY()*moveSpeed);	    
	}
	else if(key == 'd'){ //right
	    p.rotate(p.getDirX() * Math.cos(rotSpeed) - p.getDirY() * Math.sin(rotSpeed),
		     p.getDirX() * Math.sin(rotSpeed) + p.getDirY() * Math.cos(rotSpeed),
		     p.getPlaneX() * Math.cos(rotSpeed) - p.getPlaneY() * Math.sin(rotSpeed),
		     p.getPlaneY() * Math.cos(rotSpeed) + p.getPlaneX() * Math.sin(rotSpeed));
	}
	else if(key == 'q'){ //left
	    p.rotate(p.getDirX() * Math.cos(-rotSpeed) - p.getDirY() * Math.sin(-rotSpeed),
		     p.getDirX() * Math.sin(-rotSpeed) + p.getDirY() * Math.cos(-rotSpeed),
		     p.getPlaneX() * Math.cos(-rotSpeed) - p.getPlaneY() * Math.sin(-rotSpeed),
		     p.getPlaneY() * Math.cos(-rotSpeed) + p.getPlaneX() * Math.sin(-rotSpeed));
	}
	else
	    return false;

	return true;
    }
    
    public void fillScreen(SimpleInterface inter, Player p){

	double deltaDistX, deltaDistY;
	int stepX, stepY;
	double sideDistX, sideDistY; //dist de l'intersection la plus proche
	int mapX, mapY;
	double cameraX;
	double rayDirX, rayDirY;
	double perpWallDist;
	int side = 0; //was a NS or a EW wall hit?
	boolean hit; //was there a wall hit?

	//for drawing
	int lineHeight, drawStart, drawEnd;

	//time of the current frame and of the previous one
	//	double time = 0, oldTime = 0;

	//for the move
	//timing for input and FPS counter
	/*oldTime = time;
	  time = getTime???*/


	//draw rectangle for the ground
	inter.fillRect(0,0,dimx,dimy/2,skyColor);
	inter.fillRect(0,dimy/2,dimx,dimy,floorColor);
	    
	
	//width dimx
	//height dimy
	for(int i = 0; i < dimx; ++i){

	    //Calcul du vecteur (position et direction)
	    cameraX = 2 * i / (double)dimx - 1; // coordonnée X dans l'ecran
	    rayDirX = p.getDirX() + p.getPlaneX() * cameraX;
	    rayDirY = p.getDirY() + p.getPlaneY() * cameraX;

	    //which box of the map we are in
	    mapX = (int)p.getX();
	    mapY = (int)p.getY();

	    //length of ray from one x or y-side to next x or y-side
	    deltaDistX = Math.abs(1 /(rayDirX));
	    deltaDistY = Math.abs(1 /(rayDirY));

	    //what direction to step in x or y-direction (+1 or -1)
	    if(rayDirX < 0){
		stepX = -1;
		sideDistX = (p.getX() - mapX) * deltaDistX;
	    }
	    else{
		stepX = 1;
		sideDistX = (mapX + 1.0 - p.getX()) * deltaDistX;
	    }

	    if(rayDirY < 0){
		stepY = -1;
		sideDistY = (p.getY() - mapY) * deltaDistY;
	    }
	    else{
		stepY = 1;
		sideDistY = (mapY + 1.0 - p.getY()) * deltaDistY;
	    }

	    //perform DDA "Digital Differential Analysis"
	    hit = false;

	    while(!hit){
		//jump to the next map square, in x or y-direction
		if(sideDistX < sideDistY){
		    sideDistX += deltaDistX;
		    mapX += stepX;
		    side = 0;
		}else{
		    sideDistY += deltaDistY;
		    mapY += stepY;
		    side = 1;
		}

		//check if ray has hit a wall
		if(map.get(mapX,mapY) != 0){
		    hit = true;
		}
	    }

	    //Calculate distance projected on camera direction
	    if(side == 0)
		perpWallDist = (mapX - p.getX() + (1.0 - stepX) / 2.0) / rayDirX;
	    else
		perpWallDist = (mapY - p.getY() + (1.0 - stepY) / 2.0) / rayDirY;

	    //Calculate height of line to draw on screen
	    lineHeight = (int)(dimy / perpWallDist);

	    //Calculate lowest and highest pixel to fill in current stripe
	    drawStart = (int)((dimy - lineHeight) / 2.0);
	    drawEnd = (int)((dimy + lineHeight) / 2.0);

	    if(drawStart < 0) drawStart = 0;
	    if(drawEnd >= dimy) drawEnd = dimy - 1;

	    //give x and y sides different brightness
	    if(side == 1) wallColor[1] = 150;
	    else wallColor[1] = 200;
	    
	    for(int j = drawStart; j <= drawEnd; ++j){
		inter.setRGB(dimx-1-i,j,wallColor);
	    }
	}
	map.drawMiniMap(inter,p.getX(),p.getY());

    }

}
