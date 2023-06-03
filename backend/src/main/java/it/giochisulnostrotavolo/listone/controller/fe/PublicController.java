// package it.giochisulnostrotavolo.listone.controller.fe;

// import java.security.NoSuchAlgorithmException;
// import java.sql.Timestamp;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import javax.mail.MessagingException;
// import javax.servlet.http.HttpServletResponse;
// import javax.validation.Valid;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.Constant;
// import it.giochisulnostrotavolo.listone.entities.Definitions;
// import it.giochisulnostrotavolo.listone.entities.Language;
// import it.giochisulnostrotavolo.listone.entities.User;
// import it.giochisulnostrotavolo.listone.entities.payload.request.LoginRequest;
// import it.giochisulnostrotavolo.listone.entities.payload.request.UsersResetPassword;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.PortalException;
// import it.giochisulnostrotavolo.listone.exception.UserNotFoundException;
// import it.giochisulnostrotavolo.listone.repository.UserBanRepository;
// import it.giochisulnostrotavolo.listone.service.ConstantService;
// import it.giochisulnostrotavolo.listone.service.DefinitionService;
// import it.giochisulnostrotavolo.listone.service.GroupDefinitionService;
// import it.giochisulnostrotavolo.listone.service.LabelService;
// import it.giochisulnostrotavolo.listone.service.LanguageService;
// import it.giochisulnostrotavolo.listone.service.PublicService;
// import it.giochisulnostrotavolo.listone.service.RestaurantInfoService;
// import it.giochisulnostrotavolo.listone.service.UserService;

// @RestController()
// @RequestMapping
// public class PublicController {

// 	private static final Logger LOG = LoggerFactory.getLogger(PublicController.class);

// 	@Autowired
// 	private LabelService labelService;

// 	@Autowired
// 	private PublicService publicService;

// 	@Autowired
// 	private LanguageService languageService;

// 	@Autowired
// 	private RestaurantInfoService restaurantInfoService;

// 	@Autowired
// 	private UserBanRepository banRepo;

// 	@Autowired
// 	private GroupDefinitionService groupDefinitionService;

// 	@Autowired
// 	private ConstantService constantService;
	
// 	@Autowired
// 	private UserService userService;
	
// 	@Autowired
// 	private DefinitionService definitionService;	

// 	@GetMapping(path = "/public/fe/langlist")
// 	public List<Language> getAll() {
// 		return languageService.findAllByOrderByCodeAsc();
// 	}

// 	@GetMapping(path = "/public/fe/labels/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
// 	public ResponseEntity<?> getLabels(@PathVariable String lang) {
// 		String response = null;
// 		try {
// 			response = labelService.getJsonTranslateByLang(lang);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile trovare l'oggetto per lingua: ".concat(lang), e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
// 		}

// 		HttpHeaders headers = new HttpHeaders();
// 		headers.add("Content-Type", "application/json; charset=utf-8");
// 		return new ResponseEntity<String>(response, headers, HttpStatus.OK);
// 	}

// 	@GetMapping(path = "/public/fe/restaurantInfo/getAll/{HWNum}")
// 	public ResponseEntity<?> getAllRestaurant(@PathVariable String HWNum) {
// 		if (banRepo.existsById(HWNum)) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("sei stato bannato contatta il ristorante"));
// 		}

// 		return ResponseEntity.ok(restaurantInfoService.findAll());
// 	}

// 	@GetMapping(path = "/public/fe/getImage/{id}")
// 	public byte[] getImage(@PathVariable Long id) {
// 		try {
// 			return publicService.getImage(id);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile visualizzare l'immagine", e);
// 			return null;
// 		}
// 	}

// 	@GetMapping(path = "/public/fe/downloadPdf/{id}")
// 	public void downloadPdf(@PathVariable Long id, HttpServletResponse response) {
// 		try {
// 			publicService.downloadPdf(id, response);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile scaricare il pdf ", e);
// 		}
// 	}

// 	@GetMapping(path = "/public/fe/getAllConstants")
// 	public Map<String, String> getAllConstants() {
// 		try {
// 			return publicService.getAllConstants();
// 		} catch (Exception e) {
// 			LOG.error("Impossibile caricare tutte le constanti ", e);
// 			return null;
// 		}
// 	}

// 	@GetMapping(path = "/public/fe/groupDefinition/getCodeAndDefinition/{code}")
// 	public Map<String, Definitions> getCodeAndDefinition(@PathVariable String code) {
// 		try {
// 			return groupDefinitionService.getCodeAndDefinition(code);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile caricare tutte le definizioni ", e);
// 			return null;
// 		}
// 	}
	
// 	@GetMapping(path = "/public/fe/groupDefinition/getIdAndCode/{code}")
// 	public Map<Long, String> getIdAndCode(@PathVariable String code) {
// 		try {
// 			return groupDefinitionService.getIdAndCode(code);
// 		} catch (Exception e) {
// 			LOG.error("Impossibile caricare tutte le definizioni ", e);
// 			return null;
// 		}
// 	}

