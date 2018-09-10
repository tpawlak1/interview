package pl.tp.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;

import pl.tp.storage.HibernateUtil;
import pl.tp.service.EventService;
import pl.tp.model.Event;
import pl.tp.model.LogEntry;

public class App {
	public static void main (String[] args) {
		if (args.length<1) {
			System.out.print("File name should be provided");
			System.exit(1);
		} 
		HashMap<String,Event> result = new HashMap<String,Event>();
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
		HibernateUtil util = ctx.getBean(HibernateUtil.class);
        EventService service = ctx.getBean(EventService.class);
		String line;
		Gson g = new Gson();			
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			while ((line = br.readLine()) != null) {
				LogEntry logEntry = g.fromJson(line, LogEntry.class);
				Event event = result.get(logEntry.id);
				if (event == null) {
					event = service.create(logEntry);
					util.save(event);
					result.put(logEntry.id,event);
				}
				else
					service.handleTime(event,logEntry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Results");
		for (String id : result.keySet()) {
			Event event = util.get(Event.class,id);
			System.out.println(event);
		}
		ctx.close();
	}
}