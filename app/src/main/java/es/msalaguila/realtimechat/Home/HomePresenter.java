package es.msalaguila.realtimechat.Home;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.app.RepositoryInterface;

public class HomePresenter implements HomeContract.Presenter {

  public static String TAG = HomePresenter.class.getSimpleName();

  private WeakReference<HomeContract.View> view;
  private HomeViewModel viewModel;
  private HomeContract.Model model;
  private HomeContract.Router router;

  public HomePresenter(HomeState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<HomeContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(HomeContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(HomeContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchData() {
    // Log.e(TAG, "fetchData()");

    // set passed state
    HomeState state = router.getDataFromPreviousScreen();
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
  public void isUserLoggedIn() {
    model.isUserLoggedIn(new RepositoryInterface.CheckIfUserIsLoggedIn() {
      @Override
      public void onUserIsLoggedIn(boolean isLoggedIn) {
        if (!isLoggedIn) {
          Activity activity = view.get().getActivity();
          router.routeToRegister(activity);
        } else {

        }
      }
    });
  }


}