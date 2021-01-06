package Quoridor;

import java.util.Scanner;

/**
 * Cette classe définit un joueur humain ainsi que son tour
*/ 



public class JoueurHumain extends Pion {

	private Scanner scan;
/**
 * Le constructeur de JoueurHumain, il initialize les attributs avec les paramètres donnés
 * @param nomJoueur : Un string contenant le nom du joueur
 * @param plateau : le plateau du jeu
 * @param uneCase : la couleur du pion qu'auras le joueur
*/
	public JoueurHumain(String nomJoueur, Plateau plateau, Case uneCase) {
			
			super(nomJoueur,plateau,uneCase);
			this.nom=nomJoueur;
			this.plateau=plateau;
			this.uneCase=uneCase;
			this.scan = new Scanner(System.in);

	}
	
/**
 * Une méthode qui va gérer le tour du joueur
*/ 	

	public void jouer() {
	
		int reponse,repX,repY,i,j;
		boolean trouve,verif;
		boolean[][]pos;
		
		
		repX=0;
		repY=0;
		trouve=false;
		// verif=false;
		
		Cube[][]grille=this.plateau.getGrille();
		
		System.out.println(" \n Debut du tour du joueur :"+nom);
		System.out.println("\n Voulez-vous avancer ou poser un mur : ");
		System.out.println("\n 1- Avancer le pion ");
		System.out.println("\n 2- Poser le mur");
		
		//Penser à traiter le cas ou l'user entre une lettre !
		
		reponse= scan.nextInt();
			
		if ( reponse== 1 ) {
		
			System.out.println("\n Vous avez choisi de placer un pion ");
			i=0;
			while ( (i<grille.length) && (!trouve) ) {
				j=0;
				while ( (j<grille[i].length) && (!trouve) ) {
					
					if ( grille[i][j].getCase()== this.uneCase ) {	
						repX=i;
						repY=j;		
						trouve=true;
					}
					j++;
				}
				i++;
			}
			this.plateau.placementPion(repX,repY);
			
			System.out.println(" Selectionner une position possible en entrant ses coordoones,parmis la liste suivante :");
			
			pos=plateau.getPos();
			
			for (i=0; i<pos.length; i++ ) {	
				for (j=0; j<pos.length; j++ ) {
					if( pos[i][j] ) {
					
						System.out.println(" Position possible ("+i+","+j+")");
					}
				}
			}
			
			//Penser à traiter le cas ou l'user entre une lettre !
			
			System.out.println(" Attention pour les coordonnees, veuillez entrer des chiffres pairs");
			
			System.out.println("\n Entrer la coordonnee en x :");
			repX=scan.nextInt();
			
			System.out.println("\n Entrer la coordonnee en y: ");
			repY=scan.nextInt();
			
			if ( ((repX>=0) && (repX<grille.length)) && (repY>=0) && (repY<grille.length)) {
				
				if (pos[repX][repY] ) {
				
					this.placerUnPion(repX,repY);

				}
				else { 
					
					System.out.println("\n Vous ne pouvez pas jouer a cette case ");
					System.out.println("\n Veuillez entrer des coordonnees de la liste ci-dessus : ");
					
					while (!pos[repX][repY] ) {
					
						System.out.println("\n Entrer la coordonnee en x :");
						repX=scan.nextInt();
			
						System.out.println("\n Entrer la coordonnee en y: ");
						repY=scan.nextInt();
						
						while ( ((repX<0) || (repX>grille.length )) || ((repY<0) || (repY>grille.length )) ) {
					
							System.out.println("\n Attention coordonnee incorrect, veuillez reessayer : ");
					
							System.out.println("\n Entrer la coordonnee en x :");
							repX=scan.nextInt();
			
							System.out.println("\n Entrer la coordonnee en y: ");
							repY=scan.nextInt();
						}
					}
					this.placerUnPion(repX,repY);
				}	
					
					
			}
			else { 

				System.out.println(" \n Attention coordonnee incorrect ");
				System.out.println("\n Veuillez entrer des coordonnees de la liste ci-dessus : ");
					
				do {
					
					System.out.println("\n Entrer la coordonnee en x :");
					repX=scan.nextInt();
			
					System.out.println("\n Entrer la coordonnee en y: ");
					repY=scan.nextInt();
						
						
					while ( ((repX<0) || (repX>grille.length )) || ((repY<0) || (repY>grille.length )) ) {
					
						System.out.println("\n Attention coordonnees incorrect, veuillez reessayer : ");
					
						System.out.println("\n Entrer la coordonnee en x :");
						repX=scan.nextInt();
			
						System.out.println("\n Entrer la coordonnee en y: ");
						repY=scan.nextInt();
					}
				}while (!pos[repX][repY] );
				
				this.placerUnPion(repX,repY);
					
			}
		}
		else if ( reponse==2 && (nbMur>0)) {	
				
			Case contraire;
			
			if ( uneCase==Case.NOIR ) {

				contraire=Case.BLANC;
			}
			else {
				
				contraire=Case.NOIR;
			}	
					
			System.out.println("Vous avez choisi de placer un mur");
					
			System.out.println("Pour la pose du mur , veuillez entrez une coordonnee paire et une autre impaire ");
			System.out.println("Si vous voulez poser un mur vertical, entrer d'abord la coordonnee paire");
			System.out.println("sinon pour un mur horizontal entrer la coordonnee impaire avant \n");		
			System.out.println("Entrer la coordonnee x du mur :");
			repX=scan.nextInt();
					
			System.out.println("Entrer la coordonnee y du mur :");
			repY=scan.nextInt();			
					
			if ( ((repX>=0) && (repX<grille.length)) && (repY>=0) && (repY<grille.length)) {
					
				verif=plateau.placementMur(repX,repY,contraire);
							
				if(!verif) {
						
					System.out.println(" Le mur ne peut être place a cette endroit ou le couple de coordonnee n'est pas pair/impair ");
					System.out.println(" RAPPEL : les coordonnee x et y corresponderont pour les murs horizontaux :");
					System.out.println(" - Au bord gauche du mur ");
					System.out.println(" Pour les murs verticaux :");
					System.out.println(" - Au bord haut du mur "); 
					
					while( !verif ) {
					
						System.out.println("\n Entrer la coordonnee en x :");
						repX=scan.nextInt();
			
						System.out.println("\n Entrer la coordonnee en y: ");
						repY=scan.nextInt();
						verif=plateau.placementMur(repX,repY,contraire);
						
						while (((repX<0) && (repX>=grille.length)) && ((repY<0) && (repY>=grille.length))){
							
							System.out.println("\n Entrer la coordonnee en x :");
							repX=scan.nextInt();
			
							System.out.println("\n Entrer la coordonnee en y: ");
							repY=scan.nextInt();
							verif=plateau.placementMur(repX,repY,contraire);
						}
					}	
					nbMur--;
					plateau.reinitialiserTot();
					System.out.println("\n Le mur a ete pose , murs restants :"+nbMur);
				}
				else {
					nbMur--;
					plateau.reinitialiserTot();
					System.out.println("\n Le mur a ete pose , murs restants :"+nbMur); 
				}
			}
			else { 
					
				System.out.println("\n Attention les coordonnee entre sont incorrectes");
				System.out.println(" \n Veuillez entrez un couple de coordonnees correctes et pair/impair ");
				System.out.println(" Le mur ne peut être place a cette endroit ou le couple de coordonnee n'est pas pair/impair ");
				System.out.println(" RAPPEL : les coordonnee x et y corresponderont pour les murs horizontaux :");
				System.out.println(" - Au bord gauche du mur ");
				System.out.println(" Pour les murs verticaux :");
				System.out.println(" - Au bord haut du mur ");
					
				do{
					
					System.out.println("\n Entrer la coordonnee en x :");
					repX=scan.nextInt();
				
					System.out.println("\n Entrer la coordonnee en y: ");
					repY=scan.nextInt();
							
					while (((repX<0) || (repX>=grille.length)) || ((repY<0) ||(repY>=grille.length))){
								
						System.out.println("\n Entrer la coordonnee en x :");
						repX=scan.nextInt();
				
						System.out.println("\n Entrer la coordonnee en y: ");
						repY=scan.nextInt();
					}
					verif=plateau.placementMur(repX,repY,contraire);
				}
				while(!verif);		
				
				nbMur--;
				plateau.reinitialiserTot();
				System.out.println("\n Le mur a ete pose , murs restants :"+nbMur);
			}	
		}
		else {
		
			System.out.println("\n Vous n'avez plus de mur vous ne pouvez donc plus en poser");
		}	
	}
 
/**
 * Une méthode qui permet placer un pion sur une case, elle met la case ou le pion se trouve à "free" pour mettre la case ou le pion veut se mettre de sa couleur
 * @param x: la coordonnée x
 * @param y: la coordonnée y
*/ 

 
	private void placerUnPion(int x,int y) {
	
		int i,j;
		boolean trouve=false;
	
		Cube[][]grille=this.plateau.getGrille();
		
			i=0;
			while ( (i<grille.length) && (!trouve) ) {
				j=0;
				while ( (j<grille[i].length) && (!trouve) ) {
					
					if ( grille[i][j].getCase()== this.uneCase ) {	
							
						grille[i][j].initializeCase("Free");
						trouve=true;
					}
					j++;
				}
				i++;
			}		
		
		
		grille[x][y].setCase(uneCase);
	}	
	
}