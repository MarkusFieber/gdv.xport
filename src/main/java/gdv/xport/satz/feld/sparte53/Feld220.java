/*
 * Copyright (c) 2011 by agentes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 07.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte53;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 53.
 * 
 * @author oliver
 * @since 07.04.11
 */
public enum Feld220 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /**
     * KFT-Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 43
    )
    KFT_BEGINN,

    /**
     * KFT-Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 51
    )
    KFT_AUSSCHLUSS,

    /**
     * KFT-aenderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 59
    )
    KFT_AENDERUNGSDATUM,

    /**
     * KFT-Tarifgruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 67
    )
    KFT_TARIFGRUPPE,

    /**
     * KFT-Deckungsart.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 71
    )
    KFT_DECKUNGSART,

    /**
     * KFT-Beitrag in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 73
    )
    KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * KFT-Zuschlaege in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 81
    )
    KFT_ZUSCHLAEGE_IN_PROZENT,

    /**
     * KFT-Abschlaege in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 86
    )
    KFT_ABSCHLAEGE_IN_PROZENT,

    /**
     * KFT-Zuschlaege in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 91
    )
    KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * KFT-Abschlaege in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 97
    )
    KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Flottenrabatt in % (2,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 3,
            byteAdresse = 103
    )
    FLOTTENRABATT_IN_PROZENT,

    /**
     * Gueltige AKB.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 106
    )
    GUELTIGE_AKB,

    /**
     * Provision (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 110
    )
    PROVISION,

    /**
     * Typklasse fuer KFT.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 114
    )
    TYPKLASSE_FUER_KFT,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer Teilkasko.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 116
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO,

    /**
     * Kennzeichen fuer abweichende Provision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 126
    )
    KENNZEICHEN_FUER_ABWEICHENDE_PROVISION,

    /**
     * Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugteil in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 127
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 135
    )
    REFERENZNUMMER,

    /**
     * Tarif.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 142
    )
    TARIF,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 152
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 156
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Schutzbrief /Verkehrsservice.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 173
    )
    SCHUTZBRIEF_VERKEHRSSERVICE,

    /**
     * GAP-Deckung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 174
    )
    GAP_DECKUNG,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /**
     * Produktkennung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 43
    )
    PRODUKTKENNUNG,

    /**
     * Versicherte Gefahren.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 63
    )
    VERSICHERTE_GEFAHREN,

    /**
     * Selbstbeteiligung in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 93
    )
    SELBSTBETEILIGUNG_IN_PROZENT,

    /**
     * Selbstbeteiligung in WE (mind.) (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 98
    )
    SELBSTBETEILIGUNG_IN_WE_MIND,

    /**
     * Selbstbeteiligung in WE (max.) (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 110
    )
    SELBSTBETEILIGUNG_IN_WE_MAX,

    /**
     * Geltungsbereich.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 122
    )
    GELTUNGSBEREICH,

    /**
     * Geltungsbereicheinschraenkung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 152
    )
    GELTUNGSBEREICHEINSCHRAENKUNG,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER2;

}