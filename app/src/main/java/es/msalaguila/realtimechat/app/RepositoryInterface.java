package es.msalaguila.realtimechat.app;

import java.util.ArrayList;
import java.util.List;

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

  void sendMessageToUser(String message, RegisteredUser userMessageSentTo,
                         RepositoryInterface.SendMessage callback);

  void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);

  void registerNewUser(RegisteredUser user, RepositoryInterface.RegisterNewUser callback);

  void logInUser(LoginUser user, RepositoryInterface.LoginNewUser callback);

  void logoutUser(RepositoryInterface.LogoutButtonPressed callback);

  void getCurrentUser(RepositoryInterface.GetCurrentUser callback);

  void getCurrentUsers(RepositoryInterface.GetCurrentUsers callback);

  void loadMessagesForTappedUserInsideChat(RegisteredUser tappedUser
          , RepositoryInterface.LoadMessagesForTappedUserInsideChat callback);

  void getUserWithUID(String uid, RepositoryInterface.GetUserWithUID callback);

  void eliminateInsideChatReference(RegisteredUser userTappedToRemoveRef);
}
