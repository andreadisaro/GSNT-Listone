package it.giochisulnostrotavolo.listone.repository;

import it.giochisulnostrotavolo.listone.entities.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
  public List<Event> findAll();
}
