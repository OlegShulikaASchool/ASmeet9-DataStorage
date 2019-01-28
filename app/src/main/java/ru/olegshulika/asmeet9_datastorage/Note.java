package ru.olegshulika.asmeet9_datastorage;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Note implements Parcelable {
    private static final String TAG = "Note";
    private long id;
    private long created;
    private long updated;
    private int version;
    private String filePath;
    private String title;
    private String noteBody;

    public Note (long id, long created, long updated, int ver, String path, String title, String body) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.version = ver;
        this.filePath = path;
        this.title = title;
        this.noteBody = body;
    }

    public Note (String title, String body) {
        this.id = -1;
        this.created = System.currentTimeMillis();
        this.updated = this.created;
        this.version = 1;
        this.filePath = null;
        this.title = title;
        this.noteBody = body;
    }


    protected Note (Parcel in) {
        long[] longData = new long[3];
        String[] strData = new String[3];

        in.readLongArray(longData);
        this.id = longData[0];
        this.created = longData[1];
        this.updated = longData[2];

        this.version = in.readInt();

        in.readStringArray(strData);
        this.filePath = strData[0];
        this.title = strData[1];
        this.noteBody = strData[2];

        Log.d(TAG, "Read (from parcel) note#"+this.id);
    }

    public long getId() { return id; }
    //public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getNoteBody() { return noteBody; }
    public void setNoteBody(String noteBody) { this.noteBody = noteBody; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    public long getCreated() { return created; }
    public void setCreated(long created) { this.created = created; }

    public long getUpdated() { return updated; }
    public void setUpdated(long updated) { this.updated = updated; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLongArray(new long[] {id, created, updated});
        dest.writeInt(version);
        dest.writeStringArray(new String[] {filePath, title, noteBody});
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source){
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size){
            return new Note[size];
        }
    };

    // getDateTime(systime,"dd/MM/yyyy hh:mm:ss.SSS");
    protected static String getDateTime(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
