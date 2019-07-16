/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.min;

/**
 *
 * @author fkamgaing
 */
public class Convertisseur { // la class convertisseur me converrti un tableau de tableau en un tableau et eccfectue aussi le parcours de la matrice zig-zag
	
	private int m_longueur; //represente la largeur de tableau
	private int m_hauteur; // c'est la hauteur comme son nom l'indice
	int[][] m_depart;  // ici j'ai la matrice de depart 
	int[][] m_direction; // direction de parcous de la matrice car elle s'effectue sur le demiu perimetre soit la longueur et la hauteur 
	int[] m_taille; // taille  de la matrice a parcourir
	
	public Convertisseur(int longueur,int hauteur){ // ici dans le contructeur j'initialise les attribut a utiliser
		m_longueur=longueur;
		m_hauteur=hauteur;
		m_depart= new int[2][demiperim()];  // 2 represente la largeur et demiperim c'est la distance qu'on parcours  donc longueur + hauteur cdans notre algorithme 
		m_direction =new int[2][demiperim()];
		m_taille= new int[demiperim()];
		for(int i =0;i<demiperim();i++){ 
			m_taille[i]= Math.min(min(i+1,longueur),min(hauteur,demiperim()-i));  // la taille c'est minimum entre min(i+1,longueur) et min(hauteur,demiperim()-i)
			if(i%2==0){ // ici j'effectu le parcours  dans un sens ou dans l'autre 
				m_direction[0][i]=-1;
				m_direction[1][i]=1;

				if(i<longueur){
					m_depart[0][i]=i;
					m_depart[1][i]=0;
				}
				else{ 
					m_depart[0][i]=longueur-1;
					m_depart[1][i]=i-(longueur-1);
				}
			}else{
				m_direction[0][i]=1;
				m_direction[1][i]=-1;
				if(i<hauteur){
					m_depart[0][i]=0;
					m_depart[1][i]=i;
				}
				else{
					m_depart[0][i]=i-(hauteur-1);
					m_depart[1][i]=hauteur-1;
				}
			}
		}
		
	}
	
	
	
	private int demiperim(){ // demi perim est juste la hauteur de parcours de la matrice
		return m_longueur+m_hauteur-1;
	}
	
	int [] blockToLine(int [][] tab){  // block to line est une fonction qui me permet de prendre traité qui est a deux dimension et de l'ecrirze a une seule diension 
		int[] tableauRetour = new int[m_longueur*m_hauteur];
		int index = 0;
		for(int i=0;i<demiperim();i++){
			for(int j =0;j<m_taille[i];j++){
				int x = m_depart[0][i] + j*m_direction[0][i];
				int y = m_depart[1][i] + j*m_direction[1][i];
				tableauRetour[index]=tab[y][x]; 
				index++;
			}
		}
		return tableauRetour;
	}
	
	int[][] lineToBlock(int[] tab){  // prend un tableau d'une dimension et rzenvoi un tableau de tableau 
		int[][] tableauRetour = new int[m_hauteur][m_longueur];
		int index = 0;
		for(int i=0;i<demiperim();i++){
			for(int j =0;j<m_taille[i];j++){
				int x = m_depart[0][i] + j*m_direction[0][i];
				int y = m_depart[1][i] + j*m_direction[1][i];
				tableauRetour[y][x]=tab[index]; 
				index++;
			}
		}
		return tableauRetour;
	}

			
			

 }
	
