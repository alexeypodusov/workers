package ru.alexey_podusov.workers.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface SpecialtyListView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setProgress(boolean isShow);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSpecialties(List<Specialty> specialties);

    @StateStrategyType(SkipStrategy.class)
    void showError(String error);
}
