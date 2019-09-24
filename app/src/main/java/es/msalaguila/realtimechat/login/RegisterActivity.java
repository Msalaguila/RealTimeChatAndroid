package es.msalaguila.realtimechat.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import es.msalaguila.realtimechat.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

  public static String TAG = RegisterActivity.class.getSimpleName();

  private RegisterContract.Presenter presenter;
  private EditText nameEditText;
  private EditText emailEditText;
  private EditText passwordEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    nameEditText = findViewById(R.id.nameEditText);
    emailEditText = findViewById(R.id.emailEditText);
    passwordEditText = findViewById(R.id.passwordEditText);

    Log.d("RegisterActivity", "Name: " + nameEditText.getText().toString());

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
}
