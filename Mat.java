/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.min;

/**
 *
 * @author fkamgaing
 */
public class Mat {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO code application logic here
		
		//f.afficher();
		
		int [] tab =null;
		
		//f1.afficher();
        //f2.afficher();
		
				
				
		String entree=null;
		String sortie=null;
		int largeur=-1;
		int hauteur;
		boolean sensConvert=true;
        for(int i =0;i<args.length;i++)
		{
			if(args[i].equals("-i"))
			{
				i++;
				entree=args[i];
			}
			else if(args[i].equals("-o"))
			{
				i++;
				sortie=args[i];
			}
			else if(args[i].equals("-b2l"))
			{
				sensConvert=true;
			}
			else if(args[i].equals("-l2b"))
			{
				sensConvert=false;
				
			}
			else if(args[i].equals("-w")) 
			{
				i++;
				largeur=Integer.parseInt(args[i]);
			}		
		}
		LectureFichier f =new LectureFichier(entree);
		LectureFichier f1=null;
		if(sensConvert==true)
		{
			Convertisseur convert= new  Convertisseur(f.getLongueur(),f.getHauteur());
			tab =  convert.blockToLine(f.getTab());
			for( int elem : tab)
				System.out.print("\t"+elem);
				System.out.println();
				if(largeur<=0)
				{
					largeur=f.getLongueur();
				}
				 f1 =new LectureFichier(pack(tab,largeur));
		}
		else
		{
			if(largeur<=0)
			{
				largeur=f.getLongueur();
			}
			hauteur=(f.getLongueur()*f.getHauteur()+largeur-1)/largeur;
			Convertisseur convert1= new  Convertisseur(largeur,hauteur);
			 f1 =new LectureFichier(convert1.lineToBlock(toLine(f.getTab())));
		}
		f1.sauvegarder(sortie);
	}
	
	public static int[][] pack(int[] tab,int largeur)
	{
		int[][]ret=new int[(tab.length+largeur-1)/largeur][largeur];
		for(int i =0;i<tab.length;i++)
			ret[i/largeur][i%largeur]=tab[i];
		return ret;
	}
	// 
	public static int[] toLine(int[][] tab)
	{
		int[]ret=new int[tab.length*tab[0].length];
		for(int i =0;i<tab.length;i++)
			for(int j =0;j<tab[0].length;j++)
				ret[j+tab[0].length*i]=tab[i][j];
		return ret;
	}
	
}