package Quoridor;
 import java.io.Serializable; 


/**
 * Cette classe définit le plateau ainsi que les vérifications sur les placements des murs/pions
*/ 

public class Plateau implements Serializable {

	private Cube[][]grille;
	private final int taille=17;
	private boolean[][]position;
	private int[][]tot;
	

/**
 * Le constructeur de la classe, il initialize les attributs avec les paramètres donnés
 */
	public Plateau() {
	
		grille= new Cube[taille][taille];
		position=new boolean[taille][taille];
		tot= new int[taille][taille];
		
		for ( int i=0; i<grille.length; i=i+2 ) {
			for ( int j=0; j<grille.length; j=j+2 ) {

				this.grille[i][j]=new Cube(i,j,"FREE");
				
			}
		}
		
		for ( int i=1; i<grille.length; i=i+2 ) {
			for ( int j=0; j<grille.length; j++ ) {
			
				this.grille[i][j]=new Cube(i,j,"NONE");
			}
		}


		for ( int j=1; j<grille.length; j=j+2 ) {
			for ( int i=0; i<grille.length; i++ ) {
			
				this.grille[i][j]= new Cube(i,j,"NONE");
			}
		}
	}	

/**
 * Cette méthode controleras le placement du pion
 * Elle controle la totalité des déplacements des pions, ainsi que les cas particuliers
 * Elle prend en paramètre un couple de coordonnées x et y qui indique la position du joueur
 * Avec ses coordonnées elle indique dans le tableau à double dimension de booléen "position" les possibilités de déplacements
 * Par exemple si le joueur peut se déplacer en (0,4) alors on auras  : position[0][4]=true
 * @param x : la coordonnée x du joueur
 * @param y : la coordonnée y du joueur
*/ 
	public void placementPion(int x,int y) {
	
		Case caseMur,caseFree,caseMurPion,caseMurG,caseMurD,blanc, noir,free,none,mur;	
		blanc = Case.BLANC;
		noir = Case.NOIR;
		free= Case.FREE;
		none= Case.NONE;
		mur= Case.MUR;
		
		/* Les variables "caseMur","caseFree","caseMurPion" sont utilisées pour vérifier  les possibilités de placement du joueur :
		
				- caseMur : Elle prendra pour valeur la "couleur" de la case juste à coté du joueur comme chaque entre chaque case jouable,
							il y a une case ou un mur peut-être posé, cette variable sert donc à vérifier si le joueur peut aller à la case suivante
							( donc la case après la case ou le mur peut être posé), le joueur peut aller à la case suivante si la couleur est à NONE
							ce qui correspond à aucun mur de posé.
							
				- caseFree : Utilisé pour obtenir la couleur de la case précédente, si elle est égale à "FREE" et qu'un mur n'est pas posé
							  les deux cases le joueur peut y aller.
							  
				-caseMurPion : Utilisé dans le cas ou les deux joueurs sont en face et qu'il y a un mur derrière un des deux joueurs
							   Elle sert donc à vérifier le cas ou le joueur peut se déplacer en diagonale.
		*/
		if ( ( x>=0 ) && ( y>=0 ) && ( x<grille.length ) && (y<grille.length ) ) {
			if ( (x==0) && (y>3) && (y<13) ) {  // 1er cas : Quand le pion blanc est sur sa ligne du départ, le déplacement en arrière est impossible 
				
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				caseMurD= grille[x+2][y+1].getCase();
				
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}				
					
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
			}
			else if ( (x==16) && (y>3) && (y<13) ){ // 2ème cas : Quand le pion noir se trouve sur sa ligne de départ, le déplacement arrière est impossible
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y-1].getCase();
				caseMurD= grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}	
					
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
			}
			else if ( (y==0) && (x>3) && (x<13) ) { // 3ème cas : Quand un pion se trouve sur la colonne la plus à gauche du jeu, le déplacement à gauche est impossible
				
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurD= grille[x+2][y+1].getCase();				
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {
				
					position[x+2][y+2]=true;
				}	
					
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD= grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurD=grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {
				
					position[x-2][y+2]=true;
				}				

			}
			else if ( (y==16) && (x>3) && (x<13) ){ //4ème cas : Quand un pion se trouve sur la colonne la plus à droite du jeu, le déplacement à droite est impossible
				
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG=grille[x+2][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}	
					
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG=grille[x-2][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG=grille[x-1][y-2].getCase();
				caseMurD=grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y-2]=true;
				}				

			}
			else if ( (x==0) && (y==0) ) { // 5ème cas : Quand le pion blanc se trouve sur le coin en haut à gauche, 2 déplacement sont impossible celui à gauche et celui arrière
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurD= grille [x+2][y+1].getCase();

				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur)  && (caseMurD!=mur) )  {
				
					position[x+2][y+2]=true;
				}

				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}				
			}
			else if ( (x==0) && (y==16) ) { //6ème cas : Quand le pion blanc se trouve sur le coin en haut à droite, 2 déplacements sont impossible celui arrière et celui à droite
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {
				
					position[x+2][y-2]=true;
				}

				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				
			}
			else if ( (x==16) && (y==0) ) { //7ème cas : Quand le pion noir se trouve sur le coin en bas à gauche, 2 déplacements sont impossible celui en arrière et celui de gauche
			
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurD= grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {
				
					position[x-2][y+2]=true;
				}

				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}				
			}
			else if ( (x==16) && (y==16) ) { //8ème cas : Quand le pion noir se trouve sur le coin en bas à droite, 2 déplacements sont impossible, celui en arrière et celui de droite
			
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG=grille[x-2][y-1].getCase();

				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur)&& (caseMurG!=mur) ) {
				
					position[x-2][y-2]=true;
				}

				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}				
			}
			else if ( (x<3) && ( (y>3) && (y<13 ) ) ) {	//9ème cas : Quand le pion se trouve sur la  2ème ligne du plateau entre les case 3 et 13 (exclues) le saut et diagonale en avant est impossible
			
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG=grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG=grille[x-1][y+2].getCase();
				caseMurD=grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG=grille[x-1][y-2].getCase();
				caseMurD=grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y-2]=true;
				}
				
			}
			else if ( (x>13) && ( (y>3) && (y<13 ) ) ) { // 10ème cas : Quand un pion se trouve sur la 14ème ligne entre  les case 3 et 13 (exclues) le saut et diagonale en arrière est impossible 
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG=grille[x-2][y-1].getCase();
				caseMurD=grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
			
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG=grille[x-1][y+2].getCase();
				caseMurD=grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG= grille[x-1][y-2].getCase();
				caseMurD= grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {
				
					position[x+2][y+2]=true;
				}
			}
			else if ( (y<3) && ( (x>3) && (x<13) ) ) { //11ème cas : Quand le pion se trouve sur la 2ème colonne entre les lignes 3 et 13(exclues), le saut et diagonales vers la gauche sont impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG=grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG=grille[x-2][y-1].getCase();
				caseMurD=grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD=grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
			}
			else if ( (y==14) && ( (x>3) && ( x<13)) ) { //12ème cas : Quand le pion se trouve sur la 14ème colonne entre les lignes 3 et 13(exclues), le saut et diagonales vers la droite sont impossible
				
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG=grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y+1].getCase();
				caseMurD= grille[x-2][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y-2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG=grille[x-1][y-1].getCase();
				caseMurD=grille[x+1][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y-2]=true;
				}
			}
			else if ( (x==2) && (y==2) ) { //13ème cas : Quand un pion se trouve sur la case(2,2) le saut et diagonales vers le haut et la gauche sont impossibles
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD= grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
			}
			else if ( (x==2) && ( y==14) ) { //14ème cas : Quand un pion se trouve sur la case(2,14) le saut et diagonales vers le haut et la droite sont impossibles
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
			
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}

				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG= grille[x-1][y-2].getCase();
				caseMurD= grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y-2]=true;
				}
			}
			else if ( (x==14) && (y==2) ) { // 15ème cas : Quand un pion sur la case(14,2) le saut et diagonales vers le bas et la gauche sont impossibles
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y-1].getCase();
				caseMurD= grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD= grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
			}
			else if ( (x==14) && (y==14 ) ) { // 16ème cas : Quand un pion se trouve sur la case(14,14) le saut et diagonales vers le bas et la droite sont impossibles
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG=grille[x-2][y-1].getCase();
				caseMurD=grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG= grille[x-1][y-2].getCase();
				caseMurD= grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y-2]=true;
				}
			}
			else if ( (x==0) && ( y==2) ) { // 17ème cas : Quand un pion se trouve sur la case(0,2) un déplacement est interdit celui vers le haut et le saut/diagonales vers la gauche est impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				caseMurD= grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();

				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
			}
			else if ( ( x==0 ) &&( y==14 ) ) { // 18ème cas : Quand un pion se trouve sur la case(0,14) un déplacement est interdit celui vers le haut et le saut/diagonales vers la droite est impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG=grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();

				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
			}
			else if	( ( x==14) && (y==16 ) ) { // 19ème cas : Quand un pion se trouve sur la case(14,16) un déplacement est interdit celui vers la droite et le saut/diagonales vers la bas est impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG= grille[x-1][y-2].getCase();
				caseMurD=grille[x+1][y-2].getCase();
				
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && (caseMurPion!=mur) ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {
					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {
					
					position[x+2][y-2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=null) ) {

					position[x-2][y-2]=true;
				}
			}
			else if ( ( x==14 ) && (y==0 ) ) { // 20ème cas : Quand un pion se trouve sur la case(14,0) un déplacement est interdit celui vers la gauche et le saut/diagonales vers la bas est impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurD=grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD=grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
			}
			else if  ( (x==2) && ( y==0) ) { // 21ème cas : Quand un pion se trouve sur la case(2,0) un déplacement est interdit celui vers la gauche et le saut/diagonales vers la haut est impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurD= grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD= grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}	
			}
			else if ( (x==2) &&( y==16 ) ) { // 22ème cas : Quand un pion se trouve sur la case(2,16) un déplacement est interdit celui vers la droite et le saut/diagonales vers la haut est impossible
			
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG= grille[x-1][y-2].getCase();
				caseMurD= grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc )) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
			}
			else if ( (x==16) && ( y==14 ) ) { // 23ème cas : Quand un pion se trouve sur la case(16,14) un déplacement est interdit celui vers le bas et le saut/diagonales vers la droite est impossible
			
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y-1].getCase();
				caseMurD=grille[x-2][y+1].getCase();

				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}

				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}	
			}
			else if ( (x==16) && ( y==2 ) ) { // 24ème cas : Quand un pion se trouve sur la case(16,2) un déplacement est interdit celui vers le bas et le saut/diagonales vers la gauche est impossible
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y-1].getCase();
				caseMurD= grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
			}
			else { // 25 ème cas :  Le cas normal avec toutes directions autorisées autant pour les déplacements que pour les sauts/diagonales
		
		
				caseMur = grille[x+1][y].getCase();
				caseFree = grille[x+2][y].getCase();
				caseMurPion= grille[x+3][y].getCase();
				caseMurG= grille[x+2][y-1].getCase();
				caseMurD=grille[x+2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x+2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x+4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x+2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
				
				caseMur = grille[x-1][y].getCase();
				caseFree = grille[x-2][y].getCase();
				caseMurPion= grille[x-3][y].getCase();
				caseMurG= grille[x-2][y-1].getCase();
				caseMurD=grille[x-2][y+1].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x-2][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion!=mur) ) {
						
					position[x-4][y]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x-2][y+2]=true;
				}
				
				caseMur = grille[x][y-1].getCase();
				caseFree = grille[x][y-2].getCase();
				caseMurPion= grille[x][y-3].getCase();
				caseMurG= grille[x-1][y-2].getCase();
				caseMurD=grille[x+1][y-2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y-2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x][y-4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y-2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y-2]=true;
				}
				
				caseMur = grille[x][y+1].getCase();
				caseFree = grille[x][y+2].getCase();
				caseMurPion= grille[x][y+3].getCase();
				caseMurG= grille[x-1][y+2].getCase();
				caseMurD=grille[x+1][y+2].getCase();
				if ((caseMur!= mur) && (caseFree==free)){
					
					position[x][y+2]=true;
				}
				else if ( (caseMur!=mur) && ((caseFree==noir)|| (caseFree==blanc)) && caseMurPion!=mur ) {
				
					position[x][y+4]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurG!=mur) ) {

					position[x-2][y+2]=true;
				}
				else if ( (caseMur!= mur) && ((caseFree==noir) || (caseFree==blanc)) && (caseMurPion==mur) && (caseMurD!=mur) ) {

					position[x+2][y+2]=true;
				}
			}	
		}	
	}	
	

