package ru.olegshulika.asmeet9_datastorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
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
        super.onCreateOptionsMenu(menu);
        return true;
    }

    private void initViews() {
        mCreateNewBtn = findViewById(R.id.button_createnew);
        //mNotesRV = findViewById(R.id.recyclerview_notes);
    }

    private void initListeners() {

    }

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

}
