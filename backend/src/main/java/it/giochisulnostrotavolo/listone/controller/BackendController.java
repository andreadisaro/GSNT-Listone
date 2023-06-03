package it.giochisulnostrotavolo.listone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BackendController {

  private static final Logger LOG = LoggerFactory.getLogger(
    BackendController.class
  );

  // Forwards all routes to FrontEnd except: '/', '/index.html', '/auth', '/public/**'
  // Required because of 'mode: history' usage in frontend routing, see README for further details
  @RequestMapping(value = "{_:^(?!index\\.html|auth|public).*$}")
  public String redirectApi() {
    LOG.info(
      "URL entered directly into the Browser, so we need to redirect..."
    );
    return "forward:/";
  }
}
