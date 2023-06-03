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

// import it.giochisulnostrotavolo.listone.entities.GreenPassVaxData;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.GreenPassVaxDataService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/greenPassVaxData")
// public class GreenPassVaxDataController {

// 	private static final Logger LOG = LoggerFactory.getLogger(GreenPassVaxDataController.class);

// 	@Autowired
// 	private GreenPassVaxDataService greenPassVaxDataService;

// 	@GetMapping(path = "/getByCodeAndDoseAndTotal")
// 	public ResponseEntity<GreenPassVaxData> get(@RequestParam String code ,@RequestParam Integer dose ,@RequestParam Integer totalDose) {
// 		GreenPassVaxData greenPassVaxData = greenPassVaxDataService.findByCodeAndDoseAndTotalDosesAndActive(code, dose ,totalDose,  true).map(greenPass -> {
// 			return greenPass;
// 		}).orElseThrow(
// 				() -> new PortalException("The green pass data with the id " + code + " couldn't be found in the database."));
// 		return ResponseEntity.ok(greenPassVaxData);
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<?> getAllPaged(@Valid @RequestBody GreenPassVaxData exampleObj, @RequestParam int page, @RequestParam int size,
// 			@RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<GreenPassVaxData> example = Example.of(exampleObj, matcher);
// 		return greenPassVaxDataService.findAll(example, pageRequest);
// 	}

// 	@PutMapping
// 	public ResponseEntity<?> update(@Valid @RequestBody GreenPassVaxData greenPassVaxData) {
// 		try {
// 			greenPassVaxDataService.update(greenPassVaxData);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(greenPassVaxData.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody GreenPassVaxData greenPassVaxData) {
// 		try {
// 				greenPassVaxDataService.save(greenPassVaxData);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(greenPassVaxData.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			greenPassVaxDataService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	

// }
