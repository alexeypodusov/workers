package ru.alexey_podusov.workers.injection;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import ru.alexey_podusov.workers.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();

    void inject(MainActivity mainActivity);
}
