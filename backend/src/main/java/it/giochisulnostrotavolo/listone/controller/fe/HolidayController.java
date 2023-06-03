// package it.giochisulnostrotavolo.listone.controller.fe;

// import javax.validation.Valid;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.ExampleMatcher;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.Holidays;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.service.HolidayService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/holidays")
// public class HolidayController {

// 	private static final Logger LOG = LoggerFactory.getLogger(HolidayController.class);

// 	@Autowired
// 	private HolidayService holidayService;

    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody Holidays exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<Holidays> example = Example.of(exampleObj, matcher);
// 		return holidayService.findAll(holidayService.getSpecFromDatesAndExample(exampleObj.getDateFrom(), exampleObj.getDateTo(), example), pageRequest);
//     }
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody Holidays holidays) {
// 		try {
// 			holidayService.save(holidays);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(holidays.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			holidayService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// }
