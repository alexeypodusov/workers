package ru.alexey_podusov.workers.presenters;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.alexey_podusov.workers.models.WorkersAndSpecialtiesInteractor;
import ru.alexey_podusov.workers.views.WorkerView;

@InjectViewState
public class WorkerPresenter extends BasePresenter<WorkerView> {
    WorkersAndSpecialtiesInteractor interactor;

    public WorkerPresenter(WorkersAndSpecialtiesInteractor interactor) {
        this.interactor = interactor;
    }

    public void onBindedViews(long workerId) {
        unsubscribeOnDestroy(interactor.getWorkerWithAgeById(workerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((workerFullInfoData -> {
                    getViewState().showWorkerInfo(workerFullInfoData);
                }), error -> {
                    getViewState().showError(error.getMessage());
                }));
    }
}
