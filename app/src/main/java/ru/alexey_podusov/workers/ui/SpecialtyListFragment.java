package ru.alexey_podusov.workers.ui;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import javax.inject.Inject;

import ru.alexey_podusov.workers.R;
import ru.alexey_podusov.workers.presenters.SpecialtyListPresenter;
import ru.alexey_podusov.workers.views.SpecialtyListView;
@EFragment(R.layout.fragment_base_list)
public class SpecialtyListFragment extends MvpAppCompatFragment implements SpecialtyListView {

    @InjectPresenter
    SpecialtyListPresenter mSpecialityListPresenter;

    @AfterViews
    public void bindViews() {
        int a = 0;
    }
}
