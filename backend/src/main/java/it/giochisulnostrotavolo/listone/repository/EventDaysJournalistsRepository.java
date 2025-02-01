package it.giochisulnostrotavolo.listone.repository;

import it.giochisulnostrotavolo.listone.entities.EventDaysJournalists;
import it.giochisulnostrotavolo.listone.entities.EventDaysJournalistsId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface EventDaysJournalistsRepository
    extends JpaRepository<EventDaysJournalists, EventDaysJournalistsId> {
  public @NonNull List<EventDaysJournalists> findAll();
}
