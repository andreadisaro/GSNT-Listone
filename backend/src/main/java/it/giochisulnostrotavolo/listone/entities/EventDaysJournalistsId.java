package it.giochisulnostrotavolo.listone.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EventDaysJournalistsId implements java.io.Serializable {

  private static final long serialVersionUID = 5931061279645456360L;

  @ManyToOne
  @JoinColumn(name = "id_day")
  private EventDays eventDay;

  @ManyToOne
  @JoinColumn(name = "id_journalist")
  private Journalists journalist;

  public EventDays getEventDay() {
    return eventDay;
  }

  public void setId(EventDays eventDays) {
    this.eventDay = eventDays;
  }

  public Journalists getJournalist() {
    return journalist;
  }

  public void setJournalist(Journalists journalist) {
    this.journalist = journalist;
  }

  public EventDaysJournalistsId() {}

  public EventDaysJournalistsId(EventDays eventDay, Journalists journalist) {
    this.eventDay = eventDay;
    this.journalist = journalist;
  }
}
