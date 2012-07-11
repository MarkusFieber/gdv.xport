/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 30.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.satz.*;
import gdv.xport.satz.model.*;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um einen vorgegebene Satz, der z.B. aus einem
 * Import kommt, in den entsprechende Satz wandeln zu koennen.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (30.10.2009)
 */
public final class SatzFactory {

    /** The Constant log. */
    private static final Log log = LogFactory.getLog(SatzFactory.class);
    
    /** The registered satz classes. */
    private static final Map<Integer, Class<? extends Satz>> registeredSatzClasses =
        new HashMap<Integer, Class<? extends Satz>>();
    
    /** The registered datensatz classes. */
    private static final Map<Integer, Class<? extends Datensatz>> registeredDatensatzClasses =
        new ConcurrentHashMap<Integer, Class<? extends Datensatz>>();
    
    /** The registered Enum classes. */
    private static final Map<Integer, Class<? extends Enum<?>>> registeredEnumClasses =
        new HashMap<Integer, Class<? extends Enum<?>>>();

    static {
        register(Vorsatz.class, 1);
        register(Satz100.class, 100);
        register(Satz200.class, 200);
        register(Satz210.class, 210);
        register(Satz210.class, 210, 10);
        register(Satz210.class, 210, 30);
        register(Satz210.class, 210, 50);
        register(Satz210.class, 210, 70);
        register(Satz211.class, 211);
        register(Satz211.class, 211, 10);
        register(Satz211.class, 211, 50);
        register(Satz220.class, 220);
        register(Satz220.class, 220, 30);
        register(Satz220.class, 220, 51);
        register(Satz220.class, 220, 52);
        register(Satz220.class, 220, 53);
        register(Satz220.class, 220, 70);
        register(Satz221.class, 221);
        register(Satz221.class, 221, 30);
        register(Satz221.class, 221, 51);
        register(Satz221.class, 221, 52);
        register(Satz221.class, 221, 53);
        register(Satz221.class, 221, 70);
        register(Nachsatz.class, 9999);
    }

