package es.msalaguila.realtimechat.Home;

import android.app.Activity;

import java.lang.ref.WeakReference;

import es.msalaguila.realtimechat.Chat.ChatState;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

interface HomeContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(HomeViewModel viewModel);

    Activity getActivity();

    void finishActivity();

    /**
     * Displays the current user information
     * @param viewModel: Contains the data to be displayed
     */
    void displayCurrentUser(HomeViewModel viewModel);

    /**
     * Displays the current user's messages
     * @param viewModel: Contains the data to be displayed
     */
    void displayHomeMessages(HomeViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    /**
     * Checks if the user is currently logged-in
     */
    void isUserLoggedIn();

    /**
     * Gets called when the user presses the Logout button
     */
    void logoutButtonPressed();

    /**
     * Gets called when the usr presses the New Message button
     */
    void newMessageButtonPressed();

    /**
     * Gets called when the user taps on a cell from the Recycler View to pass its data to the next
     * screen
     * @param userTapped: User that has been tapped
     */
    void userTapped(RegisteredUser userTapped);

    /**
     * Method that checks every time a message is received or sent if the notification should be
     * displyed
     * @param onScreen: When false the notification is displayed
     */
    void saveCurrentNotificationState(boolean onScreen);
  }

  interface Model {

    /**
     * Method that checks when the application is launched whether the user was logged in or not
     * @param callback: Callback that is going to be called when the checking is done
     */
    void isUserLoggedIn(RepositoryInterface.CheckIfUserIsLoggedIn callback);

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
     * Method that loads the messages for the in-app user when the application is opened
     * @param callback: Callback that is going to be called when the messages are retrieved from
     *                the database
     */
    void loadHomeMessages(RepositoryInterface.LoadHomeMessages callback);
  }

  interface Router {
    HomeState getDataFromPreviousScreen();

    void routeToRegister(Activity activity);

    void routeToNewMessage(Activity activity);

    void routeToChat(Activity activity);

    void passDataToChat(ChatState state);
  }
}
