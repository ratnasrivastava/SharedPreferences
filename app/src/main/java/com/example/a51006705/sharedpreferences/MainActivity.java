package com.example.a51006705.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText_text;
    private TextView textView_text;
    private Button button_apply;
    private Button button_save;
    private Switch switch1;

    private static final String SHAREDPREFS = "shared_prefs";
    private static final String TEXT = "text";
    private static final String SWITCH1 = "switch1";
    private static Boolean switch_on_off;
    private static String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_text = findViewById(R.id.textview_text);
        editText_text = findViewById(R.id.edittext_text);
        button_apply = findViewById(R.id.button_apply);
        button_save =findViewById(R.id.button_save);
        switch1 = findViewById(R.id.switch1);

        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 textView_text.setText(editText_text.getText().toString());
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveData();
            }
        });
        loadData();
        updateViews();
    }

    private void saveData() {
        if(switch_on_off) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(TEXT, textView_text.getText().toString());
            editor.putBoolean(SWITCH1, switch1.isChecked());
            editor.apply();
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        switch_on_off = sharedPreferences.getBoolean(SWITCH1, false);
    }

    private void updateViews(){
        textView_text.setText(text);
        switch1.setChecked(switch_on_off);
    }
}
