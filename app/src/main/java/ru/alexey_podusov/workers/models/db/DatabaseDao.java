package ru.alexey_podusov.workers.models.db;

import android.content.Context;

import com.yahoo.squidb.android.AndroidOpenHelper;
import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Table;

import ru.alexey_podusov.workers.models.db.tables.WorkerAndSpecialtyRelation;
import ru.alexey_podusov.workers.views.Specialty;
import ru.alexey_podusov.workers.views.Worker;

import static ru.alexey_podusov.workers.Const.DATABASE_NAME;

public class DatabaseDao extends SquidDatabase {
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    public DatabaseDao(Context context) {
        mContext = context;
    }

    @Override
    public String getName() {
        return DATABASE_NAME;
    }

    @Override
    protected int getVersion() {
        return DATABASE_VERSION;
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                Specialty.TABLE,
                Worker.TABLE,
                WorkerAndSpecialtyRelation.TABLE
        };
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        return true;
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate, int version) {
        return new AndroidOpenHelper(mContext, databaseName, delegate, version);
    }
}
