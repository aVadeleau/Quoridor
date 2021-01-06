package test;
import Quoridor.*;
import java.util.Scanner;


public class TestPlateau2{

	public static void main ( String[]args ) {
	
		//scenarioPlateau();
		//scenarioMur();
		scenario();
	}
	
	
	public static void scenarioPlateau(){
	
		boolean verif;
	
		System.out.println("\n NOIR ");
	
		Plateau plat= new Plateau();
		
		Cube[][]grille=plat.getGrille();
		
		grille[16][8].initializeCase("noir");
		
		 verif=plat.cheminPossible(16,8,Case.NOIR);
		
		 System.out.println(verif+plat.toString());	

	
	
		Plateau plat2= new Plateau();
		
		Cube[][]grille2=plat2.getGrille();
		
		grille2[16][8].initializeCase("noir");
		
		grille2[1][0].initializeCase("Mur");
		grille2[1][2].initializeCase("Mur");
		grille2[1][4].initializeCase("Mur");
		grille2[1][5].initializeCase("Mur");
		grille2[1][6].initializeCase("Mur");
		grille2[1][8].initializeCase("Mur");
		grille2[1][9].initializeCase("Mur");
		grille2[1][10].initializeCase("Mur");
		grille2[1][12].initializeCase("Mur");
		grille2[1][13].initializeCase("Mur");
		grille2[1][14].initializeCase("Mur");
		grille2[1][16].initializeCase("Mur");
		
		verif=plat2.cheminPossible(16,8,Case.NOIR);
		System.out.println(verif+plat2.toString());
	
		Plateau plat3= new Plateau();
		Cube[][]grille3=plat3.getGrille();
		
		grille3[12][10].initializeCase("noir");
		
		grille3[1][8].initializeCase("Mur");
		grille3[1][9].initializeCase("Mur");
		grille3[1][10].initializeCase("Mur");
		
		verif=plat3.cheminPossible(12,10,Case.NOIR);
		System.out.println(verif+plat3.toString());
		
		Plateau plat4= new Plateau();
		Cube[][]grille4=plat4.getGrille();
		
		grille4[14][14].initializeCase("noir");
		
		grille4[16][13].initializeCase("Mur");
		grille4[15][13].initializeCase("Mur");
		grille4[14][13].initializeCase("Mur");
		grille4[13][14].initializeCase("Mur");
		grille4[13][15].initializeCase("Mur");
		grille4[13][16].initializeCase("Mur");
		
		verif=plat4.cheminPossible(14,14,Case.NOIR);
		System.out.println(verif+plat4.toString());
		
		
		Plateau plat5= new Plateau();
		Cube[][]grille5=plat5.getGrille();
		
		grille5[14][0].initializeCase("noir");
		
		grille5[16][13].initializeCase("Mur");
		grille5[15][13].initializeCase("Mur");
		grille5[14][13].initializeCase("Mur");
		grille5[13][14].initializeCase("Mur");
		grille5[13][15].initializeCase("Mur");
		grille5[13][16].initializeCase("Mur");
		
		verif=plat5.cheminPossible(14,0,Case.NOIR);
		System.out.println(verif+plat5.toString());
	
	
		
		System.out.println("\n BLANC ");
		
		
		Plateau plat6= new Plateau();
		
		Cube[][]grille6=plat6.getGrille();
		
		grille6[0][8].initializeCase("blanc");
		
		 verif=plat6.cheminPossible(0,8,Case.BLANC);
		
		 System.out.println(verif+plat6.toString());	

	
		Plateau plat7= new Plateau();
		
		Cube[][]grille7=plat7.getGrille();
		
		grille7[0][8].initializeCase("blanc");
		
		grille7[1][0].initializeCase("Mur");
		grille7[1][2].initializeCase("Mur");
		grille7[1][4].initializeCase("Mur");
		grille7[1][5].initializeCase("Mur");
		grille7[1][6].initializeCase("Mur");
		grille7[1][8].initializeCase("Mur");
		grille7[1][9].initializeCase("Mur");
		grille7[1][10].initializeCase("Mur");
		grille7[1][12].initializeCase("Mur");
		grille7[1][13].initializeCase("Mur");
		grille7[1][14].initializeCase("Mur");
		 grille7[1][16].initializeCase("Mur");
		
		verif=plat7.cheminPossible(0,8,Case.BLANC);
		System.out.println(verif+plat7.toString());
		
		Plateau plat8= new Plateau();
		Cube[][]grille8=plat8.getGrille();
		
		grille8[2][2].initializeCase("BLANC");
		
		grille8[15][1].initializeCase("Mur");
		grille8[15][2].initializeCase("Mur");
		grille8[15][3].initializeCase("Mur");
		
		verif=plat8.cheminPossible(2,2,Case.BLANC);
		System.out.println(verif+plat8.toString());
		
		Plateau plat9= new Plateau();
		Cube[][]grille9=plat9.getGrille();
		
		grille9[2][2].initializeCase("BLANC");
	
		grille9[0][3].initializeCase("Mur");
		grille9[1][3].initializeCase("Mur");
		grille9[2][3].initializeCase("Mur");
		grille9[3][0].initializeCase("Mur");
		grille9[3][1].initializeCase("Mur");
		grille9[3][2].initializeCase("Mur");
		
		verif=plat9.cheminPossible(2,2,Case.BLANC);
		System.out.println(verif+plat9.toString());
		
			
		
		
		
	}
	
	
	
	
	
