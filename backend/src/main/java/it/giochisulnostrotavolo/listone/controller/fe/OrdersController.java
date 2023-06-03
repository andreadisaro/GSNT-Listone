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

// import it.giochisulnostrotavolo.listone.entities.Orders;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.DefinitionService;
// import it.giochisulnostrotavolo.listone.service.OrderService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/order")
// public class OrdersController {

// 	private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);

// 	@Autowired
// 	private OrderService orderService;
	
// 	@Autowired
// 	private DefinitionService definitionService;
	

//     @GetMapping(path = "/{id}")
//     public Orders get(@PathVariable Long id) {

//         return orderService.findById(id).map(orders -> {
//             return orders;
//         }).orElseThrow(() -> new PortalException("The order with the id " + id + " couldn't be found in the database."));
//     }
    
//     @GetMapping(path = "/getListByUser/{username}")
//     public List<Orders> getListByUser(@PathVariable String username) {
//         return orderService.findByUsernameOrderByDeliveryTimeDesc(username);
//     }
    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody Orders exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//     			  .withIgnoreCase()
//     			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<Orders> example = Example.of(exampleObj, matcher);
//         return orderService.findAll(example, pageRequest);
//     }
    
//     @PostMapping(path = "/allList")
//     public List<Orders> getAllPaged(@Valid @RequestBody Orders exampleObj) {
//     	ExampleMatcher matcher = ExampleMatcher.matching()     
//   			  .withIgnoreCase()
//   			  .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);                          

// 		Example<Orders> example = Example.of(exampleObj, matcher);
// 		return orderService.findAll(example);
//     }
	
// 	@PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody Orders orders, @PathVariable String id) {
// 		try {
// 			if(orders.getStatus().equals(definitionService.findByCode("ORD_COM").get())) orderService.save(orders);
// 			else orderService.updateOrderAndSendEmail(orders);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(orders.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Generic Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public Orders create(@Valid @RequestBody Orders orders) {
// 		try {	
// 			return orderService.create(orders);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(orders.toString()), e);
// 			return null;
// 		}
// 	}
	
// 	@PutMapping(path = "/changeStatus/{id}/{status}")
// 	public ResponseEntity<?> changeStatus( @PathVariable Long id, @PathVariable String status) {
// 		try {	
// 			Orders order = orderService.findById(id).map(ord -> {
// 	            return ord;
// 	        }).orElseThrow(() -> new PortalException("The order with the id " + id + " couldn't be found in the database."));
// 			 order.setStatus(definitionService.findByCode(status).get());
// 			orderService.updateOrderAndSendEmail( order);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile cambiare l'oggetto", e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			orderService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
	
// 	@GetMapping(path = "/getAvailabilityDelivery/{idRest}/{month}/{year}")
// 	public List<Map<String, Object>> getAvailabilityDelivery(@PathVariable Long idRest, @PathVariable Integer month, @PathVariable Integer year) {
// 		try {
// 			return orderService.getAvailabilityDelivery(idRest, month, year);
// 		} catch (Exception e) {
// 			LOG.error("error getAvailabilityDelivery", e);
// 			return null;
// 		}
// 	}
	
// }
