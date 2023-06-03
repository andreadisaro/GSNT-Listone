// package it.giochisulnostrotavolo.listone.controller.fe;

// import java.util.List;

// import javax.validation.Valid;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
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

// import it.giochisulnostrotavolo.listone.entities.payload.response.Menu;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.service.LabelService;
// import it.giochisulnostrotavolo.listone.service.MenuService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/menu")
// public class MenuController {
	
//     private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);
    
//     @Autowired
//     private MenuService menuService;
    
//     @Autowired
//    	private LabelService labelService;

//     @GetMapping(path = "/menulist")
//     public List<Menu> getMenuList() {
// 		return menuService.getMenuList();
//     }    
    
//     @PostMapping(path = "/allPaged")
//     public Page<?> getAllPaged(@Valid @RequestBody Menu exampleObj, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam boolean sortDesc) {
//     	PageRequest pageRequest;
//     	if (sortDesc) {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy).descending());
//     	} else {
//     		pageRequest =  PageRequest.of(page, size, Sort.by(sortBy));
//     	}

//         return menuService.findAll(exampleObj, pageRequest);
//     }

//     @PutMapping(path = "/{id}")
// 	public ResponseEntity<?> update(@Valid @RequestBody Menu menu, @PathVariable Long id) {
// 		try {
// 			Menu menuObj = menuService.findById(id).orElseThrow(() -> new PortalException("The menu with the id " + id + " couldn't be found in the database."));
// 			if(!menu.getOrdinal().equals(menuObj.getOrdinal())) {
// 				if(menu.getParentMenu() != null && menuService.countByOrdinalWithParentMenu(menu.getOrdinal(), menu.getParentMenu()) > 0)
// 					return ResponseEntity.badRequest().body(new MessageResponse("Error: this ordinal already exists!"));
				
// 				if(menu.getParentMenu() == null && menuService.countByOrdinal(menu.getOrdinal()) > 0 )
// 					return ResponseEntity.badRequest().body(new MessageResponse("Error: this ordinal already exists!"));
// 			}
			
// 			menuService.save(menu);			
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(menu.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody Menu menu) {
// 		try {
// 			if(menu.getParentMenu() != null && menuService.countByOrdinalWithParentMenu(menu.getOrdinal(), menu.getParentMenu()) > 0)
// 				return ResponseEntity.badRequest().body(new MessageResponse("Error: this ordinal already exists!"));
			
// 			if(menu.getParentMenu() == null && menuService.countByOrdinal(menu.getOrdinal()) > 0)
// 				return ResponseEntity.badRequest().body(new MessageResponse("Error: this ordinal already exists!"));
			
// 			menuService.save(menu);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile salvare l'oggetto: ".concat(menu.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}
	
// 	@DeleteMapping(path = "/{id}")
// 	public ResponseEntity<?> delete(@PathVariable Long id) {
// 		try {
// 			Menu menu = menuService.findById(id).orElseThrow(() -> new PortalException("The menu with the id " + id + " couldn't be found in the database."));
// 			if(labelService.existsByCode(menu.getName().concat(" menu"))){
// 				labelService.deleteByCode(menu.getName().concat(" menu"));
// 			}
// 			menuService.deleteById(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile eliminare l'oggetto con id: ".concat(id.toString()), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}
// 		return ResponseEntity.ok(new MessageResponse("Deleted!"));
// 	}
    
// }
