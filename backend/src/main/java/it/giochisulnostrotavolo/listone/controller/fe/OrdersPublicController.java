// package it.giochisulnostrotavolo.listone.controller.fe;

// import javax.validation.Valid;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.Orders;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.DefinitionService;
// import it.giochisulnostrotavolo.listone.service.OrderService;

// @RestController()
// @RequestMapping("/public/order")
// public class OrdersPublicController {

// 	private static final Logger LOG = LoggerFactory.getLogger(OrdersPublicController.class);

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
    
//     @PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody Orders orders, @PathVariable String id) {
// 		try {
// 			if(orders.getStatus().equals(definitionService.findByCode("ORD_COM").get())) orderService.save(orders);
// 			else orderService.updateOrderAndSendEmail(orders);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(orders.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
// }
