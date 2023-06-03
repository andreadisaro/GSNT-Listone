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

// import it.giochisulnostrotavolo.listone.entities.Definitions;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.DefinitionService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/definition")
// public class DefinitionController {

// 	private static final Logger LOG = LoggerFactory.getLogger(DefinitionController.class);


// 	@Autowired
// 	private DefinitionService definitionService;

// 	@GetMapping(path = "/{id}")
// 	public Definitions get(@PathVariable Long id) {
// 		return definitionService.findById(id).map(definition -> {
// 			return definition;
// 		}).orElseThrow(
// 				() -> new PortalException("The definition with the id " + id + " couldn't be found in the database."));
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody Definitions exampleObj, @RequestParam int page, @RequestParam int size,
// 			@RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<Definitions> example = Example.of(exampleObj, matcher);
// 		return definitionService.findAll(example, pageRequest);
// 	}

// 	@PutMapping
// 	public ResponseEntity<?> update(@Valid @RequestBody Definitions definitions) {
// 		try {
// 			definitionService.save(definitions);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(definitions.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
 
// 	@GetMapping(path = "/getDefinitions/{code}")
// 	public ResponseEntity<?> getDefinitions(@PathVariable String code) {
// 		try {
// 			return ResponseEntity.ok(definitionService.findByCodeGroup(code));
// 		} catch (Exception e) {
// 			LOG.error("Impossibile trovare l'oggetto con id: ".concat(code), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 	}
	
// 	@PostMapping(path = "/{code}")
// 	public ResponseEntity<?> create(@Valid @RequestBody Definitions definitions, @PathVariable String code) {
// 		try {
// 			definitionService.save(definitions);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile trovare l'oggetto con id: ".concat(code), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> deleteDefinition(@PathVariable Long id) {
// 		try {
// 			definitionService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile trovare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}		
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
 

// }
