package ru.alexey_podusov.workers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import ru.alexey_podusov.workers.models.WorkersAndSpecialtiesInteractor;
import ru.alexey_podusov.workers.models.db.DatabaseRepository;
import ru.alexey_podusov.workers.models.db.tables.Worker;
import ru.alexey_podusov.workers.ui.SpecialtyListFragment_;

public class MainActivity extends AppCompatActivity {
    @Inject
    WorkersAndSpecialtiesInteractor interactor;

    @Inject
    DatabaseRepository databaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkersApp.getAppComponent().inject(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SpecialtyListFragment_()).commit();

        Worker worker = new Worker();
        worker.setFirstName("3535");
        Worker worker2 = new Worker();
        worker.setFirstName("35353");
        //databaseRepository.saveWorkers(Arrays.asList(worker, worker2));

    }
}
