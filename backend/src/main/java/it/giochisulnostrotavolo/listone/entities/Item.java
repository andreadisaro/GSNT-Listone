package it.giochisulnostrotavolo.listone.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "ITEM")
public class Item implements java.io.Serializable {

  private static final long serialVersionUID = 398992325640164380L;

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private Boolean isAvailable;

  @Column
  private Boolean isPlayable;

  @Column
  private Boolean isBuyable;

  @Column
  private Date updateDate;

  @Column
  @Nullable
  private String bookType;

  @Column
  @Nullable
  private String notBuyableType;

  @ManyToOne
  @JoinColumn(name = "id_editor")
  private Editor editor;

  @ManyToOne
  @JoinColumn(name = "id_category")
  private Category category;

  @Column
  private Long idBgg;

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

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public Boolean getIsPlayable() {
    return isPlayable;
  }

  public void setIsPlayable(Boolean isPlayable) {
    this.isPlayable = isPlayable;
  }

  public Boolean getIsBuyable() {
    return isBuyable;
  }

  public void setIsBuyable(Boolean isBuyable) {
    this.isBuyable = isBuyable;
  }

  public String getBookType() {
    return bookType;
  }

  public void setBookType(String bookType) {
    this.bookType = bookType;
  }

  public String getNotBuyableType() {
    return notBuyableType;
  }

  public void setNotBuyableType(String notBuyableType) {
    this.notBuyableType = notBuyableType;
  }

  public Editor getEditor() {
    return editor;
  }

  public void setEditor(Editor editor) {
    this.editor = editor;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Long getIdBgg() {
    return idBgg;
  }

  public void setIdBgg(Long idBgg) {
    this.idBgg = idBgg;
  }
}
