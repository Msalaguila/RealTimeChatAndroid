package es.msalaguila.realtimechat.Chat;

import java.lang.ref.WeakReference;

interface ChatContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(ChatViewModel viewModel);

    void displayUserTappedName(ChatViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void getUserTappedFromPreviousScreen();
  }

  interface Model {
    String fetchData();
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(ChatState state);

    ChatState getDataFromPreviousScreen();
  }
}
