package es.msalaguila.realtimechat.login_login;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class LoginScreen {

  public static void configure(LoginContract.View view) {

    WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    LoginState state = mediator.getLoginState();

    RepositoryInterface repository = Repository.getInstance(context.get());
    LoginContract.Router router = new LoginRouter(mediator);
    LoginContract.Presenter presenter = new LoginPresenter(state);
    LoginContract.Model model = new LoginModel(repository);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
