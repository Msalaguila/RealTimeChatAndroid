package es.msalaguila.realtimechat.NewMessage;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;

public class NewMessagePresenter implements NewMessageContract.Presenter {

  public static String TAG = NewMessagePresenter.class.getSimpleName();

  private WeakReference<NewMessageContract.View> view;
  private NewMessageViewModel viewModel;
  private NewMessageContract.Model model;
  private NewMessageContract.Router router;

  public NewMessagePresenter(NewMessageState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<NewMessageContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(NewMessageContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(NewMessageContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchData() {
    // Log.e(TAG, "fetchData()");

    // set passed state
    NewMessageState state = router.getDataFromPreviousScreen();
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
  public void onBacbButtonPressed() {
    Activity activity = view.get().getActivity();
    view.get().finishActivity();
    router.routeToHome(activity);
  }


}
