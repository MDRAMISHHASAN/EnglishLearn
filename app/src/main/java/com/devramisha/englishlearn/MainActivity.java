package com.devramisha.englishlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

  //  private AppBarConfiguration mAppBarConfiguration;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime+1000>System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else
        {
            Toast.makeText(this, "Press back button twice continuously", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    /*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
     */


    public void homeScreen(View view)
    {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void alphabetScreen(View view)
    {
        Intent intent = new Intent(this,AlphabetActivity.class);
        startActivity(intent);
    }

    public void wordScreen(View view)
    {
        Intent intent = new Intent(this,WordActivity.class);
        startActivity(intent);
    }

    public void quizScreen(View view)
    {
        Intent intent = new Intent(this,QuizActivity.class);
        startActivity(intent);
    }

    public void settingsScreen(View view)
    {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }


    public void aboutusScreen(View view)
    {
        Intent intent = new Intent(this,AboutusActivity.class);
        startActivity(intent);
    }


}
