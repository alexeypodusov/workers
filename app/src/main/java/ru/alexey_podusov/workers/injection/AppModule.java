package ru.alexey_podusov.workers.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
