package ru.alexey_podusov.workers.models.db.tables;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "WorkerAndSpecialtyRelation", tableName = "worker_and_specialty_relations")
public class WorkerAndSpecialtyRelationsModelSpec {
    @PrimaryKey
    @ColumnSpec(name = "_id")
    public long id;

    @ColumnSpec(name = "specialty_id")
    public long specialtyId;

    @ColumnSpec(name = "worker_id")
    public long workerId;
}

