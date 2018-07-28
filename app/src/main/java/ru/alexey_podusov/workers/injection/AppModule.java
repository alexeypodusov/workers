package ru.alexey_podusov.workers.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alexey_podusov.workers.Const;
import ru.alexey_podusov.workers.models.WorkersAndSpecialtiesInteractor;
import ru.alexey_podusov.workers.models.db.DatabaseDao;
import ru.alexey_podusov.workers.models.db.DatabaseRepository;
import ru.alexey_podusov.workers.models.WorkersApi;
import ru.alexey_podusov.workers.presenters.SpecialtyListPresenter;
import ru.alexey_podusov.workers.presenters.WorkerListPresenter;
import ru.alexey_podusov.workers.presenters.WorkerPresenter;

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

    @Provides
    public WorkersAndSpecialtiesInteractor provideWorkersAndSpecialtiesInteractor(WorkersApi workersApi,
                                                                                  DatabaseRepository databaseRepository) {
        return new WorkersAndSpecialtiesInteractor(workersApi, databaseRepository);
    }

    @Provides
    public WorkersApi provideWorkersApi(Retrofit retrofit) {
        return retrofit.create(WorkersApi.class);
    }

    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Const.WORKERS_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public DatabaseDao provideDatabaseDao(Context context) {
        return new DatabaseDao(context);
    }

    @Provides
    public SpecialtyListPresenter provideSpecialtyListPresenter(WorkersAndSpecialtiesInteractor workersAndSpecialtiesInteractor) {
        return new SpecialtyListPresenter(workersAndSpecialtiesInteractor);
    }

    @Provides
    public WorkerListPresenter provideWorkerListPresenter(WorkersAndSpecialtiesInteractor workersAndSpecialtiesInteractor) {
        return new WorkerListPresenter(workersAndSpecialtiesInteractor);
    }

    @Provides
    public WorkerPresenter provideWorkerPresenter(WorkersAndSpecialtiesInteractor workersAndSpecialtiesInteractor) {
        return new WorkerPresenter(workersAndSpecialtiesInteractor);
    }
}
