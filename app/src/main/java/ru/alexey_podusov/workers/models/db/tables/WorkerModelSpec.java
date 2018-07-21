package ru.alexey_podusov.workers.models.db.tables;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

import java.util.List;
@TableModelSpec(className = "Worker", tableName = "worker")
public class WorkerModelSpec {
    @ColumnSpec(name = "first_name")
    public String firstName;

    @ColumnSpec(name = "last_name")
    public String lastName;

    @ColumnSpec(name = "birthday")
    public String birthday;

    @PrimaryKey
    @ColumnSpec(name = "_id")
    public long id;
}
