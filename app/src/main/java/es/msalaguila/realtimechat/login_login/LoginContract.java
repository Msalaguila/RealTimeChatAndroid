package es.msalaguila.realtimechat.login_login;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

interface LoginContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(LoginViewModel viewModel);

    Activity getActivity();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void routeToRegister(Context activity);
  }

  interface Model {
    String fetchData();
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(LoginState state);

    void routeToRegister(Context activity);

    LoginState getDataFromPreviousScreen();
  }
}
