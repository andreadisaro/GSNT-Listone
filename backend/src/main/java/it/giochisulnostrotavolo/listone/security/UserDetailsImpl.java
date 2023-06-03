package it.giochisulnostrotavolo.listone.security;

import it.giochisulnostrotavolo.listone.entities.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 2124116485872564144L;

  private User user;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
    User user,
    Collection<? extends GrantedAuthority> authorities
  ) {
    this.user = user;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    // cicle user.getRoles() and create list of GrantedAuthorities
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    return new UserDetailsImpl(user, authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public User getUser() {
    return user;
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return "";
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(getUsername(), user.getUsername());
  }
}
