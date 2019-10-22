package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface RegisterContract {

  interface View {
    void injectPresenter(Presenter presenter);

    Activity getActivity();

    /**
     * Gets called when the user inserted a short password
     */
    void displayPasswordTooShort();

    /**
     * Gets called when the user hasn't chosen an image
     */
    void displayPickUpPhotoImage();

    /**
     * Gets displayed when one of the fields isn't filled
     */
    void displayFillAllFieldsMessage();

    /**
     * Gets displayed when the registration is successful
     */
    void displayRegisteredSuccesful();

    /**
     * Gets called while the registration was being executed and an error happens
     */
    void displayError();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void routeToLogin(Activity activity);

    /**
     * Opens the phone's gallery
     * @param activity: Context
     */
    void openGallery(Activity activity);

    /**
     * Calls the model to register a new user
     * @param user: User to be registered
     */
    void onRegisterButtonPressed(RegisteredUser user);

    /**
     * Gets called when the registration is successful
     */
    void routeToHome();
  }

  interface Model {
    /**
     * Method that register a new user
     * @param user: User that is being registered
     * @param callback: Callback that is going to be called when the user is created
     */
    void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback);
  }

  interface Router {
    void navigateToLogin(Activity activity);

    RegisterState getDataFromPreviousScreen();

    void openGallery(Activity activity);

    void routeToHome(Activity activity);
  }
}
