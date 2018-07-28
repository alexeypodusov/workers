package ru.alexey_podusov.workers.views;


import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/*
    При сборке в сгенерированном классе SpecialtyListView,
    ругается на сгенерированный класс Specialty(точнее на его отсутствие)
    Расположение в одном package решает проблему, поэтому он временно побудет в этом package
    P.S. ничто так не постоянно как временное
 */
@TableModelSpec(className = "Specialty", tableName = "specialty")
public class SpecialtyModelSpec {
    @ColumnSpec(name = "name")
    public String name;

    @ColumnSpec(name = "specialty_response_id")
    public long specialtyResponseId;

    @PrimaryKey
    @ColumnSpec(name = "_id")
    public long id;
}
