package it.giochisulnostrotavolo.listone.controller;

import it.giochisulnostrotavolo.listone.entities.Event;
import it.giochisulnostrotavolo.listone.entities.EventDaysJournalists;
import it.giochisulnostrotavolo.listone.entities.Item;
import it.giochisulnostrotavolo.listone.entities.Vote;
import it.giochisulnostrotavolo.listone.service.EventDaysJournalistsService;
import it.giochisulnostrotavolo.listone.service.EventService;
import it.giochisulnostrotavolo.listone.service.ItemService;
import it.giochisulnostrotavolo.listone.service.VoteService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/public/api/")
public class ApiController {

  // private static final Logger LOG = LoggerFactory.getLogger(
  // ApiController.class
  // );

  @Autowired
  private ItemService itemService;

  @Autowired
  private EventService eventService;

  @Autowired
  private VoteService voteService;

  @Autowired
  private EventDaysJournalistsService eventDaysJournalistsService;

  @GetMapping("heartBeat")
  public ResponseEntity<String> heartBeat() {
    return ResponseEntity.ok().body("OK");
  }

  @GetMapping("items")
  public List<Item> items() {
    return itemService.findAll();
  }

  @GetMapping("item/{id}")
  public Item item(@PathVariable Long id) {
    return itemService.findById(id);
  }

  @GetMapping("event")
  public Event event() {
    return eventService.getEvent();
  }

  @GetMapping("journalists")
  public List<EventDaysJournalists> journalists() {
    return eventDaysJournalistsService.findAll();
  }

  @PostMapping("vote/{idItem}")
  public Vote vote(@PathVariable Long idItem) {
    Vote vote = new Vote();
    vote.getId().setIdItem(idItem);
    return voteService.save(vote);
  }

  @GetMapping("votes")
  public List<Vote> votes() {
    return voteService.findAll();
  }

}
