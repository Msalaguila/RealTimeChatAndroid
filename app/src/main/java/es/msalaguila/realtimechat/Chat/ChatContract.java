package es.msalaguila.realtimechat.Chat;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface ChatContract {

  interface View {
    void injectPresenter(Presenter presenter);

    /**
     * When the user is retrieved from the database, it displays its name at the top
     * @param viewModel: Contains the data to be displayed
     */
    void displayUserTappedName(ChatViewModel viewModel);

    /**
     * When a message is sent it cleans the Input Text Field
     */
    void cleanInputTextField();

    /**
     * Method that reloads the messages of the Recycler View
     * @param viewModel: Contains the data to be displayed
     */
    void displayChatMessages(ChatViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    /**
     * Loads the user from the previous screen
     */
    void getUserTappedFromPreviousScreen();

    /**
     * Calls the model to send the message
     * @param textToSend: Message to be sent
     */
    void sendButtonPressed(String textToSend);

    /**
     * Calls the model to retrieve the chat messages
     */
    void loadMessagesForTappedUser();

    /**
     * Calls the model to eliminate the reference from the chat
     */
    void eliminateInsideChatReference();
  }

  interface Model {
    /**
     * Method used when a message is going to be sent to a user
     * @param textToSend: Text that is going to be sent
     * @param userToSendMessageTo: User that the message is going to be sent
     * @param callback: Callback that is going to be called when the message has been sent
     */
    void sendMessageToUser(String textToSend, RegisteredUser userToSendMessageTo,
                           RepositoryInterface.SendMessage callback);

    /**
     * Method that loads the messages from the database for the tapped user when opening a chat
     * @param userToLoadMessages: Tapped user to load messages to
     * @param callback: Callback that is going to be called when the messages are retrieved from
     *                the database
     */
    void loadMessagesForTappedUserInsideChat(RegisteredUser userToLoadMessages
            , RepositoryInterface.LoadMessagesForTappedUserInsideChat callback);

    /**
     * Method that eliminates the reference from the database so the messages are not loaded anymore
     * when the user leaves the Chat Activity
     * @param userTappedToRemoveRef: User to remove the reference from
     */
    void eliminateInsideChatReference(RegisteredUser userTappedToRemoveRef);
  }

  interface Router {
    ChatState getDataFromPreviousScreen();
  }
}
