import java.util.ArrayList;

public class Niveau {
	
	/* === Attributs === */
	private int id;
	private int nbrCoffres = 0;
	private ArrayList<Coffre> coffres;
	private int cave = 0;
	
	public ArrayList<Coffre> getCoffres() {
		return coffres;
	}

	/* === Constructeur === */
	public Niveau (int id, int nbrCoffres, int cave) {
		
		setId(id);
		setNbrCoffres(nbrCoffres);
		setCave(cave);
		this.coffres = new ArrayList<Coffre>();
		
		// G�n�ration de la liste des coffres
		for (int i = 0 ; i < getNbrCoffres() ; i++) {
			Coffre c = new Coffre(cave);
			coffres.add(c);
		}
		
	}
	
	/* ==========================
	 * 		Override
	 * ==========================
	 */

	@Override
	public String toString() {
		return "Niveau [id=" + id + ", nbrCoffres=" + nbrCoffres + ", coffres=" + coffres + ", cave=" + cave + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cave;
		result = prime * result + ((coffres == null) ? 0 : coffres.hashCode());
		result = prime * result + id;
		result = prime * result + nbrCoffres;
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
		Niveau other = (Niveau) obj;
		if (cave != other.cave)
			return false;
		if (coffres == null) {
			if (other.coffres != null)
				return false;
		} else if (!coffres.equals(other.coffres))
			return false;
		if (id != other.id)
			return false;
		if (nbrCoffres != other.nbrCoffres)
			return false;
		return true;
	}
	
	/* ==========================
	 * 		GETTERs & SETTERs
	 * ==========================
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCave() {
		return cave;
	}

	public void setCave(int cave) {
		this.cave = cave;
	}

	public int getNbrCoffres() {
		return nbrCoffres;
	}

	public void setNbrCoffres(int nbrCoffres) {
		this.nbrCoffres = nbrCoffres;
	}
	
	/* ==========================
	 * 		M�thodes
	 * ==========================
	 */
	
	public boolean isCoffre () {
		
		if (coffres.isEmpty())
			return false;
		
		return true;
		
	}
	
	public void supprimerCoffre(Coffre c) {
		
			this.coffres.remove(0);
		
	}

}
