package es.msalaguila.realtimechat.login;

import android.util.Log;

import java.lang.ref.WeakReference;

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


}
