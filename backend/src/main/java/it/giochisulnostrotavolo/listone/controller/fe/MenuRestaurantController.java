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

// import it.giochisulnostrotavolo.listone.entities.Categories;
// import it.giochisulnostrotavolo.listone.entities.MenuRestaurant;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.MenuRestaurantService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/menuRestaurant")
// public class MenuRestaurantController {

// 	private static final Logger LOG = LoggerFactory.getLogger(MenuRestaurantController.class);

// 	@Autowired
// 	private MenuRestaurantService menuRestaurantService;

//     @GetMapping(path = "/{id}")
//     public MenuRestaurant get(@PathVariable Long id) {

//         return menuRestaurantService.findById(id).map(menu -> {
//             return menu;
//         }).orElseThrow(() -> new PortalException("The menu with the id " + id + " couldn't be found in the database."));
//     }

//     @GetMapping(path = "/mainCatMenu/{id}")
//     public List<Categories> getAllMainCatForMenu(@PathVariable Long id) {
    	
//          return menuRestaurantService.getAllMainCatForMenu(id);
//     }
    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody MenuRestaurant exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<MenuRestaurant> example = Example.of(exampleObj, matcher);
//         return menuRestaurantService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody MenuRestaurant menuRestaurant, @PathVariable String id) {
// 		try {								
// 			menuRestaurantService.update(menuRestaurant);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(menuRestaurant.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody MenuRestaurant menuRestaurant) {
// 		try {
// 			if(menuRestaurantService.existByIdRestaurant_idRest(menuRestaurant.getIdRestaurant().getIdRest())) return ResponseEntity.badRequest().body(new MessageResponse("Error! questo ristorante ha già un menu collegato")); 
// 			menuRestaurantService.save(menuRestaurant);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(menuRestaurant.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			menuRestaurantService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// }
