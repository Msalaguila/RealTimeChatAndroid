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

    /**
     * Gets called when the user inserted a short password
     */
    void displayPasswordTooShort();

    /**
     * Gets called while the log-in was being executed and an error happens
     */
    void displayLoginErrorAlert();

    /**
     * Gets called when the user hasn't filled the email field
     */
    void displayFillEmailAlert();

    void finishActivity();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    /**
     * Gets called when the user taps on the Register Button
     * @param activity: Context
     */
    void routeToRegister(Activity activity);

    /**
     * Calls the model to perform the login of the user
     * @param user: User to be logged-in
     */
    void onLoginButtonPressed(LoginUser user);
  }

  interface Model {
    String fetchData();

    /**
     * Method that log-ins a new user previously created to the application
     * @param user: User to be logged-in
     * @param callback: Callback that is going to be called when the user is logged-in
     */
    void loginUser(LoginUser user, RepositoryInterface.LoginNewUser callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(LoginState state);

    void routeToRegister(Activity activity);

    LoginState getDataFromPreviousScreen();

    void routeToHome(Activity activity);
  }
}
