package it.giochisulnostrotavolo.listone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category implements java.io.Serializable {

  private static final long serialVersionUID = 5931063379645636960L;

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private Integer ordering;

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

  public Integer getOrdering() {
    return ordering;
  }

  public void setPosition(Integer ordering) {
    this.ordering = ordering;
  }
}
