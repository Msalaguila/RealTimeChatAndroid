package es.msalaguila.realtimechat.NewMessage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.msalaguila.realtimechat.R;

public class NewMessageActivity
        extends AppCompatActivity implements NewMessageContract.View {

  public static String TAG = NewMessageActivity.class.getSimpleName();

  private NewMessageContract.Presenter presenter;
  private ImageView backButton;
  private RecyclerView recyclerView;
  private NewMessageAdapter newMessageAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_message);

    // do the setup
    NewMessageScreen.configure(this);

    backButton = findViewById(R.id.backButtonNewMessage);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.onBackButtonPressed();
      }
    });

    newMessageAdapter = new NewMessageAdapter(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

      }
    });

    recyclerView = findViewById(R.id.recyclerViewNewMessage);
    recyclerView.setAdapter(newMessageAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
    presenter.getCurrentUsers();
  }

  @Override
  public void injectPresenter(NewMessageContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(NewMessageViewModel viewModel) {
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

  @Override
  public void displayCurrentUsers(final NewMessageViewModel viewModel) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        newMessageAdapter.setItems(viewModel.currentUsers);
      }
    });
  }
}