/**
 * Cette méthode est utilisée dans le cadre dans la récursivité pour vérifier si un joueur n'est pas bloqué par un mur
 * Elle va indiquer si un déplacement est possible
 * Pour cela elle vérifier dans le tableau de booléen si la position est autorisée
 * Puis va vérifier que la case n'as pas déja été marquée ( Algorithme de parcours en profondeur)  et si aucun des deux joueurs ne se trouve sur cette case
 * @param x : la coordonnée x de la position à testée
 * @param y : la coordonnée y de la position à testée
 * @param tab : le tableau de booléen qui contient les position possibles
 * @param tab2 : le tableau d'entier qui contiendras les cases marquées (si la case est égale à 1, elle est marquée, 0 elle est pas encore marquée
 * @return ret : retourne vrai si la position est possible
*/ 
	
	
	private boolean estCePossible(int x, int y, boolean[][]tab, int[][]tab2 ) {
	
		boolean ret=false;

		if( (tab[x][y]) && (grille[x][y].getCase()!=Case.TEST)&& (grille[x][y].getCase()!=Case.NOIR)&& (grille[x][y].getCase()!=Case.BLANC) && (tab2[x][y]!=1)) {

			ret= true;
			
		}
		return ret;	
	}	
	
/**
 * Une méthode qui indique donne les déplacements possibles dans le cadre de la récursivité 
 * Elle convertit les possibilités autorisées dans le tableau de booléen "position" 
 * En tableau à double dimension d'entier qui sera de la taille du nombre de possibilités autorisée pour la première dimension
 * Et la deuxième dimension de taille 1( 0 ou 1 ) 
 * Donc on aura dans les case du tableau de type tab[k][0] la coordonée x et dans les cases de type tab[k][1] la coordonnée y
 * @param tab : le tableau de booléen à convertir
 * @return ret : le tableau à deux dimension d'entier contenant les position possible
*/ 
	
	private int[][] donnerSuivant(boolean[][] tab) {	
		
		int[][]ret;
		int tailleTab=0;
		int k=0;
		
		for(int i=0; i<tab.length; i++ ) {
			for( int j=0; j<tab.length; j++ ) {
				if( tab[i][j] ) {				
					tailleTab++;
				}
			}	
		}
		
		ret= new int [tailleTab][2];
		
		for(int i=0; i<tab.length; i++ ) {
			for( int j=0; j<tab.length; j++ ) {
				if( tab[i][j] ) {
									
					ret[k][0]=i;
					ret[k][1]=j;
					k++;
				}
			}	
		}
		return ret;
	}	
				
		
