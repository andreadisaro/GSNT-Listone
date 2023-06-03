package it.giochisulnostrotavolo.listone.controller;

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
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/public/api")
public class ApiController {

  private static final Logger LOG = LoggerFactory.getLogger(
    ApiController.class
  );

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("heartBeat")
  public ResponseEntity<String> heartBeat() {
    return ResponseEntity.ok().body("OK");
  }

  @PostMapping("{path}")
  public ResponseEntity<String> postExample(
    @RequestBody Object request,
    @PathVariable("path") String path
  ) {
    LOG.info(
      "\n\nChiamata a ".concat(path)
        .concat("\nInput:\n")
        .concat(request.toString())
    );
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> entity = new HttpEntity<>(request, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(
      "/" + path,
      entity,
      String.class
    );
    LOG.info("\nOutput".concat(response.getBody()));
    return response;
  }
}
