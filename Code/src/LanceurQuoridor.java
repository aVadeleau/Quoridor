import Quoridor.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.Scanner;

public class LanceurQuoridor{


	public static void main (String[]args ) {

		menuPrincipal();
	}


	public static void menuPrincipal() {


		String choix="";
		String nomJoueur1,nomJoueur2;
		nomJoueur1="";
		nomJoueur1="";
		String chemin ="../data/sauvegardes";
		File dir = new File(chemin);
		File[]sauvegardes=dir.listFiles();
		System.out.println("taille"+(sauvegardes.length>0));
		
		
		Scanner scan= new Scanner(System.in);
		
		System.out.println("---------------------------------------");
		System.out.println("|                                     |");
		System.out.println("|            QUORIDOR                 |");
		System.out.println("|         1-Nouvelle Partie           |");
		System.out.println("|         2-Charger Partie            |");
		System.out.println("|                                     |");
		System.out.println("---------------------------------------");
		System.out.println("\n Bienvenue sur le Quoridor : ");
		System.out.println("Pour lancer une partie veuillez saisir 1");
		System.out.println("Pour charger une partie veuillez saisir 2");
		
		
		while ((choix.compareToIgnoreCase("1")!=0) && (choix.compareToIgnoreCase("2")!=0)) {	
		
				choix=scan.next();
				System.out.println(choix);
		}	
		
		if (choix.compareToIgnoreCase("1")==0 ) {
		
			System.out.println("\n Quel est le mode auquel vous voulez jouer ?");
			System.out.println("\n 1- Humain contre Humain");
			System.out.println("\n 2- Humain contre IA ");
			System.out.println("\n 3- IA contre IA ");
			
			choix=scan.next();
			
			while ((choix.compareToIgnoreCase("1")!=0) && (choix.compareToIgnoreCase("2")!=0) && (choix.compareToIgnoreCase("3")!=0)) {	
		
				choix=scan.next();
			}

			if (choix.compareToIgnoreCase("1")==0 ) {	
			
			System.out.println(" Entrer le nom du 1er joueur ");
			nomJoueur1= scan.next();

			System.out.println("Entrer le nom du 2nd joueur ");
			nomJoueur2= scan.next();

			Quoridor quoridor= new Quoridor(nomJoueur1,nomJoueur2,"../data/Mode/Mode HH.txt");
			}
			else if (choix.compareToIgnoreCase("2")==0 ) {

				System.out.println(" Entrer le nom du joueur ");
				nomJoueur1= scan.next();
			
				Quoridor quoridor= new Quoridor(nomJoueur1,"IA","../data/Mode/Mode HIA.txt");
			}
			else if (choix.compareToIgnoreCase("3")==0 ) {
			
			
				Quoridor quoridor= new Quoridor("IA 1","IA 2","../data/Mode/Mode IAIA.txt");
			}
		}										
		else if ((choix.compareToIgnoreCase("2")==0) && (sauvegardes.length>0)) {
		
			Quoridor quoridor= new Quoridor();
		}
		else {
		
			System.out.println("\n Vous n'avez pas de sauvegardes, veuillez relancer le jeu et lancer une nouvelle partie");
		}	
	}
}	

	
	
	
	
	

