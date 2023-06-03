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

// import it.giochisulnostrotavolo.listone.entities.GroupDefinitions;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.GroupDefinitionService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/groupDefinition")
// public class GroupDefinitionController {

// 	private static final Logger LOG = LoggerFactory.getLogger(GroupDefinitionController.class);

// 	@Autowired
// 	private GroupDefinitionService groupDefinitionService;

// 	@GetMapping(path = "/{code}")
// 	public ResponseEntity<GroupDefinitions> get(@PathVariable String code) {
// 		GroupDefinitions groupeDefinition = groupDefinitionService.findByCode(code).map(groupDef -> {
// 			return groupDef;
// 		}).orElseThrow(
// 				() -> new PortalException("The groupDef with the id " + code + " couldn't be found in the database."));
// 		return ResponseEntity.ok(groupeDefinition);
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody GroupDefinitions exampleObj, @RequestParam int page, @RequestParam int size,
// 			@RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<GroupDefinitions> example = Example.of(exampleObj, matcher);
// 		return groupDefinitionService.findAll(example, pageRequest);
// 	}

// 	@PutMapping
// 	public ResponseEntity<?> update(@Valid @RequestBody GroupDefinitions groupDefinition) {
// 		try {
// 			groupDefinitionService.update(groupDefinition);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(groupDefinition.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody GroupDefinitions groupDefinition) {
// 		try {
// 			if (!groupDefinitionService.existsByCode(groupDefinition.getCode())) {
// 				groupDefinitionService.save(groupDefinition);
// 			} else {
// 				return ResponseEntity.badRequest().body(new MessageResponse("Error: The label already exists!"));
// 			}
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(groupDefinition.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@DeleteMapping(path = "/{code}")
// 	public ResponseEntity<?> delete(@PathVariable String code) {
// 		try {
// 			groupDefinitionService.deleteByCode(code);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(code), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	

// }
