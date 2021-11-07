package com.epam.mvc.dto;

import com.epam.mvc.dao.EventEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(namespace = "net.javaguides.javaxmlparser.jaxb")
public class EventsInit {

	private List <Event> eventList;

	@XmlElementWrapper(name = "eventList")
	@XmlElement(name = "event")
	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	private static final String EVENTS_XML = "mvc/src/main/resources/events-init.xml";

	public static List<EventEntity> convertXMLToEvents() {
		try {
			JAXBContext context = JAXBContext.newInstance(EventsInit.class);
			Unmarshaller un = context.createUnmarshaller();
			EventsInit eventsInit = (EventsInit) un.unmarshal(new File(EVENTS_XML));
			List<Event> events = eventsInit.getEventList();
			List<EventEntity> eventEntities = new ArrayList<>();
			for (Event event: events) {
				EventEntity eventEntity = event.toEntity();
				eventEntities.add(eventEntity);
			}
			return eventEntities;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}