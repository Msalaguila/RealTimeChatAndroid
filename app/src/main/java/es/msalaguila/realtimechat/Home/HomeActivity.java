package es.msalaguila.realtimechat.Home;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.R;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

  public static String TAG = HomeActivity.class.getSimpleName();

  private HomeContract.Presenter presenter;
  private Button logoutButton;
  private Button newMessageButton;
  private CircleImageView profilePhotoImageView;
  private TextView profileNameHome;

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

  @Override
  public void displayCurrentUser(HomeViewModel viewModel) {
    String userName = viewModel.registeredUser.getName();
    String profileImageURL = viewModel.registeredUser.getProfileImageUrl();

    profileNameHome.setText(userName);
    loadImageFromURL(profilePhotoImageView, profileImageURL);
  }

  private void loadImageFromURL(ImageView imageView, String imageUrl){
    RequestManager reqManager = Glide.with(imageView.getContext());
    RequestBuilder reqBuilder = reqManager.load(imageUrl);
    RequestOptions reqOptions = new RequestOptions();
    reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    reqBuilder.apply(reqOptions);
    reqBuilder.into(imageView);
  }


}
