package Quoridor;

/**
 * Une énumération qui définit la couleur d'une case 
 * BLANC : La couleur du pion blanc 
 * NOIR : La couleur du pion noir
 * MUR : La case qui auras MUR contiendras un mur
 * FREE : les case libres
 * TEST : la couleur qui testera si un parcours est possible
*/

public enum Case {
	BLANC,
	NOIR,
	FREE,
	NONE,
	MUR,
	TEST
}