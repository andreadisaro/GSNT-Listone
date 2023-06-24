package it.giochisulnostrotavolo.listone.controller;

import it.giochisulnostrotavolo.listone.entities.Event;
import it.giochisulnostrotavolo.listone.entities.EventDaysJournalists;
import it.giochisulnostrotavolo.listone.entities.Item;
import it.giochisulnostrotavolo.listone.service.EventDaysJournalistsService;
import it.giochisulnostrotavolo.listone.service.EventService;
import it.giochisulnostrotavolo.listone.service.ItemService;
import java.util.List;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api")
public class ApiController {

  // private static final Logger LOG = LoggerFactory.getLogger(
  //   ApiController.class
  // );

  @Autowired
  private ItemService itemService;

  @Autowired
  private EventService eventService;

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

  @GetMapping("event")
  public Event event() {
    return eventService.getEvent();
  }

  @GetMapping("journalists")
  public List<EventDaysJournalists> journalists() {
    return eventDaysJournalistsService.findAll();
  }
}
