/**
 * 
 */
package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import java.io.IOException;
import java.util.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;


/**
 * @author oliver
 */
public final class Vorsatz extends Satz {
	
	/** 5 Zeichen, Byte 5 - 9 */
	protected AlphaNumFeld vuNummer = new VUNummer(Config.getVUNummer(), 5);
	/** 30 Zeichen, Byte 10 - 39 */
	private AlphaNumFeld absender = new AlphaNumFeld(ABSENDER, 30, 10);
	/** 30 Zeichen, Byte 40 - 69 */
	private AlphaNumFeld adressat = new AlphaNumFeld(ADRESSAT, 30, 40);
	/** 8 Zeichen, Byte 70 - 77 */
	private Datum von = new Datum(ERSTELLUNGSDATUM_ZEITRAUM_VOM, 70);
	/** 8 Zeichen, Byte 78 - 85 */
	private Datum bis = new Datum(ERSTELLUNGSDATUM_ZEITRAUM_BIS, 78);
	/** 10 Zeichen, Byte 86 - 95 */
	private AlphaNumFeld vermittler = new AlphaNumFeld(VERMITTLER, 10, 86);
	/** die Versionen fuer die verschiedenen Datensaetze */
	private Map<Integer, Version> versions = new HashMap<Integer, Version>();

	public Vorsatz() {
		super("0001", 3);
		setUpTeildatensaetze();
		setUpVersions();
		setUpDatensaetze();
	}
	
	public Vorsatz(String content) {
		this();
		try {
	        this.importFrom(content);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("argument too short", ioe);
        }
	}
	
	private void setUpTeildatensaetze() {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].add(this.vuNummer);
			teildatensatz[i].add(this.absender);
			teildatensatz[i].add(this.adressat);
			teildatensatz[i].add(this.von);
			teildatensatz[i].add(this.bis);
			teildatensatz[i].add(this.vermittler);
		}
	}
	
	private void setUpVersions() {
		addVersion(1, new Version(VERSION_VORSATZ, 96, "1.0"));
		addVersion(100, new Version(VERSION_ADRESSSATZ, 99));
		addVersion(200, new Version(VERSION_ALLGEMEINER_VERTRAGSTEIL, 102));
		addVersion(9999, new Version(VERSION_NACHSATZ, 225, "1.0"));
	}
	
	private void setUpDatensaetze() {
	}
	
	private void addVersion(Integer art, Version version) {
		versions.put(art, version);
		add(version);
	}
	
	public String getVuNummer() {
		return this.vuNummer.getInhalt().trim();
	}
	
	/**
	 * Absender ist Byte 10 - 39 im Teildatensatz.
	 * @param name
	 */
	public void setAbsender(String name) {
		this.absender.setInhalt(name);
	}
	
	public String getAbsender() {
		return this.absender.getInhalt().trim();
	}
	
	public void setAdressat(String name) {
		this.adressat.setInhalt(name);
	}
	
	public String getAdressat() {
		return this.adressat.getInhalt().trim();
	}
	
	/**
	 * @param startDatum im Format "TTMMJJJJ"
	 * @param endDatum im Format "TTMMJJJJ"
	 */
	public void setErstellungsZeitraum(String startDatum, String endDatum) {
		this.von.setInhalt(startDatum);
		this.bis.setInhalt(endDatum);
	}
	
	public void setVermittler(String s) {
		this.vermittler.setInhalt(s);
	}
	
	public String getVermittler() {
		return this.vermittler.getInhalt().trim();
	}
	
	/**
	 * Momentan wird die Version immer auf "1.0" fuer den uebergebenen
	 * Datensatz gesetzt.
	 * 
	 * @param datensatz
	 */
	public void setVersionFor(Datensatz datensatz) {
		int art = datensatz.getSatzart();
		Version version = versions.get(art);
		version.setInhalt("1.0");
	}
	
	/**
	 * Ermittelt die Version des uebergebenen Bezeichners.
	 * 
	 * @param bezeichner
	 *            z.B. VERSION_VORSATZ; hier koennen alle die
	 *            Bezeichner-Konstanten gewaehlt werden, die mit "VERSION_"
	 *            anfangen.
	 * @return Version des gewuenschten Bezeichners
	 */
	public String getVersion(String bezeichner) {
		return this.getFeld(bezeichner).getInhalt();
	}

}
