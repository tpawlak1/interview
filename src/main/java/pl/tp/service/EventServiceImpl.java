package pl.tp.service;

import pl.tp.model.Event;
import pl.tp.model.LogEntry;


public class EventServiceImpl implements EventService {
	final static Long ALERT_THRESHOLD = new Long(4);

	public Event create (LogEntry logEntry){
		Event event = new Event();
		event.setId(logEntry.id);
		event.setType(logEntry.type);
		event.setHost(logEntry.host);
		handleTime(event, logEntry);
		return event;		
	}

	public void handleTime (Event event, LogEntry logEntry) {
		if (logEntry.state.equals("STARTED"))	
			event.setStarted(logEntry.timestamp);
		if (logEntry.state.equals("FINISHED"))	
			event.setFinished(logEntry.timestamp);
		if (event.getStarted()!=null && event.getFinished()!=null) {
			event.setDuration (event.getFinished() - event.getStarted());
			if (event.getDuration()>ALERT_THRESHOLD)
				event.setAlert(Boolean.TRUE);
		}
	}
}