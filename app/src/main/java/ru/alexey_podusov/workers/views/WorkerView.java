package ru.alexey_podusov.workers.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.alexey_podusov.workers.models.WorkerFullInfoData;

public interface WorkerView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showWorkerInfo(WorkerFullInfoData workerFullInfoData);

    @StateStrategyType(SkipStrategy.class)
    void showError(String textError);
}
