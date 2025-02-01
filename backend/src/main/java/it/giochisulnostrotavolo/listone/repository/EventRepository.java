package it.giochisulnostrotavolo.listone.repository;

import it.giochisulnostrotavolo.listone.entities.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface EventRepository extends JpaRepository<Event, String> {
  public @NonNull List<Event> findAll();
}