/**
 * La méthode récursive qui implémente l'algorithme de parcours en profondeur utilisée dans le placement d'un mur
 * Pour vérifier que le mur posé ne bloque pas le joueur ( lui laisse une chance de gagner)
 * Elle prend en paramètre un couple x,y qui indique la position du joueur et une "case qui indique sa couleur  pour différencier le joueur noir du blanc
 * Si le joueur n'est pas bloqué par le mur la méthode renvoie "vrai"
  * @param x : La coordonée x du joueur
  * @param y : La coordonnée y du joueur
  * @param couleur : la couleur du pion du joueur
  * @return ret : le booléen qui indique si un chemin est possible, si un chemin est possible il retourne vrai
*/ 
	
	public boolean cheminPossible ( int x, int y,Case couleur) {
		
		
		boolean ret,verif;
		boolean[][]pos;
		int[][]nouvPos;
		int nouvX,nouvY,i;	
		 
		ret=false;
		i=0;
		reinitialiserPosition();
		
		placementPion(x,y);
		pos= new boolean[taille][taille];
		
		for (int j=0; j<pos.length; j++) {
			for (int k=0; k<pos[j].length; k++ ) {
		
				pos[j][k]=this.position[j][k];
			}	
		}	
		nouvPos=donnerSuivant(pos);	
		if ( couleur==Case.NOIR ){	
		
			while ( (!ret) && ( i<nouvPos.length ) ) {
				nouvX=nouvPos[i][0];
				nouvY=nouvPos[i][1];
			
				verif=estCePossible(nouvX,nouvY,pos,tot);
				
				if(verif) {
					grille[nouvX][nouvY].initializeCase("test");
					tot[nouvX][nouvY]=1;
					
					if ( nouvX==0 ) {
					
						ret=true;
					}
					else {
						ret=cheminPossible(nouvX,nouvY,couleur);
					}

					if (!ret ) {
						grille[nouvX][nouvY].initializeCase("free");
					}
				}
				i++;	
			}	
			
		}	
		else if ( couleur==Case.BLANC ) {
		
			while ( (!ret) && ( i<nouvPos.length ) ) {
				nouvX=nouvPos[i][0];
				nouvY=nouvPos[i][1];
			
				verif=estCePossible(nouvX,nouvY,pos,tot);
				
				if(verif) {
					grille[nouvX][nouvY].initializeCase("test");
					
					if ( nouvX==16 ) {
					
						ret=true;
					}
					else {
						ret=cheminPossible(nouvX,nouvY,couleur);
					}

					if (!ret ) {
						grille[nouvX][nouvY].initializeCase("free");
					}
				}
				i++;	
			}	
			
		}
		return ret;
	}	
		
	
