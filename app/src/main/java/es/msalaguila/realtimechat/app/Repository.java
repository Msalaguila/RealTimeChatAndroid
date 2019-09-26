package es.msalaguila.realtimechat.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

  @Override
  public void logInUser(LoginUser user, final LoginNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    String email = user.getEmail();
    String password = user.getPassword();

    // Firstly we check whether the password is too short or not
    if (password.length() < 6) {
      callback.onUserLoggedIn(false, true);
      return;
    } else {
      mAuth.signInWithEmailAndPassword(email, password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                    // Sign in succesful
                    callback.onUserLoggedIn(false, false);
                    return;
                  } else {
                    // Sign in failed
                    callback.onUserLoggedIn(true, false);
                    return;
                  }
                }
              });
    }
  }

  private void createNewUser(RegisteredUser user, RegisterNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    // TODO: Complete method. Register user with email and password. Afterwards, upload image to
    // storage

  }


}
