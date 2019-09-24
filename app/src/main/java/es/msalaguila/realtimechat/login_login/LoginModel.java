package es.msalaguila.realtimechat.login_login;

import android.util.Log;

import java.lang.ref.WeakReference;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;

public class LoginModel implements LoginContract.Model {

  public static String TAG = LoginModel.class.getSimpleName();

  public LoginModel() {

  }

  @Override
  public String fetchData() {
    // Log.e(TAG, "fetchData()");
    return "Hello";
  }
}
