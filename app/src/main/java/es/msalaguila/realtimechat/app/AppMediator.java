package es.msalaguila.realtimechat.app;

import android.app.Application;

import es.msalaguila.realtimechat.Home.HomeState;
import es.msalaguila.realtimechat.login_login.LoginState;
import es.msalaguila.realtimechat.login_register.RegisterState;

public class AppMediator extends Application {

  private RegisterState registerState = new RegisterState();
  private LoginState loginState = new LoginState();
  private HomeState homeState = new HomeState();

  public void setRegisterState(RegisterState state) {
    this.registerState = state;
  }

  public RegisterState getRegisterState() {
    return registerState;
  }

  public LoginState getLoginState() {
    return loginState;
  }

  public void setLoginState(LoginState state) {
    this.loginState = state;
  }

  public HomeState getHomeState() {
    return homeState;
  }

  public void setHomeState(HomeState state) {
    this.homeState = state;
  }
}
