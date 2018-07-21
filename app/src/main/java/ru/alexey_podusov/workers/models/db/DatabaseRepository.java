package ru.alexey_podusov.workers.models.db;

import java.util.List;

import javax.inject.Inject;

import ru.alexey_podusov.workers.models.db.tables.Specialty;
import ru.alexey_podusov.workers.models.db.tables.Worker;
import ru.alexey_podusov.workers.models.db.tables.WorkerAndSpecialtyRelations;

public class DatabaseRepository {
    private DatabaseDao databaseDao;

    @Inject
    public DatabaseRepository(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    public void clearDatabase() {
        databaseDao.clear();
    }

    public void saveSpecialties(List<Specialty> specialties) {
        for (Specialty specialty : specialties) {
            databaseDao.persist(specialty);
        }
    }

    public void getAllSpecialties() {

    }

    public void saveWorker(Worker worker, List<Long> specialtiesId) {
        databaseDao.persist(worker);
        for (Long specialtyId : specialtiesId) {
            WorkerAndSpecialtyRelations relation = new WorkerAndSpecialtyRelations();
            relation.setWorkerId(worker.getId());
            relation.setSpecialtyId(specialtyId);
            databaseDao.persist(relation);
        }
    }
}

