package it.giochisulnostrotavolo.listone.serviceImpl;

import it.giochisulnostrotavolo.listone.entities.User;
import it.giochisulnostrotavolo.listone.entities.payload.request.LoginRequest;
import it.giochisulnostrotavolo.listone.service.UserService;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Resource
  private UserService self;

  public User createUserAndSendEmail(User user) throws MessagingException {
    return user;
  }

  public User save(User user) {
    return user;
  }

  public Boolean existsByUser(String user) {
    return true;
  }

  public String encodePassword(String password) {
    return "";
  }

  public void deleteByUser(String user) {}

  public User update(String username, User user) {
    return user;
  }

  public void sendEmailResetPassword(LoginRequest loginRequest)
    throws MessagingException, NoSuchAlgorithmException {}
}
