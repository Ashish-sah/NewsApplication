package com.ashish.NewsApplication.EntertainmentFolder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ashish.NewsApplication.R;

public class EntertainmentDeveloper extends AppCompatActivity {
    ImageButton linkedin, github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_developer);

        linkedin = findViewById(R.id.linkedin_Image);
        github = findViewById(R.id.github_Image);
    }

    public void linkedin_Profile_Function(View view) {
        String url = "https://www.linkedin.com/in/ashish-sah-943171160";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void github_Profile_Function(View view) {
        String urlGithub = "https://github.com/Ashish-sah";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlGithub));
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), EntertainmentActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}