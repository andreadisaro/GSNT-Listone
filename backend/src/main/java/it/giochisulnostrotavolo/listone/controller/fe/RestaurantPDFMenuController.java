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
// import org.springframework.web.multipart.MultipartFile;

// import it.giochisulnostrotavolo.listone.entities.RestaurantPDFMenu;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.RestaurantPDFMenuService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/restaurantPDFMenu")
// public class RestaurantPDFMenuController {

// 	private static final Logger LOG = LoggerFactory.getLogger(RestaurantPDFMenuController.class);

// 	@Autowired
// 	private RestaurantPDFMenuService restaurantPDFMenuService;

//     @GetMapping(path = "/{id}")
//     public RestaurantPDFMenu get(@PathVariable Long id) {

//         return restaurantPDFMenuService.findById(id).map(restaurantPDFMenu -> {
//             return restaurantPDFMenu;
//         }).orElseThrow(() -> new PortalException("The restaurantPDFMenu with the id " + id + " couldn't be found in the database."));
//     }

    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody RestaurantPDFMenu exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<RestaurantPDFMenu> example = Example.of(exampleObj, matcher);
//         return restaurantPDFMenuService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody RestaurantPDFMenu restaurantPDFMenu, @PathVariable String id) {
// 		try {								
// 			restaurantPDFMenuService.update(restaurantPDFMenu);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantPDFMenu.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			restaurantPDFMenuService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// 	@PostMapping(path = "/updatePdf/{idRest}")
// 	public ResponseEntity<?> updatePdf(@PathVariable Long idRest, @RequestParam("file") MultipartFile file, @RequestParam("langCode") String langCode) {
// 		try {
// 			restaurantPDFMenuService.updatePDF(file, langCode, idRest);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'immagine: ".concat(file.getName()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// }
