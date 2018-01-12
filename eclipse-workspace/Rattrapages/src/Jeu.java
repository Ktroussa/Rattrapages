import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

public class Jeu {

	
	/* === Attributs === */
	private String nomJeu;
	private ArrayList<Cave> caves;
	private Cave c1, c2, c3;
	private ArrayList<Reserve> reserves;
	private Reserve r1, r2, r3;
	private Joueur j1, j2;
	private int phase;
	private Joueur currentJ;
	private Reserve currentR;
	
	public Jeu (String nom) {
		
		/* === Initialisation === */
		caves = new ArrayList<Cave> ();
		reserves = new ArrayList<Reserve> ();
		phase = 1;
		
		/* === Nom du jeu === */
		setNomJeu(nom);
		
		/* === G�n�ration des nombres al�atoires === */
		int n1, n2, n3;
		Random r = new Random();
		
		n1 = 9 + r.nextInt(4); // Entre 9 et 12
		n2 = 6 + r.nextInt(4); // Entre 6 et 9
		n3 = 3 + r.nextInt(4); // Entre 3 et 6
		/* ----------------------------------------- */

		
		/* === CAVES === */
		c1 = new Cave (1, n1);
		c2 = new Cave (2, n2);
		c3 = new Cave (3, n3);
		
		/* === Rajout dans l'Arrays === */
		caves.add(c1);
		caves.add(c2);
		caves.add(c3);
		
		/* === RESERVES === */
		r1 = new Reserve (1, 2 * niveauxTotaux() );
		r2 = new Reserve (2, 2 * niveauxTotaux() );
		r3 = new Reserve (3, 2 * niveauxTotaux() );
		
		/* === Rajout dans l'Arrays === */
		reserves.add(r1);
		reserves.add(r2);
		reserves.add(r3);
		
		/* === Initialisation des joueurs === */
		j1 = new Joueur("J1", caves.get(0), caves.get(0).getNiveaux().get(0));
		j2 = new Joueur("J2", caves.get(0), caves.get(0).getNiveaux().get(0));
		
		/* === Joueur qui commence === */
		currentJ = j1;
		
		/* === Reserve initiale === */
		currentR = r1;
		
		/* === Phase === */
		phase = 1;
		
		// Boucle du jeu
		startGame();
			
	}
	
	/* ============================
	 * 		GETTERs & SETTERs
	 * =============================
	 */

	public String getNomJeu() {
		return nomJeu;
	}

	public void setNomJeu(String nomJeu) {
		this.nomJeu = nomJeu;
	}
	
	/* DEBUG */
	public Cave getC() {
		return c1;
	}
	
	/* ============================
 * 			Boucle du jeu
	 * =============================
	 */
	
