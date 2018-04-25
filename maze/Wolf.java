public class Wolf{
    
    
    public static void main(String[]args){
	//int[] mine;

	
	Game game = new Game();
	
	game.run();

	/*
      	while(true){

	    ui.waitClick();
	    
	    p.move(1,0,0);

	    w.fillScreen(p);
	    w.drawScreen(ui);
	    m.drawMiniMap(ui,p);
	    ui.refresh();
	    }*/
	//	ui.drawImage(ui.loadImage("wolf.png") , 0, 0, x*20, y*20);
	//	m.drawMiniMap(ui);
	/*
	for (int t = 0; t < 10000; ++t){
	    ui.pause(1);
	    //	    m.etatSuivant();
	    
	    for (int i=0;i<m.getX();i++)
		for (int j=0;j<m.getY();j++)
		    ui.setRGB(i, j, m.get(i,j).getColor());
			    
	    //mine = ui.popClick();
	    
	    ui.refresh();
	}*/
    }
}
