package ru.olegshulika.asmeet9_datastorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_settings);
        initViews();
        initListeners();
    }

    protected static final Intent newIntent (Context context){
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }

    private void initViews() {
        //mCreateNewBtn = findViewById(R.id.button_createnew);
    }

    private void initListeners() {
        //mCreateNewBtn.setOnClickListener(new View.OnClickListener() {
    }

}
