package Quoridor;

import java.util.Scanner;
import java.util.Scanner;
import java.io.File; 
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

/**
 *  Cette classe gère le déroulement de la partie, elle contient sa description, permet de la lancer, et de sauvegarder/charger
*/ 

public class Partie  {

	private Pion pion1;
	private Pion pion2;
	private Plateau plat;
	private ModeDeJeu mode;
	

/**
 * Le constructeur de Partie il initialize les attributs avec les paramètres donnés 
 * @param nomPion1 : Un string contenant le nom du 1er joueur
 * @param nomPion2 : Un string contenant le nom du 2ème joueur
 * @param mode : Un type "Mode" qui contiendras le mode de jeu
*/
	public Partie(String nomPion1, String nomPion2, ModeDeJeu mode) {
	
		if ( (nomPion1!= null) && (nomPion2!=null) && (mode!=null) ) {
		
			this.plat= new Plateau();
			this.mode=mode;
			
			configurerPlateau();
			
			switch(mode) {
			
				case HH :
					this.pion1= new JoueurHumain(nomPion1,plat,Case.NOIR);
					this.pion2= new JoueurHumain(nomPion2,plat,Case.BLANC);
					break;
					
				case HIA : 
					this.pion1= new JoueurHumain(nomPion1,plat,Case.NOIR);
					this.pion2= new IA(nomPion2, plat, Case.BLANC);
					break;
					
				case IAIA:
					this.pion1= new IA(nomPion1,plat,Case.NOIR);
					this.pion2= new IA(nomPion2,plat,Case.BLANC);
					break;
					
			}		
					
		}
	}	

	
/**
 * Un deuxième constructeur qui est utilisé dans le cadre du chargement d'une partie
 * @param nomPion1 : Un string contenant le nom du 1er joueur
 * @param nomPion2 : Un string contenant le nom du 2ème joueur
 * @param mode : Un type "Mode" qui contiendras le mode de jeu
 * @param plat : le plateau de la partie sauvegardé
*  @param diff : la difficulté de l'IA  
*/ 
	
	public Partie(String nomPion1,String nomPion2, ModeDeJeu mode, Plateau plat,String diff){

		
		if ((nomPion1!= null) && (nomPion2!=null) && (mode!=null) && (plat!=null ) ) {
				
			this.mode=mode;
			this.plat=plat;
			
			switch(mode) {
			
				case HH :
					this.pion1= new JoueurHumain(nomPion1,plat,Case.NOIR);
					this.pion2= new JoueurHumain(nomPion2,plat,Case.BLANC);
					break;
					
				case HIA : 
					this.pion1= new JoueurHumain(nomPion1,plat,Case.NOIR);
					this.pion2= new IA(nomPion2, plat, Case.BLANC,diff);
					break;
					
			}				
			
		}	
	}

/**
 * Un getter qui retourne le mode
 * @return  : Le mode retourné
*/ 

	protected ModeDeJeu getMode() {return this.mode; }

	

/**	
 *	Cette méthode place les pions des joueurs à la position de départ 
*/
	private void configurerPlateau() {

		Cube[][]grille=this.plat.getGrille();
		
			grille[0][8].initializeCase("Blanc");
			grille[16][8].initializeCase("Noir");
	}		
		
/**
 * Une méthode qui contient la description du jeu
*/ 
	
	public void description() {
	
		System.out.println("\n Le jeu du Quoridor est un jeu de plateau qui peut se jouer jusqu'a 4 joueurs");
		System.out.println(" Le but est du jeu est d'atteindre avec son pion la ligne de depart adverse ");
		System.out.println(" Cependant pour bloquer son adversaire des murs peuvent etre poses a condition de ne pas bloquer son adversaire");
		System.out.println(" Chaque joueur a  10 murs en mode 2 joueurs et 5 mur en mode 4 joueurs");
		System.out.println("Bonne chance !");	

	}

/**
 * Cette méthode gère la partie, elle contiendras "fin()" qui annonceras qui est le vainqueur
*/ 
	
	public void lancer() {
	
		boolean fin=estCeLaFin();
		boolean sauvegarde=false;	
		int i=0;
				
		while ((!fin) && (!sauvegarde)) {
		
			System.out.println(this.toString());
		
			System.out.println("Tour["+(i+1)+"] :"+pion1.getNom() );
			plat.reinitialiserPosition();
			pion1.jouer();	
			System.out.println(this.toString());
			
			
			fin=estCeLaFin();
			if(!fin ) {
			
				System.out.println("Tour["+(i+1)+"] :"+pion2.getNom() );
				plat.reinitialiserPosition();
				pion2.jouer();
				System.out.println(this.toString());
				
				if( this.mode!=ModeDeJeu.IAIA) {
				
					sauvegarde=this.sauvegarder();	
				}
				
				fin=estCeLaFin();
				
			}
		}	
		if (!sauvegarde ) {
			this.fin();
		}
	}
/**
 * Cette méthode sauvegardera la partie actuelle, elle renveras un type booléen si le joueur veut bien sauvegarder
 * @return ret : le boolean vrai si le joueur décide de sauvegarder
*/ 

