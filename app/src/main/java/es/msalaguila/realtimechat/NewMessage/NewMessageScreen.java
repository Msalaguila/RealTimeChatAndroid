package es.msalaguila.realtimechat.NewMessage;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class NewMessageScreen {

  public static void configure(NewMessageContract.View view) {

    WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    NewMessageState state = mediator.getNewMessageState();

    RepositoryInterface repository = Repository.getInstance(context.get());
    NewMessageContract.Router router = new NewMessageRouter(mediator);
    NewMessageContract.Presenter presenter = new NewMessagePresenter(state);
    NewMessageContract.Model model = new NewMessageModel(repository);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
