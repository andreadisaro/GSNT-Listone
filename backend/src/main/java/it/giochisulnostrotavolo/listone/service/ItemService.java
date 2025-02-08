package it.giochisulnostrotavolo.listone.service;

import it.giochisulnostrotavolo.listone.entities.Item;
import java.util.List;

public interface ItemService {
  public List<Item> findAll();

  public Item findById(Long id);
}
