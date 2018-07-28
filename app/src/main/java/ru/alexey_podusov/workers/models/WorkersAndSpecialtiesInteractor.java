package ru.alexey_podusov.workers.models;

import android.support.v4.util.Pair;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import ru.alexey_podusov.workers.models.db.DatabaseRepository;
import ru.alexey_podusov.workers.models.responses.Response;
import ru.alexey_podusov.workers.utils.ResponseUtils;
import ru.alexey_podusov.workers.views.Specialty;
import ru.alexey_podusov.workers.views.Worker;

import static ru.alexey_podusov.workers.models.responses.Response.SpecialtyResponse;
import static ru.alexey_podusov.workers.models.responses.Response.WorkerResponse;

public class WorkersAndSpecialtiesInteractor {
    private WorkersApi workersApi;
    private DatabaseRepository databaseRepository;

    public WorkersAndSpecialtiesInteractor(WorkersApi workersApi, DatabaseRepository databaseRepository) {
        this.workersApi = workersApi;
        this.databaseRepository = databaseRepository;
    }

    private Maybe<Response> convertResponseAndSaveToDatabase(Response response) {
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
                specialtyForDatabase.setSpecialtyResponseId(specialtyFromResponse.specialtyId);
                specialtyForDatabase.setName(specialtyFromResponse.name);
                specialtiesForDatabase.add(specialtyForDatabase);
            }
        }

        specialtiesForDatabase = Stream.of(specialtiesForDatabase)
                .distinctBy(specialty -> specialty.getSpecialtyResponseId())
                .toList();
        databaseRepository.saveSpecialties(specialtiesForDatabase);
        return Maybe.just(response);
    }

    public Maybe<Response> downloadWorkersAndSpecialties() {
        return workersApi.getWorkers()
                .flatMap(this::convertResponseAndSaveToDatabase);

    }

    public Single<List<Specialty>> getAllSpecialties() {
        return Single.create(emitter -> {
            try {
                List<Specialty> specialties = databaseRepository.getAllSpecialties();
                emitter.onSuccess(specialties);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    public Single<List<Pair<Worker, String>>> getWorkersWithAgeBySpecialtyId(long specialtyId) {
        return Single.create(emitter -> {
            try {
                List<Worker> workers = databaseRepository.getWorkersBySpecialtyId(specialtyId);
                List<Pair<Worker, String>> workersWithAge = Stream.of(workers)
                        .map(worker -> Pair.create(worker, ResponseUtils.getAgeByStringDate(worker.getBirthday())))
                        .toList();
                emitter.onSuccess(workersWithAge);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    public Single<WorkerFullInfoData> getWorkerWithAgeById(long id) {
        return Single.create(emitter -> {
            try {
                WorkerFullInfoData workerFullInfoData = new WorkerFullInfoData();

                Worker worker = databaseRepository.getWorkerById(id);
                workerFullInfoData.setWorker(worker);
                workerFullInfoData.setAge(ResponseUtils.getAgeByStringDate(worker.getBirthday()));

                List<Specialty> specialties = databaseRepository.getSpecialtiesByWorkerId(worker.getId());
                workerFullInfoData.setSpecialtyNames(Stream.of(specialties)
                        .map(specialty -> specialty.getName())
                        .toList());

                emitter.onSuccess(workerFullInfoData);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
