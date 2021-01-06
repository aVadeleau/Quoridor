package Quoridor;
import java.io.Serializable;

/**
 * Cette classe représente une classe du plateau avec ses coordoonnées en x, y et sa couleur
*/ 

public class Cube implements Serializable {

	private Case uneCase;
	private int x;
	private int y;

/**
 * Le constructeur de la classe il initialize les attributs avec les paramètres donnés
 * @param xPos : la coordoonée en x
 * @param yPos : la coordoonée en y
 * @param nomCouleur : la couleur du Cube 
 */
	public Cube(int xPos, int yPos, String nomCouleur) {
	
		if ( (xPos>=0) && (yPos>=0) && (nomCouleur!= null )) {

			this.x=xPos;
			this.y=yPos;
			initializeCase(nomCouleur);
		}
		else {
			
			this.x=0;
			this.y=0;
			initializeCase("NONE");
		}
	}

/**
 * Un getter qui retourne la couleur du cube
 * @return ret
*/
	public Case getCase() {return this.uneCase;}
	
/**
 * Un setter qui permet de modifier la couleur de la case
 * @param uneCase : La courleur de la case 
*/
	public void setCase(Case uneCase ) {

		this.uneCase=uneCase;
	}	
	
/**
 * Une méthode qui permettras  d'initialiser la couleur d'un  cube, à l'aide d'un string rerpésentant la couleur
 * @param occupationCase : le string qui représente le nom de la couleur
*/
	public void initializeCase( String occupationCase ) {
	
		//Couleur Free (une case libre )
		if (  ( occupationCase.compareToIgnoreCase("FREE") ) ==0 ) {
			
			this.uneCase=Case.FREE;
		}
		
		
		//Couleur Blanche (un pion )
		if ( ( occupationCase.compareToIgnoreCase("BLANC") ) == 0 ) {
			
			this.uneCase=Case.BLANC;
		}

		//Couleur Noire (un pion)
		
		if ( ( occupationCase.compareToIgnoreCase("NOIR") ) == 0 ) {

			this.uneCase=Case.NOIR;
		}	
		
		//Couleur "NONE"
		
		if ( ( occupationCase.compareToIgnoreCase("NONE") ) == 0 ) {
		
			this.uneCase=Case.NONE;
		}
		
		if(( occupationCase.compareToIgnoreCase("MUR"))==0)	{
		
			this.uneCase= Case.MUR;
		}
		
		if(( occupationCase.compareToIgnoreCase("TEST"))==0)	{
		
			this.uneCase= Case.TEST;
		}
	}	
		
	
	
	
	
/**
 * Une méthode qui affiche la représentation de la classe sous forme d'un String
 * @return ret : Le string qui contient la représentation du cube 
*/ 
	public String toString() {
		String ret="\n--------------------------";
		
		ret+=" x= "+x;
		ret+=" y= "+y;
		ret+=" La case est :"+uneCase;
		ret+="-----------------------------------";
		return ret;

	}

}