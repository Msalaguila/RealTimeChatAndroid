package es.msalaguila.realtimechat.login;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.app.AppMediator;

public class RegisterRouter implements RegisterContract.Router {

  public static String TAG = RegisterRouter.class.getSimpleName();

  private AppMediator mediator;

  public RegisterRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, RegisterActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(RegisterState state) {
    mediator.setRegisterState(state);
  }

  @Override
  public RegisterState getDataFromPreviousScreen() {
    RegisterState state = mediator.getRegisterState();
    return state;
  }
}