// 	@GetMapping(path = "/public/fe/findConstantByCode/{id}")
// 	public Constant getconstant(@PathVariable String id) {

// 		return constantService.findById(id).map(constant -> {
// 			return constant;
// 		}).orElseThrow(
// 				() -> new PortalException("The constant with the id " + id + " couldn't be found in the database."));
// 	}
	
// 	@PostMapping("/public/createUser")
// 	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
// 		if (userService.existsByUser(user.getUsername())) {
// 			return ResponseEntity
// 					.badRequest()
// 					.body(new MessageResponse("Error: Username is already taken!"));
// 		}
		
// 		if (user.getMail() != null && userService.existsByMail(user.getMail())) {
// 			return ResponseEntity
// 					.badRequest()
// 					.body(new MessageResponse("Error: Mail is already taken!"));
// 		}
		
// 		Definitions def = definitionService.findByCode("customer").map(definition -> {
// 			return definition;
// 		}).orElseThrow(
// 				() -> new PortalException("The definition with code customer couldn't be found in the database."));
		
// 		Definitions defGuest = definitionService.findByCode("guest").map(definition -> {
// 			return definition;
// 		}).orElseThrow(
// 				() -> new PortalException("The definition with code guest couldn't be found in the database."));
		
// 		try {
			
// 			if (user.getProfile().getIdDefinition().equals(def.getIdDefinition()) && user.getSocialLogin().equals(Boolean.FALSE)) {
// 				userService.createUserAndSendEmail(user);
// 			}else if(user.getProfile().getIdDefinition().equals(def.getIdDefinition()) && user.getSocialLogin().equals(Boolean.TRUE) || user.getProfile().getIdDefinition().equals(defGuest.getIdDefinition())) { 
// 				user.setPassword(userService.encodePassword(user.getPassword()));
// 				userService.save(user);
// 			}
// 		}
// 		catch (MessagingException e) {
// 			return ResponseEntity
// 					.badRequest()
// 					.body(new MessageResponse("Generic Error"));
// 		}

// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@GetMapping("/public/checkUser/{username}")
// 	public ResponseEntity<?> checkUser(@PathVariable String username) {
// 		User user = userService.findByUsername(username).orElseThrow(
// 				() -> new UserNotFoundException("The user with the id " + username + " couldn't be found in the database."));
// 		boolean check = true;
// 		if (user != null) check = true;
// 		return ResponseEntity.ok(String.valueOf(check));
// 	}

// 	@PutMapping(path = "/public/updateUser/{username}")
// 	public ResponseEntity<?> update(@Valid @RequestBody User user, @PathVariable String username) {
// 		User dbUser = userService.findByUsername(username).orElseThrow(
// 				() -> new UserNotFoundException("The user with the id " + username + " couldn't be found in the database."));
// //		dbUser.setPrivacy(true);
// 		dbUser.setUpdateDate(user.getUpdateDate());
// 		dbUser.setUpdateUser(user.getUpdateUser());
// 		dbUser.setActive(user.getActive());
// 		userService.save(dbUser);
// 		return ResponseEntity.ok(new MessageResponse("Updated!"));
// 	}	
	
// 	@PostMapping("/public/sendEmailResetPassword")
// 	public ResponseEntity<?> sendEmailResetPassword(@RequestBody LoginRequest loginRequest)
// 			throws MessagingException, NoSuchAlgorithmException {
// 		if (!userService.existsByUser(loginRequest.getUsername())) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Generic Error"));
// 		}
// 		userService.sendEmailResetPassword(loginRequest);
// 		return ResponseEntity.ok("success");
// 	}

// 	@GetMapping("/public/checkMd5/{md5}")
// 	public ResponseEntity<?> checkMd5(@PathVariable String md5) {
// 		UsersResetPassword user = userService.findByMd5(md5)
// 				.orElseThrow(() -> new UserNotFoundException("Error"));
// 		boolean check = true;
// 		boolean app = false;
// 		if (user != null &&  user.getDate().before(new Timestamp(System.currentTimeMillis() - 3600 * 1000))) check = false;
// 		if(userService.findByUsername(user.getUsername()).get().getHWVersion() != null) app = true;
// 		Map<String, Boolean> response = new HashMap<>();
// 		response.put("check", check);
// 		response.put("app", app);
// 		return ResponseEntity.ok(response);
// 	}
	
// 	@PostMapping("/public/resetPassword/{md5}")
// 	public ResponseEntity<?> resetPassword(@PathVariable String md5, @RequestBody LoginRequest loginRequest) {	
// 		userService.resetPassword(md5, loginRequest.getPassword());
// 		return ResponseEntity.ok("success");
// 	}
	
// 	@GetMapping("/public/guestNextValue")
// 	public ResponseEntity<?> guestNextValue() {
// 		Constant constant = constantService.findById("nGuest").map(cons-> {
// 			return cons;
// 		}).orElseThrow( () -> new PortalException("The constant with code nGuest couldn't be found in the database."));		
// 		constant.setValue(String.valueOf(Long.valueOf(constant.getValue())+1));			
// 		return ResponseEntity.ok(constantService.save(constant).getValue());
// 	}
	
// }
