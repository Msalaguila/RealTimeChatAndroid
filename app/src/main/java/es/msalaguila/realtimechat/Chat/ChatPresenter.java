package es.msalaguila.realtimechat.Chat;

import java.lang.ref.WeakReference;
import java.util.List;

import es.msalaguila.realtimechat.Data.Message;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

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
  public void getUserTappedFromPreviousScreen() {
    ChatState state = router.getDataFromPreviousScreen();

    if (state != null) {
      viewModel.userTapped = state.tappedUser;
    }
    
    view.get().displayUserTappedName(viewModel);
  }

  @Override
  public void sendButtonPressed(String textToSend) {
    RegisteredUser userToSendMessage = viewModel.userTapped;
    model.sendMessageToUser(textToSend, userToSendMessage, new RepositoryInterface.SendMessage() {
      @Override
      public void onMessageSent() {
        view.get().cleanInputTextField();
      }
    });
  }

  @Override
  public void loadMessagesForTappedUser() {
    model.loadMessagesForTappedUserInsideChat(viewModel.userTapped
            , new RepositoryInterface.LoadMessagesForTappedUserInsideChat() {
      @Override
      public void onMessagesLoaded(List<Message> messages) {
        viewModel.chatMessages = messages;
        view.get().displayChatMessages(viewModel);
      }
    });
  }

  @Override
  public void eliminateInsideChatReference() {
    RegisteredUser tappedUser = viewModel.userTapped;
    model.eliminateInsideChatReference(tappedUser);
  }


}
