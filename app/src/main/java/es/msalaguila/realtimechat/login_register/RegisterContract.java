package es.msalaguila.realtimechat.login_register;

import java.lang.ref.WeakReference;

interface RegisterContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(RegisterViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();
  }

  interface Model {
    String fetchData();
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(RegisterState state);

    RegisterState getDataFromPreviousScreen();
  }
}
