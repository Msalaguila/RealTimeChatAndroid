package es.msalaguila.realtimechat.Chat;

import android.util.Log;

import java.lang.ref.WeakReference;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;

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
}
