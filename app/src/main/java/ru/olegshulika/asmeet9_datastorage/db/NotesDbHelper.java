package ru.olegshulika.asmeet9_datastorage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotesDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "NotesDbHelper";

    public NotesDbHelper(Context context) {
        super(context, NotesDbSchema.DB_NAME, null, NotesDbSchema.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate");
        CreateEmptyTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("onUpgrade oldV=%d newV=%d", oldVersion, newVersion));
        if (newVersion > oldVersion) {
            DeleteTables(sqLiteDatabase);
            onCreate(sqLiteDatabase);
        }
    }

    private void CreateEmptyTables(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "CreateEmptyTables");
        for (String query : NotesDbSchema.CREATE_DATABASE_QUERIES) {
            sqLiteDatabase.execSQL(query);
        }
    }

    private void DeleteTables(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "DeleteTables");
        for (String query : NotesDbSchema.DELETE_DATABASE_QUERIES) {
            sqLiteDatabase.execSQL(query);
        }
    }

}