/**
 * Cette méthode controleras le placement d'un mur
 * Pour cela elle appeleras la méthode "cheminPossible(x: int, y: int, couleur : Case): boolean, qui indiquera si un joueur est bloqué ou none
 * Elle vérifieras aussi si un mur est déja posé
 * La pose d'un mur ne fonctionne uniquement avec couple de coordonnée pair/impair ou impair/pair du à l'implémentation de la grille
 * Les coordonnées corresponderons soit a l'extrémité gauche du mur pour un mur horizontal ou a l'extrémité haute du mur pour un mur vertical
 * @param x: la coordoonée x du mur
 * @param y : la coordonée y du mur
 * @param couleur : Indique la couleur du joueur à vérifié si le mur posé le bloqueras
 * @return ret : Retourne vrai si le mur peut être posé et dans ce cas le mur se pose sinon renvoie faux et le mur n'est pas posé 
*/ 

	public boolean placementMur(int x, int y,Case couleur) {
	
		boolean impair, pair,verif,verifMur,ret;
		Case mur, free, suivant;
		int i,j,xJ,yJ;
		xJ=0;
		yJ=0;
		
		
		mur= Case.MUR;
		free=Case.FREE;
		
		ret=true;
		impair= false;
		pair=false;
		verifMur=false;
		
		if (x%2 == 0 ) {

			pair= true;
		}
		else {
			
			impair=true;
		}

		if (y%2 == 0 ) {

			pair= true;
		}
		else {
			
			impair=true;
		}
		
		
			for ( i=0; i<grille.length; i++ ) {
				for ( j=0; j<grille.length; j++ ){
				
					if ( grille[i][j].getCase()== couleur ) {
					
						xJ=i;
						yJ=j;
					}
				}
			}
			
		
		
		if ( (pair) && (impair) ) {
		
			if (x==0) {
				i=x;
				while ( (i<x+3) &&(!verifMur) ) {
				
					if ( grille[i][y].getCase()==mur ) {
					
						verifMur=true;
					}
					i++;
				}
				
				if (!verifMur) {
				
					for (i=0; i<3 ; i++ ) {
					
						grille[i][y].initializeCase("mur");
					}	
					
					verif=cheminPossible(xJ,yJ,couleur);
					reinitialiserGrille();
					
					if( !verif ) {
					
						for (i=0; i<3 ; i++ ) {
						
							grille[i][y].initializeCase("none");
						}
						ret=false;
					}
				}
				else {
				
					ret=false;
				}	
			}
			else if ( x== 16 ) {
				i=x;
				while ( (i<x-3) &&(!verifMur) ) {
				
					if ( grille[i][y].getCase()==mur ) {
					
						verifMur=true;
					}
					i--;
				}
				
				if (!verifMur) {
				
				
					for (i=16; i>13 ; i-- ) {
						
						grille[i][y].initializeCase("mur");
					}	
					
					verif=cheminPossible(xJ,yJ,couleur);
					reinitialiserGrille();
					
					if( !verif ) {

						
						for (i=16; i>13 ; i-- ) {
						
							grille[i][y].initializeCase("none");
						}
						ret=false;
					}
				}
				else {
				
					ret=false;
				}
			}
			else if ( y==0) {
			
				i=y;
				while ( (i<y+3) &&(!verifMur) ) {
				
					if ( grille[x][i].getCase()==mur ) {
					
						verifMur=true;
					}
					i++;
				}
				
				
				if (!verifMur) {
				
					for (i=0; i<3; i++ ) {
					
						grille[x][i].initializeCase("mur");
					}	
					
					verif=cheminPossible(xJ,yJ,couleur);
					reinitialiserGrille();
					
					if( !verif ) {
						for (i=0; i<3 ; i++ ) {
						
							grille[x][i].initializeCase("none");
						}
						ret=false;
					}
				}
				else {
				
					ret=false;
				}
			}
			else if ( y==16) {
			
				i=y;
				while ( (i<y-3) &&(!verifMur) ) {
				
					if ( grille[x][i].getCase()==mur ) {
					
						verifMur=true;
					}
					i--;
				}
				
				if (!verifMur) {
				
					for (i=16; i>13; i-- ) {
					
						grille[x][i].initializeCase("mur");
					}	
					
					verif=cheminPossible(xJ,yJ,couleur);
					reinitialiserGrille();
					if( !verif ) {
					
					
						for (i=16; i>13 ; i-- ) {
						
							grille[x][i].initializeCase("none");
						}
						ret=false;
					}
				}
				else {
				
					ret=false;
				}
			}
			else {
			
				suivant= grille[x][y+1].getCase();
			
					
				if (suivant.equals(Case.NONE) ) {
					i=y;
					while ( (i<y+3) &&(!verifMur) ) {
					
						if ((grille[x][i].getCase()==mur) || (grille[x][i].getCase()==free))  {
							verifMur=true;
						}
						i++;
					}
				
					if (!verifMur) {
					
						for (i=y; i<y+3 ; i++ ) {
						
							grille[x][i].initializeCase("mur");
						}	
						
						verif=cheminPossible(xJ,yJ,couleur);
						reinitialiserGrille();

						if( !verif ) {
						
							for (i=0; i<y+3 ; i++ ) {
							
								grille[x][i].initializeCase("none");
							}
							ret=false;
						}
					}
					else {
				
						ret=false;
					}
				}
				else {
					
				
					i=x;
					while ( (i<x+3) &&(!verifMur) ) {
					
						if ( (grille[i][y].getCase()==mur) || (grille[i][y].getCase()==free)  ) {
						
							verifMur=true;
						}
						i++;
					}
					
					if (!verifMur) {
					
						for (i=x; i<x+3; i++ ) {
						
							grille[i][y].initializeCase("mur");
						}	
						
						verif=cheminPossible(xJ,yJ,couleur);
						reinitialiserGrille();
						
						if( !verif ) {
						
							for (i=x; i<x+3 ; i++ ) {
							
								grille[i][y].initializeCase("none");
							}
							ret=false;
						}
							
					}
					else {
						
						ret=false;
					}
				}
			}
		}
		else {
		
			ret=false;
		}	
		return ret;	
	}	

	
