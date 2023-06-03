package it.giochisulnostrotavolo.listone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EDITOR")
public class Editor implements java.io.Serializable {

  private static final long serialVersionUID = -5931063371445636960L;

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private String position;

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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
