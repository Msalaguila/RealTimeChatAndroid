package es.msalaguila.realtimechat.app;

public interface RepositoryInterface {

  interface CheckIfUserIsLoggedIn {
    void onUserIsLoggedIn(boolean isLoggedIn);
  }

  void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);
}
