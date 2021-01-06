package test;
import org.junit.*;
import org.junit.Assert;
import Quoridor.Cube;
import Quoridor.Case;
import Quoridor.Plateau;


public class TestPlateau {

		
	@Test
	public void testPlateau () {
	
		Plateau plat= new Plateau();
		Assert.assertNotNull("L'objet n'a pas ete cree ",plat);
		Cube[][]grille=plat.getGrille();
		boolean free= true;
		boolean none= true;
		int i,j;
		
		
		i=0;
		
		while( ( i<grille.length-1 ) && ( free ) ) { 
		
			j=0;
			
			while( ( j<grille.length-1 ) && ( free ) ) {
			
				if(free) { 
				Assert.assertSame(grille[i][j].getCase(),Case.FREE);
				j=j+2;
				}
				else {
				
					free=false;
				}	
			}
			i=i+2;
		}
		
		i=1;
		while( ( i<grille.length ) && ( none ) ) { 
		
			j=0;
			
			while( ( j<grille.length) && ( none ) ) {
			
				if(none) { 
				Assert.assertSame(grille[i][j].getCase(),Case.NONE);
				j++;
				}
				else {
				
					none=false;
				}	
			}
			i=i+2;
		}
		
		j=1;
		while( ( j<grille.length ) && ( none ) ) { 
		
			i=0;
			
			while( ( i<grille.length) && ( none ) ) {
			
				if(none) { 
				Assert.assertSame(grille[i][j].getCase(),Case.NONE);
				i++;
				}
				else {
				
					none=false;
				}	
			}
			j=j+2;
		}
	}

	/* @Test 
	public void testPlacementMur() {
	
		Plateau plat= new Plateau(17);
		Cube[][]grille=plat.getGrid();
		boolean mur= true;
		boolean placement;
		int i,j;
		
		
		//Test de placement de mur qui ne cause pas de problème
		placement=plat.placementMur(1,0,1,2);
		Assert.assertTrue(placement);

		j=0;
		while( ( j<3 ) && ( mur ) ) {
			
			if(mur) { 
				Assert.assertSame(grille[1][j].getCase(),Case.MUR);
			}
			else {
				
				mur=false;
			}
		}

		mur= true;
		placement=plat.placementMur(0,1,2,1);
		Assert.assertTrue(placement);
		i=0;
		
		while( ( i<3 ) && ( mur ) ) {
			
			if(mur) { 
				Assert.assertSame(grille[i][1].getCase(),Case.MUR);
			}
			else {
				
				mur=false;
			}
		}
		
		//Test de placement de mur invalide ( le joueur n'a plus de chance de gagner)
		
		i=0;
		while ( i<grille.length) {

			grille[i][1].initializeCase("MUR");
			i++;
		}
		grille[5][1].initializeCase("NONE");
		grille[9][1].initializeCase("MUR");
		grille[13][1].initializeCase("MUR");
		
		mur= true;
		placement=plat.placementMur(1,0,1,2);
		Assert.assertFalse(placement);
		
		while( ( j<3 ) && ( mur ) ) {
			
			if(mur) { 
				Assert.assertSame(grille[1][j].getCase(),Case.NONE);
			}
			else {
				
				mur=false;
			}
		}
	}	
	*/
	
