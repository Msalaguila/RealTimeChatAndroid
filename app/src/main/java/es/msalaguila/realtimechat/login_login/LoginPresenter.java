package es.msalaguila.realtimechat.login_login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class LoginPresenter implements LoginContract.Presenter {

  public static String TAG = LoginPresenter.class.getSimpleName();

  private WeakReference<LoginContract.View> view;
  private LoginViewModel viewModel;
  private LoginContract.Model model;
  private LoginContract.Router router;

  public LoginPresenter(LoginState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<LoginContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(LoginContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(LoginContract.Router router) {
    this.router = router;
  }

  @Override
  public void routeToRegister(Activity activity) {
    router.routeToRegister(activity);
  }

  @Override
  public void onLoginButtonPressed(LoginUser user) {
    model.loginUser(user, new RepositoryInterface.LoginNewUser() {
      @Override
      public void onUserLoggedIn(boolean error, boolean shortPassword, boolean isEmailFilled) {

        if (!isEmailFilled) {
          view.get().displayFillEmailAlert();
        }

        // Password too short
        else if (shortPassword) {
          view.get().displayPasswordTooShort();
        }

        // An error happens
        else if (error) {
          view.get().displayLoginErrorAlert();
        }

        else {
          Activity activity = view.get().getActivity();
          view.get().finishActivity();
          router.routeToHome(activity);
        }
      }
    });
  }


}
