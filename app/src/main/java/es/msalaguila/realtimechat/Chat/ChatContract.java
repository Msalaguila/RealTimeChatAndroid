package es.msalaguila.realtimechat.Chat;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface ChatContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(ChatViewModel viewModel);

    void displayUserTappedName(ChatViewModel viewModel);

    void cleanInputTextField();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void getUserTappedFromPreviousScreen();

    void sendButtonPressed(String textToSend);
  }

  interface Model {
    String fetchData();

    void sendMessageToUser(String textToSend, RegisteredUser userToSendMessageTo,
                           RepositoryInterface.SendMessage callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(ChatState state);

    ChatState getDataFromPreviousScreen();
  }
}
