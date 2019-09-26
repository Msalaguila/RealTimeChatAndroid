package es.msalaguila.realtimechat.app;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

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
      return;
    } else {
      callback.onUserIsLoggedIn(false);
    }

  }

  @Override
  public void registerNewUser(RegisteredUser user, RegisterNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    // Firstly we check whether the password is too short or not
    String password = user.getPassword();

    if (password.length() < 6) {
      callback.onNewUserRegistered(false, true);
      return;
    } else {
      createNewUser(user, callback);
    }
  }

  private void createNewUser(RegisteredUser user, RegisterNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    // TODO: Complete method. Register user with email and password. Afterwards, upload image to
    // storage

  }


}
