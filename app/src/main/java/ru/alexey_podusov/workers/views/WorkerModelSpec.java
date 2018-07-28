package ru.alexey_podusov.workers.views;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/*
    При сборке в сгенерированном классе WorkerListView,
    ругается на сгенерированный класс Specialty(точнее на его отсутствие)
    Расположение в одном package решает проблему, поэтому он временно побудет в этом package
    P.S. ничто так не постоянно как временное
 */
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
