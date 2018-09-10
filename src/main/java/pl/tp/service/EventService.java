package pl.tp.service;

import pl.tp.model.Event;
import pl.tp.model.LogEntry;

public interface EventService {
	public Event create (LogEntry logEntry);
	public void handleTime (Event event, LogEntry logEntry);
}