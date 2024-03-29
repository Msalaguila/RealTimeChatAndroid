package es.msalaguila.realtimechat.Home;

import android.util.Log;

import java.lang.ref.WeakReference;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.RepositoryInterface;

public class HomeModel implements HomeContract.Model {

  public static String TAG = HomeModel.class.getSimpleName();

  private RepositoryInterface repository;

  public HomeModel(RepositoryInterface repository) {
    this.repository = repository;
  }

  @Override
  public void isUserLoggedIn(final RepositoryInterface.CheckIfUserIsLoggedIn callback) {
    repository.isUserLoggedIn(callback);
  }

  @Override
  public void logoutUser(RepositoryInterface.LogoutButtonPressed callback) {
    repository.logoutUser(callback);
  }

  @Override
  public void getCurrentUser(RepositoryInterface.GetCurrentUser callback) {
    repository.getCurrentUser(callback);
  }

  @Override
  public void loadHomeMessages(RepositoryInterface.LoadHomeMessages callback) {
    repository.loadHomeMessages(callback);
  }
}
