package es.msalaguila.realtimechat.Chat;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class ChatModel implements ChatContract.Model {

  public static String TAG = ChatModel.class.getSimpleName();

  private RepositoryInterface repository;

  public ChatModel(RepositoryInterface repository) {
    this.repository = repository;
  }

  @Override
  public String fetchData() {
    // Log.e(TAG, "fetchData()");
    return "Hello";
  }

  @Override
  public void sendMessageToUser(String textToSend, RegisteredUser userToSendMessageTo,
                                RepositoryInterface.SendMessage callback) {
    repository.sendMessageToUser(textToSend, userToSendMessageTo, callback);
  }

  @Override
  public void loadMessagesForTappedUserInsideChat(RegisteredUser userToLoadMessages
          , RepositoryInterface.LoadMessagesForTappedUserInsideChat callback) {
    repository.loadMessagesForTappedUserInsideChat(userToLoadMessages, callback);
  }
}
