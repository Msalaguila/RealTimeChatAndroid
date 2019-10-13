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
  private String profileImageUrl;
  private String id;

  // Constructor for New User

  public RegisteredUser(String name, String email, String password, CircleImageView profileImage,
                        Uri profileImageUri) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.profileImage = profileImage;
    this.profileImageUri = profileImageUri;
  }


  // Constructor for User from Firebase Database

  public RegisteredUser(String name, String email, String profileImageUrl, String id) {
    this.name = name;
    this.email = email;
    this.profileImageUrl = profileImageUrl;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getProfileImageUrl() {
    return profileImageUrl;
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

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }
}
