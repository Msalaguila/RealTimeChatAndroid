package es.msalaguila.realtimechat.Data;

public class HomeMessage {

  private String profileImageURL;
  private String profileName;
  private Long timestamp;
  private String message;
  private String uid;
  private User user;
  private String keyToReloadHomeMessages;

  public HomeMessage(String profileImageURL, String profileName, Long timestamp, String message, User user) {
    this.profileImageURL = profileImageURL;
    this.profileName = profileName;
    this.timestamp = timestamp;
    this.message = message;
    this.user = user;
  }

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

  public String getKeyToReloadHomeMessages() {
    return keyToReloadHomeMessages;
  }

  public void setKeyToReloadHomeMessages(String keyToReloadHomeMessages) {
    this.keyToReloadHomeMessages = keyToReloadHomeMessages;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUID(String uid) {
    this.uid = uid;
  }

  public String getUID() {
    return uid;
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
