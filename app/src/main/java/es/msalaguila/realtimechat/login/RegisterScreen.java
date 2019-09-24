package es.msalaguila.realtimechat.login;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.AppMediator;

public class RegisterScreen {

  public static void configure(RegisterContract.View view) {

    WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    RegisterState state = mediator.getRegisterState();

    RegisterContract.Router router = new RegisterRouter(mediator);
    RegisterContract.Presenter presenter = new RegisterPresenter(state);
    RegisterContract.Model model = new RegisterModel();
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
