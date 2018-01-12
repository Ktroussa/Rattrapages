import java.util.ArrayList;

public class Joueur {
	
	private ArrayList<Coffre> coffres;
	private int score;
	private String nom;
	private double posX, posY;
	private Cave currentCave;
	private Niveau currentNiveau;

	public Joueur (String nom, Cave cave, Niveau niveau) {
		setCurrentCave(cave);
		setCurrentNiveau(niveau);
		this.coffres = new ArrayList<Coffre>();
		setScore(0);
		setNom(nom);
		setPosX(0);
		setPosY(0);
	}
	
	/* ==========================
	 * 		Override
	 * ==========================
	 */
	
	@Override
	public String toString() {
		return "Joueur [coffres=" + coffres + ", score=" + score + ", nom=" + nom + ", posX=" + posX + ", posY=" + posY
				+ ", currentCave=" + currentCave + ", currentNiveau=" + currentNiveau + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coffres == null) ? 0 : coffres.hashCode());
		result = prime * result + ((currentCave == null) ? 0 : currentCave.hashCode());
		result = prime * result + ((currentNiveau == null) ? 0 : currentNiveau.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		long temp;
		temp = Double.doubleToLongBits(posX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(posY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (coffres == null) {
			if (other.coffres != null)
				return false;
		} else if (!coffres.equals(other.coffres))
			return false;
		if (currentCave == null) {
			if (other.currentCave != null)
				return false;
		} else if (!currentCave.equals(other.currentCave))
			return false;
		if (currentNiveau == null) {
			if (other.currentNiveau != null)
				return false;
		} else if (!currentNiveau.equals(other.currentNiveau))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(posX) != Double.doubleToLongBits(other.posX))
			return false;
		if (Double.doubleToLongBits(posY) != Double.doubleToLongBits(other.posY))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	
	/* ==========================
	 * 		GETTERs & SETTERs
	 * ==========================
	 */

	public ArrayList<Coffre> getCoffres() {
		return coffres;
	}

	public void setCoffres(ArrayList<Coffre> coffres) {
		this.coffres = coffres;
	}

	public Cave getCurrentCave() {
		return currentCave;
	}

	public void setCurrentCave(Cave currentCave) {
		this.currentCave = currentCave;
	}

	public Niveau getCurrentNiveau() {
		return currentNiveau;
	}

	public void setCurrentNiveau(Niveau currentNiveau) {
		this.currentNiveau = currentNiveau;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	/* ==========================
	 * 		M�thodes
	 * ==========================
	 */
	
	public void ajouterCoffre(Coffre c) {
		this.coffres.add(c);
	}
	
	public int nbrCoffres() {
		return this.coffres.size();
	}
	
	public int score () {
		int total = 0;
		
		for (Coffre c : this.coffres) {
			total += c.getNbrTresors();
		}
		
		return total;
		
	}

}
