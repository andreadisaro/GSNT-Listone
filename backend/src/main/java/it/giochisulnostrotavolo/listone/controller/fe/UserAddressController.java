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

// import it.giochisulnostrotavolo.listone.entities.UserAddress;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.UserAddressService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/userAddress")
// public class UserAddressController {

// 	private static final Logger LOG = LoggerFactory.getLogger(UserAddressController.class);

// 	@Autowired
// 	private UserAddressService userAddressService;

//     @GetMapping(path = "/{id}")
//     public UserAddress get(@PathVariable Long id) {

//         return userAddressService.findById(id).map(userAdd -> {
//             return userAdd;
//         }).orElseThrow(() -> new PortalException("The userAdd with the id " + id + " couldn't be found in the database."));
//     }
    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody UserAddress exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<UserAddress> example = Example.of(exampleObj, matcher);
//         return userAddressService.findAll(example, pageRequest);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody UserAddress userAddress, @PathVariable String id) {
// 		try {								
// 			userAddressService.update(userAddress);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(userAddress.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public UserAddress create(@Valid @RequestBody UserAddress userAddress) {
// 		try {
// 			return userAddressService.save(userAddress);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(userAddress.toString()), e);
// 			return null;
// 		}
// 	}
	
// 	@PostMapping(path = "/create")
// 	public List<UserAddress> createList(@Valid @RequestBody List<UserAddress> userAddressList) {
// 		try {
// 			return userAddressService.create(userAddressList);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare i oggetti: ", e);
// 			return null;
// 		}
// 	}
	
// }
