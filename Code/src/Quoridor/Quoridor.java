package Quoridor;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;

/**
 * Le but de cette classe sera de configurer le jeu avec le ou les fichiers de configuration disponible ainsi que de générer la partie en fonction du mode de jeu 
*/ 

public class Quoridor {

	private ModeDeJeu unMode;
	private Partie unePartie;

/**
 * Le constructeur de Quoridor, il initialize les attributs avec les paramètres donnés
 * @param nomJoueur1 : Un string contenant le nom du 1er joueur
 * @param nomJoueur2 : Un string contenant le nom du 2ème joueur
 * @param nomFichier : le nom du fichier utilisé par la méthode configure(nomFichier : String)
*/
	public Quoridor(String nomJoueur1, String nomJoueur2, String nomFichier) {
	
		configure(nomFichier);
		this.unePartie= new Partie(nomJoueur1,nomJoueur2,this.unMode);
		this.unePartie.description();
		unePartie.lancer();
	}
	
	/**
 * Ce constructeur sera utilisé dans le cas du chargement d'une partie
*/ 
	public Quoridor() {
	
		this.unePartie= charger();
		
		System.out.println(" Reprise de la partie :  ");
		this.unePartie.lancer();
	
	}
	
/**
 * Cette méthode a pour but d'initialiser le mode pour la partie dans le fichier dont le nom est donné en paramètre
 * @param nomFichier : Le nom du fichier
*/
	private void configure ( String nomFichier ) {
	
		int i=0;
		FileReader fichier=null;
		BufferedReader in =null;
		
		try{
			fichier= new FileReader(nomFichier);
			in  = new BufferedReader(fichier);
			String s = in.readLine();
			
			this.initialiserMode(s);
			
			in.close();
		}	
		catch (FileNotFoundException f) { f.printStackTrace();}
		catch (IOException io) { io.printStackTrace();}
	}	
	
	


	
/**
 * Cette méthode permettras de charger une partie, elle renvoie la partie chargée
 * @return ret : la partie chargée
*/ 
	
	public Partie charger() {
	
		String chemin ="../data/sauvegardes";
		String nomMode=null;
		String nomJoueur1= null;
		String nomJoueur2=null;
		String difficulte=null;
		
		Plateau plat=null;
		
		Partie ret=null;
		
		File dir = new File(chemin);
		File[]sauvegardes=dir.listFiles();
		
		Scanner scan= new Scanner(System.in);
		
		if (sauvegardes.length>0 ) {
		
			System.out.println("\n Quelle partie voulez vous charger ?");
			
			for (int i =0; i<sauvegardes.length; i++ ) {
			
				if(sauvegardes[i]!=null ) {
				
					System.out.println(" Vous avez :"+sauvegardes.length+"sauvegarde choisissez une sauvegarde en entrant un chiffre");
					System.out.println((i+1)+" "+sauvegardes[i].toString());

				}
			}
			String reponse=scan.next();	
			char[]tab = reponse.toCharArray();
			boolean valid = false;

			if(tab.length<3){
				int  i = 0;
				do {
					if (i>0){
						System.out.println("Erreur : Veuillez entrer un des choix ci-dessus : ");
						for (int j =0; j<sauvegardes.length; j++ ) {
			
							if(sauvegardes[j]!=null ) {
								System.out.println("rrf");
							System.out.println(" Vous avez :"+sauvegardes.length+"sauvegarde choisissez une sauvegarde en entrant un chiffre");
							System.out.println((j+1)+" "+sauvegardes[j].toString());
		
							}
						}


						reponse = scan.next();
						tab = reponse.toCharArray();
					}
					
					if(tab.length<3){
						if(tab.length==1){
							if(Character.isDigit(tab[0])){
								if(Integer.parseInt(reponse)>0 && Integer.parseInt(reponse)<= sauvegardes.length ){
									valid = true;
								}
							}
						}
						else{
							if(reponse.compareToIgnoreCase("10") ==0 ){
								valid = true;


							}

						}

					}



					i++;			
				} while (!valid);

				

			}
			else{
				valid = false;
				
				do {
					System.out.println("Erreur : Veuillez entrer un des choix ci-dessus : ");
					for (int i =0; i<sauvegardes.length; i++ ) {
			
						if(sauvegardes[i]!=null ) {
					
						System.out.println(" Vous avez :"+sauvegardes.length+"sauvegarde choisissez une sauvegarde en entrant un chiffre");
						System.out.println((i+1)+" "+sauvegardes[i].toString());
	
						}
					}
					reponse = scan.next();
					tab = reponse.toCharArray();
					if(tab.length<3){
						if(tab.length==1){
							if(Character.isDigit(tab[0])){
								if(Integer.parseInt(reponse)>0 && Integer.parseInt(reponse)<= sauvegardes.length ){
									valid = true;
								}
							}
						}
						else{
							if(reponse.compareToIgnoreCase("10") ==0 ){
								valid = true;


							}

						}

					}
				
				} while (!valid);
			}

			
			
			try {

				FileInputStream fichier = new FileInputStream("../data/sauvegardes/sauvegarde"+reponse+"/NomJoueur_Mode.bin");
				DataInputStream data =  new DataInputStream(fichier);
				ObjectInputStream out=null;
				String s = data.readUTF();	
				
				
				while ( (nomJoueur1==null) && (nomJoueur2==null) &&(unMode==null) && (plat==null ) ){
				
					
					if (unMode==null) {
						this.initialiserMode(s);
					}
					if (nomJoueur1==null ) {
						s = data.readUTF();
						nomJoueur1=s;
					}

					if(nomJoueur2==null) {
						s = data.readUTF();
						nomJoueur2=s;
					}
					if ( unMode==ModeDeJeu.HIA ) {
						if ( difficulte==null ) {
							s=data.readUTF();
							difficulte=s;
						}
					}	
					if( plat==null) {
						
						fichier = new FileInputStream("../data/sauvegardes/sauvegarde"+reponse+"/plateau.bin");
						out = new ObjectInputStream(fichier);
						
						plat = (Plateau)out.readObject();
					}
				}
				data.close();
				out.close();
				fichier.close();
			}	
			catch (IOException io ) {io.printStackTrace(); }
			catch (ClassNotFoundException c) { c.printStackTrace();}
		}

		ret= new Partie( nomJoueur1,nomJoueur2, this.unMode, plat,difficulte);
		
		return ret;
	}
	
	
/**
 * Cette méthode est utilisée dans "charger()" pour initialiser le mode, elle prend en paramètre le nom du mode
 * @param nomMode : le nom du mode representé par un string 
*/ 
	
	private void initialiserMode(String nomMode ) {
	

		if (  ( nomMode.compareToIgnoreCase("HH") ) ==0 ) {
			
			this.unMode= ModeDeJeu.HH;
		}
		else if ( ( nomMode.compareToIgnoreCase("HIA") ) == 0 ) {
			
			this.unMode=ModeDeJeu.HIA;
			
		}
		else if ( ( nomMode.compareToIgnoreCase("IAIA") ) == 0 ) {

			this.unMode=ModeDeJeu.IAIA;
		}	
	}
	

}