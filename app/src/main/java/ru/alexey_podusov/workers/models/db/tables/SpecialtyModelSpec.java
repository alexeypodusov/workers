package ru.alexey_podusov.workers.models.db.tables;


import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "Specialty", tableName = "specialty")
public class SpecialtyModelSpec {
    @ColumnSpec(name = "name")
    public String name;

    @PrimaryKey
    @ColumnSpec(name = "_id")
    public long id;
}
