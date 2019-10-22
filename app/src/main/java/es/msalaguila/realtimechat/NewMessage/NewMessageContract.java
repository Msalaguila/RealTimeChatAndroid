package es.msalaguila.realtimechat.NewMessage;

import android.app.Activity;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Chat.ChatState;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface NewMessageContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(NewMessageViewModel viewModel);

    Activity getActivity();

    void finishActivity();

    /**
     * Displays the current in-app users when are retrieved from the database
     * @param viewModel: Contains the data to be displayed
     */
    void displayCurrentUsers(NewMessageViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    /**
     * Gets called when the back button is pressed
     */
    void onBackButtonPressed();

    /**
     * Calls the model to retrieve the current in-app users from the database
     */
    void getCurrentUsers();

    /**
     * Calls the model with the tapped user so its information is obtained in the next screen
     * @param userTapped: User that has been tapped
     */
    void userTapped(RegisteredUser userTapped);
  }

  interface Model {
    String fetchData();

    /**
     * Method that gets called when the user taps on "New Message" Button and retrieves the
     * different registered users in the application
     * @param callback: Callback that is going to be called when the users are retrieved from the
     *                database
     */
    void getCurrentUsers(RepositoryInterface.GetCurrentUsers callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(NewMessageState state);

    NewMessageState getDataFromPreviousScreen();

    void routeToHome(Activity activity);

    void routeToChat(Activity activity);

    void passDataToChat(ChatState state);
  }
}
