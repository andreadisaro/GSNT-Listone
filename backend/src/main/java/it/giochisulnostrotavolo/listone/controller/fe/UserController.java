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

// import it.giochisulnostrotavolo.listone.entities.User;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.exception.UserNotFoundException;
// import it.giochisulnostrotavolo.listone.service.UserService;

// @RestController()
// @PreAuthorize("hasRole('ROLE_FE')")
// @RequestMapping("/auth/fe/user")
// public class UserController {

// 	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

// 	@Autowired
// 	private UserService userService;

// 	@GetMapping(path = "/Userlist")
// 	public List<User> getAllByOrderByUsername() {
// 		return userService.findAllByOrderByUser();
// 	}

// 	@PostMapping
// 	public ResponseEntity<?> create(@Valid @RequestBody User username) {
// 		if (userService.existsByUser(username.getUsername())) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
// 		}
// 		if (userService.existsByMail(username.getMail())) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
// 		}

// 		try {
// 			username.setPassword(userService.encodePassword(username.getPassword()));
// 			userService.save(username);
// 		} catch (Exception e) {
// 			LOG.error("Error created user", e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Generic Error"));
// 		}

// 		return ResponseEntity.ok(new MessageResponse("Created!"));
// 	}

// 	@PostMapping(path = "/banUser/{username}")
// 	public ResponseEntity<?> banUser(@PathVariable String username) {
// 		try {
// 			userService.banUser(username);
// 		} catch (Exception e) {
// 			LOG.error("Error banUser", e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Generic Error"));
// 		}

// 		return ResponseEntity.ok(new MessageResponse("banned user!"));
// 	}
	
// 	@DeleteMapping(path = "/deleteBanUser/{username}")
// 	public ResponseEntity<?> deleteBan(@PathVariable String username) {
// 		try {
// 			userService.deleteBan(username);
// 		} catch (Exception e) {
// 			LOG.error("Error banUser", e);
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error: ban user!"));
// 		}

// 		return ResponseEntity.ok(new MessageResponse("restored user!"));
// 	}

// 	@GetMapping(path = "/{username}")
// 	public User get(@PathVariable String username) {

// 		return userService.findByUsername(username).map(user -> {
// 			LOG.debug("Reading user with id " + user + " from database.");
// 			return user;
// 		}).orElseThrow(() -> new UserNotFoundException(
// 				"The user with the id " + username + " couldn't be found in the database."));
// 	}

// 	@PostMapping(path = "/allPaged")
// 	public Page<User> getAllPaged(@Valid @RequestBody User exampleObj, @RequestParam int page, @RequestParam int size,
// 			@RequestParam String sortBy, @RequestParam boolean sortDesc) {
// 		PageRequest pageRequest;
// 		if (sortDesc) {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
// 		} else {
// 			pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
// 		}
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<User> example = Example.of(exampleObj, matcher);
// 		return userService.findAll(
// 				userService.getSpecFromDatesAndExample(exampleObj.getDateFrom(), exampleObj.getDateTo(), example),
// 				pageRequest);
// 	}

// 	@PostMapping(path = "/allList")
// 	public List<User> getAllPaged(@Valid @RequestBody User exampleObj) {
// 		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
// 				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

// 		Example<User> example = Example.of(exampleObj, matcher);

// 		return userService.findAll(example);
// 	}

// 	@PutMapping(path = "/{username}")
// 	public ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable String username) {
// 		user = userService.update(username, user);
// 		return ResponseEntity.ok(user);
// 	}

// 	@GetMapping(path = "/active/{active}")
// 	public List<User> getUserByActive(@PathVariable Boolean active) {

// 		return userService.findByActive(active);
// 	}
	
// 	@PostMapping(path = "/userAnalytics")
// 	public List<Map<String, Object>> userAnalytics(@RequestParam String username) {
// 		return userService.userAnalytics(username);
// 	}

// }
