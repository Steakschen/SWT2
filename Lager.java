

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
	*
	*
	*
	*/
	public void artikelAnlegen (Artikel neuerArtikel) {

	}

	/**
	* Methode zum entfernen eines Artikels.
	*
	* @param artikelName Artikelname des zu entfernenden Artikels
	*
	*/
	public void entferneArtikel (String artikelName) {

	}

	/**
	* Methode zum entfernen eines Artikels.
	*
	* @param artikelNummer Artikelnummer des zu entfernenden Artikels
	*
	*/
	public void entferneArtikel (int artikelNummer) {

	}

	/**
	* Methode zum Buchen eines Artikelzugangs.
	*
	* @param artikelName Name des Artikels
	* @param menge Menge des Zugangs
	*/
	public void zugangBuchen (String artikelName, int menge) {

	}
	/**
	* Methode zum Buchen eines Artikelzugangs.
	*
	* @param artikelNummer Artikelnummer des Artikels
	* @param menge Menge des Zugangs
	*/
	public void zugangBuchen (int artikelNummer, int menge) {

	}	

	/**
	* Methode zum Buchen eines Artikelabgangs.
	*
	* @param artikelName Name des Artikels
	* @param menge Menge des Abgangs
	*/
	public void abgangBuchen (String artikelName, int menge) {

	}

	/**
	* Methode zum Buchen eines Artikelabgangs.
	*
	* @param artikelNummer Artikelnummer des Artikels
	* @param menge Menge des Abgangs
	*/
	public void abgangBuchen (int artikelNummer, int menge) {

	}

	/**
	* Ändern des Preises für alle Artikel.
	*
	* @param prozentSatz Prozentsatz um den der Preis geändert werden soll, positiv oder negativ
	*/
	public void preiseAendern (double prozentSatz) {

	}

	/**
	* Finde den Array-Index des gesuchten Artikels.
	*
	* @param artikelNummer Artikelnummer des Artikels
	*/
	private int findeArtikel (int artikelNummer) {

	}

	/**
	* Finde den Array-Index des gesuchten Artikels.
	*
	* @param artikelName Artikelname des Artikels
	*/
	private int findeArtikel (String artikelName) {

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