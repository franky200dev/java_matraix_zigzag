/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author fkamgaing
 */
public class LectureFichier {
	
	
	private int [][] m_tabFichier;
	private int m_longueur;
	private int m_hauteur;
	
	
	public LectureFichier(int[][] tabFichier){
		m_tabFichier = tabFichier;
		m_hauteur = tabFichier.length;
		m_longueur = tabFichier[0].length;
		
		
		
	}
	int getLongueur(){ return m_longueur; }
	int getHauteur(){ return m_hauteur; }
	int[][] getTab(){ return m_tabFichier; }
	
	void afficher(){
		for(int [] tab : m_tabFichier)
		{
			for(int elem : tab)
				System.out.print("\t"+elem);
			System.out.println();
		}
	}
	public LectureFichier(String nomFichier) throws IOException{
		// nomFichier=nomFichier.replace('/', '\\');
		FileReader monFichier=new FileReader(nomFichier);
		BufferedReader buf=new BufferedReader(monFichier);
		String ligne=null;
		ArrayList<int[]>stockLigne =new ArrayList<int[]>(); 
		while((ligne=buf.readLine())!=null)  // toute l'inctruction contenue dans le while me permet de lire le fichier
		{

			String[] tmp = ligne.split(";");
			int [] tabLigne=new int[tmp.length];
		    // Integer.parseInt(tmp[i]); permet de tiper la chaine de caractere(string)  en Int
			for(int i=0;i<tmp.length;i++)
				tabLigne[i]=Integer.parseInt(tmp[i]);
			stockLigne.add(tabLigne);
		}
		m_longueur=stockLigne.get(0).length; // la je me place sur la largeur de la matrice car elle est de dimension
		m_hauteur=stockLigne.size(); // ici j'ai hauteur de la matrice qui represente la taille de la matrice
		m_tabFichier=new int[m_hauteur][m_longueur]; //c'est le tableau qui contient tous mes fichiers
		for(int i=0;i<m_hauteur;i++)
			for(int j =0;j<m_longueur;j++)
				m_tabFichier[i][j]=stockLigne.get(i)[j]; // ici je stock toute les de mon  fichier dans getLigne
		
		
	}
// la fonction sauvegarder me permet de sauvegarder mon resultat et d'ecrire sur un fichier de sortie
	void sauvegarder(String sortie) throws IOException 
	{
		FileWriter fichier = new FileWriter(sortie); // Filewrite me permet d'ecrire sur le fichier
		for(int i=0;i<m_hauteur;i++)
		{	
			for(int j =0;j<m_longueur;j++)
			{
				if(j>0) // ca me permet de ne pas afficher les ;  au debut du caractere 
					fichier.write(";"); // va me separer chaque element par un point virgule
				fichier.write(Integer.toString(m_tabFichier[i][j]));//ici je contient mon fichier d'entree en chaine de carctere pour pouvoir stocker dans string
			}
			fichier.write("\n"); // je vais a la ligne a la fin de chaque ligne
		}
		fichier.close();
	}
}
