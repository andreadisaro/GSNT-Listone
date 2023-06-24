package it.giochisulnostrotavolo.listone.serviceImpl;

import it.giochisulnostrotavolo.listone.entities.EventDaysJournalists;
import it.giochisulnostrotavolo.listone.repository.EventDaysJournalistsRepository;
import it.giochisulnostrotavolo.listone.service.EventDaysJournalistsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventDaysJournalistsServiceImpl
  implements EventDaysJournalistsService {

  @Autowired
  private EventDaysJournalistsRepository repo;

  public List<EventDaysJournalists> findAll() {
    return repo.findAll();
  }
}
