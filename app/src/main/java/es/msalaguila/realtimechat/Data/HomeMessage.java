package es.msalaguila.realtimechat.Data;

public class HomeMessage {

  private String profileImageURL;
  private String profileName;
  private Long timestamp;
  private String message;
  private String fromID;
  private String toID;

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
    this.fromID = fromID;
    this.toID = toID;
  }

  public void setFromID(String fromID) {
    this.fromID = fromID;
  }

  public void setToID(String toID) {
    this.toID = toID;
  }

  public String getFromID() {
    return fromID;
  }

  public String getToID() {
    return toID;
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
