package it.giochisulnostrotavolo.listone.serviceImpl;

import it.giochisulnostrotavolo.listone.entities.Item;
import it.giochisulnostrotavolo.listone.repository.ItemRepository;
import it.giochisulnostrotavolo.listone.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ItemRepository repo;

  public List<Item> findAll() {
    return repo.findAll();
  }
}
