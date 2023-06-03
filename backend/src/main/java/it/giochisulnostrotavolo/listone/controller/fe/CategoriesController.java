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
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import it.giochisulnostrotavolo.listone.entities.Categories;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.CategoriesService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/categories")
// public class CategoriesController {

// 	private static final Logger LOG = LoggerFactory.getLogger(CategoriesController.class);

// 	@Autowired
// 	private CategoriesService categoriesService;

// 	@GetMapping(path = "/{id}")
// 	public Categories get(@PathVariable Long id) {

// 		return categoriesService.findById(id).map(categories -> {
// 			return categories;
// 		}).orElseThrow(
// 				() -> new PortalException("The category with the id " + id + " couldn't be found in the database."));
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody Categories exampleObj, @RequestParam int page,
// 			@RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<Categories> example = Example.of(exampleObj, matcher);
// 		return categoriesService.findAll(example, pageRequest);
// 	}

// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody Categories categories, @PathVariable String id) {
// 		try {
// 			categoriesService.update(categories);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(categories.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody Categories categories) {
// 		try {
// 			categoriesService.save(categories);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(categories.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@PostMapping(path = "/updateImage/{id}")
// 	public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
// 		try {
// 			Categories categories = categoriesService.findById(id).map(categorie -> {
// 				return categorie;
// 			}).orElseThrow(() -> new PortalException(
// 					"The category with the id " + id + " couldn't be found in the database."));
// 			categoriesService.updateImage(file, categories);
// 		} catch (Exception e) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@GetMapping(path = "/getAll")
// 	public List<Categories> getAll() {
// 		return categoriesService.findAll();
// 	}

// }
