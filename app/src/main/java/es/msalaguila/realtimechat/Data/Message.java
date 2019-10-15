package es.msalaguila.realtimechat.Data;

import com.google.firebase.auth.FirebaseAuth;

public class Message {

  private String fromID;
  private String toID;
  private Long timestamp;
  private String message;
  private String profileImageURL;

  public Message(String fromID, String toID, Long timestamp, String message, String profileImageURL) {
    this.fromID = fromID;
    this.toID = toID;
    this.timestamp = timestamp;
    this.message = message;
    this.profileImageURL = profileImageURL;
  }

  public String chatPartnerID() {
    String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    return fromID.equals(currentUserUID) ? toID : fromID;
  }

  public void setFromID(String fromID) {
    this.fromID = fromID;
  }

  public void setToID(String toID) {
    this.toID = toID;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setProfileImageURL(String profileImageURL) {
    this.profileImageURL = profileImageURL;
  }

  public String getFromID() {
    return fromID;
  }

  public String getToID() {
    return toID;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getProfileImageURL() {
    return profileImageURL;
  }
}
