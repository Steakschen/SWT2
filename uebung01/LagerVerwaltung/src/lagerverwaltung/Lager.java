

 package lagerverwaltung;

/**
* Fachklasse für die Verwaltung eines Lagers.
* 
* @author Moritz Fey, Carsten Gross
* 
*/
public class Lager {

	/** Standort des Lagers. */
	private String standort;

	/** Array, das die Artikel beinhaltet. */
	private Artikel [] artikel;

	/** Maximale Anzahl Artikel im Lager. */
	private int maxArtikel;

	/** Zähler für die Anzahl der Artikel im Array. */
	private int artikelCounter;
	
	/**
	* Konstruktor der Klasse Lager.
	* 
	* @param standort Name des Lagerstandorts
	* @param maxArtikel Maximale Anzahl Artikel die das Lager beinhalten kann
	*/
	public Lager (String standort, int maxArtikel) {
		assert standort != null : "Standort ist nicht übergeben worden!";
		if (standort.length() > 0) {
			this.standort = standort;
		}

		assert maxArtikel >= 0 : "Die übergebene Anzahl Artikel ist negativ oder 0";
		this.maxArtikel = maxArtikel;

		this.artikelCounter = 0;

		this.artikel = new Artikel[maxArtikel];
	}

	/**
	* Methode zum Anlegen eines neuen Artikels im Lager.
	* Mit Bestand.
	*
	* @param artikelNummer 		Artikelnummer des Artikels
	* @param bezeichnung		Bezeichnung des Artikels
	* @param bestand			Bestand des Artikels
	* @param preis 				Preis des Artikels
	*
	*/
	public void erstelleArtikel (int artikelNummer, String bezeichnung, int bestand, double preis) {
		artikel[artikelCounter] = new Artikel(artikelNummer, bezeichnung, bestand, preis);
		artikelCounter += 1;
	}

	/**
	* Methode zum Anlegen eines neuen Artikels im Lager.
	* Ohne Bestand.
	*
	* @param artikelNummer 		Artikelnummer des Artikels
	* @param bezeichnung		Bezeichnung des Artikels
	* @param preis 				Preis des Artikels
	*
	*/
	public void erstelleArtikel (int artikelNummer, String bezeichnung, double preis) {
		artikel[artikelCounter] = new Artikel(artikelNummer, bezeichnung, preis);
		artikelCounter += 1;
	}


	/**
	* Methode zum entfernen eines Artikels.
	*
	* @param artikelName 		Artikelname des zu entfernenden Artikels
	*
	*/
	public void entferneArtikel (String artikelName) {
		int pos = findeArtikel(artikelName);
		if (pos >= 0) {
			for (int i = pos; i < artikelCounter; i++) {
				artikel[i] = artikel[i+1]
				
			}
			artikelCounter -= 1;
		}
		else {
			assert pos < 0 :  "Artikel nicht vorhanden!";
		}

	}

	/**
	* Methode zum entfernen eines Artikels.
	*
	* @param artikelNummer 		Artikelnummer des zu entfernenden Artikels
	*
	*/
	public void entferneArtikel (int artikelNummer) {
		int pos = findeArtikel(artikelNummer);
		if (pos >= 0) {
			for (int i = pos; i < artikelCounter; i++) {
				artikel[i] = artikel[i+1]
				
			}
			artikelCounter -= 1;
		}
		else {
			assert pos < 0 :  "Artikel nicht vorhanden!";
		}
	}

	/**
	* Methode zum Buchen eines Artikelzugangs.
	*
	* @param artikelName 		Name des Artikels
	* @param menge 				Menge des Zugangs
	*/
	public void bucheZugang (String artikelName, int menge) {
		int pos = findeArtikel(artikelName);
		if (pos >= 0) {
			artikel[pos].bucheZugang(menge);
		}
		else {
			assert pos < 0 : "Artikel nicht vorhanden!";
		}
	}

	/**
	* Methode zum Buchen eines Artikelzugangs.
	*
	* @param artikelNummer 		Artikelnummer des Artikels
	* @param menge 				Menge des Zugangs
	*/
	public void bucheZugang (int artikelNummer, int menge) {
		int pos = findeArtikel(artikelName);
		if (pos >= 0) {
			artikel[pos].bucheZugang(menge);
		}
		else {
			assert pos < 0 : "Artikel nicht vorhanden!";
		}
	}	

	/**
	* Methode zum Buchen eines Artikelabgangs.
	*
	* @param artikelName 		Name des Artikels
	* @param menge 				Menge des Abgangs
	*/
	public void bucheAbgang (String artikelName, int menge) {
		int pos = findeArtikel(artikelName);
		if (pos >= 0) {
			artikel[pos].bucheAbgang(menge);
		}
		else {
			assert pos < 0 : "Artikel nicht vorhanden!";
		}
	}

	/**
	* Methode zum Buchen eines Artikelabgangs.
	*
	* @param artikelNummer 		Artikelnummer des Artikels
	* @param menge 				Menge des Abgangs
	*/
	public void bucheAbgang (int artikelNummer, int menge) {
		int pos = findeArtikel(artikelNummer);
		if (pos >= 0) {
			artikel[pos].bucheAbgang(menge);
		}
		else {
			assert pos < 0 : "Artikel nicht vorhanden!";
		}
	}

	/**
	* Ändern des Preises für alle Artikel.
	*
	* @param prozentSatz 		Prozentsatz um den der Preis geändert werden soll, positiv oder negativ
	*/
	public void aenderePreis (double prozentSatz) {
		assert (prozentSatz > 50 && prozentSatz < -50) : "Angegebener Prozentsatz ausserhalb des Wertebereichs";
		for (int i = 0; i < artikelCounter; i++) {
			artikel[i].aenderePreis(prozentSatz);
		}
	}

	/**
	* Finde den Array-Index des gesuchten Artikels.
	*
	* @param artikelNummer 		Artikelnummer des Artikels
	*/
	private int findeArtikel (int artikelNummer) {
		for (int i = 0; i < artikelCounter; i++) {
			if (artikel.getArtikelNr() == artikelNummer) {
				return i;
			}
		}
		return -1;
	}

	/**
	* Finde den Array-Index des gesuchten Artikels.
	*
	* @param artikelName 		Artikelname des Artikels
	*/
	private int findeArtikel (String artikelName) {
		for (int i = 0; i < artikelCounter; i++) {
			if (artikel.getBezeichnung() == artikelName) {
				return i;
			}
		}
		return -1;
	}

	/**
	* toString zur Ausgabe des kompletten Lagers.
	*
	* @return Lager als String
	*/ 
	public String toString () {
		String lagerString = new String("Lager am Standort: " + standort + '\n');
		
		for (int i = 0; i < artikelCounter; i++) {
			lagerString += artikel[i].toString();
		}

		return lagerString;
	}

}