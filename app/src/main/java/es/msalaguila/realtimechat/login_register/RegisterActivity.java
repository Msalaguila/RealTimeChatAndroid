package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.R;
import es.msalaguila.realtimechat.app.RepositoryInterface;

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
  private Button registerButton;

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
    registerButton = findViewById(R.id.registerButton);

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

    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("RegisterActivity", "Register Button pressed");
          String name = nameEditText.getText().toString();
          String email = emailEditText.getText().toString();
          String password = passwordEditText.getText().toString();


          RegisteredUser user = new RegisteredUser(name, email, password, profilePhotoImageView, imageUri);
          presenter.onRegisterButtonPressed(user);
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

  @Override
  public void displayPasswordTooShort() {

    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Password too short");
    builder1.setMessage("The password needs to have at least 6 characters");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert11 = builder1.create();
    alert11.show();

    // Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void displayPickUpPhotoImage() {
    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Select a Profile Photo");
    builder1.setMessage("You must select a photo in order to register.");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert11 = builder1.create();
    alert11.show();
  }

  @Override
  public void displayFillAllFieldsMessage() {
    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Fill All Fields");
    builder1.setMessage("All the fields must be filled in order to register.");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert11 = builder1.create();
    alert11.show();
  }

  @Override
  public void displayRegisteredSuccesful() {
    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Registration Succesful");
    builder1.setMessage("You have been registered succesfully");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                presenter.routeToHome();
                dialog.cancel();
                finish();
              }
            });
    AlertDialog alert11 = builder1.create();
    alert11.show();
  }

  @Override
  public void displayError() {
    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
    builder1.setTitle("Registration Failed");
    builder1.setMessage("An error has occured");
    builder1.setCancelable(true);

    builder1.setNegativeButton(
            "Dismiss",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });
    AlertDialog alert11 = builder1.create();
    alert11.show();
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
