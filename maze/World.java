public class World{
    private Area map;
    private double posX, posY;
    private int dimx, dimy;
    private int[] exitColor = new int[]{255,255,255};
    private int[] wallColor = new int[]{0,150,0};
    private int[] floorColor = new int[]{160,82,45};
    private int[] skyColor = new int[]{119, 181, 254};    
    
    public World(Area m, int width, int height){
	map = m;
	dimx = width;
	dimy = height;
    }

    public int getMapCell(int x, int y){
	return map.get(x,y);
    }

    public boolean walk(Player p, int key){
	double moveSpeed = 0.05;     //0.2  
	double rotSpeed = 0.04;//0.10

	switch (key){
	case 'z': //up
	    if(map.get((p.getX() + p.getDirX() * moveSpeed),(p.getY())) == 0)
		p.move(p.getDirX()*moveSpeed,0);
	    if(map.get(p.getX(),(p.getY() + p.getDirY() * moveSpeed)) == 0)
		p.move(0,p.getDirY()*moveSpeed);
	    break;
	case 's': //down
	    if(map.get((p.getX() - p.getDirX() * moveSpeed),(p.getY())) == 0)
		p.move(-p.getDirX()*moveSpeed,0);
	    if(map.get(p.getX(),(p.getY() - p.getDirY() * moveSpeed)) == 0)
		p.move(0,-p.getDirY()*moveSpeed);	    
	    break;
	case 'd': //right step
	    if(map.get((p.getX() - p.getDirY() * moveSpeed),(p.getY())) == 0)
		p.move(-p.getDirY()*moveSpeed,0);
	    if(map.get(p.getX(),(p.getY() + p.getDirX() * moveSpeed)) == 0)
		p.move(0,p.getDirX()*moveSpeed);
	    break;
	case 'q': //left step
	    if(map.get((p.getX() + p.getDirY() * moveSpeed),(p.getY())) == 0)
		p.move(p.getDirY()*moveSpeed,0);
	    if(map.get(p.getX(),(p.getY() - p.getDirX() * moveSpeed)) == 0)
		p.move(0,-p.getDirX()*moveSpeed);	    
	    break;
	case '6': //right rotate
	    p.rotate(p.getDirX() * Math.cos(rotSpeed) - p.getDirY() * Math.sin(rotSpeed),
		     p.getDirX() * Math.sin(rotSpeed) + p.getDirY() * Math.cos(rotSpeed),
		     p.getPlaneX() * Math.cos(rotSpeed) - p.getPlaneY() * Math.sin(rotSpeed),
		     p.getPlaneY() * Math.cos(rotSpeed) + p.getPlaneX() * Math.sin(rotSpeed));
	    break;
	case '4': //left rotate
	    p.rotate(p.getDirX() * Math.cos(-rotSpeed) - p.getDirY() * Math.sin(-rotSpeed),
		     p.getDirX() * Math.sin(-rotSpeed) + p.getDirY() * Math.cos(-rotSpeed),
		     p.getPlaneX() * Math.cos(-rotSpeed) - p.getPlaneY() * Math.sin(-rotSpeed),
		     p.getPlaneY() * Math.cos(-rotSpeed) + p.getPlaneX() * Math.sin(-rotSpeed));
	    break;
	default:
	    return false;
	}
	
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
	boolean hit, wall; //was there a wall hit?

	//for drawing
	int lineHeight, drawStart, drawEnd;
	int coeff_color;
	//time of the current frame and of the previous one
	//	double time = 0, oldTime = 0;

	//for the move
	//timing for input and FPS counter
	/*oldTime = time;
	  time = getTime???*/


	//draw rectangle for the ground
	inter.fillRect(0,0,dimx,dimy/2,skyColor);
	//    inter.fillRect(0,dimy/2,dimx,dimy,floorColor);


	//to change
	//	int tmp1 = (int)(2*dimy / 100);
	for(int i=dimy/2; i < dimy; ++i){
	    floorColor[0] = 120 + i/8;
	    floorColor[1] = 42 + i/8;
	    floorColor[2] = 5 + i/8;
	    inter.fillRect(0,i,dimx,i,floorColor);
	}
	    
	
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
	    wall = false;
	    
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
		if(map.get(mapX,mapY) == 1){
		    hit = true;
		    wall = true;
		}
		else if(map.get(mapX,mapY) == 2){
		    hit = true;
		    wall = false;
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

	    coeff_color = Math.abs(((drawEnd - drawStart) * 100)/dimy);
	    

	    //give x and y sides different brightness
	    if(side == 1) wallColor[1] = 116 + coeff_color;//150
	    else wallColor[1] = 156 + coeff_color;//200

	    if(wall){
		for(int j = drawStart; j <= drawEnd; ++j){
		    inter.setRGB(dimx-1-i,j,wallColor);
		}
	    }
	    else{
		for(int j = drawStart; j <= drawEnd; ++j){
		    inter.setRGB(dimx-1-i,j,exitColor);
		}
	    }

	}

    }

    /*public void drawMiniMap(boolean b){
	if(b) map.draw(inter,p.getX(),p.getY());
	else close
	} */

}
