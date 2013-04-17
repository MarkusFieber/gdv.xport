/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 29.03.2013 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gdv.xport.satz.AbstractSatzTest;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;

import org.junit.Test;

/**
 * JUnit-Tests fuer {@link Satz222}.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (29.03.2013)
 */
public class Satz222Test extends AbstractSatzTest {

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz222();
    }

    /**
     * Dies ist nur ein Test, ob die Klasse instanziiert werden kann.
     */
    @Test
    public void testSatz222() {
        Datensatz antragsdaten = new Satz222(30);
        assertEquals(222, antragsdaten.getSatzart());
        assertEquals(30, antragsdaten.getSparte());
        assertTrue("invalid: " + antragsdaten, antragsdaten.isValid());
    }

}

