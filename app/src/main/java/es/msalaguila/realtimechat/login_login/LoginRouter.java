package es.msalaguila.realtimechat.login_login;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.login_register.RegisterActivity;

public class LoginRouter implements LoginContract.Router {

  public static String TAG = LoginRouter.class.getSimpleName();

  private AppMediator mediator;

  public LoginRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(LoginState state) {
    mediator.setLoginState(state);
  }

  @Override
  public void routeToRegister(Context activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, RegisterActivity.class);
    context.startActivity(intent);
  }

  @Override
  public LoginState getDataFromPreviousScreen() {
    LoginState state = mediator.getLoginState();
    return state;
  }
}
