package es.msalaguila.realtimechat.login_login;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class LoginModel implements LoginContract.Model {

  public static String TAG = LoginModel.class.getSimpleName();

  private RepositoryInterface repository;

  public LoginModel(RepositoryInterface repository) {
    this.repository = repository;
  }

  @Override
  public String fetchData() {
    // Log.e(TAG, "fetchData()");
    return "Hello";
  }

  @Override
  public void loginUser(LoginUser user, RepositoryInterface.LoginNewUser callback) {
    repository.logInUser(user, callback);
  }
}
