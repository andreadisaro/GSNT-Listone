package it.giochisulnostrotavolo.listone.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VOTE")
public class Vote implements java.io.Serializable {

  private static final long serialVersionUID = 1241251254267658568L;

  public Vote() {
    this.id = new VotePK();
  }

  @EmbeddedId
  private VotePK id;

  public VotePK getId() {
    return id;
  }

  public void setId(VotePK id) {
    this.id = id;
  }

  @Embeddable
  public static class VotePK implements java.io.Serializable {

    private static final long serialVersionUID = 2141251251451358568L;

    public VotePK() {
      this.date = new Date();
    }

    // Default constructor
    public VotePK(Long idItem, Date date) {
      this.idItem = idItem;
      this.date = date;
    }

    @Column
    private Long idItem;

    @Column
    private Date date;

    public Long getIdItem() {
      return idItem;
    }

    public void setIdItem(Long idItem) {
      this.idItem = idItem;
    }

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final VotePK other = (VotePK) obj;
      if (this.idItem != other.idItem && (this.idItem == null || !this.idItem.equals(other.idItem))) {
        return false;
      }
      if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 97 * hash + (this.idItem != null ? this.idItem.hashCode() : 0);
      hash = 97 * hash + (this.date != null ? this.date.hashCode() : 0);
      return hash;
    }
  }
}
