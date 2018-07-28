package ru.alexey_podusov.workers.presenters;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.alexey_podusov.workers.models.WorkersAndSpecialtiesInteractor;
import ru.alexey_podusov.workers.views.SpecialtyListView;

@InjectViewState
public class SpecialtyListPresenter extends BasePresenter<SpecialtyListView> {

    private WorkersAndSpecialtiesInteractor workersAndSpecialtiesInteractor;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        downloadWorkersAndSpecialties();
    }

    public SpecialtyListPresenter(WorkersAndSpecialtiesInteractor workersAndSpecialtiesInteractor) {
        this.workersAndSpecialtiesInteractor = workersAndSpecialtiesInteractor;
    }

    private void loadSpecialtiesFromDatabase() {
        unsubscribeOnDestroy(workersAndSpecialtiesInteractor.getAllSpecialties()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((specialties -> {
                    getViewState().showSpecialties(specialties);
                    getViewState().setProgress(false);
                }), error -> {
                    getViewState().showError(error.getMessage());
                    getViewState().setProgress(false);
                }));
    }

    private void downloadWorkersAndSpecialties() {
        getViewState().setProgress(true);
        unsubscribeOnDestroy(workersAndSpecialtiesInteractor.downloadWorkersAndSpecialties()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response -> loadSpecialtiesFromDatabase()),
                        error -> {
                            getViewState().showError(error.getMessage());
                            getViewState().setProgress(false);
                        }));

    }
}


