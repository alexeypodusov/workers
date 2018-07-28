package ru.alexey_podusov.workers.injection;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import ru.alexey_podusov.workers.presenters.SpecialtyListPresenter;
import ru.alexey_podusov.workers.presenters.WorkerListPresenter;
import ru.alexey_podusov.workers.presenters.WorkerPresenter;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();
    SpecialtyListPresenter getSpecialtyListPresenter();
    WorkerListPresenter getWorkerListPresenter();
    WorkerPresenter getWorkerPresenter();
}
