package es.msalaguila.realtimechat.login_register;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class RegisterScreen {

  public static void configure(RegisterContract.View view) {

    WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    RegisterState state = mediator.getRegisterState();

    RepositoryInterface repository = Repository.getInstance(context.get());
    RegisterContract.Router router = new RegisterRouter(mediator);
    RegisterContract.Presenter presenter = new RegisterPresenter(state);
    RegisterContract.Model model = new RegisterModel(repository);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
