package es.msalaguila.realtimechat.Data;

import android.net.Uri;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisteredUser {

  private String name;
  private String email;
  private String password;
  private CircleImageView profileImage;
  private Uri profileImageUri;

  public RegisteredUser(String name, String email, String password, CircleImageView profileImage,
                        Uri profileImageUri) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.profileImage = profileImage;
    this.profileImageUri = profileImageUri;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public CircleImageView getProfileImage() {
    return profileImage;
  }

  public Uri getProfileImageUri() {
    return profileImageUri;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setProfileImage(CircleImageView profileImage) {
    this.profileImage = profileImage;
  }

  public void setProfileImageUri(Uri profileImageUri) {
    this.profileImageUri = profileImageUri;
  }
}
