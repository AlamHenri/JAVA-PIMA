public abstract class HeavyAmmu extends Arme{
    private int rayon_l; // distance maximale du lancer 
    private int rayon_a ; // rayon d'action / explosion 

    public HeavyAmmu(int lancer, int action){
	super();
	rayon_l = lancer; 
	rayon_a = action;
    }
    
    public int getRayonAction(){
	return rayon_a;
    }

    public int getRayonLancer(){
	return rayon_l;
    }

}