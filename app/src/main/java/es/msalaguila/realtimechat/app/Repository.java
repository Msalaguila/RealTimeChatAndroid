package es.msalaguila.realtimechat.app;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.msalaguila.realtimechat.Data.LoginUser;
import es.msalaguila.realtimechat.Data.Message;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.Data.SortMessageByTimestamp;
import es.msalaguila.realtimechat.Data.User;

public class Repository implements RepositoryInterface {

  public static String TAG = Repository.class.getSimpleName();

  private FirebaseAuth mAuth;

  private static Repository INSTANCE;

  private DatabaseReference refInsideChat = FirebaseDatabase.getInstance().getReference().child("user-messages");
  private DatabaseReference refMessagesInsideChat = FirebaseDatabase.getInstance().getReference().child("messages");

  private String currentUserInAppID = FirebaseAuth.getInstance().getCurrentUser().getUid();

  private ChildEventListener listenerInsideChat;
  private ValueEventListener messageListenerInsideChat;

  private DatabaseReference homeMessagesReference = FirebaseDatabase.getInstance().getReference().child("latest-messages");
  private ChildEventListener homeMessagesListener;

  private Context context;

  // Array to retrieve users from the FirebaseDatabase
  private ArrayList<RegisteredUser> currentUsers = new ArrayList<>();

  private ArrayList<Message> chatMessages = new ArrayList<>();

