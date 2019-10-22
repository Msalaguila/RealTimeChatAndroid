package es.msalaguila.realtimechat.login_login;

import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.Home.HomeActivity;
import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.login_register.RegisterActivity;

public class LoginRouter implements LoginContract.Router {

  public static String TAG = LoginRouter.class.getSimpleName();

  private AppMediator mediator;

  public LoginRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void routeToRegister(Activity activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, RegisterActivity.class);
    activity.startActivity(intent);
  }

  @Override
  public LoginState getDataFromPreviousScreen() {
    LoginState state = mediator.getLoginState();
    return state;
  }

  @Override
  public void routeToHome(Activity activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, HomeActivity.class);
    activity.startActivity(intent);
  }
}
