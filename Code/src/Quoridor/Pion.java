package Quoridor;

/**
 * Cette classe abstraite définit les méthodes importantes que doivent possèder les deux classes "JoueurHumain" et "IA"
*/ 

public abstract class Pion {

	protected String nom;
	protected Plateau plateau;
	protected Case uneCase;
	protected int nbMur=10;
/**
 * Le constructeur de Pion il initialize les attributs avec les paramètres
 * @param nomJoueur : Un string contenant le nom du 1er joueur
 * @param uneCase : la couleur du pion du joueur
 * @param plat : le plateau de jeu 
*/
	public Pion(String nomJoueur, Plateau plat, Case uneCase) {
	
		if ((nomJoueur!=null) && ( uneCase!=null)&& (plat!=null)) {
		
			this.nom= nomJoueur;
			this.plateau= plat;
			this.uneCase=uneCase;
		}
	}
	
/**
 * Un getter qui renvoie le nom du joueur/IA
 * @return le nom du joueur/IA
*/ 
	
	public String getNom() { return this.nom;}

/**
 * Cette méthode permettras de gérer le tour d'un joueur humain/IA
*/ 
	
	public abstract void jouer();


}