  public static RepositoryInterface getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = new Repository(context);
    }
    return INSTANCE;
  }

  private Repository(Context context) {
    this.context = context;
  }

  @Override
  public void sendMessageToUser(String message, RegisteredUser userMessageSentTo,
                                final SendMessage callback) {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("messages");
    final DatabaseReference messageReference = databaseReference.push();

    final String fromUserID = mAuth.getCurrentUser().getUid();
    final String toUserID = userMessageSentTo.getId();
    Long timestamp = System.currentTimeMillis()/1000;



    Log.d("Repository", "From user ID " + fromUserID);
    Log.d("Repository", "To user ID " + toUserID);

    HashMap<String, Object> values = new HashMap<>();
    values.put("text", message);
    values.put("fromID", fromUserID);
    values.put("toID", toUserID);
    values.put("timestamp", timestamp);

    sendMessageToUserHome(userMessageSentTo, values);

    messageReference.updateChildren(values, new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(@Nullable DatabaseError databaseError,
                             @NonNull DatabaseReference databaseReference) {
          if (databaseError != null) {
            Log.d("Repository", "Error sending message: " + databaseError.toString());
          }

          String messageID = messageReference.getKey();

        Log.d("Repository", "Message ID " + messageID);

          DatabaseReference fromUserMessagesRef = FirebaseDatabase.getInstance().getReference()
                  .child("user-messages").child(fromUserID).child(messageID);

        fromUserMessagesRef.setValue(1);

        DatabaseReference toUserMessagesRef = FirebaseDatabase.getInstance().getReference()
                .child("user-messages").child(toUserID).child(messageID);

        toUserMessagesRef.setValue(1);

        callback.onMessageSent();
      }
    });
  }

  private void sendMessageToUserHome(RegisteredUser userMessageSentTo, HashMap<String, Object> values) {
    homeMessagesReference.child(currentUserInAppID).child(userMessageSentTo.getId()).setValue(values);

  }

  @Override
  public void isUserLoggedIn(CheckIfUserIsLoggedIn callback) {
    mAuth = FirebaseAuth.getInstance();

    if (mAuth.getCurrentUser() != null) {
      // User is logged in
      callback.onUserIsLoggedIn(true);
    } else {
      callback.onUserIsLoggedIn(false);
    }
  }

  @Override
  public void registerNewUser(RegisteredUser user, RegisterNewUser callback) {

    mAuth = FirebaseAuth.getInstance();

    String password = user.getPassword();
    Uri imageUri = user.getProfileImageUri();

    // 1) We check whether all the fields are filled or not
    if (user.getEmail().equals("") || user.getName().equals("") || password.length() == 0) {
      callback.onNewUserRegistered(false, false, false, false);
    }

    // 2) We check whether the password is too short or not

    else if (password.length() < 6) {
      callback.onNewUserRegistered(false, true, false, true);
    }

    // 3) We check whether an image has been selected

    else if (imageUri == null) {
      callback.onNewUserRegistered(false, false, false, true);
    }

    // 4) If there are no problems, we create the user

    else {
      createNewUser(user, callback);
    }
  }

  @Override
  public void logInUser(LoginUser user, final LoginNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    String email = user.getEmail();
    String password = user.getPassword();

    // 1) We check whether the email fill is filled
    if (email.equals("")) {
      callback.onUserLoggedIn(false, false, false);
    }

    // 2) We check whether the password is too short or not
    else if (password.length() < 6) {
      callback.onUserLoggedIn(false, true, true);
    }

    // 3) If there are no problems, we log in the user

    else {
      mAuth.signInWithEmailAndPassword(email, password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                    // Sign in succesful
                    callback.onUserLoggedIn(false, false, true);
                  } else {
                    // Sign in failed
                    callback.onUserLoggedIn(true, false, true);
                  }
                }
              });
    }
  }

  @Override
  public void logoutUser(LogoutButtonPressed callback) {
    mAuth = FirebaseAuth.getInstance();

    mAuth.signOut();

    callback.onLogoutButtonPressed();
  }

  @Override
  public void getCurrentUser(final GetCurrentUser callback) {
    final String uid = mAuth.getCurrentUser().getUid();
    FirebaseDatabase.getInstance().getReference().child("users").child(uid)
            .addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String snapshot = dataSnapshot.toString();

                HashMap<String, Object> dictionary = (HashMap<String, Object>) dataSnapshot.getValue();
                String name = (String) dictionary.get("name");
                String email = (String) dictionary.get("email");
                String profileImageUrl = (String) dictionary.get("profileImageUrl");

                RegisteredUser user = new RegisteredUser(name, email, profileImageUrl, uid);
                callback.onGetCurrentUser(user);
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Repository", "Error getting Current User " + databaseError.toString());
              }
            });
  }

  @Override
  public void getCurrentUsers(final GetCurrentUsers callback) {

    currentUsers = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
    ref.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        String uid = dataSnapshot.getKey();

        String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (!currentUserUID.equals(uid)) {
          HashMap<String, Object> dictionary = (HashMap<String, Object>) dataSnapshot.getValue();
          String name = (String) dictionary.get("name");
          String email = (String) dictionary.get("email");
          String profileImageUrl = (String) dictionary.get("profileImageUrl");

          RegisteredUser user = new RegisteredUser(name, email, profileImageUrl, uid);
          currentUsers.add(user);

          callback.onGetCurrentUsers(currentUsers);
        }
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  @Override
  public void loadMessagesForTappedUserInsideChat(RegisteredUser tappedUser
          , final LoadMessagesForTappedUserInsideChat callback) {

    final String tappedUserUID = tappedUser.getId();

    chatMessages.clear();

    getUserWithUID(tappedUserUID, new GetUserWithUID() {
      @Override
      public void onUserRetrievedWithUID(User user) {

        final String profileImageURL = user.getImageUrl();

        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        getMessageForUser(profileImageURL, currentUserID, tappedUserUID, callback);
      }
    });
  }

  private void getMessageForUser(final String profileImageURL, String currentUserID
          , final String tappedUserUID, final LoadMessagesForTappedUserInsideChat callback) {

    listenerInsideChat = refInsideChat.child(currentUserID).addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        String messageID = dataSnapshot.getKey();

        refMessagesInsideChat = FirebaseDatabase.getInstance().getReference()
                .child("messages").child(messageID);

        getMessageWithUID(refMessagesInsideChat, profileImageURL, tappedUserUID, callback);
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void getMessageWithUID(DatabaseReference messageRef
          , final String profileImageURL, final String tappedUserUID
          , final LoadMessagesForTappedUserInsideChat callback) {
    messageListenerInsideChat = messageRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        HashMap<String, Object> dictionary = (HashMap<String, Object>) dataSnapshot.getValue();
        String text = (String) dictionary.get("text");
        Long timestamp = (Long) dictionary.get("timestamp");
        String toID = (String) dictionary.get("toID");
        String fromID = (String) dictionary.get("fromID");

        Message message = new Message(fromID, toID, timestamp, text, profileImageURL);

        if (message.chatPartnerID().equals(tappedUserUID)) {
          chatMessages.add(message);
          Collections.sort(chatMessages, new SortMessageByTimestamp());
        }
        callback.onMessagesLoaded(chatMessages);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  @Override
  public void getUserWithUID(String uid, final GetUserWithUID callback) {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        String userUID = dataSnapshot.getKey();

        HashMap<String, Object> dictionary = (HashMap<String, Object>) dataSnapshot.getValue();
        String name = (String) dictionary.get("name");
        String email = (String) dictionary.get("email");
        String profileImageUrl = (String) dictionary.get("profileImageUrl");

        User user = new User(name, email, profileImageUrl);

        callback.onUserRetrievedWithUID(user);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void createNewUser(final RegisteredUser user, final RegisterNewUser callback) {
    mAuth = FirebaseAuth.getInstance();

    // TODO: Complete method. Register user with email and password. Afterwards, upload image to

    Log.d("Repository", "Entered in Create User Method");

    String email = user.getEmail();
    String password = user.getPassword();

    if (mAuth != null) {
      Log.d("Repository", "Firebase no es nulo.");
    }

    // User Creation in Auth
    createUserWithEmailAndPassword(user, callback, email, password);

  }

  private void createUserWithEmailAndPassword(final RegisteredUser user,
                                              final RegisterNewUser callback, String email,
                                              String password) {
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  // Creation succesful

                  String userUID = mAuth.getCurrentUser().getUid();
                  String imageName = UUID.randomUUID().toString();
                  Uri imageUri = user.getProfileImageUri();

                  Log.d("Repository", "Image Name: " + imageName);

                  uploadNewUserProfileImage(user, imageName, imageUri, callback);
                } else {
                  // If there is any error while creating the user
                  Log.d("Registration Failed", "" + task.getException().getMessage());
                  callback.onNewUserRegistered(true, false,
                          false, true);
                }
              }
            });
  }

  private void uploadNewUserProfileImage(final RegisteredUser user, String imageName, Uri imageUri,
                                         final RegisterNewUser callback) {
    final StorageReference storageRef = FirebaseStorage.getInstance().getReference()
            .child("profile_images").child(imageName + ".png");

    // Storaging their image to Storage
    storageRef.putFile(imageUri)
            .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
              @Override
              public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                // Image storaging succesful

                if (task.isSuccessful()) {

                  // We get imageURL
                  storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri userImageUri) {
                      String userUID = FirebaseAuth.getInstance().getUid();

                      Log.d("Repository", "ImageURL: " + userImageUri.toString());

                      String name = user.getName();
                      String email = user.getEmail();
                      String profileImageUrl = userImageUri.toString();

                      Map<String, String> newUser = new HashMap<String, String>();
                      newUser.put("name", name);
                      newUser.put("email", email);
                      newUser.put("profileImageUrl", profileImageUrl);

                      saveUserToDatabase(userUID, newUser, callback);
                    }
                  });
                }
                // Failure in uploading profile image to Storage
                else {
                  callback.onNewUserRegistered(true, false,
                          false, true);
                }
              }
            });
  }

  private void saveUserToDatabase(String userUID, Map<String, String> newUser,
                                  final RegisterNewUser callback) {
    FirebaseDatabase.getInstance().getReference().child("users")
            .child(userUID).setValue(newUser).addOnCompleteListener(
            new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                  callback.onNewUserRegistered(false, false,
                          true, true);
                } else {
                  callback.onNewUserRegistered(true, false,
                          true, true);
                }
              }
            });
  }

  @Override
  public void eliminateInsideChatReference(RegisteredUser userTappedToRemoveRef) {
    refInsideChat.child(currentUserInAppID).removeEventListener(listenerInsideChat);

    Log.d("Repository", "Reference removed from: " + currentUserInAppID);
  }

}
