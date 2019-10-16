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

    void finishActivity();

    void displayCurrentUser(HomeViewModel viewModel);

    void displayHomeMessages(HomeViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void isUserLoggedIn();

    void logoutButtonPressed();

    void newMessageButtonPressed();
  }

  interface Model {
    String fetchData();

    void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);

    void logoutUser(RepositoryInterface.LogoutButtonPressed callback);

    void getCurrentUser(RepositoryInterface.GetCurrentUser callback);

    void loadHomeMessages(RepositoryInterface.LoadHomeMessages callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(HomeState state);

    HomeState getDataFromPreviousScreen();

    void routeToRegister(Activity activity);

    void routeToNewMessage(Activity activity);
  }
}
