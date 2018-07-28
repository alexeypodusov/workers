package ru.alexey_podusov.workers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import ru.alexey_podusov.workers.R;
import ru.alexey_podusov.workers.WorkersApp;
import ru.alexey_podusov.workers.presenters.SpecialtyListPresenter;
import ru.alexey_podusov.workers.views.Specialty;
import ru.alexey_podusov.workers.views.SpecialtyListView;

@EFragment(R.layout.fragment_base_list)
public class SpecialtyListFragment extends MvpAppCompatFragment implements SpecialtyListView {
    @InjectPresenter
    SpecialtyListPresenter mSpecialityListPresenter;

    @ProvidePresenter
    public SpecialtyListPresenter provideSpecialtyListPresenter() {
        return WorkersApp.getAppComponent().getSpecialtyListPresenter();
    }

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @ViewById(R.id.progress_bar)
    ProgressBar progressBar;

    private SpecialtiesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SpecialtiesAdapter(getActivity().getSupportFragmentManager());
    }


    @AfterViews
    public void onBindedViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getContext().getString(R.string.specialties_text));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setProgress(boolean isShow) {
        if (isShow) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showSpecialties(List<Specialty> specialties) {
        adapter.setSpecialties(specialties);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
