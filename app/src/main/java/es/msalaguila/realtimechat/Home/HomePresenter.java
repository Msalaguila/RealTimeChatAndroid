package es.msalaguila.realtimechat.Home;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.msalaguila.realtimechat.Chat.ChatScreen;
import es.msalaguila.realtimechat.Chat.ChatState;
import es.msalaguila.realtimechat.Data.HomeMessage;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class HomePresenter implements HomeContract.Presenter {

  public static String TAG = HomePresenter.class.getSimpleName();

  private WeakReference<HomeContract.View> view;
  private HomeViewModel viewModel;
  private HomeContract.Model model;
  private HomeContract.Router router;

  public HomePresenter(HomeState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<HomeContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(HomeContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(HomeContract.Router router) {
    this.router = router;
  }

  @Override
  public void isUserLoggedIn() {
    model.isUserLoggedIn(new RepositoryInterface.CheckIfUserIsLoggedIn() {
      @Override
      public void onUserIsLoggedIn(boolean isLoggedIn) {
        if (!isLoggedIn) {
          Activity activity = view.get().getActivity();
          router.routeToRegister(activity);
          Log.d("HomePresenter", "User NOT LOGGED IN");
        } else {

          model.getCurrentUser(new RepositoryInterface.GetCurrentUser() {
            @Override
            public void onGetCurrentUser(RegisteredUser user) {
              viewModel.registeredUser = user;
              view.get().displayCurrentUser(viewModel);

              model.loadHomeMessages(new RepositoryInterface.LoadHomeMessages() {
                @Override
                public void onHomeMessagesLoaded(List<HomeMessage> homeMessages) {

                  viewModel.homeMessageList = homeMessages;
                  view.get().displayHomeMessages(viewModel);
                }
              });
            }
          });
        }
      }
    });
  }

  @Override
  public void logoutButtonPressed() {
    model.logoutUser(new RepositoryInterface.LogoutButtonPressed() {
      @Override
      public void onLogoutButtonPressed() {
        Activity activity = view.get().getActivity();
        view.get().finishActivity();
        router.routeToRegister(activity);
      }
    });
  }

  @Override
  public void newMessageButtonPressed() {
    Activity activity = view.get().getActivity();
    router.routeToNewMessage(activity);
  }

  @Override
  public void userTapped(RegisteredUser userTapped) {
    ChatState state = new ChatState();
    state.tappedUser = userTapped;
    Activity activity = view.get().getActivity();
    router.routeToChat(activity);
    router.passDataToChat(state);
  }

  @Override
  public void saveCurrentNotificationState(boolean onScreen) {
    viewModel.onScreen = onScreen;
  }


}
