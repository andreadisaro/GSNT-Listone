// package it.giochisulnostrotavolo.listone.controller;

// import java.util.List;
// import java.util.stream.Collectors;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import it.giochisulnostrotavolo.listone.entities.payload.request.LoginRequest;
// import it.giochisulnostrotavolo.listone.entities.payload.response.JwtResponse;
// import it.giochisulnostrotavolo.listone.entities.payload.response.MessageResponse;
// import it.giochisulnostrotavolo.listone.security.UserDetailsImpl;
// import it.giochisulnostrotavolo.listone.security.jwt.JwtUtils;

// @RestController
// @RequestMapping
// public class AuthController {

// 	@Autowired
// 	private AuthenticationManager authenticationManager;

// 	@Autowired
// 	private JwtUtils jwtUtils;


// 	// per provare qual è il risultato dell'encoding della password scommentare la
// 	// riga seguente
// //	@Autowired
// //	PasswordEncoder encoder;

// 	@PostMapping("/public/authenticateUserApp/getToken")
// 	public ResponseEntity<?> authenticateUserApp(@Valid @RequestBody LoginRequest loginRequest) {
// 		// per provare qual è il risultato dell'encoding della password scommentare la
// 		// riga seguente
// //		System.out.println(encoder.encode(loginRequest.getPassword()));

// 		Authentication authentication = authenticationManager.authenticate(
// 				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

// 		SecurityContextHolder.getContext().setAuthentication(authentication);
// 		String jwt = jwtUtils.generateJwtToken(authentication, false);

// 		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
// 		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
// 				.collect(Collectors.toList());
// 		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUser(), roles));
// 	}
	
// 	@PostMapping("/public/authenticateUserBackOffice/getToken")
// 	public ResponseEntity<?> authenticateUserBackOffice(@Valid @RequestBody LoginRequest loginRequest) {
// 		// per provare qual è il risultato dell'encoding della password scommentare la
// 		// riga seguente
// //		System.out.println(encoder.encode(loginRequest.getPassword()));

// 		Authentication authentication = authenticationManager.authenticate(
// 				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

// 		SecurityContextHolder.getContext().setAuthentication(authentication);
// 		String jwt = jwtUtils.generateJwtToken(authentication, true);

// 		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
// 		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
// 				.collect(Collectors.toList());
// 		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUser(), roles));
// 	}
	


// 	@GetMapping("/auth/checkLoggedIn")
// 	@PreAuthorize("hasRole('ROLE_FE') or hasRole('ROLE_API')")
// 	public ResponseEntity<?> checkLoggedIn() {
// 		// serve solo nel caso in cui il frontend trovi un token nel localStorage, in
// 		// quel caso potrei già essere loggato, ma devo controllare se il token è ancora
// 		// valido

// 		return ResponseEntity.ok(new MessageResponse("OK"));
// 	}


// }
