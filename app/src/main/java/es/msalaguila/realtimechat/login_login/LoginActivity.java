package es.msalaguila.realtimechat.login_login;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.msalaguila.realtimechat.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

  public static String TAG = LoginActivity.class.getSimpleName();

  private LoginContract.Presenter presenter;

  private Button registerButtonSC;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    registerButtonSC = findViewById(R.id.registerButtonSCLogin);
    registerButtonSC.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.routeToRegister(getActivity());
        finish();
      }
    });

    // do the setup
    LoginScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(LoginContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(LoginViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    // deal with the data

  }

  @Override
  public Activity getActivity() {
    return this;
  }
}