/**
 * Un getter qui renvoie la grille du jeu
 * @return : la grille du jeu
*/
 	
	public Cube[][] getGrille() { return this.grille;}
	
/**
 * Un getter qui renvoie le tableau à double dimension de booleén qui indique les déplacement possibles
 * @return : le tableau de booléen
*/
	public boolean[][] getPos() { return this.position;}
	
/**
 * Une méthode qui reinitialisera le tableau à double dimension de booléen à faux
 * Cette méthode est utilisée dès que le tour passe à un autre joueur
*/ 
	
	public void reinitialiserPosition() {

		for (int i= 0; i<this.position.length; i++){
			for (int j=0; j<this.position.length; j++){
		
				this.position[i][j]=false; 
				j++;
			}
			i++;
		}
	}

/**	
 * Cette méthode reinitialise le tableau à double dimension d'entier "tot" qui  indique les cases marquées par l'algorithme de parcours en pronfondeur
 * Une case du tableau contient "1" si elle a été marquée, 0 sinon
 * Cette méthode est utilisée à chaque fois qu'un mur est posé 
*/ 
	public void reinitialiserTot() {

		for (int i= 0; i<this.tot.length; i++){
			for (int j=0; j<this.tot.length; j++){
		
				this.tot[i][j]=0; 
				j++;
			}
			i++;
		}
	}
		
	
