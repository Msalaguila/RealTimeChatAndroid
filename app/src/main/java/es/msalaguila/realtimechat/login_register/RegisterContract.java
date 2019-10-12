package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface RegisterContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(RegisterViewModel viewModel);

    Activity getActivity();

    void displayPasswordTooShort();

    void displayPickUpPhotoImage();

    void displayFillAllFieldsMessage();

    void displayRegisteredSuccesful();

    void displayError();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void routeToLogin(Activity activity);

    void fetchData();

    void openGallery(Activity activity);

    void onRegisterButtonPressed(RegisteredUser user);

    void routeToHome();
  }

  interface Model {
    String fetchData();

    void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(RegisterState state);

    void navigateToLogin(Activity activity);

    RegisterState getDataFromPreviousScreen();

    void openGallery(Activity activity);

    void routeToHome(Activity activity);
  }
}
