package it.giochisulnostrotavolo.listone.controller;

import it.giochisulnostrotavolo.listone.entities.Item;
import it.giochisulnostrotavolo.listone.service.ItemService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/public/api")
public class ApiController {

  private static final Logger LOG = LoggerFactory.getLogger(
    ApiController.class
  );

  @Autowired
  private ItemService itemService;

  @GetMapping("heartBeat")
  public ResponseEntity<String> heartBeat() {
    return ResponseEntity.ok().body("OK");
  }

  @GetMapping("items")
  public List<Item> items() {
    return itemService.findAll();
  }
}
