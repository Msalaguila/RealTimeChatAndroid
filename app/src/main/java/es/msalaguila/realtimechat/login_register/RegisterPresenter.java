package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class RegisterPresenter implements RegisterContract.Presenter {

  public static String TAG = RegisterPresenter.class.getSimpleName();

  private WeakReference<RegisterContract.View> view;
  private RegisterViewModel viewModel;
  private RegisterContract.Model model;
  private RegisterContract.Router router;

  public RegisterPresenter(RegisterState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<RegisterContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(RegisterContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(RegisterContract.Router router) {
    this.router = router;
  }

  @Override
  public void routeToLogin(Activity activity) {
    router.navigateToLogin(activity);
  }

  @Override
  public void fetchData() {
    // Log.e(TAG, "fetchData()");

    // set passed state
    RegisterState state = router.getDataFromPreviousScreen();
    if (state != null) {
      viewModel.data = state.data;
    }

    if (viewModel.data == null) {
      // call the model
      String data = model.fetchData();

      // set initial state
      viewModel.data = data;
    }

    // update the view
    view.get().displayData(viewModel);

  }

  @Override
  public void openGallery(Activity activity) {
    router.openGallery(activity);
  }

  @Override
  public void onRegisterButtonPressed(RegisteredUser user) {
    model.registerNewUser(user, new RepositoryInterface.RegisterNewUser() {
      @Override
      public void onNewUserRegistered(boolean error, boolean shortPassword, boolean isImageUri,
                                      boolean allFieldsFilled) {
        if (!allFieldsFilled) {
          view.get().displayFillAllFieldsMessage();
        } else if (shortPassword) {
          view.get().displayPasswordTooShort();
        } else if (!isImageUri) {
          view.get().displayPickUpPhotoImage();
        }
      }
    });
  }


}
