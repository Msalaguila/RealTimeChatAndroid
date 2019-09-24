package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

interface RegisterContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(RegisterViewModel viewModel);

    Activity getActivity();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void routeToLogin(Context activity);

    void fetchData();

    void openGallery(Activity activity);
  }

  interface Model {
    String fetchData();
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(RegisterState state);

    void navigateToLogin(Context activity);

    RegisterState getDataFromPreviousScreen();

    void openGallery(Activity activity);
  }
}
