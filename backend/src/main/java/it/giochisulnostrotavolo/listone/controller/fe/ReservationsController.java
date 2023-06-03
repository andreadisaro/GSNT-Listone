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
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.Reservations;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.DefinitionService;
// import it.giochisulnostrotavolo.listone.service.ReservationService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/reservation")
// public class ReservationsController {

// 	private static final Logger LOG = LoggerFactory.getLogger(ReservationsController.class);

// 	@Autowired
// 	private ReservationService reservationService;
	
// 	@Autowired
// 	private DefinitionService definitionService;

//     @GetMapping(path = "/{id}")
//     public Reservations get(@PathVariable Long id) {
//         return reservationService.findById(id).map(reservation -> {
//             return reservation;
//         }).orElseThrow(() -> new PortalException("The reservation with the id " + id + " couldn't be found in the database."));
//     }
    
//     @GetMapping(path = "/getListByUser/{username}")
//     public List<Reservations> getListByUser(@PathVariable String username) {
//         return reservationService.findByUsernameOrderByRetTimeDesc(username);
//     }
    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody Reservations exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<Reservations> example = Example.of(exampleObj, matcher);
//         return reservationService.findAll(example, pageRequest);
//     }
    
//     @PostMapping(path = "/allList")
//     public List<Reservations> getAllPaged(@Valid @RequestBody Reservations exampleObj) {
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//   			  .withIgnoreCase()
//   			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<Reservations> example = Example.of(exampleObj, matcher);
// 		return reservationService.findAll(example);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody Reservations reservations, @PathVariable Long id) {
// 		try {	
// 			if(reservations.getStatus().equals(definitionService.findByCode("RES_COM").get())) reservationService.save(reservations);
// 			else reservationService.updateReservationAndSendEmail(reservations);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(reservations.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PutMapping(path = "/changeStatus/{id}/{status}")
// 	public ResponseEntity<?> changeStatus( @PathVariable Long id, @PathVariable String status) {
// 		try {	
// 			Reservations reser = reservationService.findById(id).map(reservation -> {
// 	            return reservation;
// 	        }).orElseThrow(() -> new PortalException("The reservation with the id " + id + " couldn't be found in the database."));
// 		//	reser.setStatus(status);
// 			reservationService.updateReservationAndSendEmail(reser);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile cambiare l'oggetto", e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping(path = "/create")
// 	public ResponseEntity<?> save(@Valid @RequestBody Reservations reservations) {
// 		try {
// 			reservationService.create(reservations);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(reservations.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@PostMapping
// 	public Reservations create(@Valid @RequestBody Reservations reservations) {
// 		try {
// 		//	if(reservations.getStatus().equals(Reservations.BOZZA)) reservations.setStatus(Reservations.DA_ACCETTARE);
// 			return reservationService.save(reservations);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(reservations.toString()), e);
// 			return null;
// 		}
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			reservationService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
	
// 	@GetMapping(path = "/getAvailabilityBooking/{idRest}/{month}/{year}")
// 	public List<Map<String, Object>> getAvailabilityBooking(@PathVariable Long idRest, @PathVariable Integer month, @PathVariable Integer year) {
// 		try {
// 			return reservationService.getAvailabilityBooking(idRest, month, year);
// 		} catch (Exception e) {
// 			LOG.error("error getAvailabilityBooking", e);
// 			return null;
// 		}
// 	}

// }
