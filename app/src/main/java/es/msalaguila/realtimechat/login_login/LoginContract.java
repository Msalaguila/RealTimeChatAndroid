package es.msalaguila.realtimechat.login_login;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface LoginContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(LoginViewModel viewModel);

    Activity getActivity();

    void displayPasswordTooShort();

    void displayLoginErrorAlert();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void routeToRegister(Context activity);

    void onLoginButtonPressed(LoginUser user);
  }

  interface Model {
    String fetchData();

    void loginUser(LoginUser user, RepositoryInterface.LoginNewUser callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(LoginState state);

    void routeToRegister(Context activity);

    LoginState getDataFromPreviousScreen();
  }
}
