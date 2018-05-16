public abstract class LightAmmu extends Arme{
    private int portee;
    private int cadence; // rounds per minute
    private int chargeur; // Munitions maximales pouvant etre contenues dans un chargeur 

    public LightAmmu(String s,double d, int p,int c, int ch){
	super(s,d);
	portee = p;
	cadence = c;
	chargeur = ch;
    }

    /*public LightAmmu(){
	super();
	portee = 15 ; // rayon de pixels maximal pouvant être atteints 
      
	}*/

    public int getPortee(){
	return portee;
    }

    public int getCadence(){
	return cadence ;
    }

}
