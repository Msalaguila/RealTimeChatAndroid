package es.msalaguila.realtimechat.app;

import java.util.ArrayList;
import java.util.List;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.Data.RegisteredUser;

public interface RepositoryInterface {

  interface CheckIfUserIsLoggedIn {
    void onUserIsLoggedIn(boolean isLoggedIn);
  }

  interface RegisterNewUser {
    void onNewUserRegistered(boolean error, boolean shortPassword, boolean isImageUri,
                             boolean allFieldsFilled);
  }

  interface LoginNewUser {
    void onUserLoggedIn(boolean error, boolean shortPassword, boolean isEmailFilled);
  }

  interface LogoutButtonPressed {
    void onLogoutButtonPressed();
  }

  interface GetCurrentUser {
    void onGetCurrentUser(RegisteredUser user);
  }

  interface GetCurrentUsers {
    void onGetCurrentUsers(List<RegisteredUser> currentUsers);
  }

  void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);

  void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback);

  void logInUser(LoginUser user, RepositoryInterface.LoginNewUser callback);

  void logoutUser(RepositoryInterface.LogoutButtonPressed callback);

  void getCurrentUser(RepositoryInterface.GetCurrentUser callback);

  void getCurrentUsers(RepositoryInterface.GetCurrentUsers callback);
}
