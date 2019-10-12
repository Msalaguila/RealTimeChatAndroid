package es.msalaguila.realtimechat.Home;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.msalaguila.realtimechat.app.AppMediator;
import es.msalaguila.realtimechat.app.Repository;
import es.msalaguila.realtimechat.app.RepositoryInterface;

public class HomeScreen {

  public static void configure(HomeContract.View view) {

    WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    HomeState state = mediator.getHomeState();

    RepositoryInterface repository = Repository.getInstance(context.get());
    HomeContract.Router router = new HomeRouter(mediator);
    HomeContract.Presenter presenter = new HomePresenter(state);
    HomeContract.Model model = new HomeModel(repository);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