/**
 * Cette méthode pour la version textuelle afficheras le plateau 
 * @return ret : Le string qui contient le plateau
*/ 

	public String toString() {
	
		char[][] plat=new char[taille][taille];
		
		String ret="\n";
		System.out.println("    0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16");
		for (int i=0; i<grille.length; i++ ) {			
			for (int j=0; j<grille.length; j++ ) {
				ret+= "    ";
					
				if ( grille[i][j].getCase()==Case.BLANC) {
					
					ret+= plat[i][j]='B';
				}
				else if (grille[i][j].getCase()==Case.NOIR) {
	
					ret+= plat[i][j]='N';
				}
				else if (grille[i][j].getCase()==Case.FREE) {
					
					ret+= plat[i][j]='.';
				}
				else if ( grille[i][j].getCase()==Case.MUR)  {
						
					ret+= plat[i][j]='x';	
				}
				else if(grille[i][j].getCase()==Case.TEST)  { 
					ret+= plat[i][j]='T';
				}
				else {
				
					ret+=plat[i][j]=' ';
				}
			}	
			ret+="    "+i+"\n";
		}
		
		return ret;
	}
	
		
/** 
 * Une méthode qui sert à reinitialiser la grille du jeu en remplaçant les case marqués par "TEST" en free
 * Elle utilisée dans le cadre ou la méthode ou la méthode "cheminPossible(x: int, y: int, couleur : Case): boolean, pour reinitialiser la grille de jeu 
 * Elle est bien sur utilisée après que cheminPossible(x: int, y: int, couleur : Case): boolean, est terminée
*/ 
	
	private void reinitialiserGrille() {
		
		for (int i=0; i<grille.length; i++ ) {
			for (int j=0; j<grille[i].length; j++ ) {
			
				if( grille[i][j].getCase()==Case.TEST ) {
				
					grille[i][j].initializeCase("Free");
				}
			}
		}		
	}	
	
	

}