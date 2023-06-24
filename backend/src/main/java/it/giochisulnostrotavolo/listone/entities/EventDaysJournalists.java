package it.giochisulnostrotavolo.listone.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EVENT_DAYS_JOURNALISTS")
public class EventDaysJournalists implements java.io.Serializable {

  private static final long serialVersionUID = -5931061279645456360L;

  @EmbeddedId
  private EventDaysJournalistsId id;

  @Column
  private Boolean presence;

  @Transient
  private String day;

  @Transient
  private String journalist;

  public EventDaysJournalistsId getId() {
    return id;
  }

  public void setId(EventDaysJournalistsId id) {
    this.id = id;
    this.day = id.getEventDay().getName();
    this.journalist = id.getJournalist().getName();
  }

  public Boolean getPresence() {
    return presence;
  }

  public void setPresence(Boolean presence) {
    this.presence = presence;
  }

  public String getDay() {
    return id.getEventDay().getName();
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getJournalist() {
    return id.getJournalist().getName();
  }

  public void setJournalist(String journalist) {
    this.journalist = journalist;
  }
}
