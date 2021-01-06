package Quoridor;

import java.util.Scanner;

/**
 * Cette classe definit une IA ainsi que son tour les choix seront aleatoires dans un premier temps et si le temps le permet d'autres niveau de difficultes
*/ 

public class IA extends Pion {

	private String diff;

/**
 * Le constructeur de IA, il initialise les attributs avec les parametres donnes
 * @param nomIA : Un string contenant le nom de l'IA
 * @param plat : le plateau du jeu 
 * @param uneCase : la couleur du pion qu'auras l'IA
*/
	public IA(String nomIA, Plateau plat, Case uneCase) {
	
			super(nomIA,plat,uneCase);
			this.nom=nomIA;
			this.plateau=plateau;
			this.uneCase=uneCase;
			this.difficulteIA();
	}
	
/**
 * Le constructeur de IA, utilise dans le cadre ou une partie a ete chargee
 * @param nomIA : Un string contenant le nom de l'IA
 * @param plat : le plateau du jeu
 * @param uneCase : la couleur du pion qu'auras l'IA
 * @param diff : La difficulte de l'IA sauvegardee
*/
	public IA(String nomIA, Plateau plat, Case uneCase, String diff ) {
	
		super(nomIA,plat,uneCase);
		this.nom=nomIA;
		this.plateau=plateau;
		this.uneCase=uneCase;
		this.setDiff(diff);
	}		


/**
 * Une methode qui definiras la difficulte de l'IA, faible ou difficile
*/ 

	private void difficulteIA() {
	
		Scanner scan= new Scanner(System.in);
		String reponse,reponse2;	
		int faible, difficile;
		
		
		System.out.println("Quel sera le niveau de difficulte de l'IA ?");
			
		System.out.println("Choisissez parmi faible et difficile :");
		reponse= scan.nextLine();
				
		faible=reponse.compareToIgnoreCase("faible");
		difficile=reponse.compareToIgnoreCase("difficile");
			
		if( (faible!=0) && (difficile!=0) ) {

			System.out.println(" Veuillez choisir une difficulte valide :(faible ou difficile");
					
			while ( (faible!=0) && (difficile!=0) ) {
					
				System.out.println("Choisissez parmi faible et difficile :");
				reponse= scan.nextLine();
						
				faible=reponse.compareToIgnoreCase("faible");
				difficile=reponse.compareToIgnoreCase("difficile");
				this.setDiff(reponse);
			}
			
		}			
		else { 
			
			this.setDiff(reponse);
		}
	}	


	
/**
 * Une methode qui va gerer le tour de l'IA et va lancer l'IA selon son niveau 
 * Une difficulte difficile etait possible base sur l'algorithme de Dijkstra mais n'as pas pu etre implementee
*/
	public void jouer(){
	
		if ( diff.compareToIgnoreCase("faible")==0){
		
			niveauFaible();
			
		}
		else if (diff.compareToIgnoreCase("difficile")==0) {
		
			System.out.println(" Un deuxieme niveau de difficulte etait prevu avec  pour base l'algorithme de Dijksta");
			System.out.println(" Mais n'as pas pu etre implementee faute de temps,une IA en niveau faible est donc lancee a la place ");
				
			niveauFaible();
		
		}
	}
	
	
/**
 *  Une methode qui va definir le niveau de l'IA
*/ 
	private void niveauFaible() {
	
		int i,j,x,y,aleat,xMax,yMax,xMin,yMin;
		boolean trouve,verif;
		boolean[][]pos;
		Case contraire;
		
		x=0;
		y=0;
		trouve=false;
		
		Cube[][]grille=this.plateau.getGrille();
		
		
		if ( uneCase==Case.NOIR ) {

				contraire=Case.BLANC;
		}
		else {
		
			contraire=Case.NOIR;
		}
		
		
		aleat=(int)(Math.random()*((100)+1));
	
	
		if ( (aleat<50) && (nbMur>0) ) {  //Si aleat>50, l'IA se deplace sinon elle pose un mur 
		
				
			x=(int)(Math.random()*(16)+1);
			y=(int)(Math.random()*(16)+1);
			System.out.println("x:"+x+ " y: "+y);
					
			verif=plateau.placementMur(x,y,contraire);
				
				if(!verif ) {
				
					while (!verif){
							
						x=(int)(Math.random()*(16)+1);
						y=(int)(Math.random()*(16)+1);
						System.out.println("x:"+x+ " y: "+y);
						verif=plateau.placementMur(x,y,contraire);
					}
					nbMur--;
					plateau.reinitialiserTot();
					System.out.println("\n Le mur a ete pose , murs restants de l'IA :"+nbMur);
				}
				else {
					nbMur--;
					plateau.reinitialiserTot();					
					System.out.println("\n Le mur a ete pose , murs restants de l'IA: :"+nbMur); 
				}
		}
		else {    
			
			i=0;
			while ( (i<grille.length) && (!trouve) ) {
				j=0;
				while ( (j<grille[i].length) && (!trouve) ) {
					
					if ( grille[i][j].getCase()== this.uneCase ) {	
						x=i;
						y=j;	
						trouve=true;
					}
					j++;
				}
				i++;
			}
			this.plateau.placementPion(x,y);
			pos= plateau.getPos();
			
			
			xMax=0;
			yMax=0;
			xMin=grille.length-1;
			yMin=grille.length-1;
			
			for (i=0; i<pos.length; i++ ) {	
				for (j=0; j<pos.length; j++ ) {
					if( pos[i][j] ) {
							
						System.out.println(" Position possible ("+i+","+j+")");
						
						if (j>yMax  ) {
						
							yMax=j;
						}
						if (j<yMin) {
							
							yMin=j;
						}	
							
						if (i >xMax ) {
				
							xMax=i;
						}
						if (i <xMin ) {
				
							xMin=i;
						}
						
					}
				}
			}
	
			x=xMin + (int)(Math.random()*((xMax-xMin)+1));
			y=yMin + (int)(Math.random()*((yMax-yMin)+1));
		
			if ( pos[x][y] ) {

				this.placerUnPion(x,y);	
	
			}
			else{
							
				while (!pos[x][y]) {
		
					x=xMin + (int)(Math.random()*((xMax-xMin)+1));
					y=yMin + (int)(Math.random()*((yMax-yMin)+1));
	
				}
				
				this.placerUnPion(x,y);
				
			}
		}
	}

/**
 * (Si le temps le permet ) Une methode qui va definir le niveau de l'IA
 * Une methode qui aurais ete base sur l'algorithme de Dijksta, qui aurais cherchee le plus cours chemin pour l'IA a chaque tour
 * Grace a cet algorithme l'IA se serais deplacee plus intelligemment et aurais apportee plus de challenge
 * Mais cette fonctionnalite n'as pas pu etre implementee faute de temps
*/ 	
	private void niveauEleve() {

	}
/**
 * Un setter qui definiras la difficulte de l'IA donnee par un string
 * @param diff : Le string qui indique la difficulte
*/ 
	
	protected void setDiff( String diff ) {
	
		if (diff!=null ) {
			
			this.diff=diff;
		}
	}

/**
 * Un getter qui retourne la difficulte de l'IA
 * @return :  La difficulte de l'IA 
*/ 
	
	protected String getDiff() { return this.diff;}	
	
	
	
/**
 * Une methode qui permet placer un pion sur une case, elle met la case ou le pion se trouve a "free" pour mettre la case ou le pion veut se mettre de sa couleur
 * @param x: la coordonnee x
 * @param y: la coordonnee y
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