	public boolean sauvegarder() {
		boolean ret=false;
		boolean fichierPresent=false;

		String nomMode="";	
		String chemin= "../data/sauvegardes";
		
		File dir = new File(chemin);
		File[]sauvegardes= new File[10];
	
		Scanner scan= new Scanner(System.in);

		System.out.println(" Voulez-vous sauvegarder ?");	
		String reponse=scan.next();
		
		
		if ( reponse.compareToIgnoreCase("oui")==0) {
		
			if( mode == ModeDeJeu.HH ) {
		
			nomMode="HH";
			}
			else if (mode == ModeDeJeu.HIA ) {

			nomMode="HIA";
			}	
		
			ret= true;
			
			System.out.println(" Sauvegarde en cours, ne quittez pas s'il vous plait");
			sauvegardes= dir.listFiles();
			
			if( sauvegardes.length<10 ) {
			
				try{
				
					if ( sauvegardes.length==0 ) {
					
						File sauvegarde= new File("../data/sauvegardes/sauvegarde1");
						sauvegarde.mkdir();
						
						FileOutputStream joueurModeFichier = new FileOutputStream( new File ("../data/sauvegardes/sauvegarde1/NomJoueur_Mode.bin"));
						BufferedOutputStream buf = new BufferedOutputStream(joueurModeFichier);
						DataOutputStream data = new DataOutputStream(buf);
		
						data.writeUTF(nomMode);
						data.writeUTF(pion1.getNom());
						data.writeUTF(pion2.getNom());
						
						if( nomMode.equals("HIA") ) {
							data.writeUTF(((IA)(pion2)).getDiff());				
						}
						data.close();
						buf.close();
						joueurModeFichier.close();
						
	
			
			
						FileOutputStream plateau1 = new FileOutputStream( new File ("../data/sauvegardes/sauvegarde1/plateau.bin"));
						ObjectOutputStream out = new ObjectOutputStream(plateau1);
						
						out.writeObject(this.plat);
						
						out.close();
						plateau1.close();
						
					}
					else {
					
						int nb= sauvegardes.length+1;
						
						chemin="../data/sauvegardes/sauvegarde"+nb;
					
						File sauvegarde= new File(chemin);
						
						sauvegarde.mkdir();
						
						FileOutputStream joueurModeFichier = new FileOutputStream( new File ("../data/sauvegardes/sauvegarde"+nb+"/NomJoueur_Mode.bin"));
						BufferedOutputStream buf = new BufferedOutputStream(joueurModeFichier);
						DataOutputStream data = new DataOutputStream(buf);
						
						data.writeUTF(nomMode);
						data.writeUTF(pion1.getNom());
						data.writeUTF(pion2.getNom());
						
						if( nomMode.equals("HIA") ) {
							data.writeUTF(((IA)(pion2)).getDiff());				
						}
						
						data.close();
						buf.close();
						joueurModeFichier.close();
						
						FileOutputStream suivPlateau = new FileOutputStream( new File ("../data/sauvegardes/sauvegarde"+nb+"/plateau.bin"));
						ObjectOutputStream out = new ObjectOutputStream(suivPlateau);
						
						out.writeObject(this.plat);
						
						out.close();
						suivPlateau.close();
					}
				}
				catch(IOException io ) { io.printStackTrace(); }
			}
			else {
			
				System.out.println(" Vous avez atteint le nombre maximal de sauvegarde");
			}	
		}				
		return ret;
	}



/**
 * Une méthode qui indique si la partie est terminée 
 * Elle renvoie un booléen qui est vrai si la partie est terminée
 * Pour cela elle vérifie si un des deux joueurs à atteint le camp adverse
 * @return ret : le booléen qui est vrai si la partie est finie
*/ 

	
	private boolean estCeLaFin() {
	
		boolean ret=false;
		Cube[][]grille= plat.getGrille();
		Case noir = Case.NOIR;
		Case blanc= Case.BLANC;
		
		int xN,yN,xB,yB,i,j;
		xN=0;
		yN=0;
		xB=0;
		yB=0;
		
		i=0;
		while ( (i<grille.length) && (!ret) ) {
			j=0;
			while( (j<grille[i].length) && (!ret) ) {

				if ( grille[i][j].getCase()==noir ) {	
						
					xN=i;
					yN=j;
				}
						
				if ( grille[i][j].getCase()==blanc ) {	
						
					xB=i;
					yB=j;
				}
				j++;
			}
			i++;
		}

		if (( xN==0 ) || ( xB==16 ) ) {

			ret=true;
		}
		return ret;
		
	}		
	
	
/**
 * Une méthode qui annonce le vainqueur à la fin de la partie
 * Pour indiquer le vainqueur elle regarde qui a atteint le camp adverse
*/ 
	private void fin() {
		
		boolean verif=false;
		Cube[][]grille= plat.getGrille();
		Case noir = Case.NOIR;
		Case blanc= Case.BLANC;
		int i=0;
		
		while ( (i<grille[0].length) && (!verif) ) {
		
			if ( grille[0][i].getCase()==noir ) {
			
				verif=true;
			}
			i++;
		}
		
		if( verif ) {
		
			System.out.println(" \n Le joueur noir :"+pion1.getNom()+" a gagne ! ");
			
		}
		else {
		
			System.out.println(" \n Le joueur blanc :"+pion2.getNom()+" a gagne ! ");
		}
		
	}
	
/**
 * Une méthode qui afficheras le plateau
 * Elle appelle la méthode "toString()" de plateau
 * @return : Le String qui contient le plateau
*/ 
	
	public String toString() {
	
		String ret=plat.toString();
			
		return ret;
	}

}