package ru.alexey_podusov.workers.ui;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import ru.alexey_podusov.workers.presenters.WorkerListPresenter;
import ru.alexey_podusov.workers.views.Worker;
import ru.alexey_podusov.workers.views.WorkerListView;

@EFragment(R.layout.fragment_base_list)
public class WorkerListFramgent extends MvpAppCompatFragment implements WorkerListView {
    private final static String KEY_SPECIALTY_ID = "key_specialty_id";
    private final static String KEY_SPECIALTY_NAME = "key_specialty_name";

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    private WorkersAdapter adapter;

    @InjectPresenter
    WorkerListPresenter mWorkerListPresenter;

    @ProvidePresenter
    public WorkerListPresenter provideWorkerListPresenter() {
        return WorkersApp.getAppComponent().getWorkerListPresenter();
    }

    public static WorkerListFramgent_ create(long specialtyId, String specialtyName) {
        WorkerListFramgent_ framgent = new WorkerListFramgent_();
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_SPECIALTY_ID, specialtyId);
        bundle.putString(KEY_SPECIALTY_NAME, specialtyName);
        framgent.setArguments(bundle);
        return framgent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new WorkersAdapter(getActivity().getSupportFragmentManager());
        mWorkerListPresenter.onCreate(getArguments().getLong(KEY_SPECIALTY_ID));

    }

    @AfterViews
    public void onBindedViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString(KEY_SPECIALTY_NAME));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showWorkersWithAge(List<Pair<Worker, String>> workersWithAge) {
        adapter.setWorkersWithAge(workersWithAge);
    }

    @Override
    public void showError(String textError) {
        Toast.makeText(getContext(), textError, Toast.LENGTH_SHORT).show();
    }
}