	@Test
	public void testPlacementPion() {
	
		Plateau plat = new Plateau();
		Cube[][]grille=plat.getGrille();
		//grille[0][8].initializeCase("BLANC");
		//grille[16][8].initializeCase("NOIR");
		
		boolean[][]pos=plat.getPos();
		
		// Test de position sans mur		
		
		System.out.println("\nTest de placement avec (0,8) :");
		
		plat.placementPion(0,8);
		Assert.assertTrue(pos[0][10]);
		Assert.assertTrue(pos[0][6]);
		Assert.assertTrue(pos[2][8]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (16,8) :");
		
		plat.placementPion(16,8);
		Assert.assertTrue(pos[14][8]);
		Assert.assertTrue(pos[16][10]);
		Assert.assertTrue(pos[16][6]);
		plat.reinitialiserPosition();
		
		
		System.out.println("\nTest de placement avec (6,0) :");
		
		plat.placementPion(6,0);
		Assert.assertTrue(pos[4][0]);
		Assert.assertTrue(pos[6][2]);
		Assert.assertTrue(pos[8][0]);
		plat.reinitialiserPosition();
		
		
		System.out.println("\nTest de placement avec (8,16) :");
		
		plat.placementPion(8,16);
		Assert.assertTrue(pos[8][14]);
		Assert.assertTrue(pos[6][16]);
		Assert.assertTrue(pos[10][16]);
		plat.reinitialiserPosition();
		
		
		System.out.println("\nTest de placement avec (0,0) :");
		
		plat.placementPion(0,0);
		Assert.assertTrue(pos[0][2]);
		Assert.assertTrue(pos[2][0]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (0,16) :");
		
		plat.placementPion(0,16);
		Assert.assertTrue(pos[2][16]);
		Assert.assertTrue(pos[0][14]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (16,0) :");
		
		plat.placementPion(16,0);
		Assert.assertTrue(pos[14][0]);
		Assert.assertTrue(pos[16][2]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (16,16) :");
		
		plat.placementPion(16,16);
		Assert.assertTrue(pos[14][16]);
		Assert.assertTrue(pos[16][14]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (2,12) :");
			
		plat.placementPion(2,12);
		Assert.assertTrue(pos[0][12]);
		Assert.assertTrue(pos[4][12]);
		Assert.assertTrue(pos[2][10]);
		Assert.assertTrue(pos[2][14]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (14,8) :");
		
		plat.placementPion(14,8);
		Assert.assertTrue(pos[16][8]);
		Assert.assertTrue(pos[12][8]);
		Assert.assertTrue(pos[14][10]);
		Assert.assertTrue(pos[14][6]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (4,8) :");
		
		plat.placementPion(4,8);
		Assert.assertTrue(pos[6][8]);
		Assert.assertTrue(pos[2][8]);
		Assert.assertTrue(pos[4][10]);
		Assert.assertTrue(pos[4][6]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (8,14) :");
		
		plat.placementPion(8,14);
		Assert.assertTrue(pos[6][14]);
		Assert.assertTrue(pos[10][14]);
		Assert.assertTrue(pos[8][12]);
		Assert.assertTrue(pos[8][16]);
		plat.reinitialiserPosition();
		
		
		System.out.println("\nTest de placement avec (2,2) :");
		
		plat.placementPion(2,2);
		Assert.assertTrue(pos[4][2]);
		Assert.assertTrue(pos[0][2]);
		Assert.assertTrue(pos[2][0]);
		Assert.assertTrue(pos[2][4]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (2,14) :");
		
		plat.placementPion(2,14);
		Assert.assertTrue(pos[0][14]);
		Assert.assertTrue(pos[4][14]);
		Assert.assertTrue(pos[2][12]);
		Assert.assertTrue(pos[2][16]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (14,2) :");
		
		plat.placementPion(14,2);
		Assert.assertTrue(pos[16][2]);
		Assert.assertTrue(pos[12][2]);
		Assert.assertTrue(pos[14][4]);
		Assert.assertTrue(pos[14][0]);
		plat.reinitialiserPosition();
	
		System.out.println("\nTest de placement avec (14,14) :");
		
		plat.placementPion(14,14);
		Assert.assertTrue(pos[16][14]);
		Assert.assertTrue(pos[12][14]);
		Assert.assertTrue(pos[14][12]);
		Assert.assertTrue(pos[14][16]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (0,2) :");
		
		plat.placementPion(0,2);
		Assert.assertTrue(pos[2][2]);
		Assert.assertTrue(pos[0][0]);
		Assert.assertTrue(pos[0][4]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (0,14) :");
		
		plat.placementPion(0,14);
		Assert.assertTrue(pos[2][14]);
		Assert.assertTrue(pos[0][12]);
		Assert.assertTrue(pos[0][16]);
		plat.reinitialiserPosition();
		
		
		System.out.println("\nTest de placement avec (14,16) :");
		
		plat.placementPion(14,16);
		Assert.assertTrue(pos[12][16]);
		Assert.assertTrue(pos[16][16]);
		Assert.assertTrue(pos[14][14]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (14,0) :");
		
		plat.placementPion(14,0);
		Assert.assertTrue(pos[12][0]);
		Assert.assertTrue(pos[16][0]);
		Assert.assertTrue(pos[14][2]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (2,0) :");
		
		plat.placementPion(2,0);
		Assert.assertTrue(pos[0][0]);
		Assert.assertTrue(pos[4][0]);
		Assert.assertTrue(pos[2][2]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (2,16) :");
		
		plat.placementPion(2,16);
		Assert.assertTrue(pos[0][16]);
		Assert.assertTrue(pos[4][16]);
		Assert.assertTrue(pos[2][14]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (16,14) :");

		plat.placementPion(16,14);
		Assert.assertTrue(pos[14][14]);
		Assert.assertTrue(pos[16][16]);
		Assert.assertTrue(pos[16][12]);
		plat.reinitialiserPosition();
		
		System.out.println("\nTest de placement avec (16,2) :");

		plat.placementPion(16,2);
		Assert.assertTrue(pos[14][2]);
		Assert.assertTrue(pos[16][0]);
		Assert.assertTrue(pos[16][4]);
		plat.reinitialiserPosition();
		
		
		//Test de position avec un mur 
		grille[2][7].initializeCase("MUR");
		grille[3][7].initializeCase("BLANC");
		grille[4][7].initializeCase("BLANC");
		
		System.out.println("\nTest de placement avec un mur (2,6) :");
		
		plat.placementPion(2,6);
		Assert.assertTrue(pos[0][6]);
		Assert.assertTrue(pos[4][6]);
		Assert.assertFalse(pos[2][8]);
		Assert.assertTrue(pos[2][4]);
		plat.reinitialiserPosition();

		
		//Test de position quand les pions sont en face
		
		grille[6][10].initializeCase("BLANC");
		grille[8][10].initializeCase("NOIR");
		
		System.out.println("\nTest de placement quand les pions sont en face  (8,10) :");
		
		plat.placementPion(8,10);
		Assert.assertTrue(pos[10][10]);
		Assert.assertTrue(pos[8][12]);
		Assert.assertTrue(pos[8][8]);
		Assert.assertFalse(pos[6][10]);
		Assert.assertTrue(pos[4][10]);
		plat.reinitialiserPosition();
		
		//Test de position quand les pion sont en face et un mur est derri�re le pion
		
		grille[12][12].initializeCase("BLANC");
		grille[14][12].initializeCase("NOIR");
		grille[11][12].initializeCase("MUR");
		
		System.out.println("\nTest de placement  quand les pions sont en face avec un mur derri�re  (14,12) :");
		
		plat.placementPion(14,12);
		Assert.assertTrue(pos[16][12]);
		Assert.assertTrue(pos[14][14]);
		Assert.assertTrue(pos[14][10]);
		Assert.assertTrue(pos[12][10]);
		Assert.assertTrue(pos[12][14]);
		Assert.assertFalse(pos[10][12]);
		Assert.assertFalse(pos[12][12]);
		
		
	}	
}	




	
