package es.msalaguila.realtimechat.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.core.Repo;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.NewMessage.NewMessageAdapter;
import es.msalaguila.realtimechat.R;
import es.msalaguila.realtimechat.app.Repository;

public class ChatActivity
        extends AppCompatActivity implements ChatContract.View {

  public static String TAG = ChatActivity.class.getSimpleName();

  private ChatContract.Presenter presenter;
  private RecyclerView recyclerView;
  private ChatAdapter chatAdapter;
  private TextView profileNameChat;
  private ImageView backButtonChat;
  private EditText inputEditText;
  private Button sendButton;

  //TODO: Remove chatReference from Firebase when getting out of this Activity

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);

    // do the setup
    ChatScreen.configure(this);

    profileNameChat = findViewById(R.id.profileNameChat);
    backButtonChat = findViewById(R.id.backButtonChat);
    inputEditText = findViewById(R.id.inputTextFieldChat);
    sendButton = findViewById(R.id.sendButtonChat);

    chatAdapter = new ChatAdapter(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    backButtonChat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Close the activity when the back icon is pressed
        finish();
      }
    });

    sendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isInputTextFieldFull()) {
          presenter.sendButtonPressed(inputEditText.getText().toString());
        }
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
    presenter.getUserTappedFromPreviousScreen();
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.eliminateInsideChatReference();
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

  @Override
  public void displayUserTappedName(ChatViewModel viewModel) {
    profileNameChat.setText(viewModel.userTapped.getName());
    presenter.loadMessagesForTappedUser();
  }

  @Override
  public void cleanInputTextField() {
    inputEditText.getText().clear();
    inputEditText.setText("");
  }

  @Override
  public void displayChatMessages(final ChatViewModel viewModel) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        chatAdapter.setMessages(viewModel.chatMessages);
        recyclerView.scrollToPosition(chatAdapter.chatMessages.size() - 1);
      }
    });
  }

  private boolean isInputTextFieldFull() {
    if (inputEditText.length()!=0) {
      return true;
    } else {
      return false;
    }
  }
}
