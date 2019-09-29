package es.msalaguila.realtimechat.login_register;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.provider.MediaStore;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.login_login.LoginActivity;

public class RegisterRouter implements RegisterContract.Router {

  public static String TAG = RegisterRouter.class.getSimpleName();

  private static final int PICK_IMAGE = 100;

  private AppMediator mediator;

  public RegisterRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, RegisterActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(RegisterState state) {
    mediator.setRegisterState(state);
  }

  @Override
  public void navigateToLogin(Activity activity) {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, LoginActivity.class);
    activity.startActivity(intent);
  }

  @Override
  public RegisterState getDataFromPreviousScreen() {
    RegisterState state = mediator.getRegisterState();
    return state;
  }

  @Override
  public void openGallery(Activity activity) {
    Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    gallery.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    activity.startActivityForResult(gallery, PICK_IMAGE);
  }


}
