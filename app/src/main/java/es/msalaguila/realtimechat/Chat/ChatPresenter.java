package es.msalaguila.realtimechat.Chat;

import android.util.Log;

import java.lang.ref.WeakReference;

public class ChatPresenter implements ChatContract.Presenter {

  public static String TAG = ChatPresenter.class.getSimpleName();

  private WeakReference<ChatContract.View> view;
  private ChatViewModel viewModel;
  private ChatContract.Model model;
  private ChatContract.Router router;

  public ChatPresenter(ChatState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<ChatContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ChatContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(ChatContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchData() {
    // Log.e(TAG, "fetchData()");

    // set passed state
    ChatState state = router.getDataFromPreviousScreen();
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
  public void getUserTappedFromPreviousScreen() {
    ChatState state = router.getDataFromPreviousScreen();

    if (state != null) {
      viewModel.userTapped = state.tappedUser;
    }

    // TODO: Now Testing only
    view.get().displayUserTappedName(viewModel);
  }


}
