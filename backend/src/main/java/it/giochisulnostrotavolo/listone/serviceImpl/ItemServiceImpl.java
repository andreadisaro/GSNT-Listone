package it.giochisulnostrotavolo.listone.serviceImpl;

import it.giochisulnostrotavolo.listone.entities.Event;
import it.giochisulnostrotavolo.listone.entities.Item;
import it.giochisulnostrotavolo.listone.repository.ItemRepository;
import it.giochisulnostrotavolo.listone.service.ItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
