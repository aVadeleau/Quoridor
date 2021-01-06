package test;

import Quoridor.*;


public class TestGame {


	public static void main ( String[]args ) {
	
		scenarioGame();
	}	
	
	
	
	
	public static void scenarioGame() {
	
	
		Partie p= new Partie("Noir","Blanc",ModeDeJeu.IAIA);
		
		p.lancer();
		
	}	
}	