package ru.alexey_podusov.workers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ru.alexey_podusov.workers.R;
import ru.alexey_podusov.workers.WorkersApp;
import ru.alexey_podusov.workers.models.WorkerFullInfoData;
import ru.alexey_podusov.workers.presenters.WorkerPresenter;
import ru.alexey_podusov.workers.views.WorkerView;

@EFragment(R.layout.fragment_worker)
public class WorkerFragment extends MvpAppCompatFragment implements WorkerView {
    private final static String KEY_WORKER_ID = "key_worker_id";

    @ViewById(R.id.first_name_text_view)
    TextView firstNameTextView;

    @ViewById(R.id.last_name_text_view)
    TextView lastNameTextView;

    @ViewById(R.id.specialty_text_view)
    TextView specialtiesTextView;

    @ViewById(R.id.birthday_text_view)
    TextView birthdayTextView;

    @ViewById(R.id.age_text_view)
    TextView ageTextView;

    @InjectPresenter
    WorkerPresenter mWorkerPresenter;

    @ProvidePresenter
    public WorkerPresenter provideWorkerPresenter() {
        return WorkersApp.getAppComponent().getWorkerPresenter();
    }

    public static WorkerFragment create(long workerId) {
        WorkerFragment_ framgent = new WorkerFragment_();
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_WORKER_ID, workerId);
        framgent.setArguments(bundle);
        return framgent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @AfterViews
    public void onBindedViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getContext().getString(R.string.specialties_text));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mWorkerPresenter.onBindedViews(getArguments().getLong(KEY_WORKER_ID));
    }

    public void showWorkerInfo(WorkerFullInfoData workerFullInfoData) {
        firstNameTextView.setText(workerFullInfoData.getWorker().getFirstName());
        lastNameTextView.setText(workerFullInfoData.getWorker().getLastName());
        specialtiesTextView.setText(Stream.of(workerFullInfoData.getSpecialtyNames())
                .collect(Collectors.joining(", ")));
        birthdayTextView.setText(workerFullInfoData.getWorker().getBirthday());
        ageTextView.setText(workerFullInfoData.getAge());

        ((AppCompatActivity) getActivity())
                .getSupportActionBar()
                .setTitle(workerFullInfoData.getWorker().getFirstName() + " " + workerFullInfoData.getWorker().getLastName());


    }

    @Override
    public void showError(String textError) {
        Toast.makeText(getContext(), textError, Toast.LENGTH_SHORT).show();
    }
}
