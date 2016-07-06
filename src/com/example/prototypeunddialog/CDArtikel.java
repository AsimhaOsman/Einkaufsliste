package com.example.prototypeunddialog;

public class CDArtikel {
	
	private String sArtikel;
	private String sMenge;
	private String sEinheit;
	private boolean bGekauft;
	
	public CDArtikel(String sArtikel, String sMenge, String sEinheit) {
		super();
		this.sArtikel = sArtikel;
		this.sMenge = sMenge;
		this.sEinheit = sEinheit;
		this.bGekauft = false;
	}

	/**
	 * @return the sEinheit
	 */
	public String getsEinheit() {
		return sEinheit;
	}

	/**
	 * @param sEinheit the sEinheit to set
	 */
	public void setsEinheit(String sEinheit) {
		this.sEinheit = sEinheit;
	}

	public String getsArtikel() {
		return sArtikel;
	}

	public void setsArtikel(String sArtikel) {
		this.sArtikel = sArtikel;
	}

	public String getsMenge() {
		return sMenge;
	}

	public void setsMenge(String sMenge) {
		this.sMenge = sMenge;
	}
	
	public void setbGekauft(boolean bgek) {
		this.bGekauft = bgek;
	}
	
	public boolean getbGekauft() {
		return this.bGekauft;
	}
	
	public String toString() {
		return sMenge + " x " + sArtikel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sArtikel == null) ? 0 : sArtikel.hashCode());
		result = prime * result + ((sMenge == null) ? 0 : sMenge.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CDArtikel other = (CDArtikel) obj;
		if (sArtikel == null) {
			if (other.sArtikel != null)
				return false;
		} else if (!sArtikel.equals(other.sArtikel))
			return false;
		if (sMenge == null) {
			if (other.sMenge != null)
				return false;
		} else if (!sMenge.equals(other.sMenge))
			return false;
		return true;
	}
	
}
