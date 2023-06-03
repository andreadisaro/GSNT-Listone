package it.giochisulnostrotavolo.listone.service;

import it.giochisulnostrotavolo.listone.entities.User;
import it.giochisulnostrotavolo.listone.entities.payload.request.LoginRequest;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;

//import it.giochisulnostrotavolo.listone.entities.payload.request.UsersResetPassword;

public interface UserService {
  public User save(User user);

  public void deleteByUser(String user);

  public Boolean existsByUser(String user);

  public String encodePassword(String password);

  public User update(String username, User user);

  public void sendEmailResetPassword(LoginRequest loginRequest)
    throws MessagingException, NoSuchAlgorithmException;
}
