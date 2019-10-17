package es.msalaguila.realtimechat.Data;

public class User {

  private String id;
  private String name;
  private String email;
  private String profileImageUrl;

  public User(String id, String name, String email, String profileImageUrl) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.profileImageUrl = profileImageUrl;
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

  public String getImageUrl() {
    return profileImageUrl;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setImageUrl(String imageUrl) {
    this.profileImageUrl = imageUrl;
  }
}