	public void startGame() {
		
		boolean isPlaying = true;
		int menu = 1;
		int choix = 0;
		int action = 0;
		
		// Accueil
		drawMenu();
		
		while (choix == 0) {
			if (StdDraw.hasNextKeyTyped()) {
				int key = StdDraw.nextKeyTyped();
				if (key == 49) choix = 1;
				else if (key == 50) choix = 2;
			}
		}
		
		// Joueur VS Ordinateur
		/* if (choix == 1) {
			if (currentJ.getCurrentCave().monterNiveau(currentJ.getCurrentNiveau().getId())) {
				
			}
		} */
		
		// Joueur VS Joueur
		if (choix == 2) {
			
			drawJeu();
			
			// Position de d�part des joueurs
			j1.setPosX(0.3);
			j1.setPosY(0.72);
			j2.setPosX(0.7);
			j2.setPosY(0.72);
			
			// On dessine les joueurs
			drawPlayer();
			
			while (isPlaying) {
				if (StdDraw.hasNextKeyTyped()) {
					int key = StdDraw.nextKeyTyped();
					
					if (key == 49) action = 1;
					else if (key == 50) action = 2;
					else if (key == 51) action = 3;
					
					// -----------------------------------------
					// Action 1 : Descendre d'1 niveau
					if (action == 1) {
						
						int oxygen = currentR.getOxygen() - (1 + currentJ.nbrCoffres());
						
						if (oxygen >= 0) {
							
							// Update de la reserve
							currentR.setOxygen(currentR.getOxygen() - (1 + currentJ.nbrCoffres()  ) );
						
							// Dans le cas o� on peut descendre
							if(currentJ.getCurrentCave().descendreNiveau(currentJ.getCurrentNiveau().getId())) {
								
								// Update de la localisation
								currentJ.setCurrentNiveau(currentJ.getCurrentCave().getNiveaux().get(currentJ.getCurrentNiveau().getId() + 1));
								
								// Update de la pos Y
								currentJ.setPosY(currentJ.getPosY() - 0.01);
								
								// Changement de joueur
								if (currentJ.equals(j1))
									currentJ = j2;
								else
									currentJ = j1;
								
							}
							
							// Dans le cas o� il faut changer de cave
							else {
								
								if (currentJ.getCurrentCave().getId() < 3) {
									if (currentJ.getCurrentCave().getId() == 1)
											currentJ.setPosY(0.48);
									else if (currentJ.getCurrentCave().getId() == 2)
										currentJ.setPosY(0.25);
											
									// Update de la localisation
									int idCave = currentJ.getCurrentCave().getId();
									int idLastNiveau = caves.get(idCave - 1).getNiveaux().size();
									currentJ.setCurrentCave(caves.get(idCave));
									currentJ.setCurrentNiveau(currentJ.getCurrentCave().getNiveaux().get(0));
									
									// Changement de joueur
									if (currentJ.equals(j1))
										currentJ = j2;
									else
										currentJ = j1;
									
								}
															
					
								
							}
						}
					}
					
					// ------------------------------------------
					// Action 2 : Monter d'1 niveau
					if (action == 2) {
						
						int oxygen = currentR.getOxygen() - (1 + currentJ.nbrCoffres());
						
						if (oxygen >= 0) {
							
							// Update de la reserve
							currentR.setOxygen(currentR.getOxygen() - (1 + currentJ.nbrCoffres()  ) );
						
						
							// Dans le cas o� on pourrai monter de niveau
							if(currentJ.getCurrentCave().monterNiveau(currentJ.getCurrentNiveau().getId())) {
								// Update de la localisation
								currentJ.setCurrentNiveau(currentJ.getCurrentCave().getNiveaux().get(currentJ.getCurrentNiveau().getId() - 1));
								
								// Update de la pos Y
								currentJ.setPosY(currentJ.getPosY() + 0.01);
								
								// Changement de joueur
								if (currentJ.equals(j1))
									currentJ = j2;
								else
									currentJ = j1;
								
								
							}
							
							// Dans le cas o� il faut changer de cave pour monter
							else {
								
								if (currentJ.getCurrentCave().getId() > 1) {
									if (currentJ.getCurrentCave().getId() == 2)
											currentJ.setPosY(0.60);
									else if (currentJ.getCurrentCave().getId() == 3)
										currentJ.setPosY(0.4);
											
									// Update de la localisation
									int idCave = currentJ.getCurrentCave().getId() - 2;
									int idLastNiveau = caves.get(idCave).getNiveaux().size() - 1;

									currentJ.setCurrentCave(caves.get(idCave));
									currentJ.setCurrentNiveau(caves.get(currentJ.getCurrentCave().getId() - 1).getNiveaux().get(idLastNiveau));
									
									// Changement de joueur
									if (currentJ.equals(j1))
										currentJ = j2;
									else
										currentJ = j1;
									
								}
									
							}
							
						}
						
					}
						
						// --------------------------------------------------
						// Prendre un coffre
						if (action == 3) {
							
							int oxygen = currentR.getOxygen() - 1;
							
							if (oxygen >= 0) {
								
								// Update de la reserve
								currentR.setOxygen(currentR.getOxygen() - 1  );
			
								// Dans le cas de la pr�sence d'un coffre
								if (currentJ.getCurrentNiveau().isCoffre()) {
									currentJ.ajouterCoffre(currentJ.getCurrentNiveau().getCoffres().get(0));
									currentJ.getCurrentNiveau().supprimerCoffre(currentJ.getCoffres().get(0));
									
									// Changement de joueur
									if (currentJ.equals(j1))
										currentJ = j2;
									else
										currentJ = j1;
									
								}
								
								
							}
							
						}
					
					
					// On dessine les joueurs
					drawPlayer();
					
				}
					
				}
			}
			
		}
		
	
	/* ============================
	 * 			M�thodes
	 * =============================
	 */
	
