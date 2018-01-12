import java.util.ArrayList;

/*
 * Une cave poss�de un nombre al�atoire de niveaux
 * qui d�pendra de sa localisation.
 */

public class Cave {
	
	/* === Attributs === */
	private int id;
	private int nbrNiveaux = 0;
	private ArrayList<Niveau> niveaux;
	
	/* === Constructeur === */
	public Cave (int id, int nbrNiveaux) {
		
		setNbrNiveaux(nbrNiveaux);
		setId(id);
		this.niveaux = new ArrayList<Niveau>();
		
		// G�n�ration des niveaux
		for (int i = 0 ; i < getNbrNiveaux() ; i++) {
			Niveau n = new Niveau(i, 1, getId() );
			niveaux.add(n);
		}
		
	}
	
	/* ============================
	 * 		GETTERs & SETTERs
	 * ============================
	 */

	public ArrayList<Niveau> getNiveaux() {
		return niveaux;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrNiveaux() {
		return nbrNiveaux;
	}

	public void setNbrNiveaux(int nbrNiveaux) {
		this.nbrNiveaux = nbrNiveaux;
	}
	

	/* ============================
	 * 		Override
	 * ============================
	 */
	

	@Override
	public String toString() {
		return "Cave [id=" + id + ", nbrNiveaux=" + nbrNiveaux + ", niveaux=" + niveaux + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + nbrNiveaux;
		result = prime * result + ((niveaux == null) ? 0 : niveaux.hashCode());
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
		Cave other = (Cave) obj;
		if (id != other.id)
			return false;
		if (nbrNiveaux != other.nbrNiveaux)
			return false;
		if (niveaux == null) {
			if (other.niveaux != null)
				return false;
		} else if (!niveaux.equals(other.niveaux))
			return false;
		return true;
	}
	
	/* ============================
	 * 		M�thodes
	 * ============================
	 */
	
	public boolean monterNiveau(int index) {
		if (niveaux.size() > index - 1 && index - 1 >= 0)
			return true;
		else
			return false;
	}
	
	public boolean descendreNiveau(int index) {
		if (niveaux.size() > index + 1 && index + 1 >= 0)
			return true;
		else
			return false;
	}
	
}
