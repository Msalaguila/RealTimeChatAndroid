package es.msalaguila.realtimechat.Home;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.msalaguila.realtimechat.R;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

  public static String TAG = HomeActivity.class.getSimpleName();

  private HomeContract.Presenter presenter;
  private Button logoutButton;
  private Button newMessageButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    // do the setup
    HomeScreen.configure(this);

    logoutButton = findViewById(R.id.logoutButton);
    newMessageButton = findViewById(R.id.newMessageButton);


    logoutButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.logoutButtonPressed();
      }
    });

    newMessageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.newMessageButtonPressed();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();

    presenter.isUserLoggedIn();
  }

  @Override
  public void injectPresenter(HomeContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(HomeViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    // deal with the data
    // ((TextView) findViewById(R.id.data)).setText(viewModel.data);
  }

  @Override
  public Activity getActivity() {
    return this;
  }

  @Override
  public void finishActivity() {
    finish();
  }
}
