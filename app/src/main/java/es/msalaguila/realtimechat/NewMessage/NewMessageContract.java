package es.msalaguila.realtimechat.NewMessage;

import android.app.Activity;

import java.lang.ref.WeakReference;

interface NewMessageContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(NewMessageViewModel viewModel);

    Activity getActivity();

    void finishActivity();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void onBacbButtonPressed();
  }

  interface Model {
    String fetchData();
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(NewMessageState state);

    NewMessageState getDataFromPreviousScreen();

    void routeToHome(Activity activity);
  }
}
