package ru.alexey_podusov.workers.presenters;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.alexey_podusov.workers.models.WorkersAndSpecialtiesInteractor;
import ru.alexey_podusov.workers.views.WorkerListView;

@InjectViewState
public class WorkerListPresenter extends BasePresenter<WorkerListView> {
    private WorkersAndSpecialtiesInteractor interactor;

    public WorkerListPresenter(WorkersAndSpecialtiesInteractor interactor) {
        this.interactor = interactor;
    }

    private void loadWorkersFromDatabaseBySpecialtyId(long id) {
        unsubscribeOnDestroy(interactor.getWorkersWithAgeBySpecialtyId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((workers -> {
                    getViewState().showWorkersWithAge(workers);
                }), error -> {
                    getViewState().showError(error.getMessage());
                }));
    }

    public void onCreate(Long specialtyId) {
        loadWorkersFromDatabaseBySpecialtyId(specialtyId);
    }
}
