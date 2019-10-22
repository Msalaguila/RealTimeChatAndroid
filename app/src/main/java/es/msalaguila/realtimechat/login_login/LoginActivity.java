package es.msalaguila.realtimechat.login_login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

  public static String TAG = LoginActivity.class.getSimpleName();

  private LoginContract.Presenter presenter;

  private Button registerButtonSC;
  private Button loginButton;
  private EditText userEmail;
  private EditText userPassword;
  private ProgressBar progressBarLogin;

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

    userEmail = findViewById(R.id.emailEditTextLogin);
    userPassword = findViewById(R.id.passwordEditTextLogin);
    progressBarLogin = findViewById(R.id.progressbar_login);

    loginButton = findViewById(R.id.loginButton);

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        startProgressBar();

        LoginUser user = new LoginUser(email, password);
        presenter.onLoginButtonPressed(user);
      }
    });

    // do the setup
    LoginScreen.configure(this);
  }

  private void startProgressBar() {
    progressBarLogin.setVisibility(View.VISIBLE);
    progressBarLogin.setEnabled(true);
  }

  private void stopProgressBar() {
    progressBarLogin.setVisibility(View.INVISIBLE);
    progressBarLogin.setEnabled(false);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
  }

  @Override
  public void injectPresenter(LoginContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public Activity getActivity() {
    return this;
  }

  @Override
  public void displayPasswordTooShort() {

    stopProgressBar();

    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Password too short");
    builder1.setMessage("The password needs to have at least 6 characters");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert11 = builder1.create();
    alert11.show();

  }

  @Override
  public void displayLoginErrorAlert() {

    stopProgressBar();

    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Check your credentials");
    builder1.setMessage("Are you sure you have an account? Check your credentials again.");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert11 = builder1.create();
    alert11.show();

  }

  @Override
  public void displayFillEmailAlert() {

    stopProgressBar();

    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Fill Email Field");
    builder1.setMessage("The email field must be filled in order to log in.");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert11 = builder1.create();
    alert11.show();
  }

  @Override
  public void finishActivity() {
    finish();
  }
}
