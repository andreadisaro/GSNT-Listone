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
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.CatStyle;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.CategoriesStyleService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/categoriesStyle")
// public class CategoriesStyleController {

// 	private static final Logger LOG = LoggerFactory.getLogger(CategoriesStyleController.class);

// 	@Autowired
// 	private CategoriesStyleService categoriesStyleService;

// 	@GetMapping(path = "/{id}")
// 	public CatStyle get(@PathVariable Long id) {

// 		return categoriesStyleService.findById(id).map(cat -> {
// 			return cat;
// 		}).orElseThrow(() -> new PortalException("The cat with the id " + id + " couldn't be found in the database."));
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody CatStyle exampleObj, @RequestParam int page, @RequestParam int size,
// 			@RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<CatStyle> example = Example.of(exampleObj, matcher);
// 		return categoriesStyleService.findAll(example, pageRequest);
// 	}

// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody CatStyle categoriesStyle, @PathVariable String id) {
// 		try {
// 			categoriesStyleService.update(categoriesStyle);
// 			if (categoriesStyle.getDefaultStyle()) {
// 				categoriesStyleService.setDefaultForCategory(categoriesStyle);
// 			}
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(categoriesStyle.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody CatStyle categoriesStyle) {
// 		try {
// 			categoriesStyleService.save(categoriesStyle);
// 			if (categoriesStyle.getDefaultStyle()) {
// 				categoriesStyleService.setDefaultForCategory(categoriesStyle);
// 			}
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(categoriesStyle.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// }
