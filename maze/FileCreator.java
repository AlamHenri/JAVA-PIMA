import java.util.*;
import java.io.*;
import java.lang.*;


public class FileCreator{
    private Formatter file;

    
    public void openFile(){
	try{
	    FileWriter f = new FileWriter("ScoreTable.txt",true);
	    file = new Formatter(f);
	}catch(Exception e){
	    System.out.println("Malparido de los pepes");
	}
    }

    public void printf(String nom, int dim, int time, int score){
	file.format("%s\t%d\t%d\t%d\n",nom,dim,time,score);
    }

    public void closeFile(){
	file.close();
    }

}