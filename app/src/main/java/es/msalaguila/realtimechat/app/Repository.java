package es.msalaguila.realtimechat.app;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.Data.RegisteredUser;

public class Repository implements RepositoryInterface {

  public static String TAG = Repository.class.getSimpleName();

  private FirebaseAuth mAuth;

  private static Repository INSTANCE;

  private Context context;


  public static RepositoryInterface getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = new Repository(context);
    }
    return INSTANCE;
  }

  private Repository(Context context) {
    this.context = context;
  }

  @Override
  public void isUserLoggedIn(CheckIfUserIsLoggedIn callback) {
    mAuth = FirebaseAuth.getInstance();
    if (!mAuth.getCurrentUser().getUid().equals("")) {
      // User is logged in
      callback.onUserIsLoggedIn(true);
    } else {
      callback.onUserIsLoggedIn(false);
    }

  }

  @Override
  public void registerNewUser(RegisteredUser user, RegisterNewUser callback) {

    mAuth = FirebaseAuth.getInstance();

    String password = user.getPassword();
    Uri imageUri = user.getProfileImageUri();

    // 1) We check whether all the fields are filled or not
    if (user.getEmail().equals("") || user.getName().equals("")) {
      callback.onNewUserRegistered(false, true, false, false);
    }

    // 2) We check whether the password is too short or not

    else if (password.length() < 6) {
      callback.onNewUserRegistered(false, true, false, true);
    }

    // 3) We check whether an image has been selected

    else if (imageUri == null) {
      callback.onNewUserRegistered(false, false, false, true);
    }

    // 4) If there are no problems, we create the user

    else {
      createNewUser(user, callback);
    }
  }

  @Override
  public void logInUser(LoginUser user, final LoginNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    String email = user.getEmail();
    String password = user.getPassword();

    // 1) We check whether the email fill is filled
    if (email.equals("")) {
      callback.onUserLoggedIn(false, false, false);
    }

    // 2) We check whether the password is too short or not
    else if (password.length() < 6) {
      callback.onUserLoggedIn(false, true, true);
    }

    // 3) If there are no problems, we log in the user

    else {
      mAuth.signInWithEmailAndPassword(email, password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                    // Sign in succesful
                    callback.onUserLoggedIn(false, false, true);
                  } else {
                    // Sign in failed
                    callback.onUserLoggedIn(true, false, true);
                  }
                }
              });
    }
  }

  private void createNewUser(final RegisteredUser user, final RegisterNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    // TODO: Complete method. Register user with email and password. Afterwards, upload image to

    Log.d("Repository", "Entered in Create User Method");

    String email = user.getEmail();
    String password = user.getPassword();

    if (mAuth != null){
      Log.d("Repository", "Firebase no es nulo.");
    }
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  // If there is any error while creating the user
                  String userUID = mAuth.getCurrentUser().getUid();
                  String imageName = UUID.randomUUID().toString();
                  Uri imageUri = user.getProfileImageUri();

                  Log.d("Repository", "Image Name" + imageName);

                  StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                          .child("profile_images").child(imageName + ".png");

                  storageRef.putFile(imageUri)
                          .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                              if (task.isSuccessful()) {
                                callback.onNewUserRegistered(false, false,
                                        false, false);
                              }
                            }
                          });
                } else {
                  // Creation succesful

                    Log.d("Registration Failed", "" + task.getException().getMessage());
                    return;


                }
              }
            });

  }


}
