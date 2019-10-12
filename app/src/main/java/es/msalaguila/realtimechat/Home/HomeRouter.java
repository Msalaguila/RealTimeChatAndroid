package es.msalaguila.realtimechat.Home;

import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.content.Context;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.login_register.RegisterActivity;

public class HomeRouter implements HomeContract.Router {

  public static String TAG = HomeRouter.class.getSimpleName();

  private AppMediator mediator;

  public HomeRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, HomeActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(HomeState state) {
    mediator.setHomeState(state);
  }

  @Override
  public HomeState getDataFromPreviousScreen() {
    HomeState state = mediator.getHomeState();
    return state;
  }

  @Override
  public void routeToRegister(Activity activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, RegisterActivity.class);
    activity.startActivity(intent);
  }
}
