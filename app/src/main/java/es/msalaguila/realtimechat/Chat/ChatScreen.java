package es.msalaguila.realtimechat.Chat;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class ChatScreen {

  public static void configure(ChatContract.View view) {

    WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    ChatState state = mediator.getChatState();

    RepositoryInterface repository = Repository.getInstance(context.get());
    ChatContract.Router router = new ChatRouter(mediator);
    ChatContract.Presenter presenter = new ChatPresenter(state);
    ChatContract.Model model = new ChatModel(repository);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
