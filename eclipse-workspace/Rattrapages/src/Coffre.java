import java.util.Random;

public class Coffre {
	
	/* === Attributs === */
	private int nbrTresors = 0;
	private int caveInitiale = 0;
	
	/* === Constructeur === */
	public Coffre (int caveInitiale) {
		
		setCaveInitiale(caveInitiale);
		
		/* === Generation du nombre de tr�sors === */
		Random r = new Random();
		int n = 0;
		
		if (getCaveInitial() == 1)
			n = 1 + r.nextInt(3); // Entre 1 et 3
		else if (getCaveInitial() == 2)
			n = 5 + r.nextInt(4); // Entre 5 et 8
		else if (getCaveInitial() == 3)
			n = 10 + r.nextInt(3); // Entre 10 et 12
		
		setNbrTresors(n);
		
	}

	/* ============================
	 * 		GETTERs & SETTERs
	 * =============================
	 */
	
	public int getNbrTresors() {
		return nbrTresors;
	}

	@Override
	public String toString() {
		return "Coffre [nbrTresors=" + nbrTresors + ", caveInitial=" + caveInitiale + "]";
	}
	
	public void setNbrTresors(int nbrTresors) {
		this.nbrTresors = nbrTresors;
	}

	public int getCaveInitial() {
		return caveInitiale;
	}

	public void setCaveInitiale(int caveInitiale) {
		this.caveInitiale = caveInitiale;
	}

	/* ============================
	 * 		Override
	 * ============================
	 */
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caveInitiale;
		result = prime * result + nbrTresors;
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
		Coffre other = (Coffre) obj;
		if (caveInitiale != other.caveInitiale)
			return false;
		if (nbrTresors != other.nbrTresors)
			return false;
		return true;
	}


}
