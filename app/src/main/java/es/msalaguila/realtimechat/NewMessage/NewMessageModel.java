package es.msalaguila.realtimechat.NewMessage;

import android.util.Log;

import java.lang.ref.WeakReference;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.RepositoryInterface;

public class NewMessageModel implements NewMessageContract.Model {

  public static String TAG = NewMessageModel.class.getSimpleName();

  private RepositoryInterface repository;

  public NewMessageModel(RepositoryInterface repository) {
    this.repository = repository;
  }

  @Override
  public String fetchData() {
    // Log.e(TAG, "fetchData()");
    return "Hello";
  }
}
