package es.msalaguila.realtimechat.NewMessage;

import android.app.Activity;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.app.RepositoryInterface;

interface NewMessageContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(NewMessageViewModel viewModel);

    Activity getActivity();

    void finishActivity();

    void displayCurrentUsers(NewMessageViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void onBackButtonPressed();

    void getCurrentUsers();
  }

  interface Model {
    String fetchData();

    void getCurrentUsers(RepositoryInterface.GetCurrentUsers callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(NewMessageState state);

    NewMessageState getDataFromPreviousScreen();

    void routeToHome(Activity activity);
  }
}
