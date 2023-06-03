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
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.RestaurantInfo;
// import it.giochisulnostrotavolo.listone.entities.RestaurantOpeningHours;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.RestaurantInfoService;
// import it.giochisulnostrotavolo.listone.service.RestaurantOpeningHoursService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/restaurantOpeningHours")
// public class RestaurantOpeningHoursController {

// 	private static final Logger LOG = LoggerFactory.getLogger(RestaurantOpeningHoursController.class);

// 	@Autowired
// 	private RestaurantOpeningHoursService restaurantOpeningHoursService;
	
// 	@Autowired
// 	private RestaurantInfoService restaurantInfoService;

//     @GetMapping(path = "/{id}")
//     public RestaurantOpeningHours get(@PathVariable Long id) {

//         return restaurantOpeningHoursService.findById(id).map(restaurantOpeningHours -> {
//             return restaurantOpeningHours;
//         }).orElseThrow(() -> new PortalException("The restaurantOpeningHours with the id " + id + " couldn't be found in the database."));
//     }

    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody RestaurantOpeningHours exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<RestaurantOpeningHours> example = Example.of(exampleObj, matcher);
//         return restaurantOpeningHoursService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody RestaurantOpeningHours restaurantOpeningHours, @PathVariable String id) {
// 		try {								
// 			restaurantOpeningHoursService.update(restaurantOpeningHours);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantOpeningHours.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody RestaurantOpeningHours restaurantOpeningHours) {
// 		try {
// 			restaurantOpeningHoursService.save(restaurantOpeningHours);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantOpeningHours.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@PutMapping(path = "/createWeek/{restaurantName}")
// 	public ResponseEntity<?> createWeek(@Valid @PathVariable String restaurantName) {
// 		try {
// 			RestaurantInfo restaurantInfo=restaurantInfoService.findByName(restaurantName).get();
// 			restaurantOpeningHoursService.createWeek(restaurantInfo.getIdRest());
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare gli orari per il ristorante : ".concat(restaurantName), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			restaurantOpeningHoursService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// }