	public int niveauxTotaux() {
		int total = 0;
		
		for (Cave c : caves) {
			total += c.getNbrNiveaux();
		}
		
		return total;
		
	}
	
	/* ============================
	 * 			Draw
	 * =============================
	 */
	
	public void drawJeu() {
		
		StdDraw.clear();
		
		/* SCORES */
		StdDraw.setPenColor(StdDraw.BLACK);
		Font font = new Font("Arial", Font.BOLD, 15);
        StdDraw.setFont(font);
		StdDraw.text(0.5, 0.95, "Scores");
		StdDraw.text(0.4, 0.9, j1.getNom() + ": " + j1.getScore());
		StdDraw.text(0.6, 0.9, j2.getNom() + ": " + j2.getScore());
		StdDraw.text(0.4, 0.02, "Tour : " + currentJ.getNom());
		StdDraw.text(0.6, 0.02, "Phase : " + phase);
		StdDraw.text(0.5, 0.86, "Reserve : " + currentR.getOxygen());
		
		StdDraw.text(0.5, 0.09,
				"J1 : Cave " + j1.getCurrentCave().getId() +
				" Niveau " + (j1.getCurrentNiveau().getId() + 1) +
				" Coffres " + j1.nbrCoffres());
		
		StdDraw.text(0.5, 0.06, 
				"J2 : Cave " + j2.getCurrentCave().getId() + 
				" Niveau " + (j2.getCurrentNiveau().getId() + 1) +
				" Coffres " + j2.nbrCoffres());
		
		/* RESERVE */
		StdDraw.setPenRadius(0.04);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.line(0.2, 0.83, 0.8, 0.83);
        
        /* Caves */
		StdDraw.setPenRadius(0.04);
		
		StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(0.5, 0.23, 0.4, 0.09);
        
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledRectangle(0.5, 0.43, 0.4, 0.1);
        
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledRectangle(0.5, 0.65, 0.4, 0.11);
        
        font = new Font("Arial", Font.PLAIN, 12);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.23, "Cave 3");
        StdDraw.text(0.5, 0.43, "Cave 2");
        StdDraw.text(0.5, 0.65, "Cave 1");
        
	}
	
	public void drawMenu() {
		
		StdDraw.clear();
		StdDraw.setCanvasSize(512, 650);
		
		/* LINES */
		StdDraw.setPenRadius(0.06);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(0.3, 0.602, 0.7, 0.602);
        StdDraw.line(0.3, 0.502, 0.7, 0.502);
		
		/* TEXTES */
        Font font = new Font("Arial", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.6, "1. Joueur VS Ordinateur");
		StdDraw.text(0.5, 0.5, "2. Joueur VS Joueur");
		
	}
	
	public void drawPlayer() {
		
		StdDraw.clear();
		drawJeu();
		StdDraw.setPenRadius(0.05);
		
		// Point
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(j1.getPosX(), j1.getPosY());
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(j2.getPosX(), j2.getPosY());
        
        // Texte
        Font font = new Font("Arial", Font.BOLD, 12);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(j1.getPosX(), j1.getPosY(), j1.getNom());
        StdDraw.text(j2.getPosX(), j2.getPosY(), j2.getNom());
		
	}
	
public void drawScore() {
		
		StdDraw.clear();
		
		/* LINES */
		StdDraw.setPenRadius(0.06);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(0.3, 0.602, 0.7, 0.602);
        StdDraw.line(0.3, 0.502, 0.7, 0.502);
		
		/* TEXTES */
        Font font = new Font("Arial", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.6, "J1 : " + currentJ.score());
		StdDraw.text(0.5, 0.5, "J2 : " + currentJ.score());
		
	}

}
