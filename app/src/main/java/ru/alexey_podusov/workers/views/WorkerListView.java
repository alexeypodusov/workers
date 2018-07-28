package ru.alexey_podusov.workers.views;

import android.support.v4.util.Pair;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface WorkerListView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showWorkersWithAge(List<Pair<Worker, String>> workers);

    @StateStrategyType(SkipStrategy.class)
    void showError(String textError);
}
