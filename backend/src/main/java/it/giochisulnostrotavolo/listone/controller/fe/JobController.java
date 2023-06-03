// package it.giochisulnostrotavolo.listone.controller.fe;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.service.ToolsService;

// @RestController()
// @RequestMapping("/job")
// public class JobController {
	
// 	@Autowired
// 	private ToolsService toolService;
	
// 	@GetMapping("/checkOrders")
// 	public ResponseEntity<?> jobCheckOrders() {
// 		toolService.checkOrders();		
// 		return ResponseEntity.ok("ok");
// 	}
	
// 	@GetMapping("/checkReservations")
// 	public ResponseEntity<?> jobCheckReservations() {
// 		toolService.checkReservations();		
// 		return ResponseEntity.ok("ok");
// 	}
// }
