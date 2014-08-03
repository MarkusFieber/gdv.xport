/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 30-Jul-2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.satz.Datensatz;

import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patterntesting.runtime.exception.NotFoundException;

/**
 * Im Gegensatz zum SOP-Ansatz und zur SatzX-Klasse wird hier eine XML-
 * Beschreibung verwendet, um die einzelnen Teildatensaetze mit ihren Feldern
 * zu bestimmen. Da die XML-Datei mit der Komplett-Beschreibung relativ gross
 * ist (ca. 7 MB), verwenden wir hier nicht einen DOM-Parser. Und auch keinen
 * XPath-Ansatz.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (30.07.2014)
 */
public final class SatzXml extends Datensatz {

    private static final Logger log = LoggerFactory.getLogger(SatzXml.class);

    /**
     * Instantiiert einen neuen Satz.
     *
     * @param parser XML-Event-Parser
     * @throws XMLStreamException the XML stream exception
     */
    public SatzXml(final XMLEventReader parser) throws XMLStreamException {
        super(parse(parser));
    }

    private static int parse(final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                try {
                    return parseElement(event.asStartElement(), reader);
                } catch (NotFoundException nfe) {
                    log.debug(nfe.getMessage());
                }
            }
            log.trace("Event {} is ignored.", event);
        }
        throw new XMLStreamException("satzart not find with " + reader);
    }

    private static int parseElement(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        log.trace("Parsing element {}.", element);
        QName name = element.getName();
        if (name.getLocalPart().equals("feldreferenz")) {
            Properties props = parseFeldreferenz(name, reader);
            log.debug("Element <feldreferenz> consists of {}.", props);
            if (props.getProperty("name", "").equals("Satzart")) {
                return Integer.parseInt(props.getProperty("auspraegung", "-1"));
            }
        }
        throw new NotFoundException("<name>Satzart</name> not found in " + element);
    }

    private static Properties parseFeldreferenz(final QName name, final XMLEventReader reader)
            throws XMLStreamException {
        log.trace("Parsing element <{}>...<{}/>.", name, name);
        Properties props = new Properties();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                addAsProperty(event.asStartElement(), reader, props);
            } else if (isEndElement(name, event)) {
                log.trace("End of {} recognized.", event);
                return props;
            }
        }
        throw new XMLStreamException("end element of <" + name + "> not read");
    }

    private static void addAsProperty(final StartElement element, final XMLEventReader reader, final Properties props)
            throws XMLStreamException {
        QName name = element.getName();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.CHARACTERS:
                    Characters value = event.asCharacters();
                    props.setProperty(name.getLocalPart(), value.toString());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    return;
                default:
                    log.trace("Event {} is ignored.", event);
                    break;
            }
        }
        throw new XMLStreamException("end element of <" + name + "> not read");
    }

    private static boolean isEndElement(final QName name, final XMLEvent event) {
        return event.isEndElement() && event.asEndElement().getName().equals(name);
    }

}
