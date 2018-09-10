package pl.tp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.Id;

@Entity
public class Event {

    public Event() {
	}
	
	@Id
    private String id;
    
    public String getId() {
        return id;
    }

	public void setId(String id) {
        this.id = id; 
    }

	@Column
    private Long duration;
    
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

	@Column
    private String type;
    
    public String getType() {
        return type;
    }

	public void setType(String type) {
        this.type = type;
    }

	@Column
    private String host;
    
    public String getHost() {
        return host;
    }
	
	public void setHost(String host) {
       this.host = host;
    }


	@Column
    private Boolean alert;
    
    public Boolean isAlert() {
        return alert;
    }
	
	public void setAlert(Boolean alert) {
        this.alert = alert;
    }
	
	@Transient
	private Long started;
	
	public Long getStarted() {
        return started;
    }

	public void setStarted(Long started) {
        this.started=started;
    }
		
	@Transient
	private Long finished;
	
	public Long getFinished() {
        return finished;
    }
	
	public void setFinished(Long finished) {
        this.finished=finished;
    }
	

    public String toString() {
    	return "Event {id:"+getId()+", duration:"+getDuration()+", type:"+getType()+", host:"+getHost()+", alert:"+isAlert()+"}";
    }
}