package ru.olegshulika.asmeet9_datastorage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Note {
    private long id;
    private String title;
    private String filePath;
    private String noteBody;
    private int version;
    private long created;
    private long updated;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

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

    // getDateTime(systime,"dd/MM/yyyy");
    // getDateTime(systime,"hh:mm:ss.SSS");
    private static String getDateTime(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
