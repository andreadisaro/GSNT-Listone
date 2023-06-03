package it.giochisulnostrotavolo.listone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "EVENT")
public class Event implements java.io.Serializable {

  private static final long serialVersionUID = -5931063379645636960L;

  @Id
  @Column
  private String title;

  @Column
  @Nullable
  private String logo;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }
}
