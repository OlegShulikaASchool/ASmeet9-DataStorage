package ru.olegshulika.asmeet9_datastorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_ID_KEY ="ASmeet9.NoteActivity.NoteIdKey";

    private static final String TAG = "NoteActivity";
    private TextView mHeaderText;
    private EditText mTitleEditText;
    private TextView mVersionCreatedUpdatedText;
    private Button mSaveBtn;
    private Button mCancelBtn;
    private EditText mBodyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initViews();
        initListeners();
    }

    protected static final Intent newIntent (Context context, Long note_id){
        Intent intent = new Intent(context, NoteActivity.class);
        if (note_id != null) {
            intent.putExtra(NOTE_ID_KEY, note_id.longValue());
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

        //String newText = getIntent().getStringExtra(getString(R.string.detail_data_key));

    }

    private void initListeners() {
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
