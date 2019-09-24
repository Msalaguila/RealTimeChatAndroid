package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.msalaguila.realtimechat.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

  public static String TAG = RegisterActivity.class.getSimpleName();

  private RegisterContract.Presenter presenter;
  private EditText nameEditText;
  private EditText emailEditText;
  private EditText passwordEditText;
  private Button loginButtonSC;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    nameEditText = findViewById(R.id.nameEditTextRegister);
    emailEditText = findViewById(R.id.emailEditTextRegister);
    passwordEditText = findViewById(R.id.passwordEditTextRegister);
    loginButtonSC = findViewById(R.id.loginButtonSCRegister);

    Log.d("RegisterActivity", "Name: " + nameEditText.getText().toString());

    loginButtonSC.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("RegisterActivity", "Login Button SC Clicked");
        presenter.routeToLogin(getActivity());
        finish();
      }
    });

    // do the setup
    RegisterScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(RegisterContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(RegisterViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    // deal with the data
  }

  @Override
  public Activity getActivity() {
    return this;
  }
}
