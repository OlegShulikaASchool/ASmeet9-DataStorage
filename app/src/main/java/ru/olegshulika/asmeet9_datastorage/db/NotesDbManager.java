package ru.olegshulika.asmeet9_datastorage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ru.olegshulika.asmeet9_datastorage.Note;

public class NotesDbManager {
    private static final String TAG = "NotesDbManager";
    private NotesDbHelper dbHelper;

    public NotesDbManager(Context context) {
        this.dbHelper = new NotesDbHelper(context);
    }

    public long addNewNote(Note note) {
        SQLiteDatabase db = null;
        long id = 0;

        try {
            db = dbHelper.getWritableDatabase();
            long sysTime = System.currentTimeMillis();
            ContentValues values = NotesDbSchema.Notes.getContentValues(note.getTitle(),
                    note.getFilePath(), note.getVersion(), note.getCreated(), note.getUpdated());
            db.beginTransaction();
            id = db.insert (NotesDbSchema.Notes.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException ex) {
            Log.v(TAG, ex.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }

        return id;
    }

}
