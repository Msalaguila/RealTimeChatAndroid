package es.msalaguila.realtimechat.app;

import android.app.Application;

import es.msalaguila.realtimechat.login.RegisterState;

public class AppMediator extends Application {

  private RegisterState registerState = new RegisterState();

  public void setRegisterState(RegisterState state) {
    this.registerState = state;
  }

  public RegisterState getRegisterState() {
    return registerState;
  }
}
