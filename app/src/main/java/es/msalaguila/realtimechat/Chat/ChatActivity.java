package es.msalaguila.realtimechat.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import es.msalaguila.realtimechat.NewMessage.NewMessageAdapter;
import es.msalaguila.realtimechat.R;

public class ChatActivity
        extends AppCompatActivity implements ChatContract.View {

  public static String TAG = ChatActivity.class.getSimpleName();

  private ChatContract.Presenter presenter;
  private RecyclerView recyclerView;
  private ChatAdapter chatAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);

    // do the setup
    ChatScreen.configure(this);

    chatAdapter = new ChatAdapter(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    recyclerView = findViewById(R.id.recyclerViewChat);
    recyclerView.setAdapter(chatAdapter);
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
