// package it.giochisulnostrotavolo.listone.controller.fe;

// import java.util.LinkedHashMap;
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
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.Label;
// import it.giochisulnostrotavolo.listone.entities.LabelLang;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.LabelService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/label")
// public class LabelController {

// 	private static final Logger LOG = LoggerFactory.getLogger(LabelController.class);

// 	@Autowired
// 	private LabelService labelService;
	
	
//     @GetMapping(path = "/{code}")
//     public Label get(@PathVariable String code) {
//         return labelService.findByCode(code).map(label -> {
//             return label;
//         }).orElseThrow(() -> new PortalException("The label with the id " + code + " couldn't be found in the database."));
//     }
    
//     @GetMapping(path = "/existsByCode/{code}")
//     public Boolean existsByCode(@PathVariable String code) {
//         return labelService.existsByCode(code);
//     }
    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody Label exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<Label> example = Example.of(exampleObj, matcher);
//         return labelService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping
// 	public ResponseEntity<?> update(@Valid @RequestBody Label label) {
// 		try {								
// 			labelService.update(label);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(label.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody Label label) {
// 		try {
// 			if(!labelService.existsByCode(label.getCode())) {
// 				labelService.save(label);
// 			}else {
// 				return ResponseEntity.badRequest().body(new MessageResponse("Error: The label already exists!"));
// 			}
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(label.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{code}")
// 	public ResponseEntity<?> delete(@PathVariable String code) {
// 		try {
// 			labelService.deleteByCode(code);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(code), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// 	@PostMapping(path = "/translate")
// 	public ResponseEntity<?> translate(@Valid @RequestBody Map<String, LabelLang> label) {
// 		try {
// 			labelService.saveTranslate(label);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(label.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@GetMapping(path = "/translate/{code}")
// 	public ResponseEntity<?> getTranslate(@PathVariable String code) {
// 		Map<String, LabelLang> response = new LinkedHashMap<>();
// 		try {
// 			response = labelService.findTranslateByCode(code);			
// 		} catch (Exception e) {
// 			LOG.error("Impossibile trovare l'oggetto con id: ".concat(code), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(response);
// 	}
	
// }
