package es.msalaguila.realtimechat.Data;

public class HomeMessage {

  private String profileImageURL;
  private String profileName;
  private Long timestamp;
  private String message;
  private String uid;

  public HomeMessage(String profileImageURL, String profileName, Long timestamp, String message) {
    this.profileImageURL = profileImageURL;
    this.profileName = profileName;
    this.timestamp = timestamp;
    this.message = message;
  }

  public HomeMessage(String profileImageURL, String profileName, Long timestamp, String message
          , String fromID, String toID) {
    this.profileImageURL = profileImageURL;
    this.profileName = profileName;
    this.timestamp = timestamp;
    this.message = message;
  }

  public void setProfileImageURL(String profileImageURL) {
    this.profileImageURL = profileImageURL;
  }

  public void setProfileName(String profileName) {
    this.profileName = profileName;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getProfileImageURL() {
    return profileImageURL;
  }

  public String getProfileName() {
    return profileName;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }
}
