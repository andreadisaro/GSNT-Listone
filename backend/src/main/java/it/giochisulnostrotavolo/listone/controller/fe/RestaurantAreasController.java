// package it.giochisulnostrotavolo.listone.controller.fe;

// import java.util.List;

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
// import org.springframework.web.multipart.MultipartFile;

// import it.giochisulnostrotavolo.listone.entities.RestaurantAreas;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.LabelService;
// import it.giochisulnostrotavolo.listone.service.ReservationService;
// import it.giochisulnostrotavolo.listone.service.RestaurantAreasService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/restaurantAreas")
// public class RestaurantAreasController {

// 	private static final Logger LOG = LoggerFactory.getLogger(RestaurantAreasController.class);

// 	@Autowired
// 	private RestaurantAreasService restaurantAreasService;
	
// 	@Autowired
// 	private LabelService labelServie;
	
// 	@Autowired
// 	private ReservationService reservationService;

//     @GetMapping(path = "/{id}")
//     public RestaurantAreas get(@PathVariable Long id) {

//         return restaurantAreasService.findById(id).map(restaurantAreas -> {
//             return restaurantAreas;
//         }).orElseThrow(() -> new PortalException("The restaurantAreas with the id " + id + " couldn't be found in the database."));
//     }
    
//     @GetMapping(path = "/all")
//     public List<RestaurantAreas> getAll() {
//         return restaurantAreasService.findAll();
//     }

    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody RestaurantAreas exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<RestaurantAreas> example = Example.of(exampleObj, matcher);
//         return restaurantAreasService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody RestaurantAreas restaurantAreas, @PathVariable String id) {
// 		try {								
// 			restaurantAreasService.update(restaurantAreas);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantAreas.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody RestaurantAreas restaurantAreas) {
// 		try {
// 			restaurantAreasService.save(restaurantAreas);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantAreas.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			RestaurantAreas restArea = restaurantAreasService.findById(id).map(restaurantAreas -> {
// 	            return restaurantAreas;
// 	        }).orElseThrow(() -> new PortalException("The restaurantAreas with the id " + id + " couldn't be found in the database."));
			
// 			if(labelServie.existsByCode(restArea.getAreaDescription())) labelServie.deleteByCode(restArea.getAreaDescription());
// 			if(reservationService.existsByIdArea_idArea(restArea.getIdArea())) return ResponseEntity.badRequest().body(new MessageResponse("Error! Questa area è collegata ad una prenotazione:"));
			
// 			restaurantAreasService.deleteById(id);
// 		} catch (Exception e) {
			
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// 	@PostMapping(path = "/updateImage/{id}") 
// 	public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @PathVariable Long id) {
// 		try {
// 			RestaurantAreas restArea = restaurantAreasService.findById(id).map(restaurantAreas -> {
// 	            return restaurantAreas;
// 	        }).orElseThrow(() -> new PortalException("The restaurantAreas with the id " + id + " couldn't be found in the database."));
			
// 			restaurantAreasService.updateImage(file, name, restArea);
// 		} catch (Exception e) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
	
// }
