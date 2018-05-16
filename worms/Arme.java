public abstract class Arme{
    private String nom;
    private double degat;
    private static int cpt = 0;

    public Arme(String s, double d){
	nom = s;
	degat = d;
	++cpt;
    }

    public Arme(){
	nom = String.format("%s%02d","Arme N°",cpt);
	degat = 100; // Je savais pas quoi mettre 
    }

    public static int getCpt(){
	return cpt;
    }
    
    public double getDamage(){
	return degat;
    }

}
