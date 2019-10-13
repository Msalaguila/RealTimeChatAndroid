package es.msalaguila.realtimechat.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import es.msalaguila.realtimechat.R;

public class ChatActivity
        extends AppCompatActivity implements ChatContract.View {

  public static String TAG = ChatActivity.class.getSimpleName();

  private ChatContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);

    // do the setup
    ChatScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(ChatContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(ChatViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    // deal with the data
    // ((TextView) findViewById(R.id.data)).setText(viewModel.data);
  }
}
