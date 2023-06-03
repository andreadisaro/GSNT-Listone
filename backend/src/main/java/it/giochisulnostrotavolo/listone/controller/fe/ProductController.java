// package it.giochisulnostrotavolo.listone.controller.fe;

// import java.util.List;
// import java.util.Map;

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

// import it.giochisulnostrotavolo.listone.entities.Product;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.ProductService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/product")
// public class ProductController {

// 	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

// 	@Autowired
// 	private ProductService productService;

// 	@GetMapping(path = "/{id}")
// 	public Product get(@PathVariable String id) {

// 		return productService.findById(id).map(prod -> {
// 			return prod;
// 		}).orElseThrow(
// 				() -> new PortalException("The product with the id " + id + " couldn't be found in the database."));
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody Product exampleObj, @RequestParam int page, @RequestParam int size,
// 			@RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<Product> example = Example.of(exampleObj, matcher);
// 		return productService.findAll(example, pageRequest);
// 	}

// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody Product product, @PathVariable String id) {
// 		try {
// 			productService.update(product);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(product.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody Product product) {
// 		try {
// 			productService.save(product);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(product.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@PostMapping(path = "/updateImage/{id}")
// 	public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file, @PathVariable String id) {
// 		try {
// 			Product product = productService.findById(id).map(categorie -> {
// 				return categorie;
// 			}).orElseThrow(
// 					() -> new PortalException("The product with the id " + id + " couldn't be found in the database."));
// 			productService.updateImage(file, product);
// 		} catch (Exception e) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@GetMapping(path = "/getAll")
// 	public List<Product> getAll() {
// 		return productService.findAll();
// 	}

// 	@PostMapping(path = "/productAnalytics")
// 	public List<Map<String, Object>> productAnalytics(@RequestParam String code) {
// 		return productService.productAnalytics(code);
// 	}
// }
