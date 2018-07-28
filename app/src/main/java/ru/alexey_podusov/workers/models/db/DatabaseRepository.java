package ru.alexey_podusov.workers.models.db;

import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.alexey_podusov.workers.models.db.tables.WorkerAndSpecialtyRelation;
import ru.alexey_podusov.workers.views.Specialty;
import ru.alexey_podusov.workers.views.Worker;

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

    public List<Specialty> getAllSpecialties() {
        List<Specialty> specialties = new ArrayList<>();
        SquidCursor<Specialty> cursor = databaseDao.query(Specialty.class, Query.select());
        try {
            while (cursor.moveToNext()) {
                Specialty specialty = new Specialty();
                specialty.readPropertiesFromCursor(cursor);
                specialties.add(specialty);
            }
        } finally {
            cursor.close();
        }
        return specialties;
    }

    public List<Worker> getWorkersBySpecialtyId(long specialtyId) {
        List<Worker> workers = new ArrayList<>();
        SquidCursor<WorkerAndSpecialtyRelation> cursor = databaseDao.query(WorkerAndSpecialtyRelation.class,
                Query.select().where(WorkerAndSpecialtyRelation.SPECIALTY_ID.eq(specialtyId)));
        try {
            while (cursor.moveToNext()) {
                WorkerAndSpecialtyRelation relation = new WorkerAndSpecialtyRelation();
                relation.readPropertiesFromCursor(cursor);
                Worker worker = databaseDao.fetch(Worker.class, relation.getWorkerId());
                workers.add(worker);
            }
        } finally {
            cursor.close();
        }
        return workers;
    }

    public void saveWorker(Worker worker, List<Long> specialtiesId) {
        databaseDao.persist(worker);
        for (Long specialtyId : specialtiesId) {
            WorkerAndSpecialtyRelation relation = new WorkerAndSpecialtyRelation();
            relation.setWorkerId(worker.getId());
            relation.setSpecialtyId(specialtyId);
            databaseDao.persist(relation);
        }
    }

    public Worker getWorkerById(long id) {
        return databaseDao.fetch(Worker.class, id);
    }

    public List<Specialty> getSpecialtiesByWorkerId(long workerId) {
        List<Specialty> specialties = new ArrayList<>();
        SquidCursor<WorkerAndSpecialtyRelation> relationCursor = databaseDao.query(WorkerAndSpecialtyRelation.class,
                Query.select().where(WorkerAndSpecialtyRelation.WORKER_ID.eq(workerId)));
        try {
            while (relationCursor.moveToNext()) {
                WorkerAndSpecialtyRelation relation = new WorkerAndSpecialtyRelation();
                relation.readPropertiesFromCursor(relationCursor);
                SquidCursor<Specialty> specialtyCursor = databaseDao.query(Specialty.class,
                        Query.select().where(Specialty.SPECIALTY_RESPONSE_ID.eq(relation.getSpecialtyId())));
                while (specialtyCursor.moveToNext()) {
                    Specialty specialty = new Specialty();
                    specialty.readPropertiesFromCursor(specialtyCursor);
                    specialties.add(specialty);
                }
            }
        } finally {
            relationCursor.close();
        }
        return specialties;
    }
}

