package ru.alexey_podusov.workers;

import android.app.Application;

import ru.alexey_podusov.workers.injection.AppComponent;
import ru.alexey_podusov.workers.injection.AppModule;
import ru.alexey_podusov.workers.injection.DaggerAppComponent;

public class WorkersApp extends Application {
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
