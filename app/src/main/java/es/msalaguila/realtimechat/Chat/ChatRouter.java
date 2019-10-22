package es.msalaguila.realtimechat.Chat;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.app.AppMediator;

public class ChatRouter implements ChatContract.Router {

  public static String TAG = ChatRouter.class.getSimpleName();

  private AppMediator mediator;

  public ChatRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public ChatState getDataFromPreviousScreen() {
    ChatState state = mediator.getChatState();
    return state;
  }
}
