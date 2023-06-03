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

// import com.fasterxml.jackson.databind.ObjectMapper;

// import it.giochisulnostrotavolo.listone.entities.RestaurantImages;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.RestaurantImagesService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/restaurantImages")
// public class RestaurantImagesController {

// 	private static final Logger LOG = LoggerFactory.getLogger(RestaurantImagesController.class);

// 	@Autowired
// 	private RestaurantImagesService restaurantImagesService;

// 	@GetMapping(path = "/{id}")
// 	public RestaurantImages get(@PathVariable Long id) {

// 		return restaurantImagesService.findById(id).map(restaurantImages -> {
// 			return restaurantImages;
// 		}).orElseThrow(() -> new PortalException(
// 				"The restaurantImages with the id " + id + " couldn't be found in the database."));
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody RestaurantImages exampleObj, @RequestParam int page,
// 			@RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<RestaurantImages> example = Example.of(exampleObj, matcher);
// 		return restaurantImagesService.findAll(example, pageRequest);
// 	}

// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody RestaurantImages restaurantImages, @PathVariable String id) {
// 		try {
// 			restaurantImagesService.update(restaurantImages);
// 			if(restaurantImages.getDefaultImg()) restaurantImagesService.setDefaultForImage(restaurantImages.getIdImg() , restaurantImages.getIdRest().getIdRest());
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restaurantImages.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@RequestParam("file") MultipartFile file,
// 			@RequestParam("restImage") String restImageJson) {
// 		try {
// 			ObjectMapper mapper = new ObjectMapper();
// 			RestaurantImages restImage = mapper.readValue(restImageJson, RestaurantImages.class);
// 			restaurantImagesService.create(restImage, file);
// 			if(restImage.getDefaultImg()) restaurantImagesService.setDefaultForImage(restImage.getIdImg() , restImage.getIdRest().getIdRest());
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(restImageJson.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			restaurantImagesService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}

// }
