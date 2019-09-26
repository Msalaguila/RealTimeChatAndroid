package es.msalaguila.realtimechat.app;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.Data.RegisteredUser;

public interface RepositoryInterface {

  interface CheckIfUserIsLoggedIn {
    void onUserIsLoggedIn(boolean isLoggedIn);
  }

  interface RegisterNewUser {
    void onNewUserRegistered(boolean error, boolean shortPassword);
  }

  interface LoginNewUser {
    void onUserLoggedIn(boolean error, boolean shortPassword);
  }

  void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);

  void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback);

  void logInUser(LoginUser user, RepositoryInterface.LoginNewUser callback);
}
