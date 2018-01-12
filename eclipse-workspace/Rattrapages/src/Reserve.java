
public class Reserve {
	
	/* === Attributs === */
	private int numeroReserve = 0;
	private int oxygen = 0;
	
	/* === Constructeur === */
	public Reserve (int numeroReserve, int oxygen) {
		
		setNumeroReserve(numeroReserve);
		setOxygen(oxygen);
		
	}
	
	/* ==========================
	 * 		GETTERs & SETTERs
	 * ==========================
	 */

	public int getNumeroReserve() {
		return numeroReserve;
	}

	public void setNumeroReserve(int numeroReserve) {
		this.numeroReserve = numeroReserve;
	}

	public int getOxygen() {
		return oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}
	
	/* ============================
	 * 		Override
	 * ============================
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroReserve;
		result = prime * result + oxygen;
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
		Reserve other = (Reserve) obj;
		if (numeroReserve != other.numeroReserve)
			return false;
		if (oxygen != other.oxygen)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reserve [numeroReserve=" + numeroReserve + ", oxygen=" + oxygen + "]";
	}
	
	
	

}
