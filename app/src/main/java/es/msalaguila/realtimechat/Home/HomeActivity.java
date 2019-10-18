package es.msalaguila.realtimechat.Home;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.Data.HomeMessage;
import es.msalaguila.realtimechat.Data.HomeWatcher;
import es.msalaguila.realtimechat.Data.OnHomePressedListener;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.R;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

  public static String TAG = HomeActivity.class.getSimpleName();

  private HomeContract.Presenter presenter;
  private Button logoutButton;
  private Button newMessageButton;
  private CircleImageView profilePhotoImageView;
  private TextView profileNameHome;

  private RecyclerView homeRecyclerView;
  private HomeAdapter homeAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    // do the setup
    HomeScreen.configure(this);

    logoutButton = findViewById(R.id.logoutButton);
    newMessageButton = findViewById(R.id.newMessageButton);
    profilePhotoImageView = findViewById(R.id.profilePhotoImageViewHome);
    profileNameHome = findViewById(R.id.profileNameHome);

    logoutButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.logoutButtonPressed();
      }
    });

    newMessageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // onScreen = false;

        Log.d("ON", "ON NEW MESSAGE: " + onScreen);

        presenter.newMessageButtonPressed();
      }
    });

    homeAdapter = new HomeAdapter(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        // onScreen = false;

        Log.d("ON", "ON CELL: " + onScreen);

        HomeMessage userTapped = (HomeMessage) view.getTag();
        String name = userTapped.getProfileName();
        String profileImageURL = userTapped.getProfileImageURL();
        String userID = userTapped.getUser().getId();

        RegisteredUser userToPassData = new RegisteredUser(name, "", profileImageURL, userID);
        presenter.userTapped(userToPassData);
      }
    });

    homeRecyclerView = findViewById(R.id.recyclerViewHome);
    homeRecyclerView.setAdapter(homeAdapter);
  }

  private boolean onScreen;
  private boolean comingFromOutside;

  @Override
  protected void onResume() {
    onScreen = true;
    comingFromOutside = true;

    super.onResume();

    // do some work
    presenter.saveCurrentNotificationState(onScreen);
    presenter.fetchData();
    presenter.isUserLoggedIn();

    Log.d("ON", "ON RESUME: " + onScreen);
  }

  @Override
  protected void onPause() {
    if (isApplicationSentToBackground(this)) {
      onScreen = false;
      comingFromOutside = false;
      Log.d("ON","HOME BUTTON PRESSED: " + onScreen);
      presenter.saveCurrentNotificationState(onScreen);
    }

    super.onPause();
  }

  @Override
  public void onBackPressed() {
    onScreen = false;
    comingFromOutside = false;
    presenter.saveCurrentNotificationState(onScreen);

    super.onBackPressed();

    Log.d("ON", "ON BACK PRESSED: " + onScreen);
  }

  private String CHANNEL_ID = "1";
  private String channelName = "channelName";
  private String channelDescription = "channelDescription";
  private int notificationId = 1;

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

  @Override
  public void displayCurrentUser(HomeViewModel viewModel) {
    onScreen = true;
    String userName = viewModel.registeredUser.getName();
    String profileImageURL = viewModel.registeredUser.getProfileImageUrl();

    profileNameHome.setText(userName);
    loadImageFromURL(profilePhotoImageView, profileImageURL);
  }

  @Override
  public void displayHomeMessages(final HomeViewModel viewModel) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        homeAdapter.setMessages(viewModel.homeMessageList);
        HomeMessage lastMessage = viewModel.homeMessageList.get(0);

        // checkVariable();

        boolean screenState = viewModel.onScreen;
        showNotification(lastMessage, screenState);
      }
    });
  }

  private void checkVariable() {
    if (!comingFromOutside && !onScreen) {
      onScreen = true;
    }
  }

  private void loadImageFromURL(ImageView imageView, String imageUrl){
    RequestManager reqManager = Glide.with(imageView.getContext());
    RequestBuilder reqBuilder = reqManager.load(imageUrl);
    RequestOptions reqOptions = new RequestOptions();
    reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    reqBuilder.apply(reqOptions);
    reqBuilder.into(imageView);
  }

  private void showNotification(HomeMessage lastMessage, boolean screenState) {
    Log.d("OND", "DISPLAY MESSAGES: " + onScreen);

    if (!screenState) {
      long[] pattern = {500,500,500};

      NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
              .setSmallIcon(R.drawable.user_logo)
              .setContentTitle("My notification")
              .setContentText(lastMessage.getMessage())
              .setStyle(new NotificationCompat.BigTextStyle()
                      .bigText(lastMessage.getMessage()))
              .setPriority(NotificationCompat.PRIORITY_DEFAULT)
              .setVisibility(1)
              .setVibrate(pattern);

      createNotificationChannel();

      NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
      notificationManager.notify(notificationId, builder.build());
    }


  }

  private void createNotificationChannel() {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      CharSequence name = channelName;
      String description = channelDescription;
      int importance = NotificationManager.IMPORTANCE_HIGH;
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
      channel.setDescription(description);
      // Register the channel with the system; you can't change the importance
      // or other notification behaviors after this
      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }

  public boolean isApplicationSentToBackground(final Context context) {
    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
    if (!tasks.isEmpty()) {
      ComponentName topActivity = tasks.get(0).topActivity;
      if (!topActivity.getPackageName().equals(context.getPackageName())) {
        return true;
      }
    }
    return false;
  }

}
