package es.msalaguila.realtimechat.Home;

import android.app.Activity;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface HomeContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(HomeViewModel viewModel);

    Activity getActivity();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void isUserLoggedIn();
  }

  interface Model {
    String fetchData();

    void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(HomeState state);

    HomeState getDataFromPreviousScreen();

    void routeToRegister(Activity activity);
  }
}
