package es.msalaguila.realtimechat.login_register;

import android.content.Context;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class RegisterModel implements RegisterContract.Model {

  public static String TAG = RegisterModel.class.getSimpleName();

  private RepositoryInterface repository;

  public RegisterModel(RepositoryInterface repository) {
    this.repository = repository;
  }

  @Override
  public void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback) {
    repository.registerNewUser(user, callback);
  }
}
