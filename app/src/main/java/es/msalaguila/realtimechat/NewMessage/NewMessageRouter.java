package es.msalaguila.realtimechat.NewMessage;

import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.Chat.ChatActivity;
import es.msalaguila.realtimechat.Chat.ChatState;
import es.msalaguila.realtimechat.Home.HomeActivity;
import es.msalaguila.realtimechat.app.AppMediator;

public class NewMessageRouter implements NewMessageContract.Router {

  public static String TAG = NewMessageRouter.class.getSimpleName();

  private AppMediator mediator;

  public NewMessageRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, NewMessageActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(NewMessageState state) {
    mediator.setNewMessageState(state);
  }

  @Override
  public NewMessageState getDataFromPreviousScreen() {
    NewMessageState state = mediator.getNewMessageState();
    return state;
  }

  @Override
  public void routeToHome(Activity activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, HomeActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void routeToChat(Activity activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, ChatActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  @Override
  public void passDataToChat(ChatState state) {
    mediator.setChatState(state);
  }
}
