package es.msalaguila.realtimechat.app;

import java.util.List;

import es.msalaguila.realtimechat.Data.HomeMessage;
import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.Data.Message;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.Data.User;

public interface RepositoryInterface {

  interface CheckIfUserIsLoggedIn {
    void onUserIsLoggedIn(boolean isLoggedIn);
  }

  interface RegisterNewUser {
    void onNewUserRegistered(boolean error, boolean shortPassword, boolean isImageUri,
                             boolean allFieldsFilled);
  }

  interface LoginNewUser {
    void onUserLoggedIn(boolean error, boolean shortPassword, boolean isEmailFilled);
  }

  interface LogoutButtonPressed {
    void onLogoutButtonPressed();
  }

  interface GetCurrentUser {
    void onGetCurrentUser(RegisteredUser user);
  }

  interface GetCurrentUsers {
    void onGetCurrentUsers(List<RegisteredUser> currentUsers);
  }

  interface SendMessage {
    void onMessageSent();
  }

  interface LoadMessagesForTappedUserInsideChat {
    void onMessagesLoaded(List<Message> messages);
  }

  interface GetUserWithUID {
    void onUserRetrievedWithUID(User user);
  }

  interface LoadHomeMessages {
    void onHomeMessagesLoaded(List<HomeMessage> homeMessages);
  }

  /**
   * Method used when a message is going to be sent to a user
   * @param message: Text that is going to be sent
   * @param userMessageSentTo: User that the message is going to be sent
   * @param callback: Callback that is going to be called when the message has been sent
   */
  void sendMessageToUser(String message, RegisteredUser userMessageSentTo,
                         RepositoryInterface.SendMessage callback);

  /**
   * Method that checks when the application is launched whether the user was logged in or not
   * @param callback: Callback that is going to be called when the checking is done
   */
  void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);


  /**
   * Method that register a new user
   * @param user: User that is being registered
   * @param callback: Callback that is going to be called when the user is created
   */
  void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback);

  /**
   * Method that log-ins a new user previously created to the application
   * @param user: User to be logged-in
   * @param callback: Callback that is going to be called when the user is logged-in
   */
  void logInUser(LoginUser user, RepositoryInterface.LoginNewUser callback);

  /**
   * Method that log-outs the current in-app user
   * @param callback: Callback that is going to be called when user is logged-out
   */
  void logoutUser(RepositoryInterface.LogoutButtonPressed callback);

  /**
   * Method that gets called when the user log-ins to the application and retrieves the user
   * information
   * @param callback: Callback that is going to be called when the users are retrieved from the
   *                database
   */
  void getCurrentUser(RepositoryInterface.GetCurrentUser callback);

  /**
   * Method that gets called when the user taps on "New Message" Button and retrieves the
   * different registered users in the application
   * @param callback: Callback that is going to be called when the users are retrieved from the
   *                database
   */
  void getCurrentUsers(RepositoryInterface.GetCurrentUsers callback);

  /**
   * Method that loads the messages from the database for the tapped user when opening a chat
   * @param tappedUser: Tapped user to load messages to
   * @param callback: Callback that is going to be called when the messages are retrieved from
   *                the database
   */
  void loadMessagesForTappedUserInsideChat(RegisteredUser tappedUser
          , RepositoryInterface.LoadMessagesForTappedUserInsideChat callback);

  /**
   * Method that loads the information from an user when a chat is about to be opened
   * @param uid: Universal ID for the tapped user to retrieve its information later
   * @param callback: Callback that is going to be called when the user is retrieved from the
   *                database
   */
  void getUserWithUID(String uid, RepositoryInterface.GetUserWithUID callback);

  /**
   * Method that eliminates the reference from the database so the messages are not loaded anymore
   * when the user leaves the Chat Activity
   * @param userTappedToRemoveRef: User to remove the reference from
   */
  void eliminateInsideChatReference(RegisteredUser userTappedToRemoveRef);

  /**
   * Method that loads the messages for the in-app user when the application is opened
   * @param callback: Callback that is going to be called when the messages are retrieved from
   *                the database
   */
  void loadHomeMessages(RepositoryInterface.LoadHomeMessages callback);
}
