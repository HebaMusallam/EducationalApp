package com.example.educationalapp;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView appTitle, appContent;
    RadioGroup radioLanguage, radioTextSize;
    RadioButton radioArabic , radioEnglish;
    RadioButton radioSmall , radioMedium , radioLarge;
    Button saveBtn;
    SharedPreferences preferences;

    @Override
    protected void attachBaseContext(Context newBase){
        SharedPreferences prefs = newBase.getSharedPreferences("settings", MODE_PRIVATE);
        String language = prefs.getString("language", "en");

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration(newBase.getResources().getConfiguration());
        config.setLocale(locale);

        super.attachBaseContext(newBase.createConfigurationContext(config));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        appTitle = findViewById(R.id.appTitle);
        appContent = findViewById(R.id.appContent);
        radioLanguage = findViewById(R.id.radioLanguage);
        radioTextSize = findViewById(R.id.radioTextSize);
        radioArabic = findViewById(R.id.radioArabic);
        radioEnglish = findViewById(R.id.radioEnglish);
        radioSmall = findViewById(R.id.radioSmall);
        radioMedium = findViewById(R.id.radioMedium);
        radioLarge = findViewById(R.id.radioLarge);
        saveBtn = findViewById(R.id.saveBtn);
        preferences = getSharedPreferences("settings", MODE_PRIVATE);

        loadPreferences();

        saveBtn.setOnClickListener(v -> savePreferences());

    }
        private void savePreferences(){
            SharedPreferences.Editor editor = preferences.edit();
            if(radioArabic.isChecked()){
                editor.putString("language","ar");
            }else{
                editor.putString("language","en");
            }
            if(radioSmall.isChecked()){
                editor.putString("textSize","small");
                appContent.setTextSize(14);
            } else if (radioMedium.isChecked()) {
                editor.putString("textSize","medium");
                appContent.setTextSize(18);
            }else{
                editor.putString("textSize","large");
                appContent.setTextSize(22);
            }
            editor.apply();
            recreate();

        }
        private void loadPreferences() {
            String language = preferences.getString("language","en");
            String textSize = preferences.getString("textSize","medium");
            if(language.equals("ar")){
                radioArabic.setChecked(true);
            }else{
                radioEnglish.setChecked(true);
            }
            switch (textSize){
                case "small":
                    radioSmall.setChecked(true);
                    appContent.setTextSize(14);
                    break;
                case "medium":
                    radioMedium.setChecked(true);
                    appContent.setTextSize(18);
                    break;
                default:
                    radioLarge.setChecked(true);
                    appContent.setTextSize(22);
            }
        }}