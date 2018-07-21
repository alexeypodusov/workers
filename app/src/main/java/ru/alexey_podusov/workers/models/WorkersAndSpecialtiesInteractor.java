package ru.alexey_podusov.workers.models;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import ru.alexey_podusov.workers.models.db.DatabaseRepository;
import ru.alexey_podusov.workers.models.db.tables.Specialty;
import ru.alexey_podusov.workers.models.db.tables.Worker;
import ru.alexey_podusov.workers.models.responses.WorkersResponse;
import ru.alexey_podusov.workers.utils.ResponseUtils;

import static ru.alexey_podusov.workers.models.responses.WorkersResponse.*;

public class WorkersAndSpecialtiesInteractor {
    private WorkersApi workersApi;
    private DatabaseRepository databaseRepository;

    public WorkersAndSpecialtiesInteractor(WorkersApi workersApi, DatabaseRepository databaseRepository) {
        this.workersApi = workersApi;
        this.databaseRepository = databaseRepository;
    }

    private void convertResponseAndSaveToDatabase(WorkersResponse response) {
        List<Specialty> specialtiesForDatabase = new ArrayList<>();
        databaseRepository.clearDatabase();
        for (WorkerResponse workerFromResponse : response.workers) {
            Worker workerForDatabase = new Worker();
            workerForDatabase.setFirstName(ResponseUtils.toFirstUpperCase(workerFromResponse.firstName));
            workerForDatabase.setLastName(ResponseUtils.toFirstUpperCase(workerFromResponse.lastName));

            String birthday = "-";
            if (workerFromResponse.birthday != null) {
                if (!workerFromResponse.birthday.isEmpty()) {
                    birthday = ResponseUtils.toNeedFormatDate(workerFromResponse.birthday);
                }
            }

            workerForDatabase.setBirthday(birthday);

            List<Long> specialtiesId = Stream.of(workerFromResponse.specialties)
                    .map(specialty -> specialty.specialtyId)
                    .toList();

            databaseRepository.saveWorker(workerForDatabase, specialtiesId);

            for (SpecialtyResponse specialtyFromResponse : workerFromResponse.specialties) {
                Specialty specialtyForDatabase = new Specialty();
                specialtyForDatabase.setId(specialtyFromResponse.specialtyId);
                specialtyForDatabase.setName(specialtyFromResponse.name);
                specialtiesForDatabase.add(specialtyForDatabase);
            }
        }

        specialtiesForDatabase = Stream.of(specialtiesForDatabase)
                .distinctBy(specialty -> specialty.getId())
                .toList();
        databaseRepository.saveSpecialties(specialtiesForDatabase);
    }

    public Maybe downloadWorkersAndSpecialties() {
        return new Maybe() {
            @Override
            protected void subscribeActual(MaybeObserver observer) {
                try {
                    convertResponseAndSaveToDatabase(workersApi.getWorkers().execute().body());
                    observer.onComplete();
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        };
    }
}