	public static void scenarioMur() {
	
		boolean verif;
		
		System.out.println(" 1er Test : Mur Ok (Noir)");
		
		Plateau plat=new Plateau();
		Cube[][]grille= plat.getGrille();
		
		grille[0][8].initializeCase("BLANC");
		grille[16][8].initializeCase("NOIR");
		
		verif=plat.placementMur(9,4,Case.BLANC);
		System.out.println( " verif : "+verif);
		System.out.println(plat.toString());
		
		System.out.println(" 2eme Test : Mur Ok (Blanc) ");
		
		Plateau plat2=new Plateau();
		grille= plat2.getGrille();
		
		grille[0][8].initializeCase("BLANC");
		grille[16][8].initializeCase("NOIR");
		
		verif=plat2.placementMur(9,4,Case.NOIR);
		System.out.println( " verif : "+verif);
		System.out.println(plat2.toString());
		
		System.out.println(" 3eme test : Joueur Noir enferme ");
		
		Plateau plat3=new Plateau();
		grille= plat3.getGrille();
		
		grille[2][2].initializeCase("Blanc");
		grille[14][14].initializeCase("Noir");
		
		grille[16][13].initializeCase("Mur");
		grille[15][13].initializeCase("Mur");
		grille[14][13].initializeCase("Mur");
		
		verif=plat3.placementMur(13,14,Case.NOIR);
		System.out.println( " verif : "+verif);
		System.out.println(plat3.toString());
		
		System.out.println(" 4eme test : Joueur Blanc enferme ");
		
		Plateau plat4=new Plateau();
		grille= plat4.getGrille();
		
		grille[2][2].initializeCase("Blanc");
		grille[14][14].initializeCase("Noir");
		
		grille[3][0].initializeCase("Mur");
		grille[3][1].initializeCase("Mur");
		grille[3][2].initializeCase("Mur");
		
		verif=plat4.placementMur(0,3,Case.BLANC);
		System.out.println( " verif : "+verif);
		System.out.println(plat4.toString());
		
		System.out.println(" 5eme test : Mur qui commence par y=0 ");

		
		Plateau plat5 = new Plateau();
		grille=plat5.getGrille();
		
		grille[2][2].initializeCase("Blanc");
		grille[12][10].initializeCase("Noir");
		
		verif=plat5.placementMur(3,0,Case.BLANC);
		System.out.println( " verif : "+verif);
		System.out.println(plat5.toString());

		System.out.println(" 6eme test : Mur qui commence par y=16 ");

		
		Plateau plat6 = new Plateau();
		grille=plat6.getGrille();
		
		grille[2][2].initializeCase("Blanc");
		grille[12][10].initializeCase("Noir");
		
		verif=plat6.placementMur(3,16,Case.BLANC);
		System.out.println( " verif : "+verif);
		System.out.println(plat6.toString());
		
		
		System.out.println(" 7eme test : Mur qui commence par x=16 ");
		
		Plateau plat7 = new Plateau();
		grille=plat7.getGrille();
		
		grille[2][2].initializeCase("Blanc");
		grille[12][10].initializeCase("Noir");
		
		verif=plat7.placementMur(16,3,Case.NOIR);
		System.out.println( " verif : "+verif);
		System.out.println(plat7.toString());
		
		
		verif=false;
		int reponse;
		Scanner scan = new Scanner(System.in);
	
		
	}
		
		
		
		
		
		
	public static void scenario() {	
		
		
		boolean verif;
	
		System.out.println("\n NOIR ");
	
		Plateau plat= new Plateau();
		
		Cube[][]grille=plat.getGrille();
		
		grille[16][8].initializeCase("noir");
		grille[0][8].initializeCase("blanc");
		
		grille[3][8].initializeCase("mur");
		grille[3][9].initializeCase("mur");
		grille[3][10].initializeCase("mur");
		
		grille[4][7].initializeCase("mur");
		grille[5][7].initializeCase("mur");
		grille[6][7].initializeCase("mur");
		
		grille[7][4].initializeCase("mur");
		grille[7][5].initializeCase("mur");
		grille[7][6].initializeCase("mur");
		
		grille[15][6].initializeCase("mur");
		grille[15][7].initializeCase("mur");
		grille[15][8].initializeCase("mur");
		
		grille[15][2].initializeCase("mur");
		grille[15][3].initializeCase("mur");
		grille[15][4].initializeCase("mur");
		
		 verif=plat.cheminPossible(0,8,Case.BLANC);
		
		 System.out.println(verif+plat.toString());		
	}	
		
		
		
	
	
}	