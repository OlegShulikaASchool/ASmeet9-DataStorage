package ru.olegshulika.asmeet9_datastorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_KEY ="ru.olegshulika.asmeet9_datastorage.note_key";

    private static final String TAG = "NoteActivity";
    private TextView mHeaderText;
    private EditText mTitleEditText;
    private TextView mVersionCreatedUpdatedText;
    private Button mSaveBtn;
    private Button mCancelBtn;
    private EditText mBodyEditText;
    private Note mNote = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initViews();
        initNote();
        initListeners();
    }

    protected static final Intent newIntent (Context context, Note note){
        Intent intent = new Intent(context, NoteActivity.class);
        if (note != null) {
            intent.putExtra(NOTE_KEY, note);
        }
        return intent;
    }

    private void initViews() {
        mHeaderText = findViewById(R.id.note_header);
        mTitleEditText = findViewById(R.id.note_title);
        mVersionCreatedUpdatedText = findViewById(R.id.note_ver_ts);
        mSaveBtn = findViewById(R.id.note_save);
        mCancelBtn = findViewById(R.id.note_cancel);
        mBodyEditText = findViewById(R.id.note_body);
    }

    private void initNote(){
        mNote = getIntent().getParcelableExtra(NOTE_KEY);
        if (mNote != null) {
            mHeaderText.setText(String.format(getString(R.string.note_header),Long.toString(mNote.getId())));
            mTitleEditText.setText(mNote.getTitle());
            mVersionCreatedUpdatedText.setText(String.format(getString(R.string.ts_ver_text),
                    mNote.getVersion(),
                    Note.getDateTime(mNote.getCreated(),getString(R.string.default_ts_format)),
                    Note.getDateTime(mNote.getUpdated(),getString(R.string.default_ts_format)) ));
            mSaveBtn.setText(getString(R.string.button_save));
            mBodyEditText.setText(mNote.getNoteBody());
        } else {
            // create new Note
            mHeaderText.setText(String.format(getString(R.string.note_header),""));
            mTitleEditText.setText("");
            mVersionCreatedUpdatedText.setText("");
            mSaveBtn.setText(getString(R.string.button_add));
            mBodyEditText.setText("");
        }
    }

    private void initListeners() {
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNoteFields()) {
                    updateNote();
                    Log.d(TAG,"Save note>>"+mNote);
                    Intent data = new Intent();
                    data.putExtra(NOTE_KEY, mNote);
                    setResult(RESULT_OK, data);
                    finish();
                }

            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Cancel - don't save note...");
                mNote = null;
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed - don't save note...");
        mNote = null;
        setResult(RESULT_CANCELED, null);
        finish();
    }

    private boolean checkNoteFields() {
        boolean result = true;
        if (mTitleEditText.getText().toString().trim().isEmpty()) {
            result = false;
            mTitleEditText.setError(getString(R.string.EmptyTitleErrorMessage));
        }
        if (mBodyEditText.getText().toString().trim().isEmpty()) {
            result = false;
            mBodyEditText.setError(getString(R.string.EmptyBodyErrorMessage));
        }
        return result;
    }

    private void updateNote(){
        if (mNote==null) {
            mNote = new Note(mTitleEditText.getText().toString().trim(), mBodyEditText.getText().toString());
        }
        else {
            mNote.setTitle(mTitleEditText.getText().toString().trim());
            mNote.setNoteBody(mBodyEditText.getText().toString());
            mNote.setUpdated(System.currentTimeMillis());
            mNote.setVersion(mNote.getVersion() + 1);
        }
    }
}
