/*
 * Copyright (c) 2017 by Oliver Boehm
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
 * (c)reated 13.02.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.web;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * EInfacher Integrationstests fuer die {@link PingController}-Klasse. Die
 * Test-Klasse ist mehr oder weniger von HelloControllerIT in
 * https://github.com/spring-guides/gs-actuator-service.git abgeschaut.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public final class PingControllerIT extends AbstractControllerIT {

    /**
     * Hier ueberpruefen wir, ob wir was vom Ping-Service zurueckbekommen.
     */
    @Test
    public void testPing() {
        ResponseEntity<String> response = getResponseEntityFor("/ping", String.class);
        assertThat(response.getBody(), equalTo("OK"));
    }

}