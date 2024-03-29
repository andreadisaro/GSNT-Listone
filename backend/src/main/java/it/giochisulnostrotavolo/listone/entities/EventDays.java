package it.giochisulnostrotavolo.listone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_DAYS")
public class EventDays implements java.io.Serializable {

  private static final long serialVersionUID = -5931061279645636960L;

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
