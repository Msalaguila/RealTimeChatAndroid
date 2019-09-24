package es.msalaguila.realtimechat.login_register;

import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.login_login.LoginActivity;

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
  public void navigateToLogin(Context activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
  }

  @Override
  public RegisterState getDataFromPreviousScreen() {
    RegisterState state = mediator.getRegisterState();
    return state;
  }
}
