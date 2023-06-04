package it.giochisulnostrotavolo.listone.serviceImpl;

import it.giochisulnostrotavolo.listone.entities.Event;
import it.giochisulnostrotavolo.listone.repository.EventRepository;
import it.giochisulnostrotavolo.listone.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository repo;

  public Event getEvent() {
    return repo.findAll().get(0);
  }
}
