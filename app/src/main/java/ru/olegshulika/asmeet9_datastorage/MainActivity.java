package ru.olegshulika.asmeet9_datastorage;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static ru.olegshulika.asmeet9_datastorage.NoteActivity.NOTE_KEY;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SAVE_NOTE = 1091;

    private static final String TAG = "MainActivity";
    private Button mCreateNewBtn;
    private RecyclerView.Recycler mNotesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_createnew:
                startActivityForResult(
                        NoteActivity.newIntent(MainActivity.this,null),
                        REQUEST_CODE_SAVE_NOTE);
                return true;
            case R.id.menu_settings:
                startActivity(
                        SettingsActivity.newIntent(MainActivity.this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }    }

    private void initViews() {
        mCreateNewBtn = findViewById(R.id.button_createnew);
        //mNotesRV = findViewById(R.id.recyclerview_notes);
    }

    private void initListeners() {
        mCreateNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        NoteActivity.newIntent(MainActivity.this,null),
                        REQUEST_CODE_SAVE_NOTE);
            }
        });
    }
/*
    public static final Intent newIntent (Context context, String key, String extra){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(key, extra);
        return intent;
    }


    public static final Intent newIntent (Context context, Class<?> cls, String key, String extra){
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, extra);
        return intent;
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult, req="+requestCode+" res="+resultCode+" data="+data);
        if (requestCode==REQUEST_CODE_SAVE_NOTE && requestCode==RESULT_OK){
            if (data==null)
                return;
            Note mNote = data.getParcelableExtra(NOTE_KEY);
            //TODO
        }
    }
}