    /**
     * Instantiates a new satz factory.
     */
    private SatzFactory() {}

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     * Die Kasse <em>muss</em> einen Default-Konstruktor bereitstellen.
     * Ansonsten wird hier eine {@link IllegalArgumentException} geworfen
     * (seit 0.6).
     *
     * @param clazz the clazz
     * @param satzart the satzart
     * @since 0.2
     */
    public static void register(final Class<? extends Satz> clazz, final int satzart) {
        try {
            Constructor<? extends Satz> ctor = clazz.getConstructor();
            if (log.isDebugEnabled()) {
                log.debug("default ctor " + ctor + " found");
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("no default constructor found in " + clazz);
        }
        registeredSatzClasses.put(satzart, clazz);
    }
    
    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart
     * @since 0.6
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final int satzart) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        registeredEnumClasses.put(satzart, enumClass);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @since 0.2
     */
    public static void unregister(final int satzart) {
        registeredSatzClasses.remove(satzart);
        registeredEnumClasses.remove(satzart);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzart the satzart
     * @param sparte the sparte
     * @since 0.2
     */
    public static void register(final Class<? extends Datensatz> clazz, final int satzart,
            final int sparte) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        assert (0 <= sparte) && (sparte <= 999)    : "Sparte muss zwischen 0 und 999 liegen";
        int key = getAsKey(satzart, sparte);
        registeredDatensatzClasses.put(key, clazz);
    }
    
    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart
     * @param sparte die Sparte
     * @since 0.6
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final int satzart,
            final int sparte) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        assert (0 <= sparte) && (sparte <= 999)    : "Sparte muss zwischen 0 und 999 liegen";
        int key = getAsKey(satzart, sparte);
        registeredDatensatzClasses.remove(key);
        registeredEnumClasses.put(key, enumClass);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @since 0.2
     */
    public static void unregister(final int satzart, final int sparte) {
        int key = getAsKey(satzart, sparte);
        registeredDatensatzClasses.remove(key);
        registeredEnumClasses.remove(key);
    }

    /**
     * Gets the as key.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @return the as key
     */
    private static int getAsKey(final int satzart, final int sparte) {
        return satzart * 1000 + sparte;
    }

    /**
     * Gets the satz.
     *
     * @param satzart the satzart
     * @return angeforderte Satz
     * @since 0.2
     */
    public static Satz getSatz(final int satzart) {
        Class<? extends Satz> clazz = registeredSatzClasses.get(satzart);
        if (clazz == null) {
            return generateSatz(satzart);
        }
        try {
            Satz satz = clazz.newInstance();
            if (satz.getSatzart() != satzart) {
                Constructor<? extends Satz> ctor = clazz.getConstructor(int.class);
                satz = ctor.newInstance(satzart);
            }
            return satz;
        } catch (Exception e) {
            log.info("default ctor does not work (" + e + "), trying another ctor...");
            Constructor<? extends Satz> ctor = null;
            try {
                ctor = clazz.getConstructor(int.class);
                return ctor.newInstance(satzart);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite.getTargetException() + " in " + ctor, ite);
            } catch (NoSuchMethodException nsme) {
                throw new UnsupportedOperationException("registered " + clazz
                        + " has not the required ctor", nsme);
            } catch (InstantiationException ie) {
                throw new RuntimeException("registered " + clazz + " can't be instantiated", ie);
            } catch (IllegalAccessException iae) {
                throw new IllegalStateException("registered " + clazz + " can't be accessed", iae);
            }
        }
    }
    
    private static Satz generateSatz(final int satzart) {
        Class<? extends Enum<?>> enumClass = registeredEnumClasses.get(satzart);
        if (enumClass == null) {
            throw new NotRegisteredException(satzart);
        }
        return new SatzX(satzart, enumClass);
    }
    
    private static Datensatz generateDatensatz(final int satzart, final int sparte) {
        int key = getAsKey(satzart, sparte);
        Class<? extends Enum<?>> enumClass = registeredEnumClasses.get(key);
        if (enumClass == null) {
            return useFallback(satzart, sparte);
        }
        return new SatzX(satzart, sparte, enumClass);
    }

    /**
     * Versucht anhand des uebergebenen Strings herauszufinden, um was fuer
     * eine Satzart es sich handelt und liefert dann einen entsprechende
     * (gefuellten) Satz zurueck.
     *
     * @param content the content
     * @return einen gefuellten Satz
     * @since 0.2
     */
    public static Satz getSatz(final String content) {
        int satzart = Integer.parseInt(content.substring(0, 4));
        Satz satz;
        try {
            satz = getSatz(satzart);
        } catch (RuntimeException e) {
            log.debug("can't get Satz " + satzart + " (" + e + "), parsing Sparte...");
            int sparte = Integer.parseInt(content.substring(10, 13));
            satz = getDatensatz(satzart, sparte);
        }
        try {
            satz.importFrom(content);
            return satz;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("can't parse " + content, ioe);
        }
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart den registrierten Datensatz fuer
     * @return den registrierten Datensatz fuer 'satzart'
     * @since 0.2
     */
    public static Datensatz getDatensatz(final int satzart) {
        return (Datensatz) getSatz(satzart);
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart z.B. 210
     * @param sparte z.B. 70 (Rechtsschutz)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte'
     */
    public static Datensatz getDatensatz(final int satzart, final int sparte) {
        int key = getAsKey(satzart, sparte);
        Class<? extends Datensatz> clazz = registeredDatensatzClasses.get(key);
        if (clazz == null) {
            return generateDatensatz(satzart, sparte);
        }
        try {
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class, int.class);
            return ctor.newInstance(satzart, sparte);
        } catch (NoSuchMethodException exWithTwoParams) {
            log.info("ctor " + clazz + "(int, int) not found (" + exWithTwoParams + ")");
            return getDatensatz(sparte, clazz);
        } catch (InstantiationException exWithTwoParams) {
            log.info(clazz + "(int, int) can't be instantiated (" + exWithTwoParams + ")");
            return getDatensatz(sparte, clazz);
        } catch (IllegalAccessException exWithTwoParams) {
            log.info(clazz + "(int, int) can't be accessed (" + exWithTwoParams + ")");
            return getDatensatz(sparte, clazz);
        } catch (InvocationTargetException exWithTwoParams) {
            log.info("error in calling " + clazz + "(int, int): " + exWithTwoParams);
            return getDatensatz(sparte, clazz);
        }
    }

    /**
     * Gets the datensatz.
     *
     * @param sparte the sparte
     * @param clazz the clazz
     * @return the datensatz
     */
    private static Datensatz getDatensatz(final int sparte, final Class<? extends Datensatz> clazz) {
        try {
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class);
            return ctor.newInstance(sparte);
        } catch (NoSuchMethodException nsme) {
            log.info(clazz + " found but no " + clazz.getSimpleName() + "(" + sparte + ") ctor (" + nsme + ")");
            return getDatensatz(clazz);
        } catch (Exception exWithOneParam) {
            log.warn("constructor problem with " + clazz, exWithOneParam);
            return getDatensatz(clazz);
        }
    }

    private static Datensatz getDatensatz(final Class<? extends Datensatz> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("can't instantiate " + clazz, e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("can't access default ctor of " + clazz, e);
        }
    }

    /**
     * Use fallback.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @return the datensatz
     */
    private static Datensatz useFallback(final int satzart, final int sparte) {
        try {
            Datensatz fallback = (Datensatz) getSatz(satzart);
            fallback.setSparte(sparte);
            return fallback;
        } catch (NotRegisteredException re) {
            log.warn("reduced functionality for (unknown or unsupported) Satzart " + satzart);
            Datensatz satz = new Datensatz(satzart, sparte);
            satz.addFiller();
            return satz;
        }
    }

    /**
     * Liefert ein Datenpaket mit allen unterstuetzten Satzarten und Sparten.
     * 
     * @since 0.6
     * @return Datenpaket mit allen unterstuetzten Satzarten
     */
    public static Datenpaket getAllSupportedSaetze() {
        Datenpaket all = new Datenpaket();
        for (Integer satzart : registeredSatzClasses.keySet()) {
            try {
                all.add(getDatensatz(satzart));                
            } catch (ClassCastException canhappen) {
                if ((satzart != 1) && (satzart != 9999)) {
                    log.warn("Satzart " + satzart + " not added as supported Satz", canhappen);
                }
            }
        }
        for (Integer key : registeredDatensatzClasses.keySet()) {
            int sparte = key % 1000;
            int satzart = key / 1000;
            all.add(getDatensatz(satzart, sparte));
        }
        return all;
    }

}

