package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

  public static String TAG = RegisterActivity.class.getSimpleName();

  private static final int PICK_IMAGE = 100;
  private Uri imageUri;

  private RegisterContract.Presenter presenter;
  private EditText nameEditText;
  private EditText emailEditText;
  private EditText passwordEditText;
  private Button loginButtonSC;
  private ImageView photoImageView;
  private CircleImageView profilePhotoImageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    nameEditText = findViewById(R.id.nameEditTextRegister);
    emailEditText = findViewById(R.id.emailEditTextRegister);
    passwordEditText = findViewById(R.id.passwordEditTextRegister);
    loginButtonSC = findViewById(R.id.loginButtonSCRegister);
    photoImageView = findViewById(R.id.photoImageView);
    profilePhotoImageView = findViewById(R.id.profilePhotoImageView);

    Log.d("RegisterActivity", "Name: " + nameEditText.getText().toString());

    // do the setup
    RegisterScreen.configure(this);

    loginButtonSC.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("RegisterActivity", "Login Button SC Clicked");
        presenter.routeToLogin(getActivity());
        finish();
      }
    });

    photoImageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.openGallery(getActivity());
      }
    });
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


  /**
   * Deals with Gallery once the photo has been chosen
   * @param requestCode
   * @param resultCode
   * @param data
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
      imageUri = data.getData();
      try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
        photoImageView.setImageAlpha(0);
        profilePhotoImageView.setImageBitmap(bitmap);
      } catch (IOException e) {
        e.printStackTrace();
      }

      // photoImageView.setImageURI(imageUri);
    }

  }
}
