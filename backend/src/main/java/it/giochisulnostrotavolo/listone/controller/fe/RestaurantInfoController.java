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

// import it.giochisulnostrotavolo.listone.entities.RestaurantInfo;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.LabelService;
// import it.giochisulnostrotavolo.listone.service.RestaurantInfoService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/restaurantInfo")
// public class RestaurantInfoController {

// 	private static final Logger LOG = LoggerFactory.getLogger(RestaurantInfoController.class);

// 	@Autowired
// 	private RestaurantInfoService restaurantInfoService;
	
// 	@Autowired
// 	private LabelService labelServie;

//     @GetMapping(path = "/{id}")
//     public RestaurantInfo get(@PathVariable Long id) {

//         return restaurantInfoService.findById(id).map(restaurantInfo -> {
//             return restaurantInfo;
//         }).orElseThrow(() -> new PortalException("The restaurantInfo with the id " + id + " couldn't be found in the database."));
//     }

    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody RestaurantInfo exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<RestaurantInfo> example = Example.of(exampleObj, matcher);
//         return restaurantInfoService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody RestaurantInfo restaurantInfo, @PathVariable String id) {
// 		try {								
// 			restaurantInfoService.update(restaurantInfo);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantInfo.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody RestaurantInfo restaurantInfo) {
// 		try {
// 			restaurantInfoService.save(restaurantInfo);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantInfo.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			RestaurantInfo restInfo = restaurantInfoService.findById(id).map(restaurantInfo -> {
// 	            return restaurantInfo;
// 	        }).orElseThrow(() -> new PortalException("The restaurantInfo with the id " + id + " couldn't be found in the database."));
			
// 			if(labelServie.existsByCode(restInfo.getDescription())) labelServie.deleteByCode(restInfo.getDescription());
			
// 			restaurantInfoService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
//     @GetMapping(path = "/getAll")
//     public List<RestaurantInfo> getAll() {
//     	return restaurantInfoService.findAll();
//     }
	
// }
