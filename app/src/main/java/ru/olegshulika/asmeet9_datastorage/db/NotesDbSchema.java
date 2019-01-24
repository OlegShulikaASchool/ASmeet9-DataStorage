package ru.olegshulika.asmeet9_datastorage.db;

import android.content.ContentValues;
import android.provider.BaseColumns;

public final class NotesDbSchema {
    private NotesDbSchema() {
    }

    public static final String DB_NAME = "notes.db";
    public static final int DB_VERSION = 1;

    public static final String[] CREATE_DATABASE_QUERIES = {
            Notes.SQL_CREATE_TABLE,
            Notes.SQL_CREATE_UPDATED_INDEX
    };

    public static final String[] DELETE_DATABASE_QUERIES = {
            Notes.SQL_DELETE_UPDATED_INDEX,
            Notes.SQL_DELETE_TABLE
    };

    public static abstract class Notes implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String INDEX_UPDATED_NAME = "updated_index";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_FILEPATH = "filepath";
        public static final String COLUMN_VERSION = "version";
        public static final String COLUMN_CREATED_TS = "created_ts";
        public static final String COLUMN_UPDATED_TS = "updated_ts";

        // CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL,
        // filepath TEXT, version INTEGER NOT NULL, created_ts INTEGER, updated_ts INTEGER);
        public static final String SQL_CREATE_TABLE = String.format(
                "CREATE TABLE %s " +
                "(%s INTEGER PRIMARY KEY, " +
                "%s TEXT NOT NULL, " +
                "%s TEXT, " +
                "%s INTEGER NOT NULL, " +
                "%s INTEGER NOT NULL, " +
                "%s INTEGER NOT NULL);",
                        TABLE_NAME,
                        _ID,
                        COLUMN_TITLE,
                        COLUMN_FILEPATH,
                        COLUMN_VERSION,
                        COLUMN_CREATED_TS,
                        COLUMN_UPDATED_TS);

        public static final String SQL_CREATE_UPDATED_INDEX = String.format(
                "CREATE INDEX %s ON %s (%s);",
                        INDEX_UPDATED_NAME,
                        TABLE_NAME,
                        COLUMN_UPDATED_TS);

        public static final String SQL_DELETE_TABLE = String.format(
                "DROP TABLE IF EXISTS %s;",
                        TABLE_NAME);

        public static final String SQL_DELETE_UPDATED_INDEX = String.format(
                "DROP INDEX IF EXISTS %s;",
                        INDEX_UPDATED_NAME);

        public static ContentValues getContentValues(String title, String filepath, int version, long tsCreated, long tsUpdated) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_FILEPATH, filepath);
            values.put(COLUMN_VERSION, version);
            values.put(COLUMN_CREATED_TS, tsCreated);
            values.put(COLUMN_UPDATED_TS, tsUpdated);

            return values;
        }

    }

}
