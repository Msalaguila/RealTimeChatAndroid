package es.msalaguila.realtimechat.app;

import android.content.Context;

public class Repository implements RepositoryInterface {

  public static String TAG = Repository.class.getSimpleName();

  // private FirebaseAuth mAuth;

  private static Repository INSTANCE;

  private Context context;


  public static RepositoryInterface getInstance(Context context) {
    if(INSTANCE == null){
      INSTANCE = new Repository(context);
    }
    return INSTANCE;
  }

  // CREACIÓN DE LA BASE DE DATOS
  private Repository(Context context) {
    this.context = context;
  }

  @Override
  public void isUserLoggedIn(CheckIfUserIsLoggedIn callback) {

  }
}
