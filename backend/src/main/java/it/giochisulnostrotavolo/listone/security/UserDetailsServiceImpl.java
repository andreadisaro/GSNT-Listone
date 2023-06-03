package it.giochisulnostrotavolo.listone.security;

import it.giochisulnostrotavolo.listone.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    return UserDetailsImpl.build(new User());
  }

  public UserDetails loadUserDetailsByUsernameNoLogin(
    String username,
    String jwtToken
  ) throws Exception { //chiamato in caso di sso e checkLoggedIn
    return UserDetailsImpl.build(loadUserByUsernameNoLogin(username, jwtToken));
  }

  private User loadUserByUsernameNoLogin(String username, String jwtToken)
    throws Exception {
    return new User();
  }
